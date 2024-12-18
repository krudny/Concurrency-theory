package com.agh;

import java.util.ArrayList;

public class Table {
    private final int N;
    private ArrayList<Fork> forks;

    public Table(int N) {
        this.N = N;
        this.forks = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            forks.add(new Fork());
        }
    }

    public Fork getFork(int id) {
        return forks.get(id);
    }

    public ArrayList<Fork> getForks() {
        return forks;
    }
}
