package com.sl.microqueue.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class Consumer implements SessionHandler {
    private BlockingQueue<String> queue;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private Session session;

    public Consumer(BlockingQueue queue, Session session) {
        this.queue = queue;
        this.session = session;
        this.socket = session.getSocket();
        this.bufferedWriter = session.getBufferedWriter();
    }

    @Override
    public void close() {
        this.session.close();
    }


    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                    String message = queue.take();
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
            } catch (IOException | InterruptedException e) {
                close();
                break;
            }
        }
    }
}

