package by.it.group251003.dedov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    static class Node<E> {
        public Node<E> next;
        public Node<E> prev;
        public E elem;
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        String del = "";

       Node<E> curr = head;
        while (curr != tail) {
            res.append(del).append(curr.elem);
            curr = curr.next;
            del = ", ";
        }

        return res.append("]").toString();
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
            tail.prev.elem = e;
            return true;
        }

        tail.elem = e;
        Node<E> newNode = new Node<E>();
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

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
            tail.prev.elem = e;
            return;
        }

        Node<E> newNode = new Node<E>();
        head.prev = newNode;
        newNode.next = head;
        newNode.elem = e;

        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    public E remove(int index) {
        if (index >= size || size == 0)
            return null;

        if (index == 0) {
            E el = head.elem;
            head = head.next;
            head.prev = null;

            size--;
            return el;
        }

        Node<E> curr = head;
        int j = 0;
        while (curr != tail.prev) {
            if (j == index) {
                E el = curr.elem;

                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.prev = null;
                curr.next = null;

                size--;
                return el;
            }

            curr = curr.next;
            j++;
        }

        if (j == index) {
            E el = tail.prev.elem;
            tail = tail.prev;
            tail.next = null;

            size--;
            return el;
        }
        return null;
    }

    @Override
    public boolean remove(Object e) {
        if (size == 0)
            return false;

        if (e.equals(head.elem)) {
            head = head.next;
            head.prev = null;

            size--;
            return true;
        }

        Node<E> curr = head;
        while (curr != tail.prev) {
            if (e.equals(curr.elem)) {
                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.next = null;
                curr.prev = null;

                size--;
                return true;
            }

            curr = curr.next;
        }

        if (e.equals(tail.prev.elem)) {
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
        return head.elem;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if (size == 0)
            return null;
        return tail.prev.elem;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        E el = head.elem;
        head = head.next;
        head.prev = null;

        size--;
        return el;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size == 0)
            return null;

        E el = tail.prev.elem;
        tail = tail.prev;
        tail.next = null;

        size--;
        return el;
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

