package com.agh;

public class Fork {
    private boolean isTaken = false;

    public boolean takeFork() {
        if (isTaken) {
            return false;
        }
        isTaken = true;
        return true;
    }

    public boolean leaveFork() {
        if (!isTaken) {
            return false;
        }
        isTaken = false;
        return true;
    }

    public boolean isForkTaken() {
        return this.isTaken;
    }


}
