package com.sl.jmsprovider;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionFactory {
    private static ConnectionFactory instance = null;
    private ExecutorService connectionPool;
    private LinkedList<Connection> connections;
    private ServerSocket serverSocket;
    private static int connectionPort;

    private ConnectionFactory() {
        try {
            this.serverSocket = new ServerSocket(connectionPort);
            this.connectionPool = Executors.newFixedThreadPool(11);
            this.connections = new LinkedList<>();
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

    public Connection createConnection() {
        Connection connection = new Connection(serverSocket);
        connectionPool.submit(connection);
        connections.add(connection);
        return connection;
    }

    public void shutdown() {
        try {
            if (connectionPool != null) {
                connectionPool.shutdownNow();
            }

            if (serverSocket != null) {
                serverSocket.close();
            }

            if (connections != null) {
                for (Connection connection : connections) {
                    connection.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}