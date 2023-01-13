package com.sl.jmsprovider;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable {
    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;

    public Connection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        try {
            if (!serverSocket.isClosed()) {
                this.socket = serverSocket.accept();
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                notifyAll();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            } if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            wait();
            String receivedMessage; // TODO : add serialized message objects
            Session session = new Session();

            while(socket.isConnected()) {
                receivedMessage = bufferedReader.readLine();
                if (receivedMessage.startsWith("Request ")) { // TODO : Add command handler
                    String[] arr = receivedMessage.split(" ", 2);
                    bufferedWriter.write(session.createProducer(arr[1]).produce());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } else {
                    session.createConsumer("Sample").consume(receivedMessage);
                }
            }
        } catch (InterruptedException | IOException e) {
            close();
        }
    }
}
