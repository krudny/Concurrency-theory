package com.agh;


public class ThreadFactory {
    private final Buffer buffer;

    public ThreadFactory(Buffer buffer) {
        this.buffer = buffer;
    }

    public Thread createProducer() {
        return new Thread(() -> {
            for(int i = 0; i < 300; i++) {
                try {
                    buffer.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public Thread createConsumer() {
        return new Thread(() -> {
            for(int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " getting " + buffer.get());
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
