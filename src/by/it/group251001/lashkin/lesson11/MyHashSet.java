package by.it.group251001.lashkin.lesson11;

import java.util.*;

//                        size()
//                        clear()
//                        isEmpty()
//                        add(Object)
//                        remove(Object)
//                        contains(Object)

public class MyHashSet<E> implements Set<E> {

    private static final int CAP = 140;

    private final Node<E>[] arr;
    private int size;
    private static class Node<E> {
        private final E item;
        private Node<E> next;
        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public MyHashSet() {
        arr = (Node<E>[]) new Node[CAP];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (var element : arr) {
            var curr = element;
            while (curr != null) {
                sb.append(curr.item);
                sb.append(", ");
                curr = curr.next;
            }
        }
        var len = sb.length();
        sb.delete(len - 2, len);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E t) {
        if (contains(t))
            return false;
        int idx = (t == null) ? 0 : t.hashCode() % (arr.length - 1);
        var elem = arr[idx];
        if (elem != null) {
            while (elem.next != null) elem = elem.next;
            elem.next = new Node<>(t, null);
        } else
            arr[idx] = new Node<>(t, null);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx = (o == null) ? 0 : o.hashCode() % (arr.length - 1);
        var elem = arr[idx];
        if (elem == null)
            return false;
        if (Objects.equals(o, elem.item)) {
            arr[idx] = elem.next;
            size--;
            return true;
        }
        var curr = elem;
        while (curr.next != null)
            if (!curr.next.item.equals(o))
                curr = curr.next;
            else {
                var nextNode = curr.next;
                curr.next = nextNode.next;
                size--;
                return true;
            }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        var elem = arr[(o == null) ? 0 : o.hashCode() % (arr.length - 1)];
        if (elem == null)
            return false;
        var curr = elem;
        while (curr != null) {
            if (curr.item.equals(o)) return true;
            curr = curr.next;
        }
        return false;
    }

    ////////////////////////////////////////

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
}
