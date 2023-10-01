package com.sl.microqueue.jms;

import javax.jms.*;
import java.io.*;
import java.net.Socket;

public class SLQQueueReceiver implements QueueReceiver {
    private Socket socket;
    private BufferedReader bufferedReader;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public Queue getQueue() throws JMSException {
        return null;
    }

    @Override
    public String getMessageSelector() throws JMSException {
        return null;
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return null;
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        new Thread(() -> {
            try {
                while (socket.isConnected()) {
                    String str = bufferedReader.readLine();
                    TextMessage textMessage = new SLTextMessage();
                    textMessage.setText(str);
                    listener.onMessage(textMessage);
                }
            } catch (JMSException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public Message receive() throws JMSException {
        return null;
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        return null;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        return null;
    }

    @Override
    public void close() throws JMSException {

    }
}
