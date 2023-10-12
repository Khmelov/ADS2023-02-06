package by.it.group251001.gyscha.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] deque;
    private int capacity;
    private int size;
    private int head;
    private int tail;

    MyArrayDeque(){
        capacity=5;
        deque=(E[]) new Object[capacity];
        size=0;
        head=0;
        tail=-1;
    }

    @Override
    public E getFirst() {
        return deque[head];
    }

    @Override
    public E getLast() {
        return deque[tail];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = head; i <= tail; i++) {
            sb.append(deque[i]);
            if (i < tail) {sb.append(", ");}
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void addFirst(E e) {
        if (size == capacity) {capacity*=2;}
        E[] newList = (E[]) new Object[capacity];
        newList[0]=e;
        for (int i=1;i<=size;i++){
            newList[i]=deque[i-1];
        }
        deque = newList;
        size++;
        tail++;
    }

    @Override
    public E pollLast() {
        if (size == 0) return null;
        E elem = deque[tail];
        size--;
        tail--;
        return elem;
    }

    @Override
    public E pollFirst() {
        if (size == 0)
            return null;
        E elem = deque[head];
        E[] newList = (E[]) new Object[capacity];
        for (int i=0;i<size;i++){
            newList[i]=deque[i+1];
        }
        deque = newList;
        size--;
        tail--;
        return elem;
    }

    @Override
    public boolean add(E e) {
        if (size == capacity) {
            capacity*=2;
            E[] newList = (E[]) new Object[capacity];
            for (int i=0;i<size;i++){
                newList[i]=deque[i];
            }
            deque = newList;
        }
        deque[size]=e;
        size++;
        tail++;
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
    public void clear() {}

    @Override
    public void push(E e) {}

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
    public boolean isEmpty() {return false;}

    @Override
    public Iterator<E> iterator() {return null;}

    @Override
    public Object[] toArray() {return new Object[0];}

    @Override
    public <T> T[] toArray(T[] a) {return null;}

    @Override
    public Iterator<E> descendingIterator() {return null;}

    @Override
    public void addLast(E e) {
        add(e);
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
}