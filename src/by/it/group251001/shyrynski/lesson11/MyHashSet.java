package by.it.group251001.shyrynski.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    protected static class Node<E> {
        public E data;
        public Node<E> next;

        public Node() {

        }

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    protected static class DemoList<E>{
        private Node<E> head, back;
        public boolean contains(E o){
            Node<E> cur = head;
            while(cur!=null && !cur.data.equals(o))
                cur = cur.next;
            return cur != null;
        }
        public boolean add(E o){
            if(contains(o))
                return false;
            Node<E> cur = new Node<E>(o);
            if(back == null)
                head = back = cur;
            else {
                back.next = cur;
                back = cur;
            }
            return true;
        }
        public boolean remove(E o){
            if(head == null)
                return false;
            if(head.data.equals(o)){
                head = head.next;
                return true;
            }
            Node<E> prev = head;
            while(prev.next!=null && !prev.next.data.equals(o))
                prev = prev.next;
            if(prev.next == null)
                return false;
            if(prev.next == back) {
                prev.next = null;
                back = prev;
            }
            else
                prev.next = prev.next.next;
            return true;
        }
    }
    private int tableSize = 123;
    private int siz = 0;
    private DemoList[] table = new DemoList[tableSize];

    {
        for(int i=0;i<tableSize;i++)
            table[i] = new DemoList<E>();
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < tableSize; i++) {
            Node cur = table[i].head;
            while(cur!=null){
                res.append(cur.data.toString()).append(", ");
                cur = cur.next;
            }
        }
        return res.substring(0,res.length()-2)+"]";
    }

    @Override
    public int size() {
        return siz;
    }

    @Override
    public boolean isEmpty() {
        return siz == 0;
    }

    @Override
    public boolean contains(Object o) {
        return table[o.hashCode()%tableSize].contains(o);
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
        if(table[e.hashCode()%tableSize].add(e))
            siz++;
        else
            return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(table[o.hashCode()%tableSize].remove(o))
            siz--;
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
        siz = 0;
        for(int i=0;i<tableSize;i++)
            table[i] = new DemoList<E>();
    }
}
