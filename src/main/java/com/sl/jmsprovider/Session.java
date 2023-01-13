package com.sl.jmsprovider;

import java.util.LinkedList;

public class Session {
    private QueueManager queueManager;

    public Session() {
        queueManager = QueueManager.instantiate();
    }

    public Producer createProducer(String queueName) {
        return new Producer(queueName, queueManager);
    }

    public Consumer createConsumer(String queueName) {
        return new Consumer(queueName, queueManager);
    }
}
