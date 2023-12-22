package by.it.group251002.klimovich.lesson11;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private E[] elems = (E[]) new Object[]{};
    private int size=0;

    private void CheckSize() {
        if (size == elems.length) {
            E[] arr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elems, 0, arr, 0, size);
            elems = arr;
        }
    }
    private int BinarySearch(int L, int R,E elem){
        int M=0;
        while (L<=R) {
            M = (L+R)/2;
            if (((Comparable<E>)elem).compareTo(elems[M]) > 0)
                L = M + 1;
            else if (((Comparable<E>)elem).compareTo(elems[M]) < 0)
                R = M - 1;
            else
                return -2;
        }
        if (R==-1){
            return -1;
        }
        if (L>M){
            return M;
        }
        else{
            return M-1;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String sep ="";
        for (int i=0;i < size; i++){
            sb.append(sep).append(elems[i]);
            sep=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        elems = null;
        elems = (E[]) new Object[]{};
        size=0;
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        CheckSize();
        int index= BinarySearch(0,size-1,e);
        if (index!=-2) {
            System.arraycopy(elems, index + 1, elems, index + 2, size - index - 1);
            elems[index + 1] = e;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int M;
        int L=0;
        int R=size-1;
        while (L<=R) {
            M = (L+R)/2;
            if (((Comparable<E>)o).compareTo(elems[M]) > 0)
                L = M + 1;
            else if (((Comparable<E>)o).compareTo(elems[M]) < 0)
                R = M - 1;
            else {
                System.arraycopy(elems, M + 1, elems, M, size - M - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int M;
        int L=0;
        int R=size-1;
        while (L<=R) {
            M = (L+R)/2;
            if (((Comparable<E>)o).compareTo(elems[M]) > 0)
                L = M + 1;
            else if (((Comparable<E>)o).compareTo(elems[M]) < 0)
                R = M - 1;
            else
                return true;
        }
        return false;
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
    public boolean removeAll(Collection<?> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=c.size()-1;i>-1;i--){
            if  (contains(arr[i])){
                remove(arr[i]);
            }
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize=size;
        for (int i=size-1;i>-1;i--){
            if  (!c.contains(elems[i])){
                remove(elems[i]);
            }
        }
        if (prevSize!=size){
            return true;
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

}
