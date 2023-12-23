package by.it.group251003.kukhotskovolets.lesson10;

import java.rmi.NoSuchObjectException;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] elems = (E[]) new Object[0];
    private int size = 0;

    private void resize(){
        E[] newElems = (E[]) new Object[(elems.length * 3) / 2 + 1];
        System.arraycopy(elems, 0, newElems, 0, size);
        elems = newElems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        if (size > 0){
            for (int i = 0; i < size; i++) {
                sb.append(delimiter).append(elems[i]);
                delimiter = ", ";
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (size == elems.length){
            resize();
        }

        System.arraycopy(elems, 0, elems, 1, size);
        elems[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == elems.length){
            resize();
        }
        elems[size++] = e;
    }

    @Override
    public E element() {
        if (isEmpty()){
            throw new IllegalArgumentException("Deque is empty!");
        }
        return getFirst();
    }

    @Override
    public E getFirst() {
        return elems[0];
    }

    @Override
    public E getLast() {
        return elems[size - 1];
    }

    @Override
    public E poll() {
        if (!isEmpty()){
            E deletedElem = elems[0];
            System.arraycopy(elems, 1, elems, 0, size--);
            elems[size] = null;
            return deletedElem;
        }
        else return null;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (!isEmpty()) {
            E deletedElem = elems[--size];
            elems[size] = null;
            System.arraycopy(elems, 0, elems, 0, size);
            return deletedElem;
        }
        else {
            return null;
        }
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
        return size == 0;
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
