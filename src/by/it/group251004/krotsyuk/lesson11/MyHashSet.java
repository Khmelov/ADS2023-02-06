package by.it.group251004.krotsyuk.lesson11;

import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class MyHashSet<E> implements Set<E> {

    class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    static final int defaultSize = 32;

    Node<E>[] items;

    int count;

    public MyHashSet() {
        this(defaultSize);
    }

    public MyHashSet(int capacity) {
        items = new Node[capacity];
    }

    int GetHash(Object value) {
        return (value.hashCode() & 0x7FFFFFFF) % items.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < items.length; i++) {
            Node<E> current = items[i];
            while (current != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(current.data);
                first = false;
                current = current.next;
            }
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return(count);
    }

    @Override
    public boolean isEmpty() {
        return(count == 0);
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = items[GetHash(o)];
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
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
        int index = GetHash(e);
        Node<E> current = items[index];
        while (current != null) {
            if (current.data.equals(e)) {
                return false;
            }
            current = current.next;
        }
        Node<E> newNode = new Node<>(e);
        newNode.next = items[index];
        items[index] = newNode;
        count++;
        if (count > items.length * 0.75) {
            Node<E>[] newItems = new Node[items.length * 2];
            for (int i = 0; i < items.length; i++) {
                Node<E> curr = items[i];
                while (curr != null) {
                    Node<E> next = curr.next;
                    int newIndex = curr.data.hashCode() & 0x7FFFFFFF % newItems.length;
                    curr.next = newItems[newIndex];
                    newItems[newIndex] = curr;
                    curr = next;
                }
            }
            items = newItems;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = GetHash(o);
        Node<E> current = items[index];
        Node<E> previous = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) {
                    items[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                count--;
                return true;
            }
            previous = current;
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
        for (int i = 0; i < items.length; i++)
            items[i] = null;
        count = 0;
    }
}
