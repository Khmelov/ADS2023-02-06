package by.it.group251001.voytov.lesson10;

import java.util.*;

public class MyArrayDeque<T> implements Deque<T> {

    private static final int DEFAULT_SIZE = 16;
    private Object[] data;
    private int tail;
    private int head;

    public MyArrayDeque() {
        data = new Object[DEFAULT_SIZE + 2];
        head = DEFAULT_SIZE / 2;
        tail = head + 1;
    }

    public MyArrayDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity + 2];
        head = (initialCapacity + 2) / 2;
        tail = head + 1;
    }

    public MyArrayDeque(Collection<? extends T> c) {
        this(c.size());
        System.arraycopy(c.toArray(), 0, data, 1, c.size());
        head = 1;
        tail = data.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        for (int i = head + 1; i < tail - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.append(data[tail - 1]).append("]").toString();
    }

    @Override
    public int size() {
        return tail - head - 1;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public void addFirst(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        data[head] = t;
        if (--head < 0) {
            data = grow();
        }
    }

    @Override
    public void addLast(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        data[tail] = t;
        if (++tail == data.length) {
            data = grow();
        }
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T getFirst() {
        T element = (T) data[head + 1];
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public T getLast() {
        T element = (T) data[tail - 1];
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public T pollFirst() {
        T element = (T) data[head + 1];
        if (element != null) {
            data[head + 1] = null;
            head++;
        }
        return element;
    }

    @Override
    public T pollLast() {
        T element = (T) data[tail - 1];
        if (element != null) {
            data[tail - 1] = null;
            tail--;
        }
        return element;
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    private Object[] grow() {
        int newCapacity = data.length * 3 / 2;
        int numElements = tail - head - 1;
        Object[] newData = new Object[newCapacity];
        int copyPos = (newCapacity - numElements) / 2;
        System.arraycopy(data, head + 1, newData, copyPos, tail - head - 1);
        head = copyPos - 1;
        tail = copyPos + numElements;
        return newData;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }
    @Override
    public boolean offerFirst(T t) {
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        return false;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T peekFirst() {
        return null;
    }

    @Override
    public T peekLast() {
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
    public T remove() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
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
    public void push(T t) {

    }

    @Override
    public T pop() {
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
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }
}
