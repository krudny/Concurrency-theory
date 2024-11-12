package com.agh;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

public class List {
    private ArrayList<ListNode> nodes;
    private ListNode head;

    public List() {
        nodes = new ArrayList<>();
    }

    public boolean contains(Object o) {
        if (head == null) {
            return false;
        }

        ListNode currNode = head;
        while (currNode.getNext().isPresent()) {
            currNode = currNode.getNext().get();
            if (currNode.getValue() == o) {
                return true;
            }
        }

        return false;
    }

    public boolean remove(Object o) {
        if(head == null || head.getNext().isEmpty()) {
            return false;
        }

        ListNode prevNode = head;
        ListNode currNode = head.getNext().get();
        while (currNode.getNext().isPresent()) {
            if (currNode.getValue() != o) {
                prevNode = currNode;
                currNode = currNode.getNext().get();
            } else {
                prevNode.setNext(currNode.getNext().get());
                currNode.setNext(null);
                return true;
            }

        }
        return false;
    }

    public boolean add(Object o) {
        if (head == null) {
            head = new ListNode(o, null);
            nodes.add(head);
            return true;
        }

        ListNode currNode = head;
        while (currNode.getNext().isPresent()) {
            currNode = currNode.getNext().get();
        }
        ListNode newNode = new ListNode(o, null);
        currNode.setNext(newNode);
        nodes.add(newNode);

        return true;
    }

    public Optional<ListNode> getHead() {
        if(nodes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(nodes.getFirst());
    }

    public ArrayList<ListNode> getNodes() {
        return nodes;
    }
}
