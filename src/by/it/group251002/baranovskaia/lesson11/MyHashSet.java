package by.it.group251002.baranovskaia.lesson11;

import java.util.*;

//@SuppressWarnings("unchecked")
public class MyHashSet<T> implements Set<T> {

    private int capacity = 16;

    private Node<T>[] arr;
    private int size;
    private class Node<T> {

        T elem;
        Node<T> next;
        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }

    }

    public MyHashSet() {
        arr = (Node<T>[]) new Node[capacity];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (Node<T> element : arr) {
            for (Node<T> curr = element; curr != null; curr = curr.next) {
                sb.append(curr.elem).append(", ");
            }
        }
        return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean add(T t) {

        if (contains(t)) {
            return false;
        }

        size++;
        int index = indexByHash(t);
        Node<T> elem = arr[index];
        if (elem == null) {
            arr[index] = new Node<>(t, null);
            return true;
        }
        while(elem.next != null){
            elem = elem.next;
        }
        elem.next = new Node<>(t, null);
        return true;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexByHash(o);
        Node<T> elem = arr[index];

        if (elem == null) {
            return false;
        }

        if (o.equals(elem.elem)) {
            size--;
            arr[index] = elem.next;
            return true;
        }

        for (Node<T> curr = elem; curr.next != null; curr = curr.next) {
            if (curr.next.elem.equals(o)) {
                Node<T> temp = curr.next;
                curr.next = temp.next;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> elem = arr[indexByHash(o)];
        if (elem == null) {
            return false;
        }

        for (Node<T> curr = elem; curr != null; curr = curr.next) {
            if (curr.elem.equals(o)) {
                return true;
            }
        }

        return false;
    }

    private int indexByHash(Object o) {
        return (o == null) ? 0 : o.hashCode() % (arr.length - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
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
