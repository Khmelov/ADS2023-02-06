package by.it.group251003.evt.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
    private static int INITIAL_CAPACITY = 16;
    private static float DEFAULT_LOAD_FACTOR = 7.5f;

    private Node<E>[] arr = new Node[INITIAL_CAPACITY];
    private int size = 0;

    private int hash(E o, int len) {
        return ((o.hashCode() & 0x7fffffff) % len);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                Node<E> curr = arr[i];
                while (curr != null) {
                    sb.append(curr.item + ", ");
                    curr = curr.next;
                }
            }
        }
        if (size != 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append(']').toString();
    }

    private void resize() {
        Node<E>[] newarr = new Node[size * 2];
        for (Node<E> node : arr) {
            while (node != null) {
                int bucket = hash(node.item, newarr.length);
                Node<E> newNode = new Node<>(node.item);
                if (newarr[bucket] == null) {
                    newarr[bucket] = newNode;
                } else {
                    Node<E> tmp = newarr[bucket];
                    while (tmp.next != null)
                        tmp = tmp.next;
                    tmp.next = newNode;
                }
                node = node.next;
            }
        }
        arr = newarr;
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
        int bucket = hash((E) o, arr.length);
        if (arr[bucket] == null) {
            return false;
        } else {
            Node<E> tmp = arr[bucket];
            while (tmp != null) {
                if (tmp.item.equals(o)) {
                    return true;
                }
                tmp = tmp.next;
            }
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
        int bucket = hash(e, arr.length);

        Node<E> node = new Node<>(e);
        if (arr[bucket] == null) {
            arr[bucket] = node;
        } else {
            Node<E> tmp = arr[bucket];
            while (tmp.next != null) {
                if (tmp.item.equals(e)) return false;
                tmp = tmp.next;
            }
            if (tmp.item.equals(e)) return false;
            tmp.next = node;
        }
        if (++size > arr.length * DEFAULT_LOAD_FACTOR) resize();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int bucket = hash((E) o, arr.length);

        if (arr[bucket] == null) {
            return false;
        } else {
            Node<E> tmp = arr[bucket];
            Node<E> prev = null;
            while (tmp != null) {
                if (tmp.item.equals(o)) {
                    if (prev != null) {
                        prev.next = tmp.next;
                    } else {
                        arr[bucket] = tmp.next;
                    }
                    size--;
                    return true;
                }
                prev = tmp;
                tmp = tmp.next;
            }
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
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        size = 0;
    }
}