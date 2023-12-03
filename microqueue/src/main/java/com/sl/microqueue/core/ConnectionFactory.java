package com.sl.microqueue.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class ConnectionFactory {
    private static ConnectionFactory instance = null;
    private LinkedList<ServerHandler> serverHandlers;
    private ServerSocket serverSocket;
    private static int connectionPort;

    private ConnectionFactory() {
        try {
            this.serverSocket = new ServerSocket(connectionPort);
            this.serverHandlers = new LinkedList<>();
        } catch (IOException e) {
            shutdown();
        }
    }

    public static ConnectionFactory initialize(int port) {
        if ((instance == null) || (connectionPort != port)) {
            connectionPort = port;
            instance = new ConnectionFactory();
        } return instance;
    }

    public ServerHandler createConnection() {
        ServerHandler serverHandler = new ServerHandler(serverSocket);
        serverHandlers.add(serverHandler);
        return serverHandler;
    }

    public void shutdown() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            } 
            if (serverHandlers != null) {
                for (ServerHandler serverHandler : serverHandlers) {
                    serverHandler.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}