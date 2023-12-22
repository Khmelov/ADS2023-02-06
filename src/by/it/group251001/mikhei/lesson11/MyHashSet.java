package by.it.group251001.mikhei.lesson11;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.ceil;

public class MyHashSet<E> implements Set<E> {

    static final double DEFAULT_LOAD_FACTOR = 0.75;

    @Override
    public String toString() {
        if(isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        for(int i = 0; i < data.length; i++){
            Node cur = data[i];

            while(cur != null){
                sb.append(cur.value);
                sb.append(", ");

                cur = cur.next;
            }
        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append("]");

        return sb.toString();
    }

    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
        }

        public Node() {
        }
    }

    private Node[] data = (Node[]) Array.newInstance(Node.class, 0);
    private int size = 0;

    private final double loadFactor = DEFAULT_LOAD_FACTOR;

    private void growIfNeeded(int newSize) {
        if (newSize < data.length * loadFactor) return;

        int newLength = data.length * 2 + 1;

        if (newSize > newLength * loadFactor) newLength = (int) ceil(newSize / loadFactor);

        Node[] tmp = data;

        data = (Node[]) Array.newInstance(Node.class, newLength);

        for(var node: tmp){

            while(node != null) {
                int i = index(node.value);

                Node newNode = new Node(node.value);
                newNode.next = data[i];
                data[i] = newNode;

                node = node.next;
            }
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

    @Override
    public boolean add(E e) {
        growIfNeeded(size + 1);

        int index = index(e);
        Node cur = data[index];

        if (cur == null) {
            data[index] = new Node(e);
            size++;
            return true;
        }

        while (cur.next != null) {
            if (cur.value.equals(e)) return false;

            cur = cur.next;
        }
        if (cur.value.equals(e)) return false;

        cur.next = new Node(e);
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
            return true;
        }

        while (cur.next != null) {
            if (cur.next.value.equals(o)) {
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
        size = 0;
        data = (Node[]) Array.newInstance(Node.class, 0);;
    }
}
