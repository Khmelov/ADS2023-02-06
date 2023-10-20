package by.it.group251001.mikhei.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<E> implements Deque<E> {
    private final Node pFirst = new Node();
    private final Node aLast = new Node();
    private int size = 0;

    {
        pFirst.next = aLast;
        aLast.prev = pFirst;
    }

    private class Node {
        private Node prev, next;
        private E e;
    }

    public E remove(int index) {
        Node cur = pFirst.next;
        int i = 0;

        while (i++ < index) cur = cur.next;

        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;

        size--;

        return cur.e;
    }

    @Override
    public String toString() {
        if (pFirst.next == aLast) return "[]";

        StringBuilder sb = new StringBuilder("[");

        Node cur = pFirst.next;

        while (cur != aLast) {
            sb.append(cur.e);
            cur = cur.next;
            sb.append(cur == aLast ? "]" : ", ");
        }
        return sb.toString();
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

    @Override
    public boolean add(E e) {
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
    public void addFirst(E e) {
        Node node = new Node();

        node.e = e;
        node.prev = pFirst;
        node.next = pFirst.next;
        pFirst.next.prev = node;

        pFirst.next = node;

        size++;
    }

    @Override
    public void addLast(E e) {
        Node node = new Node();

        node.e = e;
        node.next = aLast;
        node.prev = aLast.prev;
        aLast.prev.next = node;

        aLast.prev = node;

        size++;
    }


    @Override
    public boolean remove(Object o) {
        Node cur = pFirst.next;

        while (cur != aLast) {
            if (Objects.equals(o, cur.e)) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;

                size--;
                return true;
            }

            cur = cur.next;
        }

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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E getFirst() {
        return pFirst.next.e;
    }

    @Override
    public E getLast() {
        return aLast.prev.e;
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
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (pFirst.next == aLast) return null;

        size--;

        E elem = pFirst.next.e;

        pFirst.next.next.prev = pFirst;
        pFirst.next = pFirst.next.next;

        return elem;
    }

    @Override
    public E pollLast() {
        if (pFirst.next == aLast) return null;

        size--;

        E elem = aLast.prev.e;

        aLast.prev.prev.next = aLast;
        aLast.prev = aLast.prev.prev;

        return elem;
    }

}
