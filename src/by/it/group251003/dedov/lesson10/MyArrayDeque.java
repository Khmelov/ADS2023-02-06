package by.it.group251003.dedov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] array = (E[]) new Object[0];
    private int size = 0;

    private void growSize() {
        if (size == array.length) {
            E[] arrayClone = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(array, 0, arrayClone, 0, size);
            array = arrayClone;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        if (size > 0) {
            string.append(array[0]);

            for (int i = 1; i < size; i++) {
                string.append(", ").append(array[i]);
            }
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public boolean add(E e) {
        growSize();
        array[size++] = e;
        return true;
    }
    @Override
    public void addFirst(E e) {
        growSize();
        System.arraycopy(array, 0, array, 1, size);
        array[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E element() {
        if (size == 0)
            throw new NoSuchElementException("Trying to get an element of an empty deque");
        return array[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getFirst() {
        return element();
    }


    @Override
    public E getLast() {
        if (size == 0)
            throw new NoSuchElementException("Trying to get an element of an empty deque");
        return array[size - 1];
    }

    @Override
    public E poll() {
        if (size-- != 0) {
            E el = array[0];
            System.arraycopy(array, 1, array, 0, size);
            array[size] = null;
            return el;
        } else
            return null;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size != 0) {
            E el = array[--size];
            array[size] = null;
            return el;
        } else
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
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
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
}


