package com.github.alllef.algorithm.fox_algo;

public class Counter {
    private int counter = 0;
    private final int maxCount;

    public Counter(int maxCount) {
        this.maxCount = maxCount;
    }

    public synchronized void addIter() {
        counter++;
        if (counter == maxCount) {
            counter = 0;
            notifyAll();
        }
    }
}
