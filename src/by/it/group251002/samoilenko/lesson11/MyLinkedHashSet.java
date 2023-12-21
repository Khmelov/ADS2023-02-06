package by.it.group251002.samoilenko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class LinkedNode<E> {
    public E value;
    public LinkedNode<E> next;
    public LinkedNode addNext;
    public LinkedNode addPrev;
    LinkedNode(E value){
        this.value=value;
        this.next=null;
        this.addNext=null;
        this.addPrev=null;
    }
}
public class MyLinkedHashSet<E> implements Set<E> {
    private int capacity;
    private int size;
    private LinkedNode<E>[] array;
    private LinkedNode<E> addFirst;
    private  LinkedNode<E> addLast;
    MyLinkedHashSet(){
        this.capacity=10;
        this.size=0;
        this.array=(LinkedNode<E>[])new LinkedNode[this.capacity];
        addFirst=null;
        addLast=null;
    }

    public void extend(){
        LinkedNode<E>[] exarray=this.array;
        capacity*=2;
        this.array=(LinkedNode<E>[]) new LinkedNode[capacity];
        this.size=0;
        LinkedNode<E> temp;
        for(int i=0;i<capacity/2;i++){
            temp=exarray[i];
            while(temp!=null){
                this.addWithoutCount(temp.value);
                temp=temp.next;
            }
        }
    }

    @Override
    public String toString() {
        String res=new String();
        res+="[";
        LinkedNode<E> cur=addFirst;
        while (cur!=null){
            res = res + cur.value + ", ";
            cur=cur.addNext;
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
        LinkedNode<E> temp=this.array[index];
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

    public LinkedNode<E> addWithoutCount(E e){
        int index=e.hashCode() % capacity;
        LinkedNode<E> cur=this.array[index];
        if(cur==null){
            this.array[index]=new LinkedNode<>(e);
            this.size++;
            return this.array[index];
        }
        else {
            while(cur.next!=null){
                if(Objects.equals(e,cur.value))
                    return null;
                cur=cur.next;
            }
            if(Objects.equals(e,cur.value))
                return null;
            cur.next=new LinkedNode(e);
            size++;
            return cur.next;
        }
    }
    @Override
    public boolean add(E e) {
        if(size+1>=capacity*1.5){
            extend();
        }
        LinkedNode<E> temp=this.addWithoutCount(e);
        if(temp==null)
            return false;
        else{
            temp.addPrev=addLast;
            if(size!=1){
                addLast.addNext=temp;
            } else {
                addFirst=temp;
            }
            addLast=temp;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index=o.hashCode() % capacity;
        LinkedNode<E> temp=this.array[index];
        if(temp!=null){
            if(o.equals(temp.value)) {
                if(temp.addPrev==null){
                    addFirst=temp.addNext;
                }
                else {
                    this.array[index].addPrev.addNext = this.array[index].addNext;
                }
                if(temp.addNext==null){
                    addLast=temp.addPrev;
                }
                else {
                    this.array[index].addNext.addPrev = this.array[index].addPrev;
                }
                this.array[index]=this.array[index].next;
                size--;
                return true;
            }
            LinkedNode<E> prev;
            while(temp.next!=null){
                prev=temp;
                temp=temp.next;
                if(Objects.equals(o,temp.value)){
                    prev.next=temp.next;
                    if(temp.addPrev==null){
                        addFirst=temp.addNext;
                    }
                    else {
                        temp.addPrev.addNext =temp.addNext;
                    }
                    if(temp.addNext==null){
                        addLast=temp.addPrev;
                    }
                    else {
                        temp.addNext.addPrev = temp.addPrev;
                    }
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
        this.addLast=null;
        this.addFirst=null;
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
        for(Object o:collection){
            if(!this.contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int exsize=size;
        for (E o : collection) {
            add(o);
        }
        return exsize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int exsize=this.size;
        LinkedNode<E> temp = addFirst;
        while (temp != null) {
            if(!collection.contains(temp.value)){
                remove(temp.value);
            }
            temp=temp.addNext;
        }
        return size!=exsize;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int exsize=this.size;
        for(Object el:collection){
            while (this.contains(el)){
                this.remove((E) el);
            }
        }
        return size!=exsize;
    }
}
