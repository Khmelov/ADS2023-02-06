package by.it.group251002.baranovskaia.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] array;

    private int size;

    public MyArrayDeque(){
        array = (E[]) new Object[0];
        size = 0;
    }

    private void resize() {
        resize(array.length);
    }
    private void resize(int newSize) {
        E[] newArray = (E[]) new Object[newSize * 3 / 2 + 1];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        if (size > 0) {
            SB.append(array[0]);

            for (int i = 1; i < size; i++)
                SB.append(", ").append(array[i]);
        }

        SB.append("]");
        return SB.toString();
    }

    @Override
    public void addFirst(E e) {
        if (e == null)
            throw new IllegalArgumentException("Exception: null argument");

        if (size == array.length)
            resize();

        System.arraycopy(array, 0, array, 1, size++);
        array[0] = e;
    }

    @Override
    public void addLast(E e) {
        if (e == null)
            throw new IllegalArgumentException("Exception: null argument");

        if (size == array.length)
            resize();

        array[size] = e;
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
            return null;

        E polled = array[0];
        System.arraycopy(array, 1, array, 0, size--);
        resize(size);
        return polled;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;

        E polled = array[--size];
        array[size] = null;
        return polled;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty array");

        return array[0];
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty array");

        return array[size - 1];
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
    public boolean add(E e) {
        addLast(e);
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

    @Override
    public E poll() {
        return pollFirst();
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
        return size == 0;
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
