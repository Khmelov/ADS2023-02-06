package by.it.group251003.Trukhan.lesson01.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    static class ListNode<E> {
        E data;
        public MyLinkedHashSet.ListNode<E> next;
        ListNode(E e) {
            this.data = e;
        }
    }

    private ListNode<E> elmsQueue = null;
    private void addToQueue(E e) {
        if (elmsQueue == null) {
            elmsQueue = new ListNode<>(e);
        } else {
            ListNode<E> cur = elmsQueue;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode<>(e);
        }
    }
    private void deleteTFromQueue(E e) {
        ListNode<E> cur = elmsQueue;
        ListNode<E> prev = null;
        while (cur != null) {
            if (cur.data.equals(e)) {
                if (prev == null) {
                    elmsQueue = elmsQueue.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
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
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            if (add(e))
                changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean changed = false;
        for (Object o : c)
            if (remove(o))
                changed = true;

        return changed;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }
        boolean changed = false;

        for (int i = 0;  i < map.length; i++) {
            if (map[i] != null) {
                ListNode<E> tmp = new ListNode<>(map[i].data);
                ListNode<E> head = tmp;
                ListNode<E> cur = map[i];
                while (cur != null) {
                    if (!c.contains(cur.data)) {
                        remove(cur.data);
                        changed = true;
                    } else {
                        tmp.next = new ListNode<>(cur.data);
                        if (cur.next != null)
                            tmp = tmp.next;
                        else {
                            tmp = null;
                        }
                    }
                    cur = cur.next;
                }
                map[i] = head.next;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c){
            if (!contains(o))
                return false;
        }
        return true;
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

        addToQueue(e);
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
                deleteTFromQueue(pos.data);
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
        elmsQueue = null;
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
        if (size > 0) {
            size = size;
        }
        StringBuilder str = new StringBuilder("[");

        ListNode<E> cur = elmsQueue;
        if (cur != null) {
            str.append(cur.data);
            cur = cur.next;
        }

        while (cur != null) {
            str.append(", ").append(cur.data);
            cur = cur.next;
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
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
