package com.agh;

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
        currNode.lock();

        try {
            while (true) {
                if (currNode.getValue() == o) {
                    return true;
                }

                Optional<ListNode> nextNode = currNode.getNext();
                if (nextNode.isEmpty()) {
                    return false;
                }

                nextNode.get().lock();
                currNode.unlock();
                currNode = nextNode.get();
            }
        } finally {
            currNode.unlock();
        }
    }

    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }

        ListNode prevNode = head;
        prevNode.lock();

        try {
            Optional<ListNode> nextNodeOpt = head.getNext();
            if (nextNodeOpt.isEmpty()) {
                return false;
            }

            ListNode currNode = nextNodeOpt.get();
            currNode.lock();

            try {
                while (currNode.getNext().isPresent()) {
                    if (currNode.getValue() != o) {
                        prevNode.unlock();
                        prevNode = currNode;

                        currNode = currNode.getNext().get();
                        currNode.lock();
                    } else {
                        prevNode.setNext(currNode.getNext().get());
                        currNode.setNext(null);
                        return true;
                    }
                }

                if (currNode.getValue() == o) {
                    prevNode.setNext(null);
                    return true;
                }

            } finally {
                currNode.unlock();
            }
        } finally {
            prevNode.unlock();
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
        currNode.lock();

        try {
            while (currNode.getNext().isPresent()) {
                ListNode nextNode = currNode.getNext().get();
                nextNode.lock();
                currNode.unlock();
                currNode = nextNode;
            }

            ListNode newNode = new ListNode(o, null);
            currNode.setNext(newNode);
            nodes.add(newNode);
        } finally {
            currNode.unlock();
        }

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
