package by.it.group251003.kardychka.lesson11;

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
    private ListNode<E>[] map;
    private ListNode<E> head, tail;
    private int size = 0;

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % map.length;
    }

    MyLinkedHashSet() {
        map = new ListNode[defaultSize];
    }

    MyLinkedHashSet(int size) {
        map = new ListNode[size];
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
        if (node.after != null)
            node.after.prev = node.prev;
        else
            tail = node.prev;

        if (node.prev != null)
            node.prev.after = node.after;
        else
            head = node.after;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        ListNode<E> curr = head;
        if (curr != null) {
            res.append(curr.data);
            curr = curr.after;
        }

        while (curr != null) {
            res.append(", ").append(curr.data);
            curr = curr.after;
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
        addNode(newNode);
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
                removeNode(curr);
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
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e : c) {
            flag = add(e) || flag;
        }

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean flag = false;
        MyLinkedHashSet<E> tmp = new MyLinkedHashSet<E>(map.length);
        ListNode<E> curr = head;
        while (curr != null) {
            if (c.contains(curr.data)) {
                tmp.add(curr.data);
                flag = true;
            }
            curr = curr.after;
        }

        map = tmp.map;
        head = tmp.head;
        tail = tmp.tail;
        size = tmp.size;

        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object o : c)
            flag = remove(o) || flag;

        return flag;
    }

    @Override
    public void clear() {
        for (int i = 0; i < map.length; i++) {
            map[i] = null;
        }

        head = null;
        tail = null;
        size = 0;
    }
}
