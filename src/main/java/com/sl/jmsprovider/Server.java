package com.sl.jmsprovider;

import com.sl.jmsprovider.core.ServerHandler;
import com.sl.jmsprovider.core.ConnectionFactory;

public class Server {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = ConnectionFactory.initialize(1234);
        ServerHandler serverHandler = connectionFactory.createConnection();
        serverHandler.start();

    }

}
