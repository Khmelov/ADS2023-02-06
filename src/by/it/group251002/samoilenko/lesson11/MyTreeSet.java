package by.it.group251002.samoilenko.lesson11;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {

    private E[] array;
    public int size;
    private int capacity;

    MyTreeSet(){
        capacity=10;
        size=0;
        array=(E[])new Comparable[capacity];
    }
    @Override
    public String toString() {
        String res=new String();
        res+="[";
        for(int i=0;i<size-1;i++)
            res=res+this.array[i]+", ";
        if(size>0)
            res+=this.array[size-1];
        res+="]";
        return res;
    }
    private int binary_search(E e){
        int right=this.size-1;
        int left=0;
        int mid;
        while (left<=right){
            mid=(left+right)/2;
            if(array[mid].compareTo(e)>0){
                right=mid-1;
            }
            else if(array[mid].compareTo(e)<0)
                left=mid+1;
            else
                return mid;
        }
        return -(right+2);
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
        return binary_search((E)o)>=0;
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
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(size==0){
            this.array[size++]=e;
            return true;
        }
        int position=binary_search(e);
        if(position>=0){
            return false;
        }
        if(size==capacity){
            capacity=(int)(capacity*1.5);
            E[] array=(E[]) new Comparable[capacity];
            System.arraycopy(this.array,0,array,0,size);
            this.array=array;
        }
        position=position*(-1)-1;
        System.arraycopy(this.array,position,this.array,position+1,size++-position);
        this.array[position]=e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int  position=binary_search((E) o);
        if(position<0)
            return false;
        System.arraycopy(this.array,position+1,this.array,position,--size-position);
        return true;
    }

    private void remove(int index){
        System.arraycopy(this.array,index+1,this.array, index,--size-index);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object o:collection){
            if(!this.contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int exsize=size;
        for(E e:collection){
            this.add(e);
        }
        return exsize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int exsize=size;
        for(int i=size-1;i>=0;i--){
            if(!collection.contains(this.array[i])){
                this.remove(i);
            }
        }
        return exsize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int exsize=size;
        for(Object o:collection){
            this.remove(o);
        }
        return exsize!=size;
    }

    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            this.array[i]=null;
        }
        size=0;
    }
}
