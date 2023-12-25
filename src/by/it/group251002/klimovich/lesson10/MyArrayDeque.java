package by.it.group251002.klimovich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] elems = (E[]) new Object[]{};
    private int size = 0;
    private void CheckSize() {
        if (size == elems.length) {
            E[] arr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elems, 0, arr, 0, size);
            elems = arr;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String sep ="";
        for (int i=0;i < size; i++){
            sb.append(sep).append(elems[i]);
            sep=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean add(E e){
        if (size == elems.length) {
            E[] arr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elems, 0, arr, 0, size);
            elems = arr;
            elems[size]=e;
            size++;
            return false;
        }
        else {
            elems[size]=e;
            size++;
            return true;
        }
    }
    @Override
    public void addFirst(E e) {
        CheckSize();
        System.arraycopy(elems,0,elems,1,size);
        elems[0]=e;
        size++;
    }

    @Override
    public void addLast(E e) {
        CheckSize();
        elems[size]=e;
        size++;
    }

    @Override
    public E getFirst() {
        return elems[0];
    }

    @Override
    public E getLast() {
        return elems[size-1];
    }

    @Override
    public E element() {
        return elems[0];
    }

    @Override
    public E pollFirst() {
        if (size!=0) {
            E elem = elems[0];
            System.arraycopy(elems,1,elems,0,size-1);
            size--;
            return elem;
        }
        else {
            return null;
        }
    }

    @Override
    public E pollLast() {
        if (size!=0) {
            E elem = elems[size-1];
            size--;
            return elem;
        }
        else {
            return null;
        }
    }

    @Override
    public E poll() {
        return pollFirst();
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

