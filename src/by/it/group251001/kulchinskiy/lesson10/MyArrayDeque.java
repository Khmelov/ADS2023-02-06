package by.it.group251001.kulchinskiy.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    private static class DequeNode<E> {
        E value;
        DequeNode<E> next;
        DequeNode<E> prev;

        public DequeNode(E value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }

    private DequeNode<E> m_head;
    private DequeNode<E> m_tail;
    private int m_size;

    public MyArrayDeque() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public String toString() {
        if (m_size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        var current = m_head;

        while (current != null) {
            stringBuilder.append(current.value);

            if (current.next == null) {
                return stringBuilder.append("]").toString();
            }

            stringBuilder.append(", ");

            current = current.next;
        }

        return "";
    }

    @Override
    public void addFirst(E e) {
        m_size++;

        if (m_head == null) {
            m_head = new DequeNode<E>(e);
            m_tail = m_head;
            return;
        }

        var newNode = new DequeNode<E>(e);
        newNode.next = m_head;
        m_head.prev = newNode;
        m_head = newNode;
    }

    @Override
    public void addLast(E e) {
        m_size++;

        if (m_head == null) {
            m_head = new DequeNode<E>(e);
            m_tail = m_head;
            return;
        }

        var newNode = new DequeNode<E>(e);
        newNode.prev = m_tail;
        m_tail.next = newNode;
        m_tail = newNode;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (m_head == null) {
            return null;
        }

        m_size--;

        var result = m_head.value;
        m_head = m_head.next;

        if (m_head != null) {
            m_head.prev = null;
        }

        return result;
    }

    @Override
    public E removeLast() {
        if (m_head == null) {
            return null;
        }

        m_size--;

        var result = m_tail.value;
        m_tail = m_tail.prev;

        if (m_tail != null) {
            m_tail.next = null;
        }

        return result;
    }

    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public E getFirst() {
        return m_head == null ? null : m_head.value;
    }

    @Override
    public E getLast() {
        return m_tail == null ? null : m_tail.value;
    }

    @Override
    public E peekFirst() {
        return getFirst();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        var current = m_tail;

        while (current != null) {
            if (current.value.equals(o)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    m_head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    m_tail = current.prev;
                }

                m_size--;
                return true;
            }

            current = current.prev;
        }

        return false;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return getFirst();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object o : c) {
            add((E) o);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        var current = m_head;
        while (current != null) {
            if (c.contains(current.value)) {
                current = current.next;
            } else {
                var next = current.next;
                var prev = current.prev;

                if (next != null) {
                    next.prev = prev;
                } else {
                    m_tail = prev;
                }

                if (prev != null) {
                    prev.next = next;
                } else {
                    m_head = next;
                }

                m_size--;

                current = next;
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        var current = m_head;

        while (current != null) {
            if (current.value.equals(o)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    m_head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    m_tail = current.prev;
                }

                m_size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {
        var current = m_head;

        while (current != null) {
            if (current.value.equals(o)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return m_head == null;
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
