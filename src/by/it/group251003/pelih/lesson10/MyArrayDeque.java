package by.it.group251003.pelih.lesson10;

import java.util.*;


public class MyArrayDeque<E> implements Deque<E>{

    private E[]array = (E[])new Object[16];
    private int end = 0;
    private int start = 0;

    private void resize() {
        int oldCapacity = array.length;
        int newCapacity = array.length * 2 + 1;

        E[] tmpArray = (E[]) new Object[array.length * 2 + 1];
        System.arraycopy(array, 0, tmpArray, 0, array.length);
        array = tmpArray;
        if (end < start || (end == start && array[end] != null)) {
            System.arraycopy(array, start, array, start + newCapacity - oldCapacity, oldCapacity - start);
            for (int i = start; i < start + newCapacity - oldCapacity; i++) array[i] = null;
            start += newCapacity - oldCapacity;
        }
    }

    /*Удаление(с конца) / добавление(в начало)*/
    static final int decPos(int pos, int border) {
        pos--;
        if (pos < 0) pos = border - 1;
        return pos;
    }
    static final int incPos(int pos, int border) {
        pos++;
        if (pos >= border) pos = 0;
        return pos;
    }

    @Override
    public String toString() {
        String res = "[";
        int i = start;
        while (i != end) {
            if (array[i] == null) {
                i = start;
            }
            res += array[i].toString();
            i = incPos(i, array.length);
            if (i != end) res += ", ";
        }
        return res + "]";
    }
    @Override
    public int size() {
        int tmp = end - start;
        if (tmp < 0) return tmp + array.length;
        return tmp;
    }
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }
    @Override
    public void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        start = decPos(start, array.length);
        array[start] = e;
        if (start == end)
            resize();
    }
    @Override
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        array[end] = e;
        end = incPos(end, array.length);
        if (start == end)
            resize();
    }
    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        E e = array[start];
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    @Override
    public E getLast() {
        E e = array[decPos(end, array.length)];
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    @Override
    public E poll() {
        return pollFirst();
    }
    @Override
    public E pollFirst() {
        E e = array[start];
        if (e != null) {
            array[start] = null;
            start = incPos(start, array.length);
        }
        return e;
    }
    @Override
    public E pollLast() {
        int t = decPos(end, array.length);
        E e = array[t];
        if (e != null) {
            array[t] = null;
            end = t;
        }
        return e;
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