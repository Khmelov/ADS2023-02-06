package by.it.group251002.klimovich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import by.it.group251002.klimovich.lesson09.ListC;

public class MyHashSet<E> implements Set<E> {
    private ListC<E>[] elems = (ListC<E>[]) new ListC[]{};
    private Integer[] hashes = (Integer[]) new Integer[]{};
    int size=0;
    int elemSize=0;

    private void CheckSize() {
        if (size == elems.length) {
            ListC<E>[] arr = (ListC<E>[]) new ListC[size * 3 / 2 + 1];
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
        for (int i=0;i < size; i++){
            sb.append(sep).append(elems[i].toString().substring(1,elems[i].toString().length()-1));
            sep=", ";
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
        elems = (ListC<E>[]) new ListC[]{};
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
        for (int i=0;i<size;i++){
            if (hash==hashes[i]){
                if (!elems[i].contains(e)){
                    elems[i].add(e);
                    elemSize++;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        hashes[size]=hash;
        elems[size] = new ListC<E>();
        elems[size].add(e);
        size++;
        elemSize++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        CheckSize();
        int hash = o.hashCode();
        for (int i=0;i<size;i++){
            if (hash==hashes[i]){
                if (elems[i].contains(o)){
                    elems[i].remove(o);
                    elemSize--;
                    if (elems[i].isEmpty()){
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

