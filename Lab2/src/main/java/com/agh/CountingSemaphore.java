package com.agh;

public class CountingSemaphore implements Semaphore{
    private int signals;

    public CountingSemaphore(int signals) {
        this.signals = signals;
    }

    public synchronized void acquire() throws InterruptedException {
        while(signals <= 0) {
            wait();
        }
        signals -= 1;
    }

    public synchronized void release() {
        signals += 1;
        notifyAll();
    }
}
