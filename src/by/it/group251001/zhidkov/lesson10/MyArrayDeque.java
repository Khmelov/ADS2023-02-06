package by.it.group251001.zhidkov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] array;
    private int size;
    private int front;
    private int rear;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayDeque() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }
    @Override
    public void addFirst(E element) {
        if (size == array.length)
        {
            int newCapacity = array.length * 2;
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
            {
                newArray[i] = array[(front + i) % array.length];
            }
            array = newArray;
            rear = size;
            front = 0;
        }
        front = (front - 1 + array.length) % array.length;
        array[front] = element;
        size++;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(array[(front + i) % array.length]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
    @Override
    public void addLast(E element) {
        if (size == array.length)
        {
            int newCapacity = array.length * 2;
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
            {
                newArray[i] = array[(front + i) % array.length];
            }
            array = newArray;
            rear = size;
            front = 0;
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
        size++;
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
        {
            return null;
        }
        size--;
        E element = array[front];
        front = (front + 1) % array.length;
        return element;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
        {
            return null;
        }
        rear = (rear - 1 + array.length) % array.length;
        E element = array[rear];
        size--;
        return element;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return array[(rear - 1 + array.length) % array.length];
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        }
        return getFirst();
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
