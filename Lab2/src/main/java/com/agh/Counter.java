package com.agh;

public class Counter {
    private int cnt = 0;

    public void increment() {
        synchronized (this) {
            this.cnt += 1;
        }
    }

    public void decrement() {
        synchronized (this) {
            this.cnt -= 1;
        }
    }

    public int getCnt() {
        return cnt;
    }


}
