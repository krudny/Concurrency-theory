package com.agh;

import java.util.ArrayDeque;
import java.util.Deque;

public class Buffer {
    Deque<Integer> deque = new ArrayDeque<>();

    public synchronized void put(int i) throws InterruptedException {
        while(deque.size() > 10) {
            System.out.println("\nProducer waiting \n ");
            wait();
        }


        deque.addLast(i);
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (deque.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " waiting");
            wait();
        }
        notifyAll();
        return deque.removeFirst();
    }
}
