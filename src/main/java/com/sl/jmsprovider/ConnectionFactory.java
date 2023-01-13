package com.sl.jmsprovider;

import java.util.ArrayList;

public class ConnectionFactory {
    private static ConnectionFactory instance = null;
    private ArrayList<Connection> pool;
    private ArrayList<Boolean> isOpen;
    final private int port = 1234;
    final private int size = 10;

    private ConnectionFactory() {
        pool = new ArrayList<>(size);
        isOpen = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Connection connection = new Connection(port);
            pool.add(connection);
            isOpen.add(true);
        }
    }

    public static ConnectionFactory instantiate() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    public Connection createConnection() {
        for (Connection connection : pool) {
            int i = pool.indexOf(connection);
            if (isOpen.get(i)) {
                isOpen.set(i, false);
                return connection;
            }
        }

        Connection connection = new Connection(port);
        pool.add(connection);
        isOpen.add(false);
        return connection;
    }
}
