package by.it.group251003.stasevich_uriu.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    static class Node<E> {
        public Node<E> next;
        public Node<E> prev;
        public E info;
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        String del = "";

        Node<E> currentN = head;
        while (currentN != tail) {
            s.append(del).append(currentN.info);
            currentN = currentN.next;
            del = ", ";
        }

        return s.append("]").toString();
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            head = new Node<E>();
            tail = new Node<E>();
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.info = e;
            return true;
        }

        tail.info = e;
        Node<E> newN = new Node<E>();
        tail.next = newN;
        newN.prev = tail;
        tail = newN;

        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            head = new Node<E>();
            tail = new Node<E>();
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.info = e;
        } else {
            Node<E> newN = new Node<E>();
            head.prev = newN;
            newN.next = head;
            newN.info = e;

            head = newN;
            size++;
        }
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    public E remove(int index) {
        if (index >= size || index<=0)
            return null;

        if (index == 0) {
            E buf = head.info;
            head = head.next;
            head.prev = null;

            size--;
            return buf;
        }

        Node<E> currentN = head;
        int i = 0;
        while (currentN != tail.prev) {
            if (i == index) {
                E elementDel = currentN.info;

                currentN.next.prev = currentN.prev;
                currentN.prev.next = currentN.next;
                currentN.prev = null;
                currentN.next = null;

                size--;
                return elementDel;
            }

            currentN = currentN.next;
            i++;
        }

        if (i == index) {
            E currentDel = tail.prev.info;
            tail = tail.prev;
            tail.next = null;

            size--;
            return currentDel;
        }
        return null;
    }

    @Override
    public boolean remove(Object e) {
        if (size == 0)
            return false;

        if (e.equals(head.info)) {
            head = head.next;
            head.prev = null;

            size--;
            return true;
        }

        Node<E> currentN = head;
        while (currentN != tail.prev) {
            if (e.equals(currentN.info)) {
                currentN.next.prev = currentN.prev;
                currentN.prev.next = currentN.next;
                currentN=null;
                size--;
                return true;
            }

            currentN = currentN.next;
        }

        if (e.equals(tail.prev.info)) {
            tail = tail.prev;
            tail.next = null;

            size--;
            return true;
        }

        return false;
    }

    @Override
    public E element() {
        if (size == 0)
            return null;
        return head.info;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if (size == 0)
            return null;
        return tail.prev.info;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        E firstElem = head.info;
        head = head.next;
        head.prev = null;

        size--;
        return firstElem;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size == 0)
            return null;

        E lastElem = tail.prev.info;
        tail = tail.prev;
        tail.next = null;

        size--;
        return lastElem;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}

