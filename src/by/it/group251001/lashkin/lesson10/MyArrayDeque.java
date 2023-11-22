package by.it.group251001.lashkin.lesson10;

import java.util.*;

/*              toString()
                size()

                add(E element)
                addFirst(E element)
                addLast(E element)

                element()
                getFirst()
                getLast()

                poll()
                pollFirst()
                pollLast()
*/

public class MyArrayDeque<E> implements Deque<E> {

    private E[] array = (E[]) new Object[]{};
    private int size = 0;

    public void ensureCapacity(int newSize) {
        E[] newArray = (E[]) new Object[((newSize * 3) >> 1) + 1];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public String toString() {
        String s = "[";
        if (size > 0) {
            s = s + array[0];
            for (int i = 1; i < size; i++)
                s = s + ", " + array[i];
        }
        s = s + "]";
        return s;
    }

    @Override
    public void addFirst(E e) {
        if (size == array.length) {
            ensureCapacity(size);
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == array.length) {
            ensureCapacity(size);
        }
        array[size] = e;
        size++;
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
        if (isEmpty()) {
            return null;
        }
        E e = array[0];
        size--;
        System.arraycopy(array, 1, array, 0, size);
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        E e = array[size];
        array[size] = null;
        return e;
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
    public E element() {
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
        return size;
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
