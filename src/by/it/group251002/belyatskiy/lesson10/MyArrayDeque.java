package by.it.group251002.belyatskiy.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
public class MyArrayDeque<E> implements Deque<E> {
    private E[] arr = (E[]) new Object[0];

    private int size = 0;

    private void newsized() {
        newized(arr.length);
    }
    private void newized(int newSize) {
        E[] newArr = (E[]) new Object[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
    private boolean isInvalidType(Object Obj) {
        return Obj == null;
    }
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        if (size > 0) {
            SB.append(arr[0]);

            for (int i = 1; i < size; i++)
                SB.append(", ").append(arr[i]);
        }

        SB.append("]");
        return SB.toString();
    }

    @Override
    public void addFirst(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == arr.length)
            newsized();

        System.arraycopy(arr, 0, arr, 1, size);
        arr[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == arr.length)
            newsized();

        arr[size] = e;
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

        E polled = arr[0];
        System.arraycopy(arr, 1, arr, 0, --size);
        arr[size] = null;
        return polled;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;

        E polled = arr[--size];
        arr[size] = null;
        return polled;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return arr[0];
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return arr[size - 1];
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