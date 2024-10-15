package com.agh;

public class ThreadFactory {
    Semaphore semaphore;
    Counter counter;

    public ThreadFactory(Semaphore semaphore, Counter counter) {
        this.semaphore = semaphore;
        this.counter = counter;
    }

    public Thread createIncrementThread() {
        return new Thread(() -> {
            try {
                semaphore.acquire();

                for(int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        });
    }

    public Thread createDecrementThread() {
        return new Thread(() -> {
            try {
                semaphore.acquire();

                for(int i = 0; i < 10000; i++) {
                    counter.decrement();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        });
    }
}
