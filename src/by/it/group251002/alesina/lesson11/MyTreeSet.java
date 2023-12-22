package by.it.group251002.alesina.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private static class Node<E> {
        public E data;
        public Node<E> next = null;
        public Node<E> lower = null;
        public Node<E> higher = null;
        public Node(E data) {
            this.data = data;
        }
    }
    private Node<E> min = null;  //минимальный элемент
    private Node<E> max = null;   //максимальный элемент
    private static final int CAPACITY = 1;
    private int size = 0;
    private Node<E>[] arr = new Node[CAPACITY];

    private void reSize() {
        int newSize = size < 5 ? 1 : size / 5;
        Node<E>[] newArr = new Node[newSize];

        Node<E> prevNode = null;
        Node<E> newMin = null;
        Node<E> newMax = null;
        for (Node<E> node = min; node != null; node = node.higher) {
            int hash = hash(node.data, newArr.length);
            Node<E> newNode = new Node<>(node.data);

            if (newArr[hash] == null) {
                newArr[hash] = newNode;
            } else {
                newNode.next = newArr[hash];
                newArr[hash] = newNode;
            }

            if (prevNode != null){
                newNode.lower = prevNode;
                newNode.lower.higher = newNode;
            }

            if (node == min) {
                newMin = newNode;
            }

            if (node == max) {
                newMax = newNode;
            }

            prevNode = newNode;
        }

        arr = newArr;
        min = newMin;
        max = newMax;
    }

    private int hash(Object o, int size) {
        return (o.hashCode() & 0x7fffffff) % size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        for (Node<E> node = min; node != null; node = node.higher) {
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
        arr = new Node[CAPACITY];
        size = 0;
        min = null;
        max = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Can't add a null element to the set.");
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

        if (min == null) {
            min = newNode;
            max = newNode;
        } else {
            Node<E> node = min;
            while (node != null && node.data.compareTo(newNode.data) < 0) {
                node = node.higher;
            }

            if (node == min) {
                newNode.higher = min;
                min.lower = newNode;
                min = newNode;
            } else if (node == null) {
                max.higher = newNode;
                newNode.lower = max;
                max = newNode;
            } else {
                newNode.lower = node.lower;
                newNode.higher = node;
                node.lower.higher = newNode;
                node.lower = newNode;
            }
        }

        ++size;
        if (size % 5 == 0 && size / 5 != arr.length ) {
            reSize();
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
            if (node == min) {
                min.higher.lower = null;
                min = min.higher;
            } else if (node == max) {
                max.lower.higher = null;
                max = max.lower;
            } else {
                node.lower.higher = node.higher;
                node.higher.lower = node.lower;
            }
        } else {
            min = null;
            max = null;
        }

        size--;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Can't remove a null element from the set.");
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
                    reSize();
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
            throw new NullPointerException("Trying to find null element in the set.");
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
        boolean done = false;

        for (E e : c) {
            if (add(e)) {
                done = true;
            }
        }

        return done;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean done = false;

        for (var e : c) {
            if (remove(e)) {
                done = true;
            }
        }

        return done;
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
            reSize();
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