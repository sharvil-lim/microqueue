package com.sl.jmsprovider;

import com.sl.jmsprovider.jms.SLQueue;
import com.sl.jmsprovider.jms.SLQueueConnectionFactory;
import com.sl.jmsprovider.jms.SLQueueSender;

import javax.jms.*;

public class ProducerTest {

    public static void main(String[] args) {
        try {
            QueueConnectionFactory queueConnectionFactory = new SLQueueConnectionFactory();
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = new SLQueue();
            QueueSender queueSender = queueSession.createSender(queue);
            TextMessage textMessage = queueSession.createTextMessage();

            textMessage.setText("Hello World");
            queueSender.send(textMessage);

            textMessage.setText("Hello World again");
            queueSender.send(textMessage);

            textMessage.setText("ok");
            queueSender.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
