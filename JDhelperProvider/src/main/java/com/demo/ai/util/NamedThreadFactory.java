package com.demo.ai.util;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private String prefix;
    private int threadInitNumber;

    public NamedThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    private synchronized int nextThreadNum() {
        return ++threadInitNumber;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + nextThreadNum());
    }
}
