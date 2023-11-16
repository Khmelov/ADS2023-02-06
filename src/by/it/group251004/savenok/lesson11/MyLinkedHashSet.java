package by.it.group251004.savenok.lesson11;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    class Node<E> {
        E data;
        Node<E> next;
        Node<E> after, previous;

        Node(E data) {
            this.data = data;
        }
    }
    static final int defaultSize = 32;
    Node<E>[] _items;

    Node<E> _head, _tail;
    int _count;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = _head;
        if (current != null) {
            sb.append(current.data);
            current = current.after;
        }
        while (current != null) {
            sb.append(", ").append(current.data);
            current = current.after;
        }
        return sb.append("]").toString();
    }

    public MyLinkedHashSet() {
        this(defaultSize);
    }

    public MyLinkedHashSet(int capacity) {
        _items = new Node[capacity];
    }

    int GetHash(Object value) {
        return (value.hashCode() & 0x7FFFFFFF) % _items.length;
    }

    void AddNode(Node<E> newNode) {
        if (_head == null) {
            _head = newNode;
        } else {
            _tail.after = newNode;
            newNode.previous = _tail;
        }
        _tail = newNode;
    }

    void RemoveNode(Node<E> newNode) {
        if (newNode.after != null)
            newNode.after.previous = newNode.previous;
        else
            _tail = newNode.previous;

        if (newNode.previous != null)
            newNode.previous.after = newNode.after;
        else
            _head = newNode.after;
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
    public void clear() {
        for (int i = 0; i < _items.length; i++)
            _items[i] = null;
        _head = null;
        _tail = null;
        _count = 0;
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
        AddNode(newNode);
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
                RemoveNode(current);
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
    public boolean containsAll(Collection<?> c) {
        for (Object obj: c) {
            if (!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E item : c) {
            if (add(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            this.clear();
            return true;
        }
        boolean isModified = false;
        MyLinkedHashSet<E> tempSet = new MyLinkedHashSet<>(_items.length);
        Node<E> current = _head;
        while (current != null) {
            if (c.contains(current.data)) {
                tempSet.add(current.data);
                isModified = true;
            }
            current = current.after;
        }
        _items = tempSet._items;
        _head = tempSet._head;
        _tail = tempSet._tail;
        _count = tempSet._count;

        return isModified;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (Object obj: c) {
            if (remove(obj))
                isModified = true;
        }
        return isModified;
    }


    ///////////////////////////////////////////////////////////////////

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
