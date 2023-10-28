package by.it.group251002.belyatskiy.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {

    private Node<E> first = null;

    private Node<E> last = null;

    private int size = 0;

    private static class Node<E> {
        public final E data;
        public Node<E> prev = null;
        public Node<E> next = null;
        public Node(E data) {
            this.data = data;
        }
    }
    private boolean isInvalidType(Object Obj) {
        return Obj == null;
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private Node<E> getNode(int index) {
        Node<E> cur = first;
        for (int i = 0; i < index; i++)
            cur = cur.next;

        return cur;
    }

    public E remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        E res;
        if (index == 0)
            res = removeFirst();
        else if (index == --size)
            res = removeLast();
        else {
            Node<E> cur = getNode(index);
            res = cur.data;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        return res;
    }
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        if (size > 0) {
            Node<E> cur = first;

            SB.append(cur.data);
            cur = cur.next;

            for (int i = 1; i < size; i++) {
                SB.append(", ").append(cur.data);
                cur = cur.next;
            }
        }

        SB.append("]");
        return SB.toString();
    }

    @Override
    public void addFirst(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        Node<E> newNode = new Node<E>(e);
        if (isEmpty())
            last = newNode;
        else {
            newNode.next = first;
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        Node<E> newNode = new Node<E>(e);
        if (isEmpty())
            first = newNode;
        else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
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
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return pollFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return pollLast();
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
            return null;

        E polled = first.data;
        if (--size == 0) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        return polled;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;

        E polled = last.data;
        if (--size == 0) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        return polled;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return first.data;
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty");

        return last.data;
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
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
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
    public boolean remove(Object Obj) {
        if (isInvalidType(Obj))
            throw new IllegalArgumentException("Element cannot be null");

        Node<E> cur = first;
        while (cur != null) {
            if (Obj.equals(cur.data)) {
                if (cur == first)
                    removeFirst();
                else if (cur == last)
                    removeLast();
                else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                    size--;
                }
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object Obj) {
        return false;
    }

    @Override
    public int size() {
        return size;
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

