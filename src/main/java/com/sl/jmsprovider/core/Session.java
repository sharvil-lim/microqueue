package com.sl.jmsprovider.core;

import java.io.*;
import java.net.Socket;

public class Session implements Runnable {
    private SessionHandler sessionHandler;
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
            } if (bufferedWriter != null) {
                bufferedWriter.close();
            } if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return this.bufferedWriter;
    }

    @Override
    public void run() {
        try {
            String producerConsumerString = bufferedReader.readLine();
            String queueName = bufferedReader.readLine();

            if (producerConsumerString.equals("Consumer")) {
                this.sessionHandler = new Consumer(queueName,this);
            } else if (producerConsumerString.equals("Producer")) {
                this.sessionHandler = new Producer(queueName, this);
            }

            Thread thread = new Thread(sessionHandler);
            thread.start();
        } catch (IOException e) {
            close();
        }
    }
}