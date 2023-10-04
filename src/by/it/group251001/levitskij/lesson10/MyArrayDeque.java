package by.it.group251001.levitskij.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque<E>implements Deque<E> {

    private int head = 0;
    private int tail = 0;
    private E []data = (E[])new Object[0];
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = head; i < tail; i++){
            sb.append(data[i]);
            if(i < tail-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public int size(){return tail-head;}

    @Override
    public boolean add(E element){
    addLast(element);
    return true;
    }


    @Override
    public void addFirst(E element){
        if(head == 0) {
            if (tail == data.length) {
                E[] temp = (E[]) new Object[(tail - head) * 3 / 2 + 1];
                System.arraycopy(data, head, temp, head + 1, (tail - head));
                data = temp;
            } else {
                System.arraycopy(data, head, data, head + 1, (tail - head));
            }
            head++;
            tail++;
        }
        data[--head]=element;
    }
    @Override
    public void addLast(E element){
        if(tail == data.length){
            E []temp = (E[])new Object[(tail - head)*3/2+1];
            System.arraycopy(data, head, temp, head, (tail - head));
            data = temp;
        }
        data[tail++]=element;
    }

    @Override
    public E element(){return getFirst();}

    @Override
    public E getFirst(){
        E element = data[head];
        if (element == null)
            throw new NoSuchElementException();
        return element;
    }

    @Override
    public E getLast(){
        E element = data[tail-1];
        if (element == null)
            throw new NoSuchElementException();
        return element;
    }

    @Override
    public E poll(){return pollFirst();}

    @Override
    public E pollFirst(){
        if(head==tail)
            return null;
        E element = data[head];
        data[head++] = null;
        return element;
    }

    @Override
    public E pollLast(){
        if(head==tail)
            return null;
        E element = data[--tail];
        data[tail] = null;
        return element;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
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
}
