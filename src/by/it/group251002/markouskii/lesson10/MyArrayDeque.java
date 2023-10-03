package by.it.group251002.markouskii.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
public class MyArrayDeque<E> implements Deque<E>{
    private E[] elements = (E[])  new Object[]{};
    private int size = 0;
    private int count =0;
    private int front=0;
    private int back=0;
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String delimiter = "";
        for (int i=front;i!=back;i=(i+1)%size) {
                sb.append(delimiter).append(elements[i]);
                delimiter=", ";
            }
            sb.append(delimiter).append(elements[back]);
            sb.append("]");
        return sb.toString();
        }
    @Override
    public int size(){
        return count;
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

    @Override
    public boolean add(E e){
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
    public void addLast(E e){
        if (count==size){
            Resize(size*3/2+1);
            size=size*3/2+1;
        }
        back=(back+1)%size;
        elements[back]=e;
        count++;
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
        if (count==0)
            return null;
        E temp=elements[front];
        front=(front+1)%size;
        count--;
        return temp;
    }

    @Override
    public E pollLast() {
        if (count==0)
            return null;
        E temp=elements[back];
        back=(back-1+size)%size;
        count--;
        return temp;
    }

    @Override
    public E getFirst() {
        if (count==0)
            return null;
        E temp=elements[front];
        //front=(front+1)%size;
        return temp;
    }

    @Override
    public E getLast() {
        if (count==0)
            return null;
        E temp=elements[back];
        //back=(back-1+size)%size;
        return temp;
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
    public void addFirst(E e){
        if (count==size){
            Resize(size*3/2+1);
            size=size*3/2+1;
        }
        front=(front-1+size)%size;
        elements[front]=e;
        count++;
    }
    public void Resize(int newSize){
        E[] tempArr = (E[]) new Object[newSize];
        int counter=0;
        for (int i=front;counter<count;i++) {
            tempArr[counter++]=elements[i%count];
        }
        elements=tempArr;
        front=0;
        back=count-1;
    }

}

