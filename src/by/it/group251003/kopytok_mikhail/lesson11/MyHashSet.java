package by.it.group251003.kopytok_mikhail.lesson11;

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

    private int DefaultSize = 64;

    private ListNode<E>[] map = new ListNode[DefaultSize];

    private int size = 0;

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % map.length;
    }

    private void resize() {
        ListNode<E>[] newMap = new ListNode[map.length * 2 + 1];
        for (ListNode<E> node : map) {
            ListNode<E> cur = node;

            while (cur != null) {
                ListNode<E> next = cur.next;

                int hash = (cur.data.hashCode() & 0x7FFFFFFF) % newMap.length;
                cur.next = newMap[hash];
                newMap[hash] = cur;

                cur = next;
            }
        }
        map = newMap;
    }

    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        boolean first = true;

        for (int i = 0; i < map.length; i++) {
            ListNode<E> cur = map[i];
            while (cur != null) {
                if (!first)
                    SB.append(", ");

                SB.append(cur.data);
                first = false;
                cur = cur.next;
            }
        }

        return SB.append("]").toString();
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
        ListNode<E> cur = map[hash];

        while (cur != null) {
            if (cur.data.equals(o))
                return true;
            cur = cur.next;
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
        ListNode<E> cur = map[hash];

        while (cur != null) {
            if (cur.data.equals(e))
                return false;
            cur = cur.next;
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

        for (int i = map.length - 1;  i >= 0; i--) {
            map[i] = null;
        }

        size = 0;
    }
}