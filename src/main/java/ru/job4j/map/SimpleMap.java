package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) capacity / count == LOAD_FACTOR) {
            expand();
        }
        boolean check = false;
            int keyHash = hash(Objects.hashCode(key));
            int indexTable = indexFor(keyHash);
            if (table[indexTable] == null) {
                check = true;
                table[indexTable] = new MapEntry<>(key, value);
                modCount += 1;
                count += 1;
            }

        return check;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : (hashCode ^ hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        int newTableLength = table.length * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[newTableLength];
        for (MapEntry<K, V> me : oldTable) {
            if (me != null) {
                int meHash = hash(Objects.hashCode(me.key));
                int i = indexFor(meHash);
                table[i] = me;
            }
        }
    }

    @Override
    public V get(K key) {
        int hashGet = hash(Objects.hashCode(key));
        for (MapEntry<K, V> me : table) {
            if (me != null && hash(Objects.hashCode(me.key)) == hashGet && Objects.equals(key, me.key)) {
                return me.value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean check = false;
        int keyHash = hash(Objects.hashCode(key));
        for (MapEntry<K, V> me : table) {
            if (me != null && (hash(Objects.hashCode(me.key)) == keyHash) && (Objects.equals(key, me.key))) {
                table[indexFor(hash(Objects.hashCode(me.key)))] = null;
                modCount += 1;
                check = true;
                count -= 1;
                break;
            }
        }
        return check;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Коллекция был изменена");
                }
                boolean proof = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        proof = true;
                        break;
                    }
                }
                return proof;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}