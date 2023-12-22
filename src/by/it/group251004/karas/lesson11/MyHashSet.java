package by.it.group251004.karas.lesson11;

import java.util.*;
public class MyHashSet<E> implements Set<E>{

    private static class Node<E> {
        Node next;
        E value;

        public Node(E e) {
            this.value = e;
            this.next = null;
        }

    }

    private int _size = 0;
    private Node<E>[]_buf = (Node<E>[]) new Node[]{};

    private void grow() {
        Node<E>[] oldBuf = _buf;
        _buf = (Node<E>[]) new Node[(_size * 3) / 2 + 1];
        for (Node<E> eNode : oldBuf) {
            Node<E> cur = eNode;
            while (cur != null) {
                int index = cur.value.hashCode() % _buf.length;
                addToList(index, cur.value);
                cur = cur.next;
            }
        }
    }

    private boolean addToList(int index, E e) {
        boolean ok = false;

        if (_buf[index] == null) {
            _buf[index] = new Node<>(e);
            ok = true;
        } else {
            Node<E> cur = _buf[index];
            while (cur.next != null) {
                if (e.equals(cur.value)) {
                    return false;
                }
                cur = cur.next;
            }
            if (!e.equals(cur.value)) {
                cur.next = new Node<>(e);
                ok = true;
            }
        }

        return ok;
    }

    private boolean removeFromList(int index, Object o) {
        boolean ok = false;
        Node<E> cur = _buf[index];

        if (cur != null) {
            if (o.equals(cur.value)) {
                _buf[index] = cur.next;
                ok = true;
            } else {
                while (cur.next != null && !o.equals(cur.next.value)) {
                    cur = cur.next;
                }
                if (cur.next != null) {
                    cur.next = (Node<E>) cur.next.next;
                    ok = true;
                }
            }
        }

        return ok;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (Node<E> eNode : _buf) {
            Node<E> cur = eNode;
            while (cur != null) {
                sb.append(delimiter).append(cur.value);
                delimiter = ", ";
                cur = cur.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean ok = false;

        int index = o.hashCode() % _buf.length;
        Node<E> cur = _buf[index];
        while (cur != null) {
            if (o.equals(cur.value)) {
                ok = true;
                break;
            }
            cur = cur.next;
        }

        return ok;
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
        if (_size == _buf.length) {
            grow();
        }
        int index = e.hashCode() % _buf.length;
        boolean ok = addToList(index, e);
        if (ok) {
            _size++;
        }
        return ok;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int index = o.hashCode() % _buf.length;
        boolean ok = removeFromList(index, o);
        if (ok) {
            _size--;
        }
        return ok;
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
        Arrays.fill(_buf, null);
        _size = 0;
    }
}