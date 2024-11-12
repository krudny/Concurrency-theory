package com.agh;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 80;
        List list = new List();
        Random random = new Random();

//        for(int i = 0; i < N; i++) {
//            list.add(i);
//        }


        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            Thread containsThread = new Thread(() -> {
                Object value = random.nextInt(N);
                if(list.contains(value)) {
                    System.out.println("CHECKED " + value);
                } else {
                    System.out.println("UNABLE TO CHECK " + value);
                };
            });

            Thread addThread = new Thread(() -> {
                Object value = random.nextInt(N);
                if(list.add(value)) {
                    System.out.println("ADD " + value);
                } else {
                    System.out.println("UNABLE TO ADD " + value);
                };
            });

            Thread removeThread = new Thread(() -> {
                Object value = random.nextInt(N);
                if(list.contains(value)) {
                    System.out.println("REMOVE " + value);
                } else {
                    System.out.println("UNABLE TO REMOVE " + value);
                };
            });

            threads.add(containsThread);
            threads.add(addThread);
            threads.add(removeThread);
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }


    }
}