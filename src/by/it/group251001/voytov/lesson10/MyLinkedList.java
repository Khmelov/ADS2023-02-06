package by.it.group251001.voytov.lesson10;

import java.util.*;

public class MyLinkedList<T> implements Deque<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {}

    @Override
    public String toString() {

        if (this.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder().append("[");
        Node<T> curr;
        for (curr = first; curr.next != null; curr = curr.next) {
            sb.append(curr.item).append(", ");
        }
        return sb.append(curr.item).append("]").toString();
    }

    @Override
    public boolean add(T t) {
        linkLast(t);
        return true;
    }

    @Override
    public T remove() {
        Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    public T remove(int index) {

        if (index >= size) {
            throw new NoSuchElementException();
        }

        Node<T> curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        T val = curr.item;
        unlink(curr);

        return val;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> curr = first; curr != null; curr = curr.next) {
                if (curr.item == null) {
                    unlink(curr);
                    return true;
                }
            }
        }
        else {
            for (Node<T> curr = first; curr != null; curr = curr.next) {
                if (curr.item.equals(o)) {
                    unlink(curr);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T t) {
        linkFirst(t);
    }

    @Override
    public void addLast(T t) {
        linkLast(t);
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T getFirst() {
        Node<T> node = first;
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.item;
    }

    @Override
    public T getLast() {
        Node<T> node = last;
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.item;
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T pollFirst() {
        Node<T> f = first;
        if (f == null) {
            return null;
        }
        return unlinkFirst(f);
    }

    @Override
    public T pollLast() {
        Node<T> l = last;
        if (l == null) {
            return null;
        }
        return unlinkLast(l);
    }

    private void unlink(Node<T> n) {
        Node<T> next = n.next;
        Node<T> prev = n.prev;

        if (prev == null) {
            first = next;
        }
        else {
            prev.next = next;
            n.prev = null;
        }

        if (next == null) {
            last = prev;
        }
        else {
            next.prev = prev;
            n.next = null;
        }

        n.item = null;
        size--;
    }

    private T unlinkFirst(Node<T> f) {
        T item = f.item;
        Node<T> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next != null) {
            next.prev = null;
        }
        else {
            last = null;
        }
        size--;
        return item;
    }

    private T unlinkLast(Node<T> l) {
        T item = l.item;
        Node<T> prev = l.prev;
        l.prev = null;
        l.item = null;
        last = prev;
        if (prev != null) {
            prev.next = null;
        }
        else {
            first = null;
        }
        size--;
        return item;
    }


    private void linkFirst(T item) {
        Node<T> f = first;
        Node<T> newNode = new Node<>(null, item, first);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
        else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(T item) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
    }

    //————————————————————————

    @Override
    public boolean offerFirst(T t) {
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        return false;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T peekFirst() {
        return null;
    }

    @Override
    public T peekLast() {
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
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
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
    public void push(T t) {

    }

    @Override
    public T pop() {
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
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }
}
