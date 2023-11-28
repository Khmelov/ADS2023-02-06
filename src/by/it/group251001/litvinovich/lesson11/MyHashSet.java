package by.it.group251001.litvinovich.lesson11;

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

    protected static class List<E>{
        private Node<E> head, tail;
        public boolean contains(E o){
            Node<E> cur = head;
            while(cur!=null && !cur.data.equals(o))
                cur = cur.next;
            return cur != null;
        }
        public boolean add(E o){
            if(contains(o))
                return false;
            Node<E> curr = new Node<E>(o);
            if(tail == null)
                head = tail = curr;
            else {
                tail.next = curr;
                tail = curr;
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
            if(prev.next == tail) {
                prev.next = null;
                tail = prev;
            }
            else
                prev.next = prev.next.next;
            return true;
        }
    }
    private int tableSize = 123;
    private int size = 0;
    private List[] table = new List[tableSize];

    {
        for(int i=0;i<tableSize;i++)
            table[i] = new List<E>();
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < tableSize; i++) {
            Node curr = table[i].head;
            while(curr!=null){
                res.append(curr.data.toString()).append(", ");
                curr = curr.next;
            }
        }
        return res.substring(0,res.length()-2)+"]";
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
            size++;
        else
            return false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(table[o.hashCode()%tableSize].remove(o))
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
        for(int i=0;i<tableSize;i++)
            table[i] = new List<E>();
    }
}
