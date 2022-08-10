package ru.job4j.Set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean checkAdd = false;
        while (iterator().hasNext()) {
            if (Objects.equals(value, iterator().next())) {
                checkAdd = true;
                break;
            }
            iterator().next();
        }
        if (!checkAdd) {
            set.add(value);
        }
        return !checkAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean checkCon = false;
        while (iterator().hasNext()) {
            if (Objects.equals(value, iterator().next())) {
                checkCon = true;
                break;
            }
            iterator().next();
        }
        return checkCon;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}