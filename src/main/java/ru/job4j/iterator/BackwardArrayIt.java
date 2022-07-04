package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = -1;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length - 1;
    }

    @Override
   public  Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (point == -1) {
            point  = data.length - 1;
        }
        return data[point--];
    }
}