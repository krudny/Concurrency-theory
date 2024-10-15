package com.agh;

public interface Semaphore {
    void acquire() throws InterruptedException;
    void release();
}
