package by.it.group251001.mikhei.lesson11;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.ceil;

public class MyLinkedHashSet<E> implements Set<E> {
    static final double DEFAULT_LOAD_FACTOR = 0.75;
    private final double loadFactor = DEFAULT_LOAD_FACTOR;
    private Node[] data;
    private int size;
    private Node beforeStart, afterEnd;

    public MyLinkedHashSet() {
        init();
    }

    private void init() {
        size = 0;
        data = (Node[]) Array.newInstance(Node.class, 0);
        beforeStart = new Node();
        afterEnd = new Node();

        beforeStart.after = afterEnd;
        afterEnd.before = beforeStart;
    }

    private void init(MyLinkedHashSet<E> set){
        data = set.data;
        beforeStart = set.beforeStart;
        afterEnd = set.afterEnd;
        size = set.size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        Node cur = beforeStart.after;

        while (cur != afterEnd) {
            sb.append(cur.value);
            sb.append(", ");

            cur = cur.after;
        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append("]");

        return sb.toString();
    }

    private void growIfNeeded(int newSize) {
        if (newSize < data.length * loadFactor) return;

        int newLength = data.length * 2 + 1;

        if (newSize > newLength * loadFactor) newLength = (int) ceil(newSize / loadFactor);

        data = (Node[]) Array.newInstance(Node.class, newLength);

        Node cur = beforeStart.after;

        while (cur != afterEnd) {
            int i = index(cur.value);

            cur.next = data[i];
            data[i] = cur;

            cur = cur.after;
        }
    }

    private int index(Object key) {
        return key.hashCode() % data.length;
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
        int index = index(o);
        Node cur = data[index];

        if (cur == null) {
            return false;
        }

        while (cur.next != null) {
            if (cur.value.equals(o)) return true;

            cur = cur.next;
        }

        return cur.value.equals(o);
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

    private void addNode(Node node) {
        node.before = afterEnd.before;
        node.after = afterEnd;

        node.before.after = node;
        afterEnd.before = node;
    }

    private void deleteNode(Node node) {
        node.before.after = node.after;
        node.after.before = node.before;
    }

    @Override
    public boolean add(E e) {
        growIfNeeded(size + 1);

        Node newNode = new Node(e);

        int index = index(e);
        Node cur = data[index];

        if (cur == null) {
            addNode(newNode);

            data[index] = newNode;
            size++;
            return true;
        }

        while (cur.next != null) {
            if (cur.value.equals(e)) return false;

            cur = cur.next;
        }
        if (cur.value.equals(e)) return false;

        addNode(newNode);
        cur.next = newNode;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = index(o);
        Node cur = data[index];

        if (cur == null) {
            return false;
        }

        if (cur.value.equals(o)) {
            data[index] = cur.next;
            size--;
            deleteNode(cur);
            return true;
        }

        while (cur.next != null) {
            if (cur.next.value.equals(o)) {
                deleteNode(cur.next);
                cur.next = cur.next.next;
                size--;
                return true;
            }

            cur = cur.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var x : c) {
            if (!contains(x)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for (E x : c) {
            res |= add(x);
        }
        return res;
    }

    private E get(Object o) {
        int index = index(o);
        Node cur = data[index];

        if (cur == null) {
            throw new NoSuchElementException();
        }

        while (cur.next != null) {
            if (cur.value.equals(o)) return cur.value;

            cur = cur.next;
        }

        if (cur.value.equals(o)) return cur.value;


        throw new NoSuchElementException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node cur = beforeStart.after;

        boolean res = false;
        while (cur != afterEnd){
            if(!c.contains(cur.value)){
                res |= remove(cur.value);
            }

            cur = cur.after;
        }

        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (var x : c) {
            res |= remove(x);
        }
        return res;
    }

    @Override
    public void clear() {
        init();
    }

    private class Node {
        E value;
        Node next, after, before;


        public Node(E value) {
            this.value = value;
        }

        public Node() {
        }
    }
}
