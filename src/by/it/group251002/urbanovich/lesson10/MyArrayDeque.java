package by.it.group251002.urbanovich.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            builder.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean add(E e) {
        addLast(e);
        return true;

    }

    @Override
    public void addLast(E e) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2 + 1);
        elements[size++] = e;
    }

    public void addFirst(E e) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2 + 1);
        System.arraycopy(elements, 0, elements, 1, size + 1);
        size++;
        elements[0] = e;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        return elements[0];
    }

    @Override
    public E getLast() {
        return elements[size-1];
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        E element = elements[0];
        System.arraycopy(elements, 1, elements, 0, size - 1);
        size--;
        return element;
    }

    @Override
    public E pollLast() {
        E element = elements[size-1];
        System.arraycopy(elements, 0, elements, 0, size - 1);
        size--;
        return element;
    }

    @Override

    public boolean isEmpty() {
        return false;
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

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
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
}
