package com.sl.jmsprovider;

import com.sl.jmsprovider.core.*;

public class Server {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = ConnectionFactory.initialize(1234);
        ServerHandler connection = connectionFactory.createConnection();
        connection.start();
    }

}
