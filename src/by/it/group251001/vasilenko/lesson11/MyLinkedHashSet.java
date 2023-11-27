package by.it.group251001.vasilenko.lesson11;

import by.it.group251001.vasilenko.lesson10.MyLinkedList;

import java.util.*;

public class MyLinkedHashSet<E> implements Set<E> {
    int size = 0;

    private static class Node<E> {
        E data;
        DNode<E> p;
        Node<E> next;
        private Node(E data) {
            this.p = p;
            this.data = data;
            this.next = null;
        }
    }
    public static class DNode<E> {
        E data;
        DNode<E> prev;
        DNode<E> next;

        private DNode(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    public static class DList<E> {
        int siz= 0;
        DNode<E> first ;
        DNode<E> last ;
        public DNode<E> addLast(E e) {
            DNode<E> x= new DNode<E>(e);
            if (first == null) {
                first = x;
                last = x;
            } else {
                last.next = x;
                x.prev = last;
                last = x;
            }
            siz++;
            return x;
        }

        public void remove(DNode<E> x) {
            if (x==first){
                pollFirst();
            }
            else{
                if (x == last) {
                    pollLast();
                }
                else{
                    DNode<E> tmp= x.prev;
                    tmp.next = x.next;
                    x.next.prev = tmp;
                    x = null;
                }
            }
            siz--;
        }

        public E pollFirst() {
            DNode<E> x = first;
            first = first.next;
            if (first!=null)first.prev = null;
            return x.data;
        }

        public E pollLast() {
            DNode<E> x = last;
            last = last.prev;
            last.next = null;
            return x.data;
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

        public boolean add(E e,DNode<E> d) {
            if (this.contains(e))
                return false;
            Node<E> x= new Node<E>(e);
            x.p = d;
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

        public DNode<E> remove(Object o) {
            Node<E> tmp = first;
            for (Node<E> x = first; x!=null; x = x.next){
                if (o.equals(x.data)) {
                    if (x!=first && x!=last) {
                        tmp.next = x.next;;
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
                    return x.p;
                }
                tmp = x;
            }
            return null;
        }
    }

    transient List<E>[] elements;
    DList<E> res =new DList<>() ;

    public MyLinkedHashSet() {
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
                elements[tmp.data.hashCode() % newSize].add(tmp.data,tmp.p);
                tmp = tmp.next;
            }
        }
    }

    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder sb  = new StringBuilder("[");
        for(DNode<E> tmp = res.first; tmp != null; tmp = tmp.next){
            sb.append(tmp.data.toString()).append(", ");
        }
        if (res.first != null) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
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
        if (!contains(e)) {
            size++;
            elements[e.hashCode() % elements.length].add(e,res.addLast(e));
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
        if (elements[o.hashCode() % elements.length].contains(o)){
            res.remove(elements[o.hashCode() % elements.length].remove(o));
            size--;
        }
        else
            return false;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[]  arr = c.toArray();
        for (Object i : arr){
            if (!contains(i)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean mod = false;
        for (E e : c)
            if (add(e))
                mod = true;
        return mod;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int del = size;
        for (int i = 0; i < elements.length; i++) {
            Node<E> cur = elements[i].first;
            while (cur != null) {
                if (!c.contains(cur.data))
                    res.remove(elements[i].remove(cur.data));
                cur = cur.next;
            }
        }
        size = res.siz;;
        return size!=del;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int del = size;

        for (int i = 0; i < elements.length; i++) {
           Node<E> cur = elements[i].first;
            while (cur != null) {
                if (c.contains(cur.data))
                    res.remove(elements[i].remove(cur.data));;
                cur = cur.next;
            }
        }
        size = res.siz;
        return size!=del;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new List<>();
        }
        clearRes();

    }
    public void clearRes() {
        DNode<E> tmp;
        for (tmp = res.first.next; tmp!= null ; tmp = tmp.next) {
            tmp.prev = null;
        }
        res.first = null;
        res.last = null;
        tmp = null;
        res.siz = 0;
    }
}

