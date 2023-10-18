package by.it.group251002.alesina.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;


public class MyArrayDeque<E> implements Deque<E> {

    private E[] arr = (E[]) new Object[0];
    private int size = 0;
    private void incSize(){
        if (size == arr.length){
            E[] tmpArr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(arr, 0, tmpArr, 0, size);
            arr = tmpArr;
        }
    }


    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[");
        if (size > 0) {
            str.append(arr[0]);

            for (int i = 1; i < size; i++) {
                str.append(", ").append(arr[i]);
            }
        }

        str.append("]");
        return str.toString();
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean add(E e) {
        incSize();

        arr[size++] = e;

        return true;
    }

    @Override
    public void addFirst(E e) {
        incSize();

        System.arraycopy(arr, 0, arr, 1, size);
        arr[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {

        add(e);
    }

    @Override
    public E element() {   //возвращает элемент, стоящий в начале списка
        if (size != 0)
            return arr[0];
        return null;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        return arr[size-1];
    }

    @Override
    public E poll() {  //возвращаетт 1й элемент и удаляет его из массива

        if (size-- != 0) {
            E elem = arr[0];
            System.arraycopy(arr, 1, arr, 0, size);
            arr[size] = null;
            return elem;
        }
        else
            return null;
    }


    @Override
    public E pollFirst() {
       return poll();

    }

    @Override
    public E pollLast() {
//
        return arr[size-- - 1];
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
