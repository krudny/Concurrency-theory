package com.agh;

import java.util.Optional;

public class ListNode {
    private Object value;
    private ListNode next;

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
        if(next == null) {
            return Optional.empty();
        }
        return Optional.of(next);
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
