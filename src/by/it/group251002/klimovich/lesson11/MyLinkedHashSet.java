package by.it.group251002.klimovich.lesson11;

import by.it.group251002.klimovich.lesson09.ListC;

import java.util.*;

public class MyLinkedHashSet<E> implements Set<E> {
    class ListD<E>{
        public class Node<E>{
            E elem;
            Node<E> next,prev=null;
        }
        private Node<E>[] elems =  new Node[]{};
        private int size=0;
        public Node<E> add(E e,Node<E> prev) {
            if (size==elems.length){
                Node<E>[] arr = new Node[size*3/2+1];
                System.arraycopy(elems,0,arr,0,size);
                elems=arr;
            }
            elems[size]=new Node<E>();
            if (prev!=null) {
                prev.next = elems[size];
            }
            elems[size].prev=prev;
            elems[size].next=null;
            elems[size].elem=e;
            size++;
            return elems[size-1];
        }
        public int size(){
            return size;
        }
        public Node<E> remove(Object o) {
            Node <E> res=null;
            if (o == null) {
                for (int i = 0; i < size; i++) {
                    if (elems[i].elem == null) {
                        if (elems[i].prev!=null && elems[i].next!=null){
                            elems[i].next.prev=elems[i].prev;
                            elems[i].prev.next=elems[i].next;
                        } else if (elems[i].prev==null) {
                            res=elems[i].next;
                            elems[i].next.prev=elems[i].prev;
                        } else if (elems[i].next==null && elems[i].prev!=null) {
                            elems[i].prev.next=elems[i].next;
                        }
                        System.arraycopy(elems,i+1,elems,i,size-i-1);
                        size--;
                        return res;
                    }
                }
            }
            else {
                for (int i = 0; i < size; i++) {
                    if (o.equals(elems[i].elem)) {
                        if (elems[i].prev!=null && elems[i].next!=null){
                            elems[i].next.prev=elems[i].prev;
                            elems[i].prev.next=elems[i].next;
                        } else if (elems[i].prev==null) {
                            res=elems[i].next;
                            elems[i].next.prev=elems[i].prev;
                        } else if (elems[i].next==null && elems[i].prev!=null){
                            elems[i].prev.next=elems[i].next;
                        }
                        System.arraycopy(elems,i+1,elems,i,size-i-1);
                        size--;
                        return res;
                    }
                }
            }
            return null;
        }
        public boolean contains(Object o){
            if (o == null) {
                for (int i = 0; i < size; i++) {
                    if (elems[i].elem == null) {
                        return true;
                    }
                }
            }
            else {
                for (int i = 0; i < size; i++) {
                    if (o.equals(elems[i].elem)) {
                        return true;
                    }
                }
            }
            return false;
        }
        public boolean IsEmpty(){
            if (size==0){
                return true;
            }
            return false;
        }
    }
    private ListD<E>[] elems = new ListD[]{};
    private Integer[] hashes = (Integer[]) new Integer[]{};
    int size=0;
    private ListD<E>.Node<E> prev=null;
    private ListD<E>.Node<E> head=null;
    int elemSize=0;

    private void CheckSize() {
        if (size == elems.length) {
            ListD<E>[] arr = (ListD<E>[]) new ListD[size * 3 / 2 + 1];
            System.arraycopy(elems, 0, arr, 0, size);
            elems = arr;
            Integer[] arrr = (Integer[]) new Integer[size * 3 / 2 + 1];
            System.arraycopy(hashes, 0, arrr, 0, size);
            hashes = arrr;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String sep ="";
        ListD<E>.Node<E> cur=head;
        while (cur!=null){
            sb.append(sep).append(cur.elem.toString());
            sep=", ";
            cur=cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return elemSize;
    }

    @Override
    public void clear() {
        elems = null;
        hashes = null;
        head=null;
        elems = (ListD<E>[]) new ListD[]{};
        hashes = (Integer[]) new Integer[]{};
        size=0;
        elemSize=0;
    }

    @Override
    public boolean isEmpty() {
        if (elemSize==0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        CheckSize();
        int hash = e.hashCode();
        if (elemSize==0){
            hashes[size]=hash;
            elems[size] = new ListD<E>();
            head=elems[0].add(e,prev);
            prev=head;
            size++;
            elemSize++;
            return true;
        }
        for (int i=0;i<size;i++){
            if (hash==hashes[i]){
                if (!elems[i].contains(e)){
                    prev=elems[i].add(e,prev);
                    elemSize++;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        hashes[size]=hash;
        elems[size] = new ListD<E>();
        prev=elems[size].add(e,prev);
        size++;
        elemSize++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        CheckSize();
        ListD<E>.Node<E> elem;
        int hash = o.hashCode();
        for (int i=0;i<size;i++){
            if (hash==hashes[i]){
                if (elems[i].contains(o)){
                    elem=elems[i].remove(o);
                    if (elem!=null){
                        head=elem;
                    }
                    elemSize--;
                    if (elems[i].IsEmpty()){
                        size--;
                        elems[i]=elems[size];
                        hashes[i]=hashes[size];
                        elems[size]=null;
                        hashes[size]=null;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean contains(Object o) {
        CheckSize();
        int hash = o.hashCode();
        for (int i=0;i<size;i++) {
            if (hash == hashes[i]) {
                if (elems[i].contains(o)) {
                    return true;
                }
            }
        }
        return false;
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
    public boolean containsAll(Collection<?> c) {
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            if  (!contains(arr[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            add(arr[i]);
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = elemSize;
        for (int i = size-1; i >-1; i--) {
            for (int j = elems[i].size()-1; j > -1; j--) {
                if (!c.contains(elems[i].elems[j].elem)) {
                    remove(elems[i].elems[j].elem);
                }
            }
            if (prevSize != elemSize) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            if  (contains(arr[i])){
                remove(arr[i]);
            }
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }
}
