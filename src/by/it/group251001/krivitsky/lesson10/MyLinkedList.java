package by.it.group251001.krivitsky.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;


/*
                remove(E element)
*/

public class MyLinkedList<E> implements Deque<E> {

    private E[] elements = (E[]) new Object[1];
    private int size = 0;

    private void posincsize() {
        if (size == elements.length) {
            E[] newarr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
    }

    public E remove(int index){
        E temp = elements[index];
        System.arraycopy(elements, index+1, elements, index, size - index + 1);
        size--;
        return temp;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++){
            sb.append(elements[i]).append(", ");
        }
        if (size > 0){
            sb.append(elements[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public void addFirst(E e) {
        posincsize();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        posincsize();
        elements[size] = e;
        size++;
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
        return pollFirst();
    }

    @Override
    public E removeLast() {
        return pollLast();
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        size--;
        return elements[size];
    }

    @Override
    public E getFirst() {
        return elements[0];
    }

    @Override
    public E getLast() {
        return elements[size - 1];
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
        posincsize();
        elements[size] = e;
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
        E temp = elements[0];
        size--;
        System.arraycopy(elements, 1, elements, 0, size);
        return temp;
    }

    @Override
    public E element() {
        return elements[0];
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

    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i<size; i++){
            if (elements[i].equals(o)){
                index = i;
                System.arraycopy(elements, index+1, elements, index, size-index+1);
                size--;
                return true;
            }
        }
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
