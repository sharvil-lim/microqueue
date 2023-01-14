package com.sl.jmsprovider.core;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {
    private static QueueManager instance;
    private static Hashtable<String, Queue<String>> queuePool;

    private QueueManager() {
        queuePool = new Hashtable<>();
    }

    public static QueueManager instantiate() {
        if (instance == null) {
            instance = new QueueManager();
        } else if (queuePool == null) {
            instance = new QueueManager();
        }

        return instance;
    }

    public void createQueue(String queueName) {
        Queue<String> messageQueue = new LinkedList<>();
        queuePool.put(queueName, messageQueue);
    }

    public Queue<String> searchQueue(String queueName) {
        return queuePool.get(queueName);
    }

    public void enqueueMessage(String message, String queueName) {
        if (queuePool.get(queueName) != null) {
            queuePool.get(queueName).add(message);
        }
    }

    public String dequeueMessage(String queueName) {
        if (queuePool.get(queueName) != null) {
            return queuePool.get(queueName).remove();
        } else {
            return null;
        }
    }
}
