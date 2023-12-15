package by.it.group251001.besedin_anton.lesson10;

import java.util.Deque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
public class MyArrayDeque <E> implements Deque<E> {
    transient E[] elements;
    private int size;
    private int capacity = 4;

    public MyArrayDeque() {
        elements = (E[]) (new Object[capacity]);
        size = 0;
    }


    private void extendElements() {
        int oldCapacity = size + 1;
        capacity = oldCapacity + (oldCapacity / 2);
        elements = Arrays.copyOf(elements, capacity);
    }

    public void addFirst(E e) {
        if (size == capacity) {
            this.extendElements();
        }

        System.arraycopy(elements, 0, elements, 1, size);
        elements[0] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == capacity) {
           this.extendElements();
        }
        elements[size++] = e;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            res.append(
                elements[i].toString()
            ).append(", ");
        }

        return res + elements[size - 1].toString() + ']';
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }
    @Override
    public boolean offer(E e) {
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
    public E remove() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        return false;
    }

    @Override
    public void clear() {

    }


    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        
        E temp = elements[0];

        size--;
        System.arraycopy(elements, 1, elements, 0, size);
        
        return temp;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        } else {
            return elements[--size];
        }
    }

    public E poll() {
        return pollFirst();
    }


    public E getFirst() {
        return elements[0];
    }

    public E getLast() {
        return elements[size - 1];
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

    public E element() {

        if (size == 0) {
            return null;
        } else {
            return elements[0];
        }

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
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }
}
