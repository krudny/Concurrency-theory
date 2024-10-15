package com.agh;

public class ThreadFactory {
    BinarySemaphore binarySemaphore;
    Counter counter;

    public ThreadFactory(BinarySemaphore binarySemaphore, Counter counter) {
        this.binarySemaphore = binarySemaphore;
        this.counter = counter;
    }

    public Thread createIncrementThread() {
        return new Thread(() -> {
            try {
                binarySemaphore.acquire();

                for(int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                binarySemaphore.release();
            }
        });
    }

    public Thread createDecrementThread() {
        return new Thread(() -> {
            try {
                binarySemaphore.acquire();

                for(int i = 0; i < 10000; i++) {
                    counter.decrement();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                binarySemaphore.release();
            }
        });
    }
}
