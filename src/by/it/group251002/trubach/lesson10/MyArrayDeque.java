package by.it.group251002.trubach.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] ELEMENTS = (E[]) new Object[]{};
    private int SIZE_OF_ELEMENTS = 0;
    private int HEAD_INDEX = 0;
    private int TAIL_INDEX = 0;

    private int dec(int value) {
        if (value == 0) {
            value = ELEMENTS.length - 1;
        } else {
            value -= 1;
        }
        return value;
    }

    private int inc(int value) {
        return (value + 1) % ELEMENTS.length;
    }

    private void rebuildDeque() {
        int newSize = SIZE_OF_ELEMENTS * 3 / 2 + 2;
        E[] newArr = (E[]) new Object[newSize];
        int i = HEAD_INDEX;
        int j = 0;
        if (SIZE_OF_ELEMENTS != 0) {
            do {
                i = inc(i);
                newArr[j] = ELEMENTS[i];
                j++;
            } while (i != TAIL_INDEX);
            HEAD_INDEX = newSize - 1;
            TAIL_INDEX = SIZE_OF_ELEMENTS - 1;
        } else {
            TAIL_INDEX = 0;
            HEAD_INDEX = 0;
        }
        ELEMENTS = newArr;
    }

    @Override
    public String toString() {
        StringBuilder myStr = new StringBuilder("[");
        int i = HEAD_INDEX;
        String separator = "";
        do {
            i = inc(i);
            myStr.append(separator).append(ELEMENTS[i].toString());
            separator = ", ";
        } while (i != TAIL_INDEX);
        myStr.append("]");
        return myStr.toString();
    }

    @Override
    public void addFirst(E e) {
        if (SIZE_OF_ELEMENTS == ELEMENTS.length) {
            rebuildDeque();
        }
        ELEMENTS[HEAD_INDEX] = e;
        HEAD_INDEX = dec(HEAD_INDEX);
        SIZE_OF_ELEMENTS++;
    }

    @Override
    public void addLast(E e) {
        if (SIZE_OF_ELEMENTS == ELEMENTS.length) {
            rebuildDeque();
        }
        TAIL_INDEX = inc(TAIL_INDEX);
        ELEMENTS[TAIL_INDEX] = e;
        SIZE_OF_ELEMENTS++;
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
        if (SIZE_OF_ELEMENTS == 0) {
            return null;
        }
        SIZE_OF_ELEMENTS--;
        HEAD_INDEX = inc(HEAD_INDEX);
        E elem = ELEMENTS[HEAD_INDEX];
        return elem;
    }

    @Override
    public E pollLast() {
        if (SIZE_OF_ELEMENTS == 0) {
            return null;
        }
        SIZE_OF_ELEMENTS--;
        E elem = ELEMENTS[TAIL_INDEX];
        TAIL_INDEX = dec(TAIL_INDEX);
        return elem;
    }

    @Override
    public E getFirst() {

        return ELEMENTS[inc(HEAD_INDEX)];
    }

    @Override
    public E getLast() {
        return ELEMENTS[TAIL_INDEX];
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
        if (SIZE_OF_ELEMENTS == ELEMENTS.length) {
            rebuildDeque();
        }
        TAIL_INDEX = inc(TAIL_INDEX);
        ELEMENTS[TAIL_INDEX] = e;
        SIZE_OF_ELEMENTS++;
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
        if (SIZE_OF_ELEMENTS == 0) {
            return null;
        }
        SIZE_OF_ELEMENTS--;
        HEAD_INDEX = inc(HEAD_INDEX);
        E elem = ELEMENTS[HEAD_INDEX];
        return elem;
    }

    @Override
    public E element() {
        return ELEMENTS[inc(HEAD_INDEX)];
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
        return SIZE_OF_ELEMENTS;
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
