package by.it.group251001.mikhei.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] data = (E[]) new Object[0];
    private int beginIndex = 0, endIndex = 0;

    @Override
    public String toString(){
        if(beginIndex == endIndex) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for(int i = beginIndex; i + 1 < endIndex; i++){
            sb.append(data[i]);
            sb.append(", ");
        }
        sb.append(data[endIndex - 1]);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int size() {
        return endIndex - beginIndex;
    }

    private void growIfNeeded(int newIndex){
        if(newIndex >= 0 && newIndex < data.length) return;

        E[] newData = (E[]) new Object[data.length * 2 + 8];

        int mid = newData.length / 2;
        int shift = mid - beginIndex;

        System.arraycopy(data, 0, newData, shift, data.length);
        data = newData;
        beginIndex += shift;
        endIndex += shift;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        growIfNeeded(beginIndex - 1);

        data[--beginIndex] = e;
    }

    @Override
    public void addLast(E e) {
       growIfNeeded(endIndex);

       data[endIndex++] = e;
    }


    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        return data[beginIndex];
    }

    @Override
    public E getLast() {
        return data[endIndex - 1];
    }


    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if(beginIndex == endIndex) return null;

        E elem = data[beginIndex];

        data[beginIndex++] = null;

        return elem;
    }

    @Override
    public E pollLast() {
        if(beginIndex == endIndex) return null;
        E elem = data[--endIndex];

        data[endIndex] = null;

        return elem;
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
