package com.sl.microqueue.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Producer implements SessionHandler {
    BlockingQueue<String> queue;
    Socket socket;
    BufferedReader bufferedReader;
    Session session;

    public Producer(BlockingQueue queue, Session session) {
        this.queue = queue;
        this.session = session;
        this.socket = session.getSocket();
        this.bufferedReader = session.getBufferedReader();
    }

    @Override
    public void close() {
        this.session.close();
    }

    @Override
    public void run() {
        String receivedMessage;

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
