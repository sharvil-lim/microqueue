package com.sl.jmsprovider;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Connection {
    private ServerSocket serverSocket;
    private LinkedList<Socket> sockets;

    public Connection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.sockets = new LinkedList<>();
    }

    public void start() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                ConnectionManager connectionManager = new ConnectionManager(socket);
                Thread thread = new Thread(connectionManager);
                thread.start();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (sockets != null) {
                for (Socket socket : sockets) {
                    socket.close();
                }
            } if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
