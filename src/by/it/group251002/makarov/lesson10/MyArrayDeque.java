package by.it.group251002.makarov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;



public class MyArrayDeque<E> implements Deque<E> {

    private int size = 0;
    private E[] array = (E[])new Object[]{};



    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String del = ", ";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) del = "]";
        sb.append(array[i]).append(del);
        }
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (array.length==size){
            E[] newElem = (E[])new Object[(size*3/2+1)];
            System.arraycopy(array,0,newElem,0,size);
            array=newElem;
        }
        array[size++]=e;
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (array.length==size){
            E[] newElem = (E[]) new Object[(size*3/2+1)];
            System.arraycopy(array,0,newElem,0,size);
            array = newElem;
        }
        System.arraycopy(array,0,array,1,size);
        array[0]=e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (array.length==size){
            E[] newElem = (E[])new Object[(size*3/2+1)];
            System.arraycopy(array,0,newElem,0,size);
            array=newElem;
        }
        array[size++]=e;

    }


    public E remove(E[] arr,int index) {
        E m = array[index];
        System.arraycopy(array,index+1,array,index,size-1-index);
        array[--size]=null;
        return m;
    }

    @Override
    public E poll() {
        if (size!=0){
            E e = array[0];
            remove(array,0);
            return e;
        }else return null;
    }


    @Override
    public E pollFirst() {
        if (size!=0){
            E e = array[0];
            remove(array,0);
            return e;
        }else return null;
    }

    @Override
    public E pollLast() {
        if (size!=0){
            E e = array[size-1];
            remove (array,size-1);
            return e;
        }else
        return null;
    }

    @Override
    public E element() {
        if (array!=null)
        return array[0];
        else return null;
    }

    @Override
    public E getFirst() {
        if (size!=0)
            return array[0];
        else return null;
    }

    @Override
    public E getLast() {
        if (size!=0)
        return array[size-1];
        else return null;
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
