package com.agh;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        ThreadFactory threadFactory = new ThreadFactory(buffer);
        ArrayList<Thread> threads = new ArrayList<>();

        threads.add(threadFactory.createConsumer());
        threads.add(threadFactory.createConsumer());
        threads.add(threadFactory.createConsumer());
        threads.add(threadFactory.createProducer());

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}