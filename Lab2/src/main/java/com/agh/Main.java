package com.agh;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        BinarySemaphore binarySemaphore = new BinarySemaphore();
        ThreadFactory threadFactory = new ThreadFactory(binarySemaphore, counter);
        ArrayList<Thread> threads = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            if(random.nextInt(2) == 0) {
                threads.add(threadFactory.createIncrementThread());
            } else {
                threads.add(threadFactory.createDecrementThread());
            }
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