package by.it.group251002.korti.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    static class ListNode<E> {
        E data;
        public ListNode<E> next;
        public ListNode<E> after, prev;

        ListNode(E e) {
            data = e;
        }
    }

    final private int defaultSize = 64;

    private ListNode<E>[] set;
    private ListNode<E> head, tail;
    private int size = 0;

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % set.length;
    }

    MyLinkedHashSet() {
        set = new ListNode[defaultSize];
    }

    MyLinkedHashSet(int size) {
        set = new ListNode[size];
    }

    private void resize() {

        ListNode<E>[] newMap = new ListNode[set.length * 2 + 1];

        for (ListNode<E> node : set) {
            ListNode<E> cur = node;

            while (cur != null) {
                ListNode<E> next = cur.next;

                int hash = (cur.data.hashCode() & 0x7FFFFFFF) % newMap.length;
                cur.next = newMap[hash];
                newMap[hash] = cur;

                cur = next;
            }
        }
        set = newMap;
    }

    private void addNode(ListNode<E> node) {

        if (head == null) {
            head = node;
        } else {
            tail.after = node;
            node.prev = tail;
        }

        tail = node;
    }

    private void removeNode(ListNode<E> node) {

        if (node.after != null) {
            node.after.prev = node.prev;
        } else {
            tail = node.prev;
        }

        if (node.prev != null){
            node.prev.after = node.after;
        } else {
            head = node.after;
        }
    }

    public String toString() {

        StringBuilder SB = new StringBuilder("[");
        ListNode<E> curr = head;

        if (curr != null) {
            SB.append(curr.data);
            curr = curr.after;
        }

        while (curr != null) {
            SB.append(", ").append(curr.data);
            curr = curr.after;
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
        ListNode<E> cur = set[hash];

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
        ListNode<E> cur = set[hash];

        while (cur != null) {
            if (cur.data.equals(e))
                return false;
            cur = cur.next;
        }

        ListNode<E> newNode = new ListNode<>(e);

        newNode.next = set[hash];
        set[hash] = newNode;
        addNode(newNode);
        size++;

        if (size > set.length * 0.75)
            resize();

        return true;
    }

    @Override
    public boolean remove(Object o) {

        int hash = getHash(o);
        ListNode<E> cur = set[hash];
        ListNode<E> prev = null;

        while (cur != null) {

            if (cur.data.equals(o)) {
                removeNode(cur);

                if (prev == null) {
                    set[hash] = cur.next;
                }
                else {
                    prev.next = cur.next;
                }

                size--;
                return true;
            }

            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o))
                return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        boolean added = false;
        for (E e : c) {
            added = add(e) || added;
        }

        return added;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean retained = false;
        MyLinkedHashSet<E> tmp = new MyLinkedHashSet<E>(set.length);
        ListNode<E> cur = head;

        while (cur != null) {
            if (c.contains(cur.data)) {
                tmp.add(cur.data);
                retained = true;
            }
            cur = cur.after;
        }

        set = tmp.set;
        head = tmp.head;
        tail = tmp.tail;
        size = tmp.size;

        return retained;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean removed = false;
        for (Object o : c)
            removed = remove(o) || removed;

        return removed;
    }

    @Override
    public void clear() {

        for (int i = set.length - 1; i >= 0; i--) {
            set[i] = null;
        }

        head = null;
        tail = null;
        size = 0;
    }
}