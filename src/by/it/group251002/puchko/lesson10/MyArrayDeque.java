package by.it.group251002.puchko.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    public void increaseSize() {
        if(elements.length==size) {
            E[] newAr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elements, 0, newAr, 0, size);
            elements = newAr;
        }
    }

    @Override
    public void addFirst(E e) {
        increaseSize();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        increaseSize();
        elements[size]=e;
        size++;
    }

    @Override
    public boolean add(E e) {
        if(size == elements.length){
            increaseSize();
            elements[size] = e;
            size++;
            return false;
        }else{
            elements[size] = e;
            size++;
            return true;
        }
    }

    @Override
    public E pollFirst() {
        if(size==0)
            return null;
        else{
            E elem = elements[0];
            size--;
            System.arraycopy(elements, 1, elements, 0, size);
            elements[size]=null;
            return elem;
        }
    }

    public E pollLast() {
        if(size==0)
        {
            return null;
        }
        else{
            size--;
            E elem = elements[size];
            elements[size]=null;
            return elem;
        }
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E getFirst() {
        if(size == 0)
            return null;
        else
            return elements[0];
    }

    @Override
    public E getLast() {
        if(size == 0)
            return null;
        else
            return elements[size - 1];
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String razd = "";
        for (int i = 0; i < size; i++) {
            str.append(razd).append(elements[i]);
            razd = ", ";
        }
        str.append("]");
        return str.toString();
    }


    @Override
    public int size() {
        return size;
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
    public boolean contains(Object o) {
        return false;
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
    public boolean containsAll(Collection<?> c) {
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
}
