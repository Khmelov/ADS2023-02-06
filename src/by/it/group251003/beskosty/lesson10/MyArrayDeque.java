package by.it.group251003.beskosty.lesson10;
import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {
    private E[] MyDeque = (E[]) new Object[0];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        String temp = "";
        for (int i = 0; i < size; i++){
            string.append(temp).append(MyDeque[i]);
            temp = ", ";
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
    public boolean add(E e) {
        if (size == MyDeque.length) {
            E[] newDeque = (E[]) new Object[2 * MyDeque.length + 1];
            System.arraycopy(MyDeque, 0, newDeque, 0, size);
            MyDeque = newDeque;
        }
        MyDeque[size++] = e;
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
    public void addFirst(E e){
        if (size == MyDeque.length) {
            E[] newDeque = (E[]) new Object[2 * MyDeque.length + 1];
            System.arraycopy(MyDeque, 0, newDeque, 0, size);
            MyDeque = newDeque;
        }
        for(int i = size; i > 0; i--){
            MyDeque[i] = MyDeque[i-1];
        }
        MyDeque[0] = e;
        size++;
     }
     @Override
    public void addLast(E e){
         if (size == MyDeque.length) {
             E[] newDeque = (E[]) new Object[2 * MyDeque.length + 1];
             System.arraycopy(MyDeque, 0, newDeque, 0, size);
             MyDeque = newDeque;
         }
         MyDeque[size] = e;
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
    public E element(){
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
    public E getFirst(){
        return MyDeque[0];
     }

    @Override
    public E getLast(){
        return MyDeque[size-1];
    }

    @Override
    public E peekFirst(){
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
    public E poll(){
        return pollFirst();
    }

    @Override
    public E pollFirst(){

        E temp = MyDeque[0];
        for (int i = 0; i < size - 1; i++){
            MyDeque[i] = MyDeque[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public E pollLast(){
        return MyDeque[size-- - 1];
    }
}
