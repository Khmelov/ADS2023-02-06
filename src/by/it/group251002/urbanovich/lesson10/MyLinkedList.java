package by.it.group251002.urbanovich.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {
    private int size = 0;

    static private class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(E _newValue, Node<E> next, Node<E> prev) {
            value = _newValue;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head, tail;

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        Node<E> currNode = head;
        while (currNode != null) {
            builder.append(delimiter).append(currNode.value);
            delimiter = ", ";
            currNode = currNode.next;
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int size() {
        return size;
    }

    private E deleteNode(Node<E> nodeToDelete) {
        E value = nodeToDelete.value;
        Node<E> next = nodeToDelete.next;
        Node<E> prev = nodeToDelete.prev;
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            nodeToDelete.next = null;
        }
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            nodeToDelete.prev = null;
        }
        size--;
        return value;
    }

    public E remove(int index) {
        Node<E> targetNode = head;
        for (int i = 0; i != index && i < size; i++)
            targetNode = targetNode.next;
        return deleteNode(targetNode);
    }

    @Override
    public boolean remove(Object o) {

        for (Node<E> currNode = head; currNode != null; currNode = currNode.next) {
            if (currNode.value.equals(o)) {
                deleteNode(currNode);
                return true;
            }
        }

        return false;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, head, null);
        Node<E> temp = head;
        head = newNode;
        if (temp == null)
            tail = newNode;
        else
            temp.prev = newNode;
        size++;
    }

    public boolean add(E e) {
        addLast(e);
        return true;

    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null, tail);
        Node<E> temp = tail;
        tail = newNode;
        if (temp == null)
            head = newNode;
        else
            temp.next = newNode;
        size++;
    }

    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        return head.value;
    }

    @Override
    public E getLast() {
        return tail.value;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        E value = head.value;
        Node<E> temp = head.next;
        head.next = null;
        head.value = null;
        head = temp;
        if (temp == null)
            tail = null;
        else {
            head.prev = null;
        }
        size--;
        return value;
    }

    @Override
    public E pollLast() {
        E value = tail.value;
        Node<E> temp = tail.prev;
        tail.prev = null;
        tail.value = null;
        tail = temp;
        if (temp == null)
            head = null;
        else {
            tail.next = null;
        }
        size--;
        return value;
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
