package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            if (data[row + i].length != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = null;
        for (int i = row; i < data.length; i++) {
            if (data[i].length != 0) {
                row = i;
                rsl = data[row][column];
                if (data[row].length - 1 > column) {
                    column += 1;
                }
                if (column == 0) {
                    row += 1;
                }
                return rsl;
            }
        }
        return rsl;
    }
}
