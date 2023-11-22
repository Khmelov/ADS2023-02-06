package by.it.group251002.voevoda.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    private Node<E> head, tail;
    private int size;
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        if (size > 0) {
            sb.append(head.value);
        }

        for (Node<E> ptr = head.next; ptr != null; ptr = ptr.next) {
            sb.append(", ");
            sb.append(ptr.value);
        }

        sb.append(']');

        return sb.toString();
    }

    @Override
    public boolean add(E e) {

        Node<E> second = new Node<>(e);
        if (size == 0) {
            head = second;
            tail = second;
        } else {
            tail.next = second;
            second.prev = tail;
            tail = tail.next;
        }

        ++size;
        return true;
    }

    private E rm(Node<E> ptr) {
        if (ptr.prev == null && ptr.next == null) {
            head = null;
            tail = null;
        } else if (ptr.prev == null) {
            head = head.next;
            head.prev = null;
        } else if (ptr.next == null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
        }
        return ptr.value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<E> ptr = head;
        for (int i = 0; i < index; ++i) {
            ptr = ptr.next;
        }

        --size;
        return rm(ptr);
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> ptr = head; ptr != null; ptr = ptr.next) {
            if (ptr.value.equals(o)) {
                --size;
                rm(ptr);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E e) {
        Node<E> second = new Node<>(e);
        if (size == 0) {
            head = second;
            tail = second;
        } else {
            head.prev = second;
            second.next = head;
            head = head.prev;
        }

        ++size;
    }

    @Override
    public void addLast(E e) {
        Node<E> second = new Node<>(e);
        if (size == 0) {
            head = second;
            tail = second;
        } else {
            tail.next = second;
            second.prev = tail;
            tail = tail.next;
        }

        ++size;
    }

    @Override
    public E element() {
        if (size == 0) {
            try {
                throw new Exception("Exception: deque is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return head.value;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if (size == 0) {
            try {
                throw new Exception("Exception: deque is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return tail.value;
    }

    @Override
    public E poll() {
        E res = head.value;
        head = head.next;
        head.prev = null;
        --size;
        return res;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        E res = tail.value;
        tail = tail.prev;
        tail.next = null;
        --size;
        return res;
    }

    /////////////////////////////////////////////////////////////////////////
    //////              Необязательные к реализации методы            ///////
    /////////////////////////////////////////////////////////////////////////


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
