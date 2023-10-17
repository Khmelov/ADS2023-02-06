package by.it.group251004.stasevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
    }
}

public class MyHashSet <E> implements Set<E>{
    int capacity = 32;
    Node<E>[] set;
    int size;

    public MyHashSet() {
        size=0;
        set = new Node[capacity];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < set.length; i++) {
            Node<E> current = set[i];
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
    public boolean contains(Object o) {
        Node<E> current = set[GetHash(o)];
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

    int GetHash(Object value) {
        return (value.hashCode() & 0x7FFFFFFF) % set.length;
    }

    @Override
    public boolean add(E e) {
        int index = GetHash(e);
        Node<E> current = set[index];
        while (current != null) {
            if (current.data.equals(e)) {
                return false;
            }
            current = current.next;
        }
        Node<E> newNode = new Node<>(e);
        newNode.next = set[index];
        set[index] = newNode;
        size++;
        if (size > capacity*0.75) {
            resize();
        }
        return true;
    }

    void resize() {
        capacity*=2;
        Node<E>[] newItems = new Node[capacity];
        for (int i = 0; i < set.length; i++) {
            Node<E> current = set[i];
            while (current != null) {
                Node<E> next = current.next;
                int newIndex = current.data.hashCode() & 0x7FFFFFFF % newItems.length;
                current.next = newItems[newIndex];
                newItems[newIndex] = current;
                current = next;
            }
        }
        set = newItems;
    }

    @Override
    public boolean remove(Object o) {
        int index = GetHash(o);
        Node<E> current = set[index];
        Node<E> previous = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) {
                    set[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
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
        for (int i = 0; i < set.length; i++)
            set[i] = null;
        size = 0;
    }
}
