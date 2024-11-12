package com.agh;

import java.util.Random;

public class ThreadFactory {
    private List list;
    private final int N;
    private Random random;

    public ThreadFactory(int N, List list) {
        this.N = N;
        this.list = list;
        this.random = new Random();
    }

    public Thread createAddThread() {
        return new Thread(() -> {
            Object value = random.nextInt(N);
            System.out.println((list.add(value) ? "Added " : "Unable to add ") + value);
        });
    }

    public Thread createContainsThread() {
        return new Thread(() -> {
            Object value = random.nextInt(N);
            System.out.println((list.contains(value) ? "List contains " : "List doesnt contain ") + value);
        });
    }

    public Thread createRemoveThread() {
        return new Thread(() -> {
            Object value = random.nextInt(N);
            System.out.println((list.remove(value) ? "Removed " : "List doesnt contain ") + value);
        });
    }
}
