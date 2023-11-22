package by.it.group251002.samoilenko.lesson11;

import java.util.*;

class Node<E> {
    public E value;
    public Node<E> next;
    Node(E value){
        this.value=value;
        this.next=null;
    }
}

public class MyHashSet<E extends Comparable<E>> implements Set<E>{

    private int capacity;
    private int size;
    private Node<E>[] array;


    MyHashSet(){
        this.capacity=10;
        this.size=0;
        this.array=(Node<E>[])new Node[this.capacity];
    }

    public void extend(){
        Node<E>[] exarray=this.array;
        capacity*=2;
        this.array=(Node<E>[]) new Node[capacity];
        this.size=0;
        Node<E> temp;
        for(int i=0;i<capacity/2;i++){
            temp=exarray[i];
            while(temp!=null){
                this.add(temp.value);
                temp=temp.next;
            }
        }
    }

    @Override
    public String toString() {
        String res=new String();
        res+="[";
        for(int i=0;i<capacity;i++) {
            Node<E> temp=this.array[i];
            while(temp!=null) {
                res = res + temp.value + ", ";
                temp=temp.next;
            }
        }
        if(this.size!=0)
            res=res.substring(0,res.length()-2);
        res+="]";
        return res;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        int index=o.hashCode()%capacity;
        Node<E> temp=this.array[index];
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
    @Override
    public boolean add(E e) {
        if(size+1>=capacity*1.5){
            extend();
        }
        int index=e.hashCode() % capacity;
        Node<E> cur=this.array[index];
        if(cur==null){
            this.array[index]=new Node<>(e);
            this.size++;
            return true;
        }
        else {
            while(cur.next!=null){
                if(Objects.equals(e,cur.value))
                    return false;
                cur=cur.next;
            }
            if(Objects.equals(e,cur.value))
                return false;
            cur.next=new Node(e);
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index=o.hashCode() % capacity;
        Node<E> temp=this.array[index];
        if(temp!=null){
            if(o.equals(temp.value)) {
                this.array[index]=this.array[index].next;
                size--;
                return true;
            }
            Node<E> prev;
            while(temp.next!=null){
                prev=temp;
                temp=temp.next;
                if(Objects.equals(o,temp.value)){
                    prev.next=temp.next;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void clear() {
        for(int i=0;i<this.capacity;i++){
            this.array[i]=null;
        }
        this.size=0;
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
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }


}

