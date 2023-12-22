package by.it.group251004.kalitsko.lesson11;

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
    int capacity = 32;
    Node<E>[] set;
    Node<E> head, tail;
    int size;

    public MyLinkedHashSet() {
        size = 0;
        set = new Node[capacity];
    }

    public MyLinkedHashSet(int newSize) {
        size = 0;
        set = new Node[newSize];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    int GetHash(Object value) {
        return (value.hashCode() & 0x7FFFFFFF) % set.length;
    }

    void AddNode(Node<E> newNode) {
        if (head == null) {
            head = newNode;
        } else {
            tail.after = newNode;
            newNode.previous = tail;
        }
        tail = newNode;
    }

    void RemoveNode(Node<E> newNode) {
        if (newNode.after != null)
            newNode.after.previous = newNode.previous;
        else
            tail = newNode.previous;

        if (newNode.previous != null)
            newNode.previous.after = newNode.after;
        else
            head = newNode.after;
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
        AddNode(newNode);
        size++;
        if (size > capacity * 0.75) {
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
                RemoveNode(current);
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
        boolean f = false;
        MyLinkedHashSet<E> tempSet = new MyLinkedHashSet<>(set.length);
        Node<E> current = head;
        while (current != null) {
            if (c.contains(current.data)) {
                tempSet.add(current.data);
                f = true;
            }
            current = current.after;
        }
        set = tempSet.set;
        head = tempSet.head;
        tail = tempSet.tail;
        size = tempSet.size;
        return f;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = false;
        for (Object obj: c) {
            if (remove(obj))
                f = true;
        }
        return f;
    }

    @Override
    public void clear() {
        for (int i = 0; i < set.length; i++)
            set[i] = null;
        head = null;
        tail = null;
        size = 0;
    }
}
