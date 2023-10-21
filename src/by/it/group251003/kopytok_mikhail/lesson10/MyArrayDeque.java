package by.it.group251003.kopytok_mikhail.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private int capacity = 4;
    private E[] arr = (E[]) new Object[capacity];
    private int headIndex = 0;
    private int tailIndex = -1;
    private int size = 0;

    public String toString(){
        StringBuilder str = new StringBuilder(0);
        str.append('[');
        for (int i = 0; i < size - 1; i++){
            str.append(arr[i]);
            str.append(',').append(' ');
        }
        str.append(arr[size - 1]);
        str.append(']');
        return str.toString();
    }
    private void resize(){
        capacity = (size*3)/2+1;
        E[] newArr = (E[]) new Object[capacity];
        for (int i = headIndex; i < size; i++){
            newArr[i] = arr[i];
            // arr[i] = null;
        }
        arr = newArr;
    }
    @Override
    public void addFirst(E e) {
        if (size == capacity){
            capacity++;
        }
        E[] newArr = (E[]) new Object[capacity];
        newArr[0] = e;
        for (int i = 0; i < size; i++){
            newArr[i + 1] = arr[i];
            // arr[i] = null;
        }
        arr = newArr;
        size++;
        tailIndex++;
    }

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
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E[] newArr = (E[]) new Object[size];
        E elem = arr[headIndex];
        for (int i = 1; i < size; i++){
            newArr[i - 1] = arr[i];
            // arr[i] = null;
        }
        arr = newArr;
        size--;
        tailIndex--;
        return elem;
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        E elem = arr[size - 1];
        arr[size - 1] = null;
        size--;
        tailIndex--;
        return elem;
    }

    @Override
    public E getFirst() {
        return arr[headIndex];
    }

    @Override
    public E getLast() {
        return arr[tailIndex];
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
        resize();
        arr[size] = e;
        tailIndex++;
        size++;
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
