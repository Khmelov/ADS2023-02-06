package by.it.group251002.filistovich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] elements;
    private int size;

    MyArrayDeque(){
        size = 0;
        elements= (E[]) new Object[]{};
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++){
            sb.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void addFirst(E e) {
        if (size == elements.length){
            E[] newArr = (E[]) new Object[(size * 3 )/ 2 + 1];
            System.arraycopy(elements, 0,newArr,0,size);
            elements = newArr;
        }
        System.arraycopy(elements, 0,elements,1,size);
        elements[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == elements.length){
            E[] newArr = (E[]) new Object[(size * 3 )/ 2 + 1];
            System.arraycopy(elements, 0,newArr,0,size);
            elements = newArr;
        }
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

        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        E temp = getFirst();


        System.arraycopy(elements, 1, elements, 0, --size);

        return temp;
    }

    @Override
    public E pollLast() {
        E temp = getLast();
        size--;
        return temp;
    }

    @Override
    public E getFirst() {

        return element();
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
        addLast(e);
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
