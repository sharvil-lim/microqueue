package com.sl.jmsprovider.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;


public class Consumer implements SessionHandler {
    Queue<String> queue;
    String queueName;
    QueueManager queueManager;
    Socket socket;
    BufferedWriter bufferedWriter;
    Session session;

    public Consumer(String queueName, Session session) {
        this.queueName = queueName;
        this.queueManager = QueueManager.instantiate();
        this.session = session;
        this.socket = session.getSocket();
        this.bufferedWriter = session.getBufferedWriter();
    }

    @Override
    public void close() {
        this.session.close();
    }

    @Override
    public void checkQueue() {
        if ((queueManager.getQueue(this.queueName)) == null) {
            queueManager.makeQueue(queueName);
        } queue = queueManager.getQueue(queueName);
    }


    @Override
    public void run() {
        checkQueue();

        while (socket.isConnected()) {
            try {
                if (!queue.isEmpty()) {
                    String message = queue.remove();
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                close();
                break;
            }
        }
    }
}

