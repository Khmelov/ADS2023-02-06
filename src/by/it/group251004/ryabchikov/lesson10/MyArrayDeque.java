package by.it.group251004.ryabchikov.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] array = (E[]) new Object[]{};
    private int size = 0;

    @Override
    public void addFirst(E e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        array[size++] = e;
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
        if (size > 0) {
            E elem = array[0];
            System.arraycopy(array, 1, array, 0, --size);
            return elem;
        } else {
            return null;
        }
    }

    @Override
    public E pollLast() {
        if (size > 0) {
            E elem = array[size - 1];
            array[size - 1] = null;
            size--;
            return elem;
        } else {
            return null;
        }
    }

    @Override
    public E getFirst() {
        return array[0];
    }

    @Override
    public E getLast() {
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
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        array[size++] = e;
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
        if (size > 0) {
            E elem = array[0];
            System.arraycopy(array, 1, array, 0, --size);
            return elem;
        } else {
            return null;
        }
    }

    @Override
    public E element() {
        return array[0];
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
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i + 1 != size) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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
