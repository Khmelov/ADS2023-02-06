package by.it.group251001.pavlkrat.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private Object[] arr = new Object[0];
    private int l = 0, r = -1;

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = l; i < r; i++)
            res.append(arr[i].toString()).append(", ");
        return res + arr[r].toString() + "]";
    }

    private void grow() {
        int oldSize = size(), newL = oldSize + 1, newSize = 3 * newL;
        Object[] newArr = new Object[newSize];
        if (!isEmpty()) System.arraycopy(arr, l, newArr, newL, oldSize);
        arr = newArr;
        if (oldSize > 0)
            l = newL;
        else
            l = 2;
        r = l + oldSize - 1;
    }

    @Override
    public void addFirst(E e) {
        if (l == 0)
            grow();
        arr[--l] = e;
    }

    @Override
    public void addLast(E e) {
        if (r == arr.length - 1)
            grow();
        arr[++r] = e;
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
        return (E) arr[l++];
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        return (E) arr[r--];
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return (E) arr[l];
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return (E) arr[r];
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
    public E element() throws NoSuchElementException {
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
        return r - l + 1;
    }

    @Override
    public boolean isEmpty() {
        return r < l;
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
