package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container[size] = value;
        size += 1;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
        container[index] = newValue;
        return  oldValue;

    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, 1);
        container[container.length - 1 - index] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return  container[index];
    }

    @Override
    public int size() {
        return container.length;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                int expectedModCount = modCount;
                return true;


            }

            @Override
            public T next() {
                return null;

            }

        };
    }
}