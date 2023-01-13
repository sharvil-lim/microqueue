package com.sl.jmsprovider;

public class Server {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = ConnectionFactory.initialize(1234);
        Connection connection = connectionFactory.createConnection();
        connection.start();
    }

}
