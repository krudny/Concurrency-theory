package com.agh;

import java.util.Random;

public class Philosoph implements Runnable {
    private int id;
    private Table table;
    private final int N;
    private Fork left;
    private Fork right;
    private Arbitrage arbitrage;
    Random random = new Random();

    public Philosoph(int id, int N, Table table) {
        this.id = id;
        this.N = N;
        this.table = table;
        this.arbitrage = table.getArbitrage();
    }

    private void consume() throws InterruptedException {
        arbitrage.acquirePermit();

        left = table.getFork(id);
        right = table.getFork((id + 1) % N);

        synchronized (left) {
            while (left.isForkTaken()) {
                left.wait();
            }
            left.takeFork();
            System.out.println("LEFT FORK WAS TAKEN BY " + id);
        }

        synchronized (right) {
            while (right.isForkTaken()) {
                right.wait();
            }
            right.takeFork();
            System.out.println("RIGHT FORK WAS TAKEN BY " + id);
        }

        System.out.println(id + " IS CONSUMING");
        Thread.sleep(random.nextInt(500));

        synchronized (left) {
            left.leaveFork();
        }

        synchronized (right) {
            right.leaveFork();
        }

        arbitrage.releasePermit();
    }


    private void think() throws InterruptedException {
        Thread.sleep(random.nextInt(500));
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        }
    }
}
