package by.it.group251003.snopko.lesson10;

import java.util.*;

class Node<E>{
    E value;
    Node <E> next;
    Node <E> prev;

    public Node(E e){
        value = e;
        next = null;
        prev = null;
    }
}

public class MyLinkedList <E> implements Deque<E> {
    private Node<E> tail;
    private Node<E> head;
    private int size;

    MyLinkedList (){
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public String toString(){
        Node<E> Iter = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimeter = "";
        while(Iter != null){
            sb.append(delimeter).append(Iter.value);
            delimeter = ", ";
            Iter = Iter.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        Node<E> newVal = new Node<>(e);
        if (head == null){
            head = newVal;
            tail = newVal;
        } else {
            newVal.prev = tail;
            tail.next = newVal;
            tail = newVal;
        }
        size++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        Node<E> Iter = head;
        while (Iter != null){
            if(Objects.equals(o, Iter.value)){
                if (Iter.next != null){
                    Iter.next.prev = Iter.prev;
                } else {
                    tail = Iter.prev;
                }
                if (Iter.prev != null){
                    Iter.prev.next = Iter.next;
                } else {
                    head = Iter.next;
                }
                size--;
                return true;
            }
            Iter = Iter.next;
        }
        return false;
    }

   public E remove (int index){
        Node<E> Iter = head;
        E result = null;
        int counter = 0;
        while (counter < size){
            if (counter == index){
                result = Iter.value;
                if (Iter.next != null){
                    Iter.next.prev = Iter.prev;
                } else {
                    tail = Iter.prev;
                }
                if (Iter.prev != null){
                    Iter.prev.next = Iter.next;
                } else {
                    head = Iter.next;
                }
                size--;
            }
            Iter = Iter.next;
            counter++;
        }
        return result;
   }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void addFirst(E e) {
        Node<E> newVal = new Node<>(e);
        if (head == null){
            head = newVal;
            tail = newVal;
        } else {
            newVal.next = head;
            head.prev = newVal;
            head = newVal;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E getFirst() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.value;
    }

    @Override
    public E getLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.value;
    }


    @Override
    public E element() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.value;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        Node<E> result = head;
        head = head.next;
        if (head != null){
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return result.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        Node<E> result = tail;
        tail = tail.prev;
        if (tail != null){
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return result.value;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    ////////////////////////////////////////////////////
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
    public E remove() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
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
        return size == 0;
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
