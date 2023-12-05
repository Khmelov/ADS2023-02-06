package by.it.group251003.gabrus.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private static class Node<E> {
        public E data;
        public Node<E> next = null;
        public Node<E> prevAdded = null;
        public Node<E> nextAdded = null;
        public Node(E data) {
            this.data = data;
        }
    }
    private Node<E> firstAdded = null;
    private Node<E> lastAdded = null;
    private static final int INITIAL_CAPACITY = 1;
    private int size = 0;
    private Node<E>[] arr = new Node[INITIAL_CAPACITY];

    private void resize() {
        int newSize = size < 5 ? 1 : size / 5;
        Node<E>[] newArr = new Node[newSize];

        Node<E> prevNode = null;
        Node<E> newFirstAdded = null;
        Node<E> newLastAdded = null;
        for (Node<E> node = firstAdded; node != null; node = node.nextAdded) {
            int hash = hash(node.data, newArr.length);
            Node<E> newNode = new Node<>(node.data);

            if (newArr[hash] == null) {
                newArr[hash] = newNode;
            } else {
                newNode.next = newArr[hash];
                newArr[hash] = newNode;
            }

            if (prevNode != null){
                newNode.prevAdded = prevNode;
                newNode.prevAdded.nextAdded = newNode;
            }

            if (node == firstAdded) {
                newFirstAdded = newNode;
            }

            if (node == lastAdded) {
                newLastAdded = newNode;
            }

            prevNode = newNode;
        }

        arr = newArr;
        firstAdded = newFirstAdded;
        lastAdded = newLastAdded;
    }

    private int hash(Object o, int size) {
        return (o.hashCode() & 0x7fffffff) % size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        for (Node<E> node = firstAdded; node != null; node = node.nextAdded) {
            str.append(node.data);
            str.append(", ");
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
        firstAdded = null;
        lastAdded = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Trying to add null element to set.");
        }

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

        if (firstAdded == null) {
            firstAdded = newNode;
            lastAdded = newNode;
        } else {
            lastAdded.nextAdded = newNode;
            newNode.prevAdded = lastAdded;
            lastAdded = newNode;
        }

        ++size;
        if (size % 5 == 0 && size / 5 != arr.length ) {
            resize();
        }

        return true;
    }
    private void remove(int hash, Node<E> prevNode, Node<E> node) {
        if (prevNode == null) {
            arr[hash] = node.next;
        } else {
            prevNode.next = node.next;
        }

        if (size > 1) {
            if (node == firstAdded) {
                firstAdded.nextAdded.prevAdded = null;
                firstAdded = firstAdded.nextAdded;
            } else if (node == lastAdded) {
                lastAdded.prevAdded.nextAdded = null;
                lastAdded = lastAdded.prevAdded;
            } else {
                node.prevAdded.nextAdded = node.nextAdded;
                node.nextAdded.prevAdded = node. prevAdded;
            }
        } else {
            firstAdded = null;
            lastAdded = null;
        }

        size--;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Trying to remove null element from set.");
        }

        if (size == 0) {
            return false;
        }

        int hash = hash(o, arr.length);
        Node<E> currNode = arr[hash], prevNode = null;

        while (currNode != null) {
            if (o.equals(currNode.data)) {
                remove(hash, prevNode, currNode);

                if (size % 5 == 0 && size / 5 != arr.length) {
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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;

        for (E e : c) {
            if (add(e)) {
                res = true;
            }
        }

        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;

        for (var e : c) {
            if (remove(e)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        int oldSize = size;

        for (int i = 0; i < arr.length; i++) {
            Node<E> prevNode = null;
            for (Node<E> node = arr[i]; node != null; node = node.next) {
                if (!c.contains(node.data)) {
                    remove(i, prevNode, node);
                    result = true;
                } else {
                    prevNode = node;
                }
            }
        }

        if (Math.abs(oldSize - size) >= 5) {
            resize();
        }

        return result;
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
}
