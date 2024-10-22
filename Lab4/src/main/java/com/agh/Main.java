package com.agh;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        Table table = new Table(N);
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Philosoph philosoph = new Philosoph(N, i, table);
            Thread thread = new Thread(philosoph);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }








    }
}