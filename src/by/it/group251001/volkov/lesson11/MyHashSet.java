package by.it.group251001.volkov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    protected static class LNode<E> {
        public E data;
        MyHashSet.LNode<E> next;

        LNode(E data) {
            this.data = data;
            next = null;
        }
    }

    private int DefaultSize = 100;
    private int actSize = 0;

    protected static class MyList<E> {
        MyHashSet.LNode<E> head, tail;

        public boolean contains(E o) {
            MyHashSet.LNode<E> curr = head;
            while (curr != null && !curr.data.equals(o)) {
                curr = curr.next;
            }
            return curr != null;
        }

        public boolean add(E o) {
            if (contains(o)) {
                return false;
            }
            LNode<E> curr = new LNode<E>(o);
            if (tail == null) {
                head = tail = curr;
            } else {
                tail.next = curr;
                tail = curr;
            }
            return true;
        }

        public boolean remove(E o){
            if(head == null) {
                return false;
            }
            if(head.data.equals(o)){
                head = head.next;
                return true;
            }
            LNode<E> prev = head;
            while(prev.next!=null && !prev.next.data.equals(o)) {
                prev = prev.next;
            }
            if(prev.next == null) {
                return false;
            }
            if(prev.next == tail) {
                prev.next = null;
                tail = prev;
            }
            else {
                prev.next = prev.next.next;
            }
            return true;
        }
    }

    private MyHashSet.MyList[] map = new MyHashSet.MyList[DefaultSize];
    {
        for(int i = 0; i < DefaultSize; i++) {
            map[i] = new MyHashSet.MyList<E>();
        }
    }

    public int size() {
        return actSize;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < DefaultSize; i++) {
            LNode curr = map[i].head;
            while(curr!=null){
                res.append(curr.data.toString()).append(", ");
                curr = curr.next;
            }
        }
        return res.substring(0,res.length()-2)+"]";
    }

    public void clear() {
        actSize = 0;
        for(int i = 0; i < DefaultSize; i++) {
            map[i] = new MyList<E>();
        }
    }

    public boolean isEmpty() {
        return actSize == 0;
    }

    public boolean add(E e) {
        if(map[e.hashCode()%DefaultSize].add(e)) {
            actSize++;
        }
        else {
            return false;
        }
        return true;
    }

    public boolean remove(Object o) {
        if(map[o.hashCode()%DefaultSize].remove(o))
            actSize--;
        else {
            return false;
        }
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

    public boolean contains(Object o) {
        return map[o.hashCode()%DefaultSize].contains(o);
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
}
