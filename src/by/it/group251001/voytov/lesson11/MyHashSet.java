package by.it.group251001.voytov.lesson11;

import java.util.*;

@SuppressWarnings("unchecked")
public class MyHashSet<T> implements Set<T> {

    private static final int DEFAULT_CAPACITY = 140;

    private Node<T>[] data;
    private int size;
    private static class Node<T> {

        final T item;
        Node<T> next;
        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (Node<T> element : data) {
            for (Node<T> curr = element; curr != null; curr = curr.next) {
                sb.append(curr.item).append(", ");
            }
        }

        return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
    }
    public MyHashSet() {
        data = (Node<T>[]) new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            data[i] = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean add(T t) {

        if (contains(t)) {
            return false;
        }

        size++;
        int index = indexByHash(t);
        Node<T> bucket = data[index];
        if (bucket == null) {
            data[index] = new Node<>(t, null);
            return true;
        }

        for (; bucket.next != null; bucket = bucket.next);

        bucket.next = new Node<>(t, null);

        return true;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexByHash(o);
        Node<T> bucket = data[index];

        if (bucket == null) {
            return false;
        }

        if (o.equals(bucket.item)) {
            size--;
            data[index] = bucket.next;
            return true;
        }

        for (Node<T> curr = bucket; curr.next != null; curr = curr.next) {
            if (curr.next.item.equals(o)) {
                deleteNext(curr);
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> bucket = data[indexByHash(o)];
        if (bucket == null) {
            return false;
        }

        for (Node<T> curr = bucket; curr != null; curr = curr.next) {
            if (curr.item.equals(o)) {
                return true;
            }
        }

        return false;
    }

    private int indexByHash(Object o) {
        return (o == null) ? 0 : o.hashCode() % (data.length - 1);
    }

    private void deleteNext(Node<T> prev) {
        Node<T> nextNode = prev.next;
        prev.next = nextNode.next;
    }

    private void rearrange(int newCapacity) {
        Node<T>[] newData = (Node<T>[]) new Node[newCapacity];
        Node<T>[] prevData = data;
        data = newData;
        size = 0;
        for (Node<T> prevDatum : prevData) {
            for (Node<T> curr = prevDatum; curr != null; curr = curr.next) {
                add(curr.item);
            }
        }
    }

    //–––––––

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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
}
