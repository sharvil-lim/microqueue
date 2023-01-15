package com.sl.jmsprovider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Producer implements SessionHandler {
    BlockingQueue<String> queue;
    String queueName;
    QueueManager queueManager;
    Socket socket;
    BufferedReader bufferedReader;
    Session session;

    public Producer(String queueName, Session session) {
        this.queueName = queueName;
        this.queueManager = QueueManager.instantiate();
        this.session = session;
        this.socket = session.getSocket();
        this.bufferedReader = session.getBufferedReader();
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
        String receivedMessage;
        checkQueue();

        while (socket.isConnected()) {
            try {
                receivedMessage = bufferedReader.readLine();
                queue.put(receivedMessage);
            } catch (IOException | InterruptedException e) {
                close();
                break;
            }
        }
    }
}
