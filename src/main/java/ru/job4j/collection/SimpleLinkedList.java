package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int modCount;
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
            size++;
        } else {
            l.next = newNode;
            size++;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
            return x.item;
    }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                int index = 0;
                int expectedModCount = modCount;

                @Override
                public boolean hasNext() {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException("Коллекция был изменена");
                    }
                    return index < size;
                }

                @Override
                public E next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Node<E> x = first;
                    if (index != 0) {
                        x = x.next;
                    }
                    index++;
                    return x.item;
                }
            };
        }

        private static class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;

            Node(Node<E> prev, E element, Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }
