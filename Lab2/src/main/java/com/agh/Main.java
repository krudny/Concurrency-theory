package com.agh;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread incrementThread = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        });

        incrementThread.start();
        decrementThread.start();
        incrementThread.join();
        decrementThread.join();

        System.out.println(counter.getCnt());
    }
}