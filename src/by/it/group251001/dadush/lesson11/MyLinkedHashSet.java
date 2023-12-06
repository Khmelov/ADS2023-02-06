package by.it.group251001.dadush.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public class MyLinkedHashSet<E> implements Set<E> {

    static class Node<E> {
        E value;
        int hash;
        Node next;
        Node qPrev, qNext;

        Node(E e, int h) {
            value = e;
            hash = h;
            next = null;
            qPrev = null;
            qNext = null;
        }
    }

    private Node[] data;

    private Node qStart;
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
        qStart = new Node(null, 0);
    }

    private int hash(Object key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }

    private void qAdd(Node node) {
        Node curr = qStart;
        while (curr.qNext != null)
            curr = curr.qNext;
        curr.qNext = node;
        node.qPrev = curr;
    }

    private void qRemove(Node node) {
        node.qPrev.qNext = node.qNext;
        if (node.qNext != null)
            node.qNext.qPrev = node.qPrev;
    }

    private boolean pureAdd(Node[] data, E e, Node node, boolean exists) {
        Node add;  // element to be added
        if (exists) {
            add = node;
            add.next = null;
        }
        else {
            add = new Node(e, hash(e));
        }

        int pos;
        if (data[pos = (data.length - 1) & add.hash] == null) {
            data[pos] = add;
            if (! exists)
                qAdd(add);
            bCount++;
            size++;
        } else {
            Node curr = data[pos];
            do {
                if (curr.value.equals(e))
                    return false;
                else {
                    if (curr.next == null) {
                        curr.next = add;
                        if (! exists)
                            qAdd(add);
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
        Node curr = qStart.qNext;
        size = 0;
        bCount = 0;
        for (int i = 0; i < nData.length; i++)
            nData[i] = null;
        while (curr != null) {
            pureAdd(nData, null, curr, true);
            curr = curr.qNext;
        }
        data = nData;
    }

    // --- Main methods

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node curr = qStart.qNext;
        while (curr != null) {
            sb.append(curr.value.toString());
            if (curr.qNext == null)
                return sb.append("]").toString();
            sb.append(", ");
            curr = curr.qNext;
        }
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
        return pureAdd(data, e, null, false);
    }

    public boolean remove(Object o) {
        int pos;
        if (data[pos = hash(o) & (data.length - 1)] == null)
            return false;
        else {
            Node curr = data[pos];
            if (curr.value.equals(o)) {
                qRemove(data[pos]);
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
                    qRemove(curr.next);
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

    public boolean containsAll(Collection<?> c) {
        for (Object item : c)
            if (! contains(item))
                return false;
        return true;
    }
    public boolean addAll(Collection<? extends E> c) {
        boolean edited = false;
        for (E item : c)
            edited |= add(item);
        return edited;
    }
    public boolean retainAll(Collection<?> c) {
        boolean edited = false;
        Node curr = qStart.qNext;
        while (curr != null) {
            if (! c.contains(curr.value)) {
                remove(curr.value);
                edited = true;
            }
            curr = curr.qNext;
        }
        return edited;
    }
    public boolean removeAll(Collection<?> c) {
        boolean edited = false;
        Node curr = qStart.qNext;
        while (curr != null) {
            if (c.contains(curr.value)) {
                remove(curr.value);
                edited = true;
            }
            curr = curr.qNext;
        }
        return edited;
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
    public boolean equals(Object o) {
        return true;
    }
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
