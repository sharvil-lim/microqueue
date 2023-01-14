package com.sl.jmsprovider.jms;

import javax.jms.Connection;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import java.io.IOException;
import java.net.Socket;

public class SLQueueConnectionFactory implements javax.jms.QueueConnectionFactory {
    private Socket socket;
    @Override
    public QueueConnection createQueueConnection() throws JMSException {
        SLQueueConnection SLQueueConnection = new SLQueueConnection();

        try {
              socket = new Socket("localhost", 1234);
              SLQueueConnection.setSocket(socket);
        } catch (IOException e) {
            throw new JMSException(e.getMessage());
        }

        return SLQueueConnection;
    }

    @Override
    public QueueConnection createQueueConnection(String userName, String password) throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return null;
    }

    @Override
    public JMSContext createContext() {
        return null;
    }

    @Override
    public JMSContext createContext(String userName, String password) {
        return null;
    }

    @Override
    public JMSContext createContext(String userName, String password, int sessionMode) {
        return null;
    }

    @Override
    public JMSContext createContext(int sessionMode) {
        return null;
    }
}
