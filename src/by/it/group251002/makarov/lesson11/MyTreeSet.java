package by.it.group251002.makarov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private E[] array;
    private int size=0;
    private int capacity=10;
    MyTreeSet(){
        this.array= (E[]) new Object[capacity];
        this.size=0;
    }

@Override
public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        int i =0;
        while(i<size-1){
            sb.append(array[i]).append(", ");
            i++;
        }
        sb.append(array[i]).append("]");
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
        if(isEmpty())
        return false;
        for(int i = 0;i<size;i++){
            if(o.equals(array[i])){
                return true;
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
    public boolean add(E e) {
        if (e==null){
            throw new NullPointerException();
        }
        int index = binarySearch(e);
        if(index>=0){
            return false;
        }
        index = -(index+1);
        ensureCapacity();

        System.arraycopy(array,index,array,index+1,size-index);

        array[index]=e;
        size++;
        return true;
    }
    public void ensureCapacity(){
        if(size==array.length){
            capacity=capacity*3/2;
            E[] newa = (E[])new Object[capacity];
            System.arraycopy(array,0,newa,0,size);
            array=newa;
        }
    }
    public int binarySearch(E key){
        int low = 0;
        int high = size-1;

        while(low<=high){
            int mid = (low+high) >>> 1;
            Comparable<E> midVal = (Comparable<E>) array[mid];
            int cmp = midVal.compareTo(key);
            if(cmp<0){
                low = mid+1;
            }else
            if(cmp>0){
                high = mid-1;
            }else return mid;
        }
        return -(low+1);
    }

    @Override
    public boolean remove(Object o) {
        int  position=binarySearch((E) o);
        if(position<0)
            return false;
        System.arraycopy(array,position+1,array,position,--size-position);
        return true;
    }
    public void removeint(int index){
        System.arraycopy(this.array,index+1,this.array, index,--size-index);
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean rem = false;
        for(E o:c){
            if(!contains(o)){
                add(o);
                rem = true;
            }
        }
        return rem;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int exsize=size;
        for(int i=size-1;i>=0;i--){
            if(!collection.contains(this.array[i])){
                this.removeint(i);
            }
        }
        return exsize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int exsize=size;
        for(Object o : c){
            if(contains(o)){
                remove(o);
            }
        }
        return size!=exsize;
    }

    @Override
    public void clear() {
        this.capacity = 10;
        this.array =(E[])new Object[capacity];
        this.size = 0;
    }
}
