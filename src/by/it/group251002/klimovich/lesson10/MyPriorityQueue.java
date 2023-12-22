package by.it.group251002.klimovich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private void siftup(int index) {
        int curr=index;
        int parrent=(curr-1) / 2;
        if ((curr>=0)&&(((Comparable<E>)elems[parrent]).compareTo((E) elems[curr])>0)) {
            curr=parrent;
        }
        if (curr!=index) {
            E buf=elems[curr];
            elems[curr]=elems[index];
            elems[index]=buf;
            siftup(curr);
        }
    }

    private void siftdown(int index) {
        int curr=index;
        int left=curr*2+1;
        int right=2*curr+2;
        if ((left<size) && (((Comparable<E>)elems[left]).compareTo(elems[curr])<0)) {
            curr=left;
        }
        if ((right<size) && (((Comparable<E>)elems[right]).compareTo(elems[curr])<0)) {
            curr=right;
        }
        if (curr!=index) {
            E buf=elems[curr];
            elems[curr]=elems[index];
            elems[index]=buf;
            siftdown(curr);
        }
    }

    void heap() {
        for (int i =size/2 - 1; i >= 0; i--)
            siftdown(i);
    }
    private E[] elems = (E[]) new Object[]{};
    private int size = 0;

    private void CheckSize() {
        if (size == elems.length) {
            E[] arr = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elems, 0, arr, 0, size);
            elems = arr;
        }
    }

    @Override
    public String toString() {
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
    public boolean add(E e) {
        CheckSize();
        elems[size]=e;
        siftup(size);
        size++;
        return true;
    }

    public E remove(int index) {
        if(size<index) {
            return null;
        }
        E res=elems[index];
        elems[index]=elems[size-1];
        siftdown(index);
        siftup(size-2);
        size--;
        return res;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elems[i] == null) {
                    remove(i);
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elems[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elems[i] == null) {
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elems[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        CheckSize();
        elems[size]=e;
        siftup(size);
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(size==0) {
            return null;
        }
        return remove(0);
    }

    @Override
    public E remove() {
        return remove(0);
    }
    @Override
    public E peek() {
        if(size==0) {
            return null;
        }
        return elems[0];
    }

    @Override
    public E element() {
        return elems[0];
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
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
        E elem;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=size-1;i>-1;i--){
            if((c.contains(elems[i]))) {
                elem = elems[i];
                System.arraycopy(elems, i+1, elems, i, size-i-1);
                size--;
                elems[size] = null;
            }
        }
        heap();
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        E elem;
        int prevSize=size;
        for (int i=size-1;i>-1;i--){
            if(!(c.contains(elems[i]))) {
                elem = elems[i];
                System.arraycopy(elems, i+1, elems, i, size-i-1);
                size--;
                elems[size] = null;
            }
        }
        heap();
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

