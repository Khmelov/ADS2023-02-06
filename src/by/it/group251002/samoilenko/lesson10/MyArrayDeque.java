package by.it.group251002.samoilenko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] deque;
    private int size;
    private int capacity;

    MyArrayDeque(){
        size=0;
        capacity=10;
        deque=(E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        String res=new String();
        res+="[";
        for(int i=0;i<size-1;i++)
            res=res+this.deque[i]+", ";
        res+=this.deque[size-1]+"]";
        return res;
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
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(size==capacity){
            capacity=(int)(capacity*1.5);
            E[] deque=(E[]) new Object[capacity];
            System.arraycopy(this.deque,0,deque,0,size);
            this.deque=deque;
        }
        this.deque[size++] = e;
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
    public void addFirst(E e) {
        if(size==capacity){
            capacity=(int)(capacity*1.5);
            E[] deque=(E[]) new Object[capacity];
            System.arraycopy(this.deque,0,deque,0,size);
            this.deque=deque;
        }
        System.arraycopy(this.deque,0,this.deque,1,size);
        this.deque[0]=e;
        size++;
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
    public E element() {
        return deque[0];
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
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
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E getFirst() {
        return deque[0];
    }

    @Override
    public E getLast() {
        return deque[size-1];
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
    public E poll() {
        if(size==0)
            return null;
        else {
            E el=deque[0];
            System.arraycopy(deque,1,deque,0,size--);
            deque[size]=null;
            return el;
        }
    }

    @Override
    public E pollFirst() {
        if(size==0)
            return null;
        else {
            E el=deque[0];
            System.arraycopy(deque,1,deque,0,size--);
            deque[size]=null;
            return el;
        }
    }

    @Override
    public E pollLast() {
        if(size==0)
            return null;
        else {
            E el=deque[--size];
            deque[size]=null;
            return el;
        }
    }
}
