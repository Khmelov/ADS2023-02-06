package by.it.group251001.brutskaya.lesson11;

import java.util.*;

public class MyHashSet<E> implements Set<E> {

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    int INITIAL_CAPACITY = 160;
    float LOAD_FACTOR = 0.75f;
    Node<E>[] array = new Node[INITIAL_CAPACITY];
    int size = 0;

    int hash(E e, int length) {
        return e.hashCode() % length;
    }

    void resize() {
        Node<E>[] newArray = new Node[size * 2];
        for (int i = 0; i < array.length; i++) {
            while (array[i] != null) {
                int index = hash(array[i].item, newArray.length);
                Node<E> newNode = new Node<>(array[i].item);
                if (newArray[index] == null)
                    newArray[index] = newNode;
                else {
                    Node<E> temp = newArray[index];
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = newNode;
                }
                array[i] = array[i].next;
            }
        }
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isEmpty())
            return "[]";
        s.append("[");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Node<E> temp = array[i];
                while (temp != null) {
                    s.append(temp.item).append(", ");
                    temp = temp.next;
                }
            }
        }
        if (size != 0) {
            s.delete(s.length() - 2, s.length());
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        int index = hash(e, array.length);

        Node<E> node = new Node<>(e);
        if (array[index] == null) {
            array[index] = node;
        } else {
            Node<E> temp = array[index];
            while (temp.next != null) {
                if (temp.item.equals(e)) {
                    return false;
                }
                temp = temp.next;
            }
            if (temp.item.equals(e))
                return false;
            temp.next = node;
        }
        if (++size > array.length * LOAD_FACTOR) {
            resize();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = hash((E) o, array.length);
        if (array[index] == null)
            return false;
        else {
            Node<E> temp = array[index];
            Node<E> prev = null;
            while (temp != null) {
                if (temp.item.equals(o)) {
                    if (prev != null) {
                        prev.next = temp.next;
                    } else {
                        array[index] = temp.next;
                    }
                    size--;
                    return true;
                }
                prev = temp;
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = hash((E) o, array.length);
        if (array[index] == null)
            return false;
        else {
            Node<E> temp = array[index];
            while (temp != null) {
                if (temp.item.equals(o))
                    return true;
                temp = temp.next;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////
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
