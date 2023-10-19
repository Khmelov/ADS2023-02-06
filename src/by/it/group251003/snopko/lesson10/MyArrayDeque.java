package by.it.group251003.snopko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] data;
    private int size;
    private int first;
    private int last;
    private int maxSize;

    MyArrayDeque(){
        maxSize = 10;
        data = (E[]) new Object[maxSize];
        first = -1;
        last = -1;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimeter = "";
        for (int i = 0; i < size; i++) {
            sb.append(delimeter).append(data[(first + i) % maxSize]);
            delimeter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    public void grow(){
        maxSize *= 2;
        E[] newarray = (E[]) new Object[maxSize];
        for (int i = 0; i < size; i++){
            newarray[i] = data[(first + i ) % maxSize];
        }
        data = newarray;
        first = 0;
        last = size - 1;
    }

    @Override
    public void addFirst(E e) {
        if (size == maxSize) {
            grow();
        }
        if (isEmpty()){
            first = last = 0;
        } else {
            first = (first - 1 + maxSize) % maxSize;
        }
        data[first] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == maxSize) {
            grow();
        }
        if (isEmpty()){
            first = last = 0;
        } else {
            last = (last + 1) % maxSize;
        }
        data[last] = e;
        size++;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E getFirst() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return data[first];
    }

    @Override
    public E getLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return data[last];
    }

    @Override
    public E element() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return data[first];
    }

    @Override
    public E pollFirst() {
        if (isEmpty()){
            return null;}
        E result = data[first];
        first = (first + 1) % maxSize;
        size--;
        return result;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null;}
        E result = data[last];
        last = (last - 1 + maxSize) % maxSize;
        size--;
        return result;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;}
        E result = data[first];
        first = (first + 1) % maxSize;
        size--;
        return result;
    }


    ///////////////////////////////

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
    public int size() {
        return size;
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
