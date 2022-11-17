package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countPush;
    private int countPoll;

    public T poll() {
        if (countPush <= 0 && countPoll <= 0) {
            throw new NoSuchElementException();
        }
        if (countPoll == 0) {
            while (countPush > 0) {
                out.push(in.pop());
                countPush--;
                countPoll++;
            }
        }
        countPoll--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        countPush++;
    }
}