package by.it.group251003.Trukhan.lesson01.lesson11;

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
    private int size = 0;
    private void resize() {
        ListNode<E>[] tmpMap = new ListNode[map.length * 2 + 1];
        for (ListNode<E> node : map) {
            ListNode<E> cur = node;
            while (cur != null) {
                ListNode<E> next = cur.next;

                int hash = (cur.data.hashCode() & 0x7FFFFFFF) % tmpMap.length;
                cur.next = tmpMap[hash];
                tmpMap[hash] = cur;

                cur = next;
            }
        }
        map = tmpMap;
    }

    private ListNode<E>[] map = new ListNode[64];

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % map.length;
    }

    @Override
    public boolean add(E e) {
        int hash = getHash(e);
        ListNode<E> pos = map[hash];

        while (pos != null) {
            if (pos.data.equals(e))
                return false;
            pos = pos.next;
        }

        ListNode<E> newNode = new ListNode<>(e);

        newNode.next = map[hash];
        map[hash] = newNode;

        size++;
        if (size > map.length * 0.75)
            resize();

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash = getHash(o);
        ListNode<E> pos = map[hash];
        ListNode<E> prev = null;

        while (pos != null) {
            if (pos.data.equals(o)) {
                if (prev == null) {
                    map[hash] = pos.next;
                } else {
                    prev.next = pos.next;
                }
                size--;
                return true;
            }
            prev = pos;
            pos = pos.next;
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        int hash = getHash(o);
        ListNode<E> pos = map[hash];

        while (pos != null) {
            if (pos.data.equals(o))
                return true;
            pos = pos.next;
        }

        return false;
    }
    @Override
    public void clear() {
        for (int i = 0;  i < map.length; i++) {
            map[i] = null;
        }
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        int j = 0;
        for (int i = 0; i < map.length; i++) {
            ListNode<E> cur = map[i];
            while (cur != null) {
                str.append(cur.data);
                cur = cur.next;
                j++;
                if (j < size)
                    str.append(", ");
            }
        }
        return str.append("]").toString();
    }





    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
