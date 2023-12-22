package by.it.group251003.nasevich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements Deque<E> {

    private E[] array;

    private int size = 0;

    private int capacity = 0;

    public MyArrayDeque(){
        array = (E[])new Object[16];
        capacity = 16;
    }

    public MyArrayDeque(int capacity){
        array = (E[])new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean add(E element){

        size++;

        if (size == array.length){
            E[] tempArr = (E[])new Object[size * 2];

            for (int i = 0; i < size; i++) {
                tempArr[i] = array[i];
            }

            array = tempArr;
        }

        array[size - 1] = element;

        return true;
    }

    @Override
    public void addFirst(E element){

        size++;

        if (size == array.length){
            E[] tempArr = (E[])new Object[size * 2];

            for (int i = 0; i < size; i++) {
                tempArr[i] = array[i];
            }

            array = tempArr;
        }

        for (int i = size - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = element;

    }

    @Override
    public void addLast(E element){
        add(element);
    }

    @Override
    public E poll(){
        if (size == 0)
            return null;
        else{

            E item = array[0];

            for (int i = 0; i < size - 1; i++){
                array[i] = array[i + 1];
            }

            size--;

            return item;
        }
    }

    @Override
    public E element() {

        if(size == 0){
            throw new NoSuchElementException();
        }

        return array[0];
    }

    @Override
    public E pollFirst(){
        return poll();
    }

    @Override
    public E pollLast(){
        if (size == 0)
            return null;
        else{

            E item = array[size - 1];

            array[size - 1] = null;

            size--;

            return item;
        }
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
    public E getFirst(){
        return array[0];
    }

    @Override
    public E getLast(){
        return array[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {

        String str = "[";

        str += array[0];

        for (int i = 1; i < size; i++) {
            str += ", " + array[i];
        }

        str += "]";

        return str;
    }


    //Необязательные к реализации методы
    @Override
    public boolean offerFirst(E element){

        return false;
    }

    @Override
    public boolean offerLast(E element){

        return false;
    }

    @Override
    public E removeFirst(){

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

    @Override
    public E removeLast(){
        return null;
    }

    @Override
    public E peekFirst(){
        return null;
    }

    @Override
    public E peekLast(){
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o){
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o){
        return false;
    }

    @Override
    public boolean offer(E e){
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

}
