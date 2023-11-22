package by.it.group251001.litvinovich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    protected static class Node<E> {
        public E data;
        public Node<E> next;
        public int pos;

        public Node() {

        }

        public Node(E data, int pos) {
            this.data = data;
            this.pos = pos;
            next = null;
        }
    }

    protected static class List<E> {
        private Node<E> head, tail;


        public boolean contains(E o) {
            Node<E> curr = head;
            while (curr != null && !curr.data.equals(o))
                curr = curr.next;
            return curr != null;
        }

        public boolean add(E o, int pose, boolean toCheck) {
            if (toCheck && contains(o))
                return false;
            Node<E> curr = new Node<E>(o, pose);
            if (tail == null)
                head = tail = curr;
            else {
                tail.next = curr;
                tail = curr;
            }
            return true;
        }

        public boolean remove(E o) {
            if (head == null)
                return false;
            if (head.data.equals(o)) {
                head = head.next;
                return true;
            }
            Node<E> prev = head;
            while (prev.next != null && !prev.next.data.equals(o))
                prev = prev.next;
            if (prev.next == null)
                return false;
            if (prev.next == tail) {
                prev.next = null;
                tail = prev;
            } else
                prev.next = prev.next.next;
            return true;
        }
    }

    private int tableSize = 123;
    private int size = 0, lastpose = 0;
    private List[] table = new List[tableSize];

    {
        for (int i = 0; i < tableSize; i++)
            table[i] = new List<E>();
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Node<E>[] nods = new Node[tableSize];
        for (int i = 0; i < tableSize; i++)
            nods[i] = (Node<E>) table[i].head;
        while (true) {
            Node<E> minInf = new Node<E>((E) new Object(), Integer.MAX_VALUE);
            int minI = -1;
            for (int i = 0; i < tableSize; i++)
                if (nods[i] != null && nods[i].pos < minInf.pos) {
                    minInf = nods[i];
                    minI = i;
                }
            if (minI == -1)
                break;
            nods[minI] = nods[minI].next;
            res.append(minInf.data.toString()).append(", ");
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
        return table[o.hashCode() % tableSize].contains(o);
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
        if (table[e.hashCode() % tableSize].add(e, lastpose++, true))
            size++;
        else
            return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (table[o.hashCode() % tableSize].remove(o))
            size--;
        else
            return false;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean toReturn = false;
        for (E o : c)
            if (add(o))
                toReturn = true;
        return toReturn;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int deleted = 0;
        for (int i = 0; i < tableSize; i++) {
            List<E> newList = new List<>();
            Node<E> curr = table[i].head;
            while (curr != null) {
                if (c.contains(curr.data))
                    newList.add(curr.data, curr.pos, false);
                else
                    deleted++;
                curr = curr.next;
            }
            table[i] = newList;
        }
        size -= deleted;
        return deleted > 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int deleted = 0;
        for (int i = 0; i < tableSize; i++) {
            List<E> newList = new List<>();
            Node<E> curr = table[i].head;
            while (curr != null) {
                if (!c.contains(curr.data))
                    newList.add(curr.data, curr.pos, false);
                else
                    deleted++;
                curr = curr.next;
            }
            table[i] = newList;
        }
        size -= deleted;
        return deleted > 0;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < tableSize; i++)
            table[i] = new List<E>();
    }
}