package by.it.group251002.rudakov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque <E> implements Deque<E> {
    private E[]array = (E[])new Object[16];
    private int tail = 0;
    private int head = 0;

    private void resize() {
        int oldCapacity = array.length;
        int newCapacity = array.length * 2 + 1;

        E[] tmpArray = (E[]) new Object[array.length * 2 + 1];
        System.arraycopy(array, 0, tmpArray, 0, array.length);
        array = tmpArray;
        if (tail < head || (tail == head && array[head] != null)) {
            System.arraycopy(array, head, array, head + newCapacity - oldCapacity, oldCapacity - head);
            for (int i = head; i < head + newCapacity - oldCapacity; i++) array[i] = null;
            head += newCapacity - oldCapacity;
        }
    }

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
        String rec = "[";
        int i = head;
        while (i != tail) {
            if (array[i] == null) {
                i = head;
            }
            rec += array[i].toString();
            i = incPos(i, array.length);
            if (i != tail) rec += ", ";
        }
        return rec + "]";
    }
    @Override
    public int size() {
        int tmp = tail - head;
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
        head = decPos(head, array.length);
        array[head] = e;
        if (head == tail)
            resize();
    }
    @Override
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        array[tail] = e;
        tail = incPos(tail, array.length);
        if (head == tail)
            resize();
    }
    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        E e = array[head];
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    @Override
    public E getLast() {
        E e = array[decPos(tail, array.length)];
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
        E e = array[head];
        if (e != null) {
            array[head] = null;
            head = incPos(head, array.length);
        }
        return e;
    }
    @Override
    public E pollLast() {
        int tm = decPos(tail, array.length);
        E e = array[tm];
        if (e != null) {
            array[tm] = null;
            tail = tm;
        }
        return e;
    }






    @Override
    public E peekFirst() {
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

