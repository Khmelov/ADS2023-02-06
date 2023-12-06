package by.it.group251004.rychyhin.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
    static final int defaultSize = 32;
    Node<E>[] _items;
    int _count;

    public MyHashSet() {
        this(defaultSize);
    }

    public MyHashSet(int capacity) {
        _items = new Node[capacity];
    }

    int GetHash(Object value) {
        return (value.hashCode() & 0x7FFFFFFF) % _items.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < _items.length; i++) {
            Node<E> current = _items[i];
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
        return _count;
    }

    @Override
    public boolean isEmpty() {
        return _count == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = _items[GetHash(o)];
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        int index = GetHash(e);
        Node<E> current = _items[index];
        while (current != null) {
            if (current.data.equals(e)) {
                return false;
            }
            current = current.next;
        }
        Node<E> newNode = new Node<>(e);
        newNode.next = _items[index];
        _items[index] = newNode;
        _count++;
        if (_count > _items.length * 0.75) {
            resize();
        }
        return true;
    }

    void resize() {
        Node<E>[] newItems = new Node[_items.length * 2];
        for (int i = 0; i < _items.length; i++) {
            Node<E> current = _items[i];
            while (current != null) {
                Node<E> next = current.next;
                int newIndex = current.data.hashCode() & 0x7FFFFFFF % newItems.length;
                current.next = newItems[newIndex];
                newItems[newIndex] = current;
                current = next;
            }
        }
        _items = newItems;
    }

    @Override
    public boolean remove(Object o) {
        int index = GetHash(o);
        Node<E> current = _items[index];
        Node<E> previous = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) {
                    _items[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                _count--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < _items.length; i++)
            _items[i] = null;
        _count = 0;
    }


    ////////////////////////////////////////////////////////////////////////

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
