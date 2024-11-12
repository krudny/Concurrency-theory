package com.agh;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 80;
        List list = new List();
        Random random = new Random();
        ThreadFactory factory = new ThreadFactory(N, list);
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            switch (random.nextInt(3)) {
                case 0 -> threads.add(factory.createContainsThread());
                case 1 -> threads.add(factory.createAddThread());
                case 2 -> threads.add(factory.createRemoveThread());
            }
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }


    }
}