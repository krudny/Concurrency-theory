package com.agh;

import java.util.concurrent.Semaphore;

public class Arbitrage {
    private final int N;
    private Table table;
    private Semaphore semaphore;

    public Arbitrage(int N){
        this.N = N;
        this.semaphore = new Semaphore(N - 1);
    }

    public void acquirePermit() throws InterruptedException {
        semaphore.acquire();
    }

    public void releasePermit() {
        semaphore.release();
    }
}
