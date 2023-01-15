package com.sl.jmsprovider.jms;

import javax.jms.JMSException;
import javax.jms.Queue;

public class SLQueue implements Queue {

    private String queueName;

    public SLQueue (String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String getQueueName() throws JMSException {
        return this.queueName;
    }
}
