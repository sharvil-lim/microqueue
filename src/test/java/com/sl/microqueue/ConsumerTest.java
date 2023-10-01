package com.sl.microqueue;

import com.sl.microqueue.jms.SLMyListener;
import com.sl.microqueue.jms.SLQueue;
import com.sl.microqueue.jms.SLQueueConnectionFactory;

import javax.jms.*;

public class ConsumerTest {

    public static void main(String[] args) {
        try {
            QueueConnectionFactory queueConnectionFactory = new SLQueueConnectionFactory();
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = new SLQueue("Sample");
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            MessageListener myListener = new SLMyListener();

            queueReceiver.setMessageListener(myListener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
