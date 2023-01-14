package com.sl.jmsprovider;

import com.sl.jmsprovider.core.QueueManager;

public class Producer {
    private QueueManager queueManager;
    private String queueName;

    public Producer(String queueName, QueueManager queueManager) {
        this.queueName = queueName;
        this.queueManager = queueManager;
    }

    public String produce() {
        if (queueManager.searchQueue(queueName) == null) {
            queueManager.createQueue(queueName);
        } return queueManager.dequeueMessage(queueName);
    }
}
