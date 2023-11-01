package by.it.group251002.shpitalenkov.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {

    static private int minCapacity = 4;
    private E[] arr = (E[]) new Object[minCapacity];
    private int headIndex = 0;
    private int tailIndex = 0;
    private int size = 0;

    private void growDeque() {
        E[] newArr = (E[]) new Object[(size*3)/2+1];
        int j = 0;
        for(int i = headIndex; i != decrease(tailIndex); i = increase(i)) {
            newArr[j] = arr[i];
            arr[i] = null;
            j++;
        }
        newArr[j] = arr[decrease(tailIndex)];

        headIndex = 0;
        tailIndex = size;
        arr = newArr;
    }

    private int decrease(int index) {
        index--;
        if (index < 0) {
            index = arr.length - 1;;
        }
        return index;
    }

    private int increase(int index) {
        index++;
        if (index == arr.length) {
            index = 0;
        }
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = headIndex; i != decrease(tailIndex);  i = increase(i)) {
            sb.append(delimiter).append(arr[i]);
            delimiter = ", ";
        }
        sb.append(delimiter).append(arr[decrease(tailIndex)]);
        delimiter = ", ";
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length) {
            growDeque();
        }

        arr[tailIndex] = e;
        tailIndex = increase(tailIndex);
        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (size == arr.length) {
            growDeque();
        }

        headIndex = decrease(headIndex);
        arr[headIndex] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == arr.length) {
            growDeque();
        }

        arr[tailIndex] = e;
        tailIndex = increase(tailIndex);
        size++;
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
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return arr[headIndex];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }

        return arr[decrease(tailIndex)];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        size--;
        E element = arr[headIndex];
        arr[headIndex] = null;
        headIndex = increase(headIndex);
        return element;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        E element = arr[headIndex];
        arr[headIndex] = null;
        headIndex = increase(headIndex);
        return element;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        size--;
        E element = arr[decrease(tailIndex)];
        arr[decrease(tailIndex)] = null;
        tailIndex = decrease(tailIndex);
        return element;
    }


    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        }

        return arr[headIndex];
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