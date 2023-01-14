package com.sl.jmsprovider;

import com.sl.jmsprovider.core.QueueManager;

public class Consumer {
    private QueueManager queueManager;
    private String queueName;

    public Consumer(String queueName, QueueManager queueManager) {
        this.queueName = queueName;
        this.queueManager = queueManager;
    }

    public void consume(String message) {
        if (queueManager.searchQueue(queueName) == null) {
            queueManager.createQueue(queueName);
        } queueManager.enqueueMessage(message, queueName);
    }
}
