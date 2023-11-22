package by.it.group251002.zhukovskaya.lesson10;
import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] mas = (E[]) new Object[] {};
    private int size=0;



    @Override
    public String toString() {
        if (size==0) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size - 1; i++) {
                sb.append(mas[i]).append(", ");
            }
            sb.append(mas[size - 1]).append("]");
            return sb.toString();
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (size== mas.length) {
            mas = Arrays.copyOf(mas, (size*3)/2+1);
        }
        mas[size]=e;
        size++;
        return true;
    }
    @Override
    public void addFirst(E e) {
        add(e);
        for (int i=size-1;i>0;i--) {
            mas[i]=mas[i-1];
        }
        mas[0]=e;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E getFirst() {
        return mas[0];
    }

    @Override
    public E getLast() {
        return mas[size-1];
    }
    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E pollFirst() {
        E e;
        if (size>0) {
        e=mas[0];
        System.arraycopy(mas, 1, mas, 0, size-1);
        size--;
            return e;
        }
        else
            return null;
    }

    @Override
    public E pollLast() {
        E e;
        if (size>0) {
            e = mas[size - 1];
            System.arraycopy(mas, 0, mas, 0, size - 1);
            size--;
            return e;
        }
        else
            return null;
    }
    @Override
    public E poll() {
        return pollFirst();
    }


    //////////////////////////////////////////////////////////////////
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
