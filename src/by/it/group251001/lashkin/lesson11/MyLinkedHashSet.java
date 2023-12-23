package by.it.group251001.lashkin.lesson11;

import java.util.*;

//                        toString()
//                        size()
//                        clear()
//                        isEmpty()
//                        add(Object)
//                        remove(Object)
//                        contains(Object)
//
//                        containsAll(Collection)
//                        addAll(Collection)
//                        removeAll(Collection)
//                        retainAll(Collection)

public class MyLinkedHashSet<E> implements Set<E> {
    public static int DEFAULT_SIZE;

    public static class Node<E> {
        public E data;
        public Node<E> next = null;
        public Node<E> prev = null;
        public Node<E> after = null;
        public Node(E e) {
            data = e;
        }
    }

    private Node<E>[] arr;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedHashSet() {
        DEFAULT_SIZE = 128;
        arr = (Node<E>[]) new Node[DEFAULT_SIZE];
    }

    public MyLinkedHashSet(int size) {
        arr = (Node<E>[]) new Node[size];
    }

    @Override
    public String toString() {
        var SB = new StringBuilder("[");
        var curr = head;
        if (curr != null) {
            SB.append(curr.data);
            curr = curr.after;
        }
        for (; curr != null; curr = curr.after) SB.append(", ").append(curr.data);
        SB.append("]");
        return SB.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        var hash = (e.hashCode() & 0x7FFFFFFF) % arr.length;
        var curr = arr[hash];
        for (; curr != null; curr = curr.next)
            if (curr.data.equals(e))
                return false;
        var newNode = new Node<>(e);
        newNode.next = arr[hash];
        arr[hash] = newNode;
        if (head == null) head = newNode;
        else {
            tail.after = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
        if (size > arr.length * 0.75) {
            Node<E>[] newArr = (Node<E>[]) new Node[arr.length * 2 + 1];
            for (Node<E> node : arr) {
                var temp = node;
                while (temp != null) {
                    Node<E> next = temp.next;
                    int newHash = (temp.data.hashCode() & 0x7FFFFFFF) % newArr.length;
                    temp.next = newArr[newHash];
                    newArr[newHash] = temp;
                    temp = next;
                }
            }
            arr = newArr;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        var hash = (o.hashCode() & 0x7FFFFFFF) % arr.length;
        var curr = arr[hash];
        Node<E> prev = null;
        for (; curr != null; curr = curr.next) {
            if (curr.data.equals(o)) {
                if (curr.after != null) curr.after.prev = curr.prev;
                else tail = curr.prev;
                if (curr.prev != null) curr.prev.after = curr.after;
                else head = curr.after;
                if (prev == null) arr[hash] = curr.next;
                else prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        var hash = (o.hashCode() & 0x7FFFFFFF) % arr.length;
        var curr = arr[hash];
        for (; curr != null; curr = curr.next)
            if (curr.data.equals(o))
                return true;
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
        boolean isAdded = false;
        for (E e : c) isAdded = add(e) || isAdded;
        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (Object o : c) isRemoved = remove(o) || isRemoved;
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }
        boolean isRetained = false;
        var temp = new MyLinkedHashSet<E>(arr.length);
        var curr = head;
        for (; curr != null; curr = curr.after)
            if (c.contains(curr.data)) {
                temp.add(curr.data);
                isRetained = true;
            }
        arr = temp.arr;
        head = temp.head;
        tail = temp.tail;
        size = temp.size;
        return isRetained;
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
}
