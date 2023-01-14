package com.sl.jmsprovider.core;

import java.io.*;
import java.net.Socket;

public class Session implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    Session(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            } if (bufferedReader != null) {
                bufferedReader.close();
            } if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String receivedMessage;
        QueueManager queueManager = QueueManager.instantiate();
        queueManager.createQueue("sample");

        while (socket.isConnected()) {
            try {
                receivedMessage = bufferedReader.readLine();
                if (receivedMessage.equals("Remove")) {
                    bufferedWriter.write(queueManager.dequeueMessage("sample"));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } else {
                    queueManager.enqueueMessage(receivedMessage, "sample");
                }
            } catch (IOException e) {
                close();
                break;
            }
        }
    }
}