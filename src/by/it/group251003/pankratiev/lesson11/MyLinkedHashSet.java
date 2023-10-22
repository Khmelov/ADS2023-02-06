package by.it.group251003.pankratiev.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private static class Node<E> {
        public final E data;
        public Node<E> next = null;
        public Node(E data) { this.data = data; }

        public Node<E> after = null;
        public Node<E> before = null;
    }
    private static final int INITIAL_CAPACITY = 4;
    private static final int THRESHOLD_FACTOR = 10;
    private boolean isInvalidType(Object o) {
        return o == null;
    }

    private Node<E>[] arr = new Node[INITIAL_CAPACITY];
    private Node<E> head = null;
    private Node<E> tail = null;
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
            result.append(head.data);

            var current = head.after;
            for (int i = 1; i < size; i++) {
                result.append(", ").append(current.data);
                current = current.after;
            }
        }

        result.append("]");
        return result.toString();
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
        if (arr[hash] == null) {
            arr[hash] = newNode;
            addConfigureAfterBefore(newNode);
        }
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
            addConfigureAfterBefore(newNode);
        }

        if (++size >= arr.length * THRESHOLD_FACTOR)
            resize();

        return true;
    }
    private void addConfigureAfterBefore(Node<E> newNode) {
        if (head == null)
            head = newNode;
        else {
            tail.after = newNode;
            newNode.before = tail;
        }
        tail = newNode;
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
                removeConfigureAfterBefore(current);
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
    private void removeConfigureAfterBefore(Node<E> removedNode) {
        if (removedNode.before != null)
            removedNode.before.after = removedNode.after;
        else
            head = removedNode.after;

        if (removedNode.after != null)
            removedNode.after.before = removedNode.before;
        else
            tail = removedNode.before;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty())
            return false;

        for (E element : c)
            add(element);

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;

        var current = head;
        while (current != null){
            if (!c.contains(current.data)){
                var temp = current.data;
                current = current.after;
                remove(temp);
            }
            else
                current = current.after;
        }
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;

        var current = head;
        while (current != null){
            if (c.contains(current.data)){
                var temp = current.data;
                current = current.after;
                remove(temp);
            }
            else
                current = current.after;
        }
        return prevSize != size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
        for (int i = 0; i < arr.length; i++)
            arr[i] = null;
    }
}
