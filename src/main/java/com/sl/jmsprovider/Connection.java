package com.sl.jmsprovider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connection {
    private ServerSocket serverSocket = null;
    private ExecutorService pool;
    private ArrayList<Socket> sockets;
    final private int poolSize = 10;

    public Connection(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.pool = Executors.newFixedThreadPool(poolSize);
            this.sockets = new ArrayList<>();
        } catch (IOException e) {
            close();
        }
    }

    public void start() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                ConnectionManager connectionManager = new ConnectionManager(socket);
                pool.execute(connectionManager);
            }
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (pool != null) {
                pool.shutdownNow();
            }

            if (sockets != null && sockets.size() > 0) {
                for (Socket socket : sockets) {
                    socket.close();
                }
            }

            if (serverSocket != null) {
                serverSocket.isClosed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ConnectionManager implements Runnable {
        private Socket socket;
        private BufferedReader bufferedReader;

        ConnectionManager(Socket socket) {
            try {
                this.socket = socket;
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                close();
            }
        }

        public void close() {
            try {
                if (socket != null) {
                    socket.close();
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
                    queueManager.enqueueMessage(receivedMessage, "sample");
                } catch (IOException e) {
                    close();
                    break;
                }
            }
        }
    }
}
