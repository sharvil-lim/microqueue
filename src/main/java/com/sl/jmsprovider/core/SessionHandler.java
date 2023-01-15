package com.sl.jmsprovider.core;

import java.util.concurrent.ConcurrentLinkedQueue;


public interface SessionHandler extends Runnable {
    void close();
    void checkQueue();
}
