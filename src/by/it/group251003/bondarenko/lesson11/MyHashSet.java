package by.it.group251003.bondarenko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    static class ListNode<E> {
        E data;
        public ListNode<E> next;

        ListNode(E e) {
            this.data = e;
        }
    }

    private int defaultSize = 64;
    private ListNode<E>[] map = new ListNode[defaultSize];
    private int size = 0;

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % map.length;
    }

    private void resize() {
        ListNode<E>[] newMap = new ListNode[map.length * 2 + 1];
        for (ListNode<E> el : map) {
            ListNode<E> curr = el;

            while (curr != null) {
                ListNode<E> next = curr.next;

                int hash = (curr.data.hashCode() & 0x7FFFFFFF) % newMap.length;
                curr.next = newMap[hash];
                newMap[hash] = curr;

                curr = next;
            }
        }
        map = newMap;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < map.length; i++) {
            ListNode<E> curr = map[i];
            while (curr != null) {
                if (!first)
                    res.append(", ");

                res.append(curr.data);
                first = false;
                curr = curr.next;
            }
        }

        return res.append("]").toString();
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
    public boolean contains(Object o) {
        int hash = getHash(o);
        ListNode<E> curr = map[hash];
        while (curr != null) {
            if (curr.data.equals(o))
                return true;
            curr = curr.next;
        }
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
    public boolean add(E e) {
        int hash = getHash(e);
        ListNode<E> curr = map[hash];

        while (curr != null) {
            if (curr.data.equals(e))
                return false;
            curr = curr.next;
        }

        ListNode<E> newNode = new ListNode<>(e);

        newNode.next = map[hash];
        map[hash] = newNode;
        size++;

        if (size > map.length * 0.75) {
            resize();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash = getHash(o);
        ListNode<E> curr = map[hash];
        ListNode<E> prev = null;

        while (curr != null) {
            if (curr.data.equals(o)) {
                if (prev == null) {
                    map[hash] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
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

    @Override
    public void clear() {
        for (int i = 0; i < map.length; i++) {
            map[i] = null;
        }

        size = 0;
    }
}
