package com.sl.jmsprovider.jms;

import javax.jms.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class SLQueueSender implements QueueSender {
    private Socket socket;
    private BufferedWriter bufferedWriter;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public Queue getQueue() throws JMSException {
        return null;
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {

    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        return false;
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {

    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        return false;
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {

    }

    @Override
    public int getDeliveryMode() throws JMSException {
        return 0;
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {

    }

    @Override
    public int getPriority() throws JMSException {
        return 0;
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {

    }

    @Override
    public long getTimeToLive() throws JMSException {
        return 0;
    }

    @Override
    public void setDeliveryDelay(long deliveryDelay) throws JMSException {

    }

    @Override
    public long getDeliveryDelay() throws JMSException {
        return 0;
    }

    @Override
    public Destination getDestination() throws JMSException {
        return null;
    }

    @Override
    public void close() throws JMSException {

    }

    @Override
    public void send(Message message) throws JMSException {
        try {
            String str = ((TextMessage)message).getText();
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new JMSException(e.getMessage());
        }
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }

    @Override
    public void send(Message message, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Queue queue, Message message) throws JMSException {

    }

    @Override
    public void send(Queue queue, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }
}
