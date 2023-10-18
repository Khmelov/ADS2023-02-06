package by.it.group251002.alesina.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;


public class MyLinkedList<E> implements Deque<E> {

    Node first = null;  //начало списка
    Node last = null;  //конец списка
    int size = 0;

    class Node{
        public E data;
        public Node next = null;
        public Node prev = null;

        public Node(E data){
            this.data = data;
        }
    }

    @Override
    public String toString() {
        Node curr = this.first;

        StringBuilder string = new StringBuilder("[");

        while (curr != null){  //проходим по списку, добавляя элементы в строку
            string.append(curr.data);
            curr = curr.next;

            if (curr != null)
                string.append(", ");
        }

        string.append("]");
        return string.toString();
    }


    @Override
    public boolean add(E e) {
        Node newElem = new Node(e);
        size++;
        if (last == null)
            first = newElem;
        else {
            last.next = newElem;
            newElem.prev = last;
        }
        last = newElem;

        return true;
    }

    @Override
    public E poll() {
        if (first == null)
            return null;
        size--;
        E firstEl = first.data;
        first = first.next;
        first.prev = null;
        return firstEl;

    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (first == null)
            return null;

        size--;
        E lastEl = last.data;
        last = last.prev;
        last.next = null;
        return lastEl;
    }

    public E remove(int index) {
        if (index >= size || index<0 || first==null) {
            return null;
        }

        if (index == 0) {
            return pollFirst();
        }

        Node curr = first;
        int i = 0;
        while(curr!= last.prev) {
            if (i == index) {
                size--;
                E elem = curr.data;
                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.next = null;
                curr.prev = null;
                return elem;
            }
            curr = curr.next;
            i++;
        }

        if (index == size-1) {
            return pollLast();
        }

        return null;
    }
    @Override
    public void addFirst(E e) {
        Node newElem = new Node(e);
        size++;
        if (first == null)
            last = newElem;
        else {
            newElem.next = first;
            first.prev = newElem.prev;
        }
        first= newElem;

    }

    @Override
    public void addLast(E e) {
       add(e);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public E getFirst() {

        return element();
    }

    @Override
    public E getLast() {
        if (first==null) {
            return null;
        }
        return last.data;
    }



    @Override
    public E element() {
        if (first==null) {
            return null;
        }
        return first.data;
    }


    @Override
    public boolean remove(Object o) {
        if (first==null) {
            return false;
        }


        Node curr = first;
        while (curr != null){
            if (o.equals(curr.data)){
                if (curr == first){
                    pollFirst();
                } else if (curr== last) {
                    pollLast();
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }

                size--;
                return true;
            }

            curr = curr.next;
        }

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
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
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
