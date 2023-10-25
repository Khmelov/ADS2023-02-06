package by.it.group251004.asepkov.lesson10;

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
        public Node(E data) { this.data = data; }
    }
    private boolean isInvalidType(Object o) {
        return o == null;
    }
    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }
    private Node<E> getNode(int index) {
        Node<E> current = first;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current;
    }
    public E remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        E result;
        if (index == 0)
            result = removeFirst();
        else if (index == --size)
            result = removeLast();
        else {
            Node<E> current = getNode(index);
            result = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            Node<E> current = first;

            result.append(current.data);
            current = current.next;

            for (int i = 1; i < size; i++) {
                result.append(", ").append(current.data);
                current = current.next;
            }
        }

        result.append("]");
        return result.toString();
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
            throw new java.util.NoSuchElementException("The deque is empty");


        return pollFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The deque is empty");

        return pollLast();
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
            return null;

        E result = first.data;
        if (--size == 0) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        return result;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;

        E result = last.data;
        if (--size == 0) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        return result;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The deque is empty");

        return first.data;
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The deque is empty");

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
    public boolean remove(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Element cannot be null");

        Node<E> current = first;
        while (current != null) {
            if (o.equals(current.data)) {
                if (current == first)
                    removeFirst();
                else if (current == last)
                    removeLast();
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
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
