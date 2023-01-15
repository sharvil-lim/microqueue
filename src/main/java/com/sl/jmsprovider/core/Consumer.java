package com.sl.jmsprovider.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;


public class Consumer implements SessionHandler {
    private BlockingQueue<String> queue;
    private String queueName;
    private QueueManager queueManager;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private Session session;

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
                //if (!queue.isEmpty()) {
                    String message = queue.take();
                    //String message = queue.remove();
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                //}
            } catch (IOException | InterruptedException e) {
                close();
                break;
            }
        }
    }
}

