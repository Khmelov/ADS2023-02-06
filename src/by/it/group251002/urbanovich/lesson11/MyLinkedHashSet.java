package by.it.group251002.urbanovich.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private static class Node<E> {
        E value;
        Node<E> next, previous;

        Node(E value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    private class List<E> {
        Node<E> head, tail;

        boolean contains(Object o) {
            Node<E> currNode = head;
            while (currNode != null) {
                if (currNode.value.equals(o)) return true;
                currNode = currNode.next;
            }
            return false;
        }

        boolean add(E elem) {
            if (contains(elem)) return false;
            Node<E> newElem = new Node<E>(elem);
            if (head == null) head = newElem;
            else {
                newElem.previous = tail;
                tail.next = newElem;
            }
            tail = newElem;
            return true;
        }

        boolean remove(Object o) {
            if (!contains(o)) return false;
            else {
                if (head.value.equals(o) && tail.value.equals(o)) head = tail = null;
                else if (tail.value.equals(o)) {
                    tail = tail.previous;
                } else if (head.value.equals(o)) {
                    head = head.next;
                } else {
                    Node<E> currNode = head;
                    while (!currNode.value.equals(o)) currNode = currNode.next;
                    Node<E> cPrev = currNode.previous, cNext = currNode.next;
                    cPrev.next = cNext;
                    cPrev.previous = cPrev;

                }
                return true;
            }
        }

        boolean isEmpty() {
            return head == null;
        }
    }

    private int size = 0, capacity = 0;
    private List<E>[] container = new List[capacity];
    private E[] list_to_str = (E[]) new Object[]{};

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            builder.append(delimiter).append(list_to_str[i]);
            delimiter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
    public boolean contains(Object o) {
        return size != 0 && container[o.hashCode() % capacity].contains(o);
    }

    @Override
    public boolean add(E elem) {
        if (contains(elem)) return false;
        int new_cap = capacity;
        if (size == 0) new_cap = 16;
        else if ((float) size / capacity == 0.75f) new_cap = capacity * 3;
        List<E>[] temp = new List[new_cap];
        for (int i = 0; i < new_cap; i++)
            temp[i] = new List<>();
        for (int i = 0; i < capacity; i++) {
            while (!container[i].isEmpty()) {
                E tempVal = container[i].head.value;
                temp[tempVal.hashCode() % new_cap].add(tempVal);
                container[i].remove(container[i].head.value);
            }
        }
        List<E>[] tCont = container;
        container = temp;
        tCont = null;

        capacity = new_cap;
        container[elem.hashCode() % capacity].add(elem);
        list_to_str = Arrays.copyOf(list_to_str, capacity);

        list_to_str[size++] = elem;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        else {
            container[o.hashCode() % capacity].remove((E) o);
            int i = 0;
            while (i < size && !list_to_str[i].equals(o)) i++;
            System.arraycopy(list_to_str, i + 1, list_to_str, i, size - i - 1);
            size--;
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (size == 0) return false;
        for (Object elem : c)
            if (!contains(elem)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        for (Object elem : c)
            add((E) elem);
        return prevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0) return false;
        boolean retained = false;
        for (int i = 0; i < size; i++)
            if (!c.contains(list_to_str[i])) {
                remove(list_to_str[i--]);
                retained = true;
            }
        return retained;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (size == 0) return false;
        int prevSize = size;
        for (Object elem : c)
            if (contains(elem)) remove(elem);
        return size != prevSize;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            while (!container[i].isEmpty()) container[i].remove(container[i].head.value);
        }
        size = 0;
    }
}
