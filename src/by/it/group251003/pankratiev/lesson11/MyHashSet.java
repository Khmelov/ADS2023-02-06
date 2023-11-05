package by.it.group251003.pankratiev.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private static class Node<E> {
        public final E data;
        public Node<E> next = null;
        public Node(E data) { this.data = data; }
    }
    private static final int INITIAL_CAPACITY = 4;
    private static final int THRESHOLD_FACTOR = 10;
    private boolean isInvalidType(Object o) {
        return o == null;
    }

    private Node<E>[] arr = new Node[INITIAL_CAPACITY];
    private int size = 0;

    private void resize() {
        resize(arr.length);
    }
    private void resize(int newSize) {
        var newArr = new Node[newSize * 2];

        for (var current : arr) {
            while (current != null) {
                int hash = hash(current.data, newArr.length);
                var newNode = new Node<E>(current.data);
                if (newArr[hash] == null)
                    newArr[hash] = newNode;
                else {
                    var temp = newArr[hash];
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = newNode;
                }
                current = current.next;
            }
        }
        arr = newArr;
    }
    private int hash(Object o) { return hash(o, arr.length); }
    private int hash(Object o, int length) { return (o.hashCode() & 0x7fffffff) % length; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            if (arr[0] != null) {
                result.append(arr[0].data);
                appendListToStringBuilder(result, arr[0].next);
            }

            for (int i = 1; i < arr.length; i++)
                appendListToStringBuilder(result, arr[i]);
        }

        result.append("]");
        return result.toString();
    }
    private void appendListToStringBuilder(StringBuilder result, Node<E> head) {
        while (head != null) {
            result.append(", ").append(head.data);
            head = head.next;
        }
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
        if (isInvalidType(o))
            throw new IllegalArgumentException("Element cannot be null");

        int hash = hash(o);

        var current = arr[hash];
        while (current != null) {
            if (current.data.equals(o))
                return true;
            current = current.next;
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
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        int hash = hash(e);

        var newNode = new Node<E>(e);
        if (arr[hash] == null)
            arr[hash] = newNode;
        else {
            var current = arr[hash];
            while (current.next != null) {
                if (current.data.equals(e))
                    return false;

                current = current.next;
            }
            if (current.data.equals(e))
                return false;
            current.next = newNode;
        }

        if (++size >= arr.length * THRESHOLD_FACTOR)
            resize();

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Element cannot be null");

        int hash = hash(o);

        Node<E> prev = null;
        Node<E> current = arr[hash];
        while (current != null) {
            if (current.data.equals(o)) {
                if (prev == null)
                    arr[hash] = current.next;
                else
                    prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
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
        for (int i = 0; i < arr.length; i++)
            arr[i] = null;
    }
}
