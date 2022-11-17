package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countPush;
    private int countPoll;

    //Метод poll() - должен возвращать первое значение и удалять его из коллекции.
    public T poll() {
        if (countPush <= 0 && countPoll <= 0) {
            throw new NoSuchElementException();
        }

        if (countPoll == 0) { // счетчик удаления первого значения
            while (countPush > 0) { // счетчик помещения значения в конец
                out.push(in.pop()); // из стека in удаляется первый элемент и помещается в стек out первым
                countPush--;
                countPoll++;
            }
            // цикл работает пока стек in имеет элементы и начинается если в стеке out нет элементов
        }
        countPoll--;
        return out.pop(); // возвращаем 1-ый элемент из стека out и понижаем счетчик (как только он станет = 0,
        // начнется опять переброс элементов между стеками)

    }

    //Метод push(T value) - помещает значение в конец.
    public void push(T value) {
        in.push(value); // добавляем элемент в начало стека
        countPush++;
    }
}