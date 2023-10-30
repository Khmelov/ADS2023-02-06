package by.it.group251002.yanucevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class MyNode<E>{
    public E value;
    public MyNode next;
    public MyNode(E value){
        this.value=value;
        this.next=null;
    }
}

public class MyHashSet<E> implements Set<E> {

    private int size = 0;
    private int capacity = 8;
    private MyNode<E>[] arr = (MyNode<E>[]) new MyNode[capacity];

    private int hashIndex(Object e){
        return e.hashCode()%capacity;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimiter = "";
        MyNode temp;
        for(int i=0;i<capacity;i++){
            temp=arr[i];
            while(temp!=null){
                sb.append(delimiter).append(temp.value.toString());
                delimiter=", ";
                temp=temp.next;
            }
        }

        sb.append("]");
        return sb.toString();
    }
    private void resize(){
        int oldCap = capacity;
        int newCapacity=capacity*3/2+1;
        MyNode[] oldArr = arr;
        MyNode[] newArr = new MyNode[newCapacity];
        capacity=newCapacity;
        arr=newArr;
        size=0;
        for(int i=0;i<newCapacity;i++){
            newArr[i]=null;
        }

        MyNode<E> temp;
        for(int i=0;i<oldCap;i++){
            temp=oldArr[i];
            while(temp!=null){
                add(temp.value);
                temp=temp.next;
            }
        }
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
        int hash = hashIndex(o);
        MyNode temp = arr[hash];
        if (temp!=null){
            while(temp!=null){
                if(Objects.equals(o,temp.value)){
                    return true;
                }
                else{
                    temp=temp.next;
                }
            }
            return false;
        }
        else{
            return false;
        }

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
        if((size+1)>=capacity*3/4){
            resize();
        }
        int hash = hashIndex(e);
        MyNode temp = arr[hash];
        if(temp!=null){
            while(temp.next!=null){
                if(!Objects.equals(e,temp.value)){
                    temp=temp.next;
                }
                else{
                    return false;
                }
            }
            if (!Objects.equals(e,temp.value)){
                temp.next=new MyNode(e);
                size++;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            arr[hash]=new MyNode(e);
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int hash = hashIndex(o);
        MyNode temp = arr[hash];
        if(temp!=null){
            // if the first one is the man, decapitate him
            if(Objects.equals(o,temp.value)){
                arr[hash]=temp.next;
                size--;
                return true;
            }
            else{
                MyNode prev = arr[hash];
                temp=temp.next;
                while(temp!=null){
                    if(Objects.equals(o,temp.value)){
                        prev.next=temp.next;
                        size--;
                        return true;
                    }
                    else {
                        prev=temp;
                        temp=temp.next;
                    }
                }
                return false;
            }
        }
        else{
            return false;
        }
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
        for(int i=0;i<capacity;i++){
            arr[i]=null;
        }
        size=0;
    }
}
