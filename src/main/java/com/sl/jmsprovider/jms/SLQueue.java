package com.sl.jmsprovider.jms;

import javax.jms.JMSException;
import javax.jms.Queue;

public class SLQueue implements Queue {

    @Override
    public String getQueueName() throws JMSException {
        return null;
    }
}
