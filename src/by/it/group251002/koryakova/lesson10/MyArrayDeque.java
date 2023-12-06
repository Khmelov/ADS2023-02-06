package by.it.group251002.koryakova.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Deque;

public class MyArrayDeque<E> implements Deque<E>{
    private E[] deque;
    private int size;
    private int capacity;

    MyArrayDeque(){
        size = 0;
        capacity = 10;
        deque = (E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        String rez = new String();
        rez = rez + "[";
        int i;
        for(i = 0; i < (size - 1); i++){
            rez = rez + this.deque[i] + ", ";
        }
        rez = rez + this.deque[size - 1] + "]";
        return rez;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        if(size == capacity){
            capacity = (int)(capacity*1.5);
            E[] deque = (E[]) new Object[capacity];
            System.arraycopy(this.deque, 0, deque, 0, size);
            this.deque = deque;
        }
        this.deque[size] = element;
        size = size + 1;
        return true;
    }

    @Override
    public void addFirst(E element){
        if(size == capacity){
            capacity = (int)(capacity*1.5);
            E[] deque = (E[]) new Object[capacity];
            System.arraycopy(this.deque, 0, deque, 0, size);
            this.deque = deque;
        }
        System.arraycopy(this.deque, 0, this.deque, 1, size);
        this.deque[0] = element;
        size = size + 1;
    }

    @Override
    public void addLast(E element){
        add(element);
    }

    @Override
    public E element(){
        return deque[0];
    }

    @Override
    public E getFirst(){
        return deque[0];
    }

    @Override
    public E getLast(){
        return deque[size - 1];
    }

    @Override
    public E poll(){
        if(size == 0){
            return null;
        }
        else{
            E element = deque[0];
            System.arraycopy(deque, 1, deque, 0, size);
            size = size - 1;
            deque[size] = null;
            return element;
        }
    }

    @Override
    public E pollFirst(){
        if(size == 0){
            return null;
        }
        else{
            E element = deque[0];
            System.arraycopy(deque, 1, deque, 0, size);
            size = size - 1;
            deque[size] = null;
            return element;
        }
    }

    @Override
    public E pollLast(){
        if(size == 0){
            return null;
        }
        else{
            size = size - 1;
            E element = deque[size];
            deque[size] = null;
            return element;
        }
    }
    // not realized
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
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
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


}

