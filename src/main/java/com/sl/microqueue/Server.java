package com.sl.microqueue;

import com.sl.microqueue.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("Starting Main");
        System.out.println("test");
        ConnectionFactory connectionFactory = ConnectionFactory.initialize(1234);
        ServerHandler connection = connectionFactory.createConnection();
        connection.start();
    }

}
