package com.sl.jmsprovider.core;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueManager {
    private static QueueManager instance;
    private static ConcurrentHashMap<String, BlockingQueue<String>> queuePool;

    private QueueManager() {
        queuePool = new ConcurrentHashMap<>();
    }

    public static QueueManager instantiate() {
        if (instance == null) {
            instance = new QueueManager();
        } else if (queuePool == null) {
            instance = new QueueManager();
        }

        return instance;
    }

    public void makeQueue(String queueName) {
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
        queuePool.put(queueName, messageQueue);
    }

    public BlockingQueue<String> getQueue(String queueName) {
        return queuePool.get(queueName);
    }
}
