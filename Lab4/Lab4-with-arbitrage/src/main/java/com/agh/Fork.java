package com.agh;

public class Fork {
    private boolean isTaken = false;

    public void takeFork() {
        isTaken = true;
    }

    public void leaveFork() {
        isTaken = false;
        notifyAll();
    }

    public boolean isForkTaken() {
        return this.isTaken;
    }


}
