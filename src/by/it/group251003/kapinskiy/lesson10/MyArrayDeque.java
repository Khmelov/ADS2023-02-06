package by.it.group251003.kapinskiy.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] arr = (E[]) new Object[10];

    private int size = 0;

    private void resize(){
        E[] newarr = (E[]) new Object[size + 10];
        System.arraycopy(arr, 0, newarr, 0, size);
        arr = newarr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++)
            sb.append(arr[i] + ", ");
        if (size != 0)
            sb.append(arr[size - 1]);
        return sb.append(']').toString();
    }

    @Override
    public void addFirst(E e) {
        if (e == null)
            throw new IllegalArgumentException("Null element");
        if (size == arr.length)
            resize();
        System.arraycopy(arr, 0, arr, 1, size);
        arr[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (e == null)
            throw new IllegalArgumentException("Null element");
        if (arr.length == size)
            resize();
        arr[size++] = e;
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
        E e = arr[0];
        System.arraycopy(arr, 1, arr, 0, size - 1);
        size--;
        return e;
    }

    @Override
    public E pollLast() {
        size--;
        return arr[size];
    }

    @Override
    public E getFirst() {
        return arr[0];
    }

    @Override
    public E getLast() {
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
