package com.agh;

public class Fork {
    private boolean isAquired = false;

    public synchronized boolean isTaken() {
        return isAquired;
    }

    public synchronized boolean takeFork() {
        if (!isAquired) {
            isAquired = true;
            return true;
        }

        return false;
    }

    public synchronized boolean leaveFork() {
        if (isAquired) {
            isAquired = false;
            notifyAll();
            return true;
        }

        return false;
    }
}
