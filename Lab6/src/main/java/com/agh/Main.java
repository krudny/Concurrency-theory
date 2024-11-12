package com.agh;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int N = 5;
        List list = new List(5);
        ArrayList<ListNode> nodes = list.getNodes();

        ListNode currNode = nodes.getFirst();

        while (currNode.getNext().isPresent()) {
            System.out.println(currNode.getValue());
            currNode = currNode.getNext().get();
        }

    }
}