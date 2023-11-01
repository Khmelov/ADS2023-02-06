package by.it.group251003.stasevich_uriu.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] elements = (E[])new Object[0];
    private int size = 0;
    private void checkSize(){
        if(size == elements.length){
            E [] elementsAdd= (E[]) new Object[(size*3)/2+1];
            System.arraycopy(elements, 0, elementsAdd, 0, size);
            elements=elementsAdd;
        }
    }
    @Override public String toString() {
        StringBuilder s = new StringBuilder("[");
        String inter="";
        for(int i = 0; i<size; i++){
            s.append(inter).append(elements[i]);
            inter=", ";
        }
        s.append("]");
        return s.toString();
    }

    @Override public int size(){
        return size;
    }

    @Override
    public boolean add(E e) {
        checkSize();
        elements[size++]=e;
        return true;
    }


    @Override public void addFirst(E e) {
        checkSize();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[0]=e;
        size++;
    }

    @Override
    public E element() {
        if(size==0) {
            throw new NoSuchElementException();
        } else {
            return elements[0];
        }
    }

    @Override public void addLast(E e) {
        add(e);
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if(size==0) {
            throw new NoSuchElementException();
        } else {
            return elements[size-1];
        }
    }

    @Override
    public E poll() {
        if(size==0){
            return null;
        } else {
            E buf = elements[0];
            System.arraycopy(elements, 1, elements, 0, --size);
            elements[size]=null;
            return buf;
        }
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if(size==0){
            return null;
        } else {
            E buf = elements[size-1];
            elements[--size]=null;
            return buf;
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
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection collection) {
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
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Iterator descendingIterator() {
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
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}
