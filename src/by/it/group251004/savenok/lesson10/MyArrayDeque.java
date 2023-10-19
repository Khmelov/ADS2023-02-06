package by.it.group251004.savenok.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] array = (E[]) new Object[]{};
    private int size;
    private final int front = 0;

    public MyArrayDeque() {
        size = 0;
    }

    @Override
    public void addFirst(E element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        size++;
        System.arraycopy(array, 0, array, 1, size - 1);
        array[front] = element;
    }

    @Override
    public void addLast(E element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        array[size++] = element;
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
        if (!isEmpty()) {
            E firstElem = array[front];
            System.arraycopy(array, 1, array, 0, size - 1);
            size--;
            return firstElem;
        }
        return null;
    }

    @Override
    public E pollLast() {
        if (!isEmpty()) {
            E lastElem = array[size - 1];
            size--;
            return lastElem;
        }
        return null;
    }

    @Override
    public E getFirst() {
        return array[front];
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

    public boolean add(E element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        array[size++] = element;
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
        if (!isEmpty()) {
            E firstElem = array[front];
            System.arraycopy(array, 1, array, 0, size - 1);
            size--;
            return firstElem;
        }
        return null;
    }

    @Override
    public E element() {
        return array[front];
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
        if (size == 0) {
            return true;
        }
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            str.append(array[i]);
            if (i + 1 != size) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }


//    private void resizeArray() {
//        int newCapacity = array.length * 2;
//        Object[] newArray = new Object[newCapacity];
//        for (int i = 0; i < size; i++) {
//            newArray[i] = array[(front + i) % array.length];
//        }
//        array = newArray;
//        front = 0;
//        rear = size - 1;
//    }
}

