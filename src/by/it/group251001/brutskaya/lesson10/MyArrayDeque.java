package by.it.group251001.brutskaya.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    E[] elements = (E[]) new Object[3];
    int head;
    int tail;
    int size;

    private int dec(int index) {
        index--;
        if (index < 0) {
            index = elements.length - 1;
            ;
        }
        return index;
    }

    private int inc(int index) {
        index++;
        if (index == elements.length) {
            index = 0;
        }
        return index;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = head; i != dec(tail); i = inc(i)) {
            s.append(elements[i]).append(", ");
        }
        s.append(elements[dec(tail)]).append("]");
        return s.toString();
    }

    public void addFirst(E element) {
        if (size == elements.length) {
            grow();
        }
        head = dec(head);
        elements[head] = element;
        size++;
    }

    public void grow() {
        E[] newElements = (E[]) new Object[(size * 3) / 2 + 1];
        int j = 0;
        for (int i = head; i != dec(tail); i = inc(i)) {
            newElements[j] = elements[i];
            elements[i] = null;
            j++;
        }
        newElements[j] = elements[dec(tail)];
        head = 0;
        tail = size;
        elements = newElements;
    }

    @Override
    public void addLast(E element) {
        if (element == null)
            throw new NullPointerException();
        if (size == elements.length) {
            grow();
        }
        elements[tail] = element;
        tail = inc(tail);
        size++;
    }

    @Override
    public boolean offerFirst(Object o) {
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
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
        return poll();
    }

    @Override
    public E pollLast() {
        E element = elements[dec(tail)];
        elements[dec(tail)]=null;
        tail=dec(tail);
        size--;
        return element;
    }

    @Override
    public E getFirst() {
        return elements[head];
    }

    @Override
    public E getLast() {
        return elements[dec(tail)];

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
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        E element = elements[head];
        elements[head]=null;
        head = inc(head);
        size--;
        return element;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void push(Object o) {

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
    public boolean containsAll(Collection c) {
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
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public E[] toArray(Object[] a) {
        return null;
    }

    @Override
    public Iterator descendingIterator() {
        return null;
    }
}
