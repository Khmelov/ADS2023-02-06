package by.it.group251002.baranovskaia.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {

    private Node<E> firstNode, lastNode;
    private int size;

    public MyLinkedList(){
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    private static class Node<E> {
        final E data;
        Node<E> prev = null;
        Node<E> next = null;
        public Node(E data) {
            this.data = data;
        }
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private Node<E> getNode(int index) {
        Node<E> currentNode = firstNode;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;

        return currentNode;
    }

    public E remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Exception: out of bounds");
        if (index == 0)
            return removeFirst();
        else if (index == --size)
            return removeLast();
        else {
            Node<E> currentNode = getNode(index);
            E removed = currentNode.data;
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            return removed;
        }
    }
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        if (size > 0) {
            Node<E> currentNode = firstNode;

            SB.append(currentNode.data);
            currentNode = currentNode.next;

            for (int i = 1; i < size; i++) {
                SB.append(", ").append(currentNode.data);
                currentNode = currentNode.next;
            }
        }

        SB.append("]");
        return SB.toString();
    }

    @Override
    public void addFirst(E e) {
        if (e == null)
            throw new IllegalArgumentException("Exception: null argument");

        Node<E> newNode = new Node<E>(e);
        if (isEmpty())
            lastNode = newNode;
        else {
            newNode.next = firstNode;
            firstNode.prev = newNode;
        }
        firstNode = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (e == null)
            throw new IllegalArgumentException("Exception: null argument");

        Node<E> newNode = new Node<E>(e);
        if (isEmpty())
            firstNode = newNode;
        else {
            newNode.prev = lastNode;
            lastNode.next = newNode;
        }
        lastNode = newNode;
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
            throw new java.util.NoSuchElementException("Exception: empty list");

        return pollFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty list");

        return pollLast();
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
            return null;

        E polled = firstNode.data;
        if (--size == 0) {
            firstNode = null;
            lastNode = null;
        }
        else {
            firstNode = firstNode.next;
            firstNode.prev = null;
        }
        return polled;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;

        E polled = lastNode.data;
        if (--size == 0) {
            firstNode = null;
            lastNode = null;
        }
        else {
            lastNode = lastNode.prev;
            lastNode.next = null;
        }
        return polled;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty list");

        return firstNode.data;
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty list");

        return lastNode.data;
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
    public boolean remove(Object o) {
        if (o == null)
            throw new IllegalArgumentException("Exception: null argument");

        Node<E> cur = firstNode;
        while (cur != null) {
            if (o.equals(cur.data)) {
                if (cur == firstNode)
                    removeFirst();
                else if (cur == lastNode)
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
    public boolean contains(Object o) {
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
