package com.agh;

public class BinarySemaphore {
    private boolean is_available = true;

    public synchronized void acquire() throws InterruptedException {
        while(!is_available) {
            wait();
        }
        is_available = false;
    }

    public synchronized void release() {
        is_available = true;
        notify();
    }

}
