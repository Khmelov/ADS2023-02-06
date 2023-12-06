package by.it.group251002.koryakova.lesson11;

import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class MyLinkedHashSet<E> implements Set<E>{
    protected static class Node<E> {
        public E data;
        public Node<E> next;
        public int pos;
        public Node(E data, int actpos) {
            this.data = data;
            this.pos = actpos;
            next = null;
        }
    }
    private int defsize = 100;
    private int size = 0;
    private int lastpos = 0;
    protected static class MyList<E> {
        private Node<E> head, tail;

        public boolean contains(E o) {
            Node<E> curr = head;
            while ((curr != null) && !curr.data.equals(o)) {
                curr = curr.next;
            }
            return (curr != null);
        }
        public boolean add(E o, int actpos, boolean needsCheck) {
            if (needsCheck && contains(o)) {
                return false;
            }
            Node<E> curr = new Node<E>(o, actpos);
            if (tail == null) {
                head = tail = curr;
            }
            else {
                tail.next = curr;
                tail = curr;
            }
            return true;
        }
        public boolean remove(E o) {
            if (head == null) {
                return false;
            }
            if (head.data.equals(o)) {
                head = head.next;
                return true;
            }
            Node<E> prev = head;
            while ((prev.next != null) && !prev.next.data.equals(o)) {
                prev = prev.next;
            }
            if (prev.next == null) {
                return false;
            }
            if (prev.next == tail) {
                prev.next = null;
                tail = prev;
            }
            else {
                prev.next = prev.next.next;
            }
            return true;
        }
    }
    private MyList[] map = new MyList[defsize];{
        int i;
        for (i = 0; i < defsize; i++) {
            map[i] = new MyList<E>();
        }
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder rez = new StringBuilder("[");
        Node<E>[] nodes = new Node[defsize];
        int i;
        for (i = 0; i < defsize; i++) {
            nodes[i] = (Node<E>) map[i].head;
        }
        while(true){
            Node<E> mininf = new Node<E>((E) new Object(), Integer.MAX_VALUE);
            int min_i = -1;
            for(i = 0; i < defsize; i++){
                if((nodes[i] != null) && (nodes[i].pos < mininf.pos)){
                    mininf = nodes[i];
                    min_i = i;
                }
            }
            if(min_i == -1){
                break;
            }
            nodes[min_i] = nodes[min_i].next;
            rez.append(mininf.data.toString()).append(", ");
        }
        return rez.substring(0, rez.length() - 2) + "]";
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        size = 0;
        int i;
        for(i = 0; i < defsize; i++){
            map[i] = new MyList<E>();
        }
    }
    @Override
    public boolean isEmpty(){
        return (size == 0);
    }
    @Override
    public boolean add(E e){
        if(map[e.hashCode() % defsize].add(e, lastpos++, true)){
            size = size + 1;
        }
        else{
            return false;
        }
        return true;
    }
    @Override
    public boolean remove(Object o){
        if(map[o.hashCode() % defsize].remove(o)){
            size = size - 1;
        }
        else{
            return false;
        }
        return true;
    }
    @Override
    public boolean contains(Object o){
        return map[o.hashCode() % defsize].contains(o);
    }
    @Override
    public boolean containsAll(Collection<?> c){
        for(Object o : c){
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        boolean added = false;
        for(E o : c){
            if(add(o)){
                added = true;
            }
        }
        return added;
    }
    @Override
    public boolean removeAll(Collection<?> c){
        int removed = 0;
        int i;
        for (i = 0; i < defsize; i++) {
            MyList<E> newMyList = new MyList<>();
            Node<E> curr = map[i].head;
            while (curr != null) {
                if (!c.contains(curr.data)) {
                    newMyList.add(curr.data, curr.pos, false);
                }
                else {
                    removed = removed + 1;
                }
                curr = curr.next;
            }
            map[i] = newMyList;
        }
        size = size - removed;
        return (removed > 0);
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        int removed = 0;
        int i;
        for (i = 0; i < defsize; i++) {
            MyList<E> newMyList = new MyList<>();
            Node<E> curr = map[i].head;
            while (curr != null) {
                if (c.contains(curr.data)) {
                    newMyList.add(curr.data, curr.pos, false);
                }
                else {
                    removed = removed + 1;
                }
                curr = curr.next;
            }
            map[i] = newMyList;
        }
        size = size - removed;
        return (removed > 0);
    }
    @Override
    public Iterator<E> iterator(){
        return null;
    }

    @Override
    public Object[] toArray(){
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a){
        return null;
    }
}