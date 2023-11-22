package by.it.group251003.gabrus.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private static class Node<E> {
        public E data;
        public Node<E> next = null;
        public Node(E data) {
            this.data = data;
        }
    }
    private static final int INITIAL_CAPACITY = 1;
    private int size = 0;
    private Node<E>[] arr = new Node[INITIAL_CAPACITY];

    private void resize() {
        Node<E>[] newArr = new Node[size / 5];

        for (Node<E> hashNode : arr) {
            for (Node<E> node = hashNode; node != null; node = node.next) {
                add(node.data, newArr);
            }
        }

        arr = newArr;
    }

    private int hash(Object o, int size) {
        return (o.hashCode() & 0x7fffffff) % size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        for (Node<E> hashNode : arr) {
            for (Node<E> node = hashNode; node != null; node = node.next) {
                str.append(node.data);
                str.append(", ");
            }
        }


        str.setLength(Math.abs(str.length() - 2));
        str.append(']');

        return str.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        arr = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean add(E e, Node<E>[] arr) {
        int hash = hash(e, arr.length);

        Node<E> newNode = new Node<>(e);
        Node<E> prevNode = null;
        for (Node<E> node = arr[hash]; node != null; node = node.next) {
            if (e.equals(node.data)) {
                return false;
            }

            prevNode = node;
        }

        if (prevNode == null) {
            arr[hash] = newNode;
        } else {
            prevNode.next = newNode;
        }

        return true;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Trying to add null element to set.");
        }

        boolean res = add(e, arr);

        if (res) {
            ++size;
            if (size % 5 == 0 && size / 5 != arr.length ) {
                resize();
            }
        }

        return res;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Trying to remove null element from set.");
        }

        int hash = hash(o, arr.length);
        Node<E> currNode = arr[hash], prevNode = null;

        while (currNode != null) {
            if (o.equals(currNode.data)) {
                if (prevNode == null) {
                    arr[hash] = currNode.next;
                } else {
                    prevNode.next = currNode.next;
                }

                --size;
                if (size % 5 == 0 && size / 5 != arr.length && size != 0) {
                    resize();
                }

                return true;
            }

            prevNode = currNode;
            currNode = currNode.next;
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Trying to find null element in set.");
        }

        for(Node<E> currNode = arr[hash(o, arr.length)]; currNode != null; currNode = currNode.next) {
            if (o.equals(currNode.data)) {
                return true;
            }
        }

        return false;
    }

    //---------------------------------------------------------------------------

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
