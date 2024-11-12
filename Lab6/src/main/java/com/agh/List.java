package com.agh;

import java.util.ArrayList;

public class List {
    private ArrayList<ListNode> nodes;

    public List(int N) {
        nodes = new ArrayList<>();
        ListNode first = new ListNode();
        first.setValue(0);
        ListNode prev = first;
        nodes.add(first);

        for(int i = 1; i < N; i++) {
            ListNode nextNode = new ListNode();
            nextNode.setValue(i);
            prev.setNext(nextNode);
            prev = nextNode;
            nodes.add(nextNode);
        }
    }

    public ArrayList<ListNode> getNodes() {
        return nodes;
    }
}
