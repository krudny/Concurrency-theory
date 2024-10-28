package com.agh;

public class Fork {
    private boolean isTaken = false;

    public synchronized void takeFork() {
        isTaken = true;
    }

    public synchronized void leaveFork() {
        isTaken = false;
        notifyAll();
    }

    public synchronized boolean isForkTaken() {
        return this.isTaken;
    }


}
