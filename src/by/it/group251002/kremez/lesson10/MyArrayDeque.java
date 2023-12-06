package by.it.group251002.kremez.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
public class MyArrayDeque<E> implements Deque<E>{
    E[] values = (E[]) new Object[]{};
    int size = 0;

    public void clear(){

    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public Object[] toArray(){
        return null;
    }

    public boolean containsAll(Collection <?> c){
        return false;
    }

    public boolean retainAll(Collection <?> c){
        return false;
    }

    public boolean removeAll(Collection <?> c){
        return false;
    }

    public boolean offerFirst(E elem) {
        return false;
    }

    public boolean offer(E elem) {
        return false;
    }

    public boolean offerLast(E elem) {
        return false;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public E remove() {
        return poll();
    }

    public boolean remove(Object c) {
        return false;
    }

    public String toString(){
        String s = "[";
        for(int i = 0; i < size; ++i) {
            s = s + values[i].toString();
            if(i != size - 1)
                s = s + ", ";
        }
        s = s + "]";
        return  s;
    }
    public int size(){
        return size;
    }

    public boolean add(E element){
        if(size == values.length)
            values = Arrays.copyOf(values, size * 3 / 2 + 1);
        values[size++] = element;
        return true;
    }

    public void push(E element){
        add(element);
    }

    public void addFirst(E element){
        add(element);
        for(int i = size - 1; i > 0; --i){
            E temp = values[i];
            values[i] = values[i - 1];
            values[i - 1] = temp;
        }
    }
    public void addLast(E element){
        add(element);
    }

    public E element(){
        return getFirst();
    }

    public E peek(){
        return getFirst();
    }

    public E getFirst(){
        return values[0];
    }

    public E peekFirst(){
        return values[0];
    }

    public E getLast(){
        return values[size - 1];
    }

    public E peekLast(){
        return values[size - 1];
    }

    public E poll(){
        return pollFirst();
    }
    public E pollFirst(){
        for(int i = 0; i < size - 1; ++i){
            E temp = values[i];
            values[i] = values[i + 1];
            values[i + 1] = temp;
        }
        return pollLast();
    }

    public E removeFirst(){
        return pollFirst();
    }

    public E pollLast(){
        if(size > 0)
            return values[(size--) - 1];
        else
            return null;
    }

    public E pop(){
        return poll();
    }

    public E removeLast(){
        return poll();
    }

    public boolean removeFirstOccurrence(Object c){
        return  false;
    }

    public boolean removeLastOccurrence(Object c){
        return  false;
    }

    public boolean addAll(Collection <? extends E> c){
        return false;
    }

    public boolean contains(Object c){
        return  false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Iterator<E> descendingIterator() {
        return null;
    }
}
