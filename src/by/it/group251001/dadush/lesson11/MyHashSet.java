package by.it.group251001.dadush.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public class MyHashSet<E> implements Set<E> {

    static class Node<E> {
        E value;
        int hash;
        Node next;

        Node(E e, int h) {
            value = e;
            hash = h;
            next = null;
        }
    }

    private Node[] data;

    private float loadFactor = 0.75f;

    private int size = 0;
    private int bCount = 0;

    private static final int INIT_CAPACITY = 16;

    {
        init();
    }

    // --- Service methods

    private void init() {
        data = new Node[INIT_CAPACITY];
        for (int i = 0; i < INIT_CAPACITY; i++)
            data[i] = null;
        size = 0;
        bCount = 0;
    }

    private int hash(Object key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }

    private boolean pureAdd(Node[] data, E e) {
        int h = hash(e);
        int pos;
        if (data[pos = (data.length - 1) & h] == null) {
            data[pos] = new Node(e, h);
            bCount++;
            size++;
        } else {
            Node curr = data[pos];
            do {
                if (curr.value.equals(e))
                    return false;
                else {
                    if (curr.next == null) {
                        curr.next = new Node(e, h);
                        size++;
                        curr = null;
                    } else
                        curr = curr.next;
                }
            } while (curr != null);
        }
        return true;
    }

    private void resize() {
        Node[] nData = new Node[data.length << 1];
        size = 0;
        bCount = 0;
        for (int i = 0; i < nData.length; i++)
            nData[i] = null;
        for (int i = 0; i < data.length; i++)
            if (data[i] != null) {
                Node curr = data[i];
                do {
                    pureAdd(nData, (E) curr.value);
                } while ((curr = curr.next) != null);
            }
        data = nData;
    }

    // --- Main methods

    public String toString() {
        if (size == 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                Node curr = data[i];
                do {
                    sb.append(curr.value.toString() + ", ");
                } while ((curr = curr.next) != null);
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.append("]").toString();
    }

    public int size() {
        return size;
    }

    public void clear() {
        init();
    }

    public boolean isEmpty() {return size == 0;}

    public boolean add(E e) {
        if (bCount >= data.length * loadFactor)
            resize();
        return pureAdd(data, e);
    }

    public boolean remove(Object o) {
        int pos;
        if (data[pos = hash(o) & (data.length - 1)] == null)
            return false;
        else {
            Node curr = data[pos];
            if (curr.value.equals(o)) {
                if (curr.next == null) {
                data[pos] = null;
                bCount--;
                } else
                    data[pos] = curr.next;
                size--;
                return true;
            }
            while (curr.next != null) {
                if (curr.next.value.equals(o)) {
                    curr.next = (curr.next).next;
                    size--;
                    return true;
                } else
                    curr = curr.next;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        int pos;
        if (data[pos = hash(o) & (data.length - 1)] == null)
            return false;
        else {
            Node curr = data[pos];
            do {
                if (curr.value.equals(o))
                    return true;
            } while ((curr = curr.next) != null);
        }
        return false;
    }

    ////////////////////////
    public Iterator<E> iterator() {
        return null;
    }
    public Object[] toArray() {
        return null;
    }
    public <T> T[] toArray(T[] a) {
        return null;
    }
    public boolean containsAll(Collection<?> c) {
        return true;
    }
    public boolean addAll(Collection<? extends E> c) {
        return true;
    }
    public boolean retainAll(Collection<?> c) {
        return true;
    }
    public boolean removeAll(Collection<?> c) {
        return true;
    }
    public boolean equals(Object o) {
        return true;
    }
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
