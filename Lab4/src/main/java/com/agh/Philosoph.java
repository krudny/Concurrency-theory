package com.agh;

import java.util.Random;

public class Philosoph implements Runnable {
    private final int N;
    private final int id;
    private Table table;
    private Fork left;
    private Fork right;
    Random random = new Random();

    public Philosoph(int N, int id, Table table) {
        this.N = N;
        this.id = id;
        this.table = table;
    }

    public void takeForks() throws InterruptedException {
        boolean success = false;

        while (!success) {
            left = table.getFork(id);
            synchronized (left) {
                while (left.isTaken()) {
                    left.wait();
                }
                left.takeFork();
                System.out.println("LEFT TAKEN by " + id);
            }

            right = table.getFork((id + 1) % N);
            synchronized (right) {
                while (right.isTaken()) {
                    right.wait();
                }
                right.takeFork();
                System.out.println("RIGHT TAKEN by " + id);
            }

            success = true;
        }

        consume();
    }

    private void consume() throws InterruptedException {
        Thread.sleep(random.nextInt(1000));

        synchronized (left) {
            left.leaveFork();
            left.notifyAll();
        }

        synchronized (right) {
            right.leaveFork();
            right.notifyAll();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(50));
                this.takeForks();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
