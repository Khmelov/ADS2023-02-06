package by.it.group251001.vasilenko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    int size = 0;

    private static class Node<E> {
        E data;
        Node<E> next;
        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class List<E> {
        int size = 0;
        Node<E> first, last;
        public boolean contains(Object o) {
            Node<E> x = first;
            while (x!=null){
                if (o.equals(x.data)) return true;
                x = x.next;
            }
            return false;
        }

        public boolean add(E e) {
            if (this.contains(e))
                return false;
            Node<E> x= new Node<E>(e);
            if (first == null) {
                first = x;
                last = x;
            } else {
                last.next = x;
                last = x;
            }
            size++;
            return true;
        }

        public boolean remove(Object o) {
            Node<E> tmp = first;
            for (Node<E> x = first; x!=null; x = x.next){
                if (o.equals(x.data)) {
                    if (x!=first && x!=last) {
                        tmp.next = x.next;
                        x = null;
                    }
                    else
                    {
                        if (x==last && x!=first){
                            tmp.next = null;
                            last = tmp;
                        }
                        else
                        {
                            first = x.next;
                            tmp = null;
                        }
                    }
                    return true;
                }
                tmp = x;
            }
            return false;
        }
    }

    transient List<E>[] elements;

    public MyHashSet() {
        elements = new List[5];
        for (int i = 0; i < 5; i++) {
            elements[i] = new List<E>();
        }
        size = 0;
    }

    public void ReHash(int newSize) {
        List<E>[] x=  new List[elements.length];
        System.arraycopy(elements,0,x,0,elements.length);
        elements = new List[newSize];
        for (int i = 0; i < newSize; i++) {
            elements[i] = new List<E>();
        }
        for (int i = 0; i < x.length; i++) {
            Node<E> tmp =x[i].first;
            while(tmp!=null){
                elements[tmp.data.hashCode() % newSize].add(tmp.data);
                tmp = tmp.next;
            }
        }
    }

    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < elements.length; i++) {
            Node<E> tmp = elements[i].first;
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
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return elements[o.hashCode() % elements.length].contains(o);
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
        if (elements[e.hashCode() % elements.length].add(e)) {
            size++;
            if (size*4/3>= elements.length) {
                ReHash(elements.length * 3 / 2);
            }
        }
        else
            return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (elements[o.hashCode() % elements.length].remove(o))
            size--;
        else
            return false;
        return true;
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
        size = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new List<>();
        }
    }
}
