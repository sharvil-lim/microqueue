package com.sl.jmsprovider;

import com.sl.jmsprovider.jms.SLMyListener;
import com.sl.jmsprovider.jms.SLQueue;
import com.sl.jmsprovider.jms.SLQueueConnectionFactory;

import javax.jms.*;

public class ConsumerTest {

    public static void main(String[] args) {
        try {
            QueueConnectionFactory queueConnectionFactory = new SLQueueConnectionFactory();
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = new SLQueue();
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            MessageListener myListener = new SLMyListener();

            queueReceiver.setMessageListener(myListener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
