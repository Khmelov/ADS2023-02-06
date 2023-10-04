package by.it.group251002.punko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList <E> implements Deque<E> {
    private static class Node<E> {
        public final E data;
        public Node<E> prev = null;
        public Node<E> next = null;
        public Node(E data) { this.data = data; }
    }
    private Node<E> start = null;
    private Node<E> end = null;
    private int size = 0;

    private boolean checkType(Object o) {
        return o == null;
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    public int size() {
        return size;
    }
    @Override
    public String toString() {
        String res = "[";
        Node<E> el = start;
        while (el != null) {
            res += el.data.toString();
            if (el.next != null) res += ", ";
            el = el.next;
        }
        return res + "]";
    }
    public E remove(int index) {
        if (checkIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        E result = null;
        int count = 0;
        Node<E> el = start;
        while (el != null) {
            if (count == index) {
                size--;
                el.prev.next = el.next;
                el.next.prev = el.prev;
                result = el.data;
                break;
            }
            el = el.next;
            count++;
        }
        return result;
    }
    @Override
    public boolean remove(Object o) {
        if (checkType(o))
            throw new IllegalArgumentException("Element cannot be null");
        if (size == 0) return false;
        boolean isChanged = false;
        Node<E> el = start;
        while (el != null) {
            if (o.equals(el.data)) {
                size--;
                el.prev.next = el.next;
                el.next.prev = el.prev;
                isChanged = true;
                break;
            }
            el = el.next;
        }
        return isChanged;
    }
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }
    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e);
        if (size == 0) {
            end = newNode;
            start = newNode;
        } else {
            start.prev = newNode;
            start.prev.next = start;
            start = start.prev;
        }
        size++;
    }
    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e);
        if (size == 0) {
            end = newNode;
            start = newNode;
        } else {
            end.next = newNode;
            end.next.prev = end;
            end = end.next;
        }
        size++;
    }
    @Override
    public E element() {
        return getFirst();
    }
    @Override
    public E getFirst() {
        return start.data;
    }

    @Override
    public E getLast() {
        return end.data;
    }
    @Override
    public E poll() {
        return pollFirst();
    }
    @Override
    public E pollFirst() {
        if (size == 0)
            return null;

        size--;
        E el = start.data;

        if (size == 0) {
            end = null;
            start = null;
        } else {
            start = start.next;
            start.prev = null;
        }

        return el;
    }
    @Override
    public E pollLast() {
        if (size == 0)
            return null;

        size--;
        E el = end.data;

        if (size == 0) {
            end = null;
            start = null;
        } else {
            end = end.prev;
            end.next = null;
        }
        return el;
    }












    @Override
    public E remove() {
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
