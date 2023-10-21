package by.it.group251001.sevamisiul.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private int size;
    private final int capacity = 1;

    private static class Node<E> {
        E data;
        Node<E> previous;
        Node<E> next;

        private Node(E data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private static class List<E> {
        Node<E> first, last;

        public boolean contains(Object o) {
            Node<E> tmp = first;
            while (tmp != null && !tmp.data.equals(o))
                tmp = tmp.next;
            return tmp != null;
        }

        public boolean add(E o) {
            if (this.contains(o))
                return false;
            Node<E> tmpNode = new Node<E>(o);
            if (first == null) {
                first = tmpNode;
            } else {
                tmpNode.previous = last;
                last.next = tmpNode;
            }
            last = tmpNode;
            return true;
        }

        public boolean remove(Object o) {
            Node<E> tmpNode = first;
            while (tmpNode != null && !o.equals(tmpNode.data))
                tmpNode = tmpNode.next;
            if (tmpNode != null) {
                Node<E> tmpP = tmpNode.previous;
                Node<E> tmpN = tmpNode.next;
                if (tmpP != null)
                    tmpP.next = tmpN;
                if (tmpN != null)
                    tmpN.previous = tmpP;
                if (tmpNode == first)
                    first = first.next;
                if (tmpNode == last)
                    last = last.previous;
                return true;
            }
            return false;
        }
    }

    transient List<E>[] elementData;

    public MyLinkedHashSet() {
        elementData = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            elementData[i] = new List<E>();
        }
        size = 0;
    }

    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < capacity; i++) {
            Node<E> tmp = elementData[i].first;
            while (tmp != null) {
                res.append(tmp.data.toString()).append(", ");
                tmp = tmp.next;
            }
        }
        return res.substring(0, res.length() - 2) + "]";
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
        return elementData[o.hashCode() % capacity].contains(o);
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
        if (elementData[e.hashCode() % capacity].add(e))
            size++;
        else
            return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (elementData[o.hashCode() % capacity].remove(o))
            size--;
        else
            return false;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int deleted = 0;
        for (int i = 0; i < capacity; i++) {
            List<E> newList = new List<>();
            Node<E> cur = elementData[i].first;
            while (cur != null) {
                if (c.contains(cur.data))
                    newList.add(cur.data);
                else
                    deleted++;
                cur = cur.next;
            }
            elementData[i] = newList;
        }
        size -= deleted;
        return deleted != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int deleted = 0;
        for (int i = 0; i < capacity; i++) {
            List<E> newList = new List<>();
            Node<E> cur = elementData[i].first;
            while (cur != null) {
                if (!c.contains(cur.data))
                    newList.add(cur.data);
                else
                    deleted++;
                cur = cur.next;
            }
            elementData[i] = newList;
        }
        size -= deleted;
        return deleted != 0;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            elementData[i] = new List<>();
        }
    }
}
