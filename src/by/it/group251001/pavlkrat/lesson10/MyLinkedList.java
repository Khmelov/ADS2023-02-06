package by.it.group251001.pavlkrat.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Deque<E> {
    protected static class Node<E> {
        public E data;
        public Node<E> prev, next;

        public Node() {

        }

        public Node(E data) {
            this.data = data;
            prev = next = null;
        }
    }

    private Node<E> head = null, back = null;
    public int siz = 0;

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Node<E> cur = head;
        while (cur.next != null) {
            res.append(cur.data.toString()).append(", ");
            cur = cur.next;
        }
        return res + cur.data.toString() + "]";
    }

    @Override
    public void addFirst(E e) {
        if (siz++ == 0) {
            head = back = new Node<E>(e);
            return;
        }
        Node<E> cur = new Node<E>(e);
        head.prev = cur;
        cur.next = head;
        head = cur;
    }

    @Override
    public void addLast(E e) {
        if (siz++ == 0) {
            head = back = new Node<E>(e);
            return;
        }
        Node<E> cur = new Node<E>(e);
        back.next = cur;
        cur.prev = back;
        back = cur;
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

    public E remove(int index) {
        if (index < 0 || index >= siz)
            throw new IndexOutOfBoundsException();
        siz--;
        Node<E> cur = head;
        if (siz == 0) {
            head = back = null;
            return cur.data;
        }
        if (index < siz / 2)
            for (int i = 0; i < index; i++)
                cur = cur.next;
        else {
            cur = back;
            for (int i = 0; i < siz - index; i++)
                cur = cur.prev;
        }
        if (cur == head) {
            head.next.prev = null;
            head = head.next;
            return cur.data;
        }
        if (cur == back) {
            back.prev.next = null;
            back = back.prev;
            return cur.data;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        return cur.data;
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
            return null;
        E toRet = head.data;
        remove(0);
        return toRet;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        E toRet = back.data;
        remove(siz - 1);
        return toRet;
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

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() throws NoSuchElementException {
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
        Node<E> cur = head;
        while (cur != null && !((o == null && cur.data == null) || (o != null && o.equals(cur.data))))
            cur = cur.next;
        if (cur == null)
            return false;
        siz--;
        if (siz == 0) {
            head = back = null;
            return true;
        }
        if (cur == head) {
            head.next.prev = null;
            head = head.next;
            return true;
        }
        if (cur == back) {
            back.prev.next = null;
            back = back.prev;
            return true;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
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
        return siz;
    }

    @Override
    public boolean isEmpty() {
        return siz == 0;
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
