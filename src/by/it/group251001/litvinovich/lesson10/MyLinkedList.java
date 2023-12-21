package by.it.group251001.litvinovich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Deque<E> {

    protected static class Node<E> {
        public E data;
        public Node<E> prev, next;

        public Node(E data) {
            this.data = data;
            prev = next = null;
        }
    }

    private Node<E> head = null, back = null;
    public int size = 0;
    @Override
    public void addFirst(E e) {
        if (size++ == 0) {
            head = back = new Node<E>(e);
            return;
        }
        Node<E> currNode = new Node<E>(e);
        head.prev = currNode;
        currNode.next = head;
        head = currNode;
    }

    @Override
    public void addLast(E e) {
        if (size++ == 0) {
            head = back = new Node<E>(e);
            return;
        }
        Node<E> currNode = new Node<E>(e);
        back.next = currNode;
        currNode.prev = back;
        back = currNode;
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
    public E pollFirst() {
        if (isEmpty())
            return null;
        E toReturn = head.data;
        remove(0);
        return toReturn;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        E toReturn = back.data;
        remove(size - 1);
        return toReturn;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.data;
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return back.data;
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
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        size--;
        Node<E> curentNode = head;
        if (size == 0) {
            head = back = null;
            return curentNode.data;
        }
        if (index < size / 2)
            for (int i = 0; i < index; i++)
                curentNode = curentNode.next;
        else {
            curentNode = back;
            for (int i = 0; i < size - index; i++)
                curentNode = curentNode.prev;
        }
        if (curentNode == head) {
            head.next.prev = null;
            head = head.next;
            return curentNode.data;
        }
        if (curentNode == back) {
            back.prev.next = null;
            back = back.prev;
            return curentNode.data;
        }
        curentNode.prev.next = curentNode.next;
        curentNode.next.prev = curentNode.prev;
        return curentNode.data;
    }
    @Override
    public E remove() {
        return null;
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
        Node<E> currentNode = head;
        while (currentNode != null && !((o == null && currentNode.data == null) || (o != null && o.equals(currentNode.data))))
            currentNode = currentNode.next;
        if (currentNode == null)
            return false;
        size--;
        if (size == 0) {
            head = back = null;
            return true;
        }
        if (currentNode == head) {
            head.next.prev = null;
            head = head.next;
            return true;
        }
        if (currentNode == back) {
            back.prev.next = null;
            back = back.prev;
            return true;
        }
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        return true;
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

    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            res.append(currentNode.data.toString()).append(", ");
            currentNode = currentNode.next;
        }
        return res + currentNode.data.toString() + "]";
    }
}
