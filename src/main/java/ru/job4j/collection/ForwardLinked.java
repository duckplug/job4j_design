package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }
        T oldValue = head.value;
        Node<T> headNodeNew = head.next;
        head.value = null;
        head.next = null;
        head = headNodeNew;
        return oldValue;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public boolean revert() {
        boolean validate = true;
        if (!iterator().hasNext() || head.next == null) {
            validate = false;
        } else {
            Node<T> prevNode = null;
            Node<T> presNode = head;
            while (iterator().hasNext() && presNode != null) {
                Node<T> nextNode = presNode.next;
                presNode.next = prevNode;
                prevNode = presNode;
                presNode = nextNode;
            }
            head = prevNode;
        }
        return validate;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}