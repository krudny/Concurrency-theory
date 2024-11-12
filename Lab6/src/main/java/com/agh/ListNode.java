package com.agh;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class ListNode {
    private Object value;
    private ListNode next;
    private final ReentrantLock lock = new ReentrantLock();

    public ListNode() {
    }

    public ListNode(Object value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Optional<ListNode> getNext() {
        return Optional.ofNullable(next);
    }

    public void setNext(ListNode next) {
        this.next = next;
    }


    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
