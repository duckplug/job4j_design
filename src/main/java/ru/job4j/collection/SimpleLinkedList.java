package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int modCount;
    private Node<E> last;
    int size;

    @Override
    public void add(E value) {
        size++;
        modCount++;
        Node<E> l = last;
        Node<E> newNode = new Node<>(value, null);
        if (l == null) {
            last = newNode;
        } else {
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = last;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
            return x.item;
    }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                int expectedModCount = modCount;
                Node<E> node = last;

                @Override
                public boolean hasNext() {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException("Коллекция был изменена");
                    }
                    return node != null;
                }

                @Override
                public E next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    E value = node.item;
                    node = node.next;
                    return value;
                }
            };
        }

        private static class Node<E> {
            E item;
            Node<E> next;

            Node(E element, Node<E> next) {
                this.item = element;
                this.next = next;
            }
        }
    }
