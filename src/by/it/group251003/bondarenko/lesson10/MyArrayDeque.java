package by.it.group251003.bondarenko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private int minCapacity = 5;
    private E[] arr = (E[]) new Object[minCapacity];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    private void resize() {
        E[] newArr = (E[]) new Object[size * 2 + 1];
        int j = 0;
        for (int i = head; i != dec(tail); i = inc(i)) {
            newArr[j] = arr[i];
            arr[i] = null;
            j++;
        }
        newArr[j] = arr[dec(tail)];

        head = 0;
        tail = size;
        arr = newArr;
    }

    private int dec(int i) {
        i--;
        if (i < 0)
            i = arr.length - 1;
        return i;
    }

    private int inc(int i) {
        i++;
        if (i >= arr.length)
            i = 0;
        return i;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");

        String del = "";
        for (int i = head; i != dec(tail); i = inc(i)) {
            res.append(del).append(arr[i]);
            del = ", ";
        }
        res.append(del).append(arr[dec(tail)]).append("]");
        return res.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length)
            resize();

        arr[tail] = e;
        tail = inc(tail);
        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (size == arr.length)
            resize();

        head = dec(head);
        arr[head] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E element() {
        if (size == 0)
            return null;
        return arr[head];
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if (size == 0)
            return null;
        return arr[dec(tail)];
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        E el = arr[head];
        arr[head] = null;
        head = inc(head);
        size--;

        return el;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size == 0)
            return null;

        E el = arr[dec(tail)];
        arr[tail] = null;
        tail = dec(tail);
        size--;

        return el;
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

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
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

}
