package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean checkAdd = false;
        if (!contains(value)) {
            set.add(value);
            checkAdd = true;
        }
        return checkAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean checkCon = false;
        for (T t: set) {
            if (Objects.equals(t, value)) {
                checkCon = true;
                break;
            }
        }
        return checkCon;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}