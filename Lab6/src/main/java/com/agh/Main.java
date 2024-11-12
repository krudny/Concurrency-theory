package com.agh;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int N = 10;
        List list = new List();

        for(int i = 0; i < N; i++) {
            list.add(i);
        }


        ArrayList<ListNode> nodes = list.getNodes();

        list.remove(3);
        ListNode currNode = nodes.getFirst();
        while (currNode.getNext().isPresent()) {
            System.out.print(currNode.getValue());
            System.out.print(currNode.getNext().get().getValue());
            System.out.println();

            currNode = currNode.getNext().get();

        }


    }
}