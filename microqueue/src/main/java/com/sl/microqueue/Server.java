package com.sl.microqueue;

import com.sl.microqueue.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = ConnectionFactory.initialize(1234);
        ServerHandler connection = connectionFactory.createConnection();
        connection.start();
    }
}
