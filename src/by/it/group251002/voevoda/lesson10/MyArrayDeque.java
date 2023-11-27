package by.it.group251002.voevoda.lesson10;

import by.it.group251002.voevoda.lesson09.Node;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] base;
    private int start;
    private int length;
    private int capacity;
    public MyArrayDeque(int length) {
        start = 0;
        base = (E[]) new Object[length];
        this.length = length;
        capacity = length;
    }
    public MyArrayDeque(int length, int capacity) {
        start = 0;
        base = (E[]) new Object[capacity];
        this.length = length;
        this.capacity = capacity;
    }
    public MyArrayDeque(E[] base) {
        start = 0;
        this.base = (E[]) new Object[length];
        for (int i = 0; i < base.length; ++i) {
            this.base[i] = base[i];
        }
        this.length = base.length;
        this.capacity = base.length;
    }
    public MyArrayDeque() {
        int initialSize = 16;
        base = (E[]) new Object[initialSize];
        length = 0;
        capacity = initialSize;
        start = 0;
    }

    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        if (length > 0) {
            sb.append(base[start].toString());
        }

        for (int i = start + 1; i < start + length; ++i) {
            sb.append(", ");
            if (base[i] == null) {
                sb.append("null");
            } else {
                sb.append(base[i].toString());
            }
        }

        sb.append(']');

        return sb.toString();
    }
    @Override
    public int size() {
        return length;
    }
    @Override
    public boolean add(E e) {

        this.addLast(e);

        return true;
    }
    @Override
    public void addFirst(E e) {

        if (start > 0) {
            base[--start] = e;
            ++length;
            ++capacity;
            return;
        }

        if (length + 1 > capacity) {
            if (capacity < 1024) {
                capacity <<= 1;
            } else {
                capacity = capacity + (capacity / 4);
            }
            E[] base = (E[]) new Object[capacity];
            for (int i = start; i < start + length; ++i) {
                base[i+1 - start] = this.base[i];
            }
            this.base = base;
            start = 0;
        } else {
            for (int i = start + length - 1; i >= 0; --i) {
                base[i+1] = this.base[i];
            }
        }

        base[start] = e;
        ++length;

    }
    @Override
    public void addLast(E e) {
        if (length + 1 > capacity) {
            if (capacity < 1024) {
                capacity <<= 1;
            } else {
                capacity = capacity + (capacity / 4);
            }
            E[] base = (E[]) new Object[capacity];
            for (int i = start; i < start + length; ++i) {
                base[i-start] = this.base[i];
            }
            this.base = base;
            start = 0;
        }

        base[start + length++] = e;
    }
    private E retrieveByIndex(int i) {
        if (length == 0) {
            try {
                throw new Exception("Exception: deque is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return base[start + i];
    }
    @Override
    public E element() {
        return this.retrieveByIndex(0);
    }
    @Override
    public E getFirst() {
        return this.retrieveByIndex(0);
    }

    @Override
    public E getLast() {
        return this.retrieveByIndex(length - 1);
    }
    @Override
    public E poll() {
        if (length == 0) {
            return null;
        }
        E res = base[start++];
        --length;
        --capacity;
        return res;
    }
    @Override
    public E pollFirst() {
        return this.poll();
    }
    @Override
    public E pollLast() {
        if (length == 0) {
            return null;
        }
        return base[start + --length];
    }

    /////////////////////////////////////////////////////////////////////////
    //////              Необязательные к реализации методы            ///////
    /////////////////////////////////////////////////////////////////////////
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
