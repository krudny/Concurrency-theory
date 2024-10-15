package com.agh;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
//        BinarySemaphore binarySemaphore = new BinarySemaphore();
        CountingSemaphore countingSemaphore = new CountingSemaphore(5);
        ThreadFactory threadFactory = new ThreadFactory(countingSemaphore, counter);
        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            threads.add(threadFactory.createIncrementThread());
            threads.add(threadFactory.createDecrementThread());
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        System.out.println(counter.getCnt());
    }
}