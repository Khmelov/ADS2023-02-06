package by.it.group251002.makarov.lesson11;

import java.util.*;

public class MyLinkedHashSet<E> implements Set<E> {

    public class Node<E>{
        E value;
        Node<E> next;
        Node addNext;
        Node addPrev;
        Node(E value){
            this.addNext = null;
            this.addPrev = null;
            this.next = null;
            this.value = value;
        }
    }
    private int capacity;
    private int size;
    private Node<E>[] table;
    private Node<E> addFirst;
    private Node<E> addLast;
    MyLinkedHashSet(){
        this.capacity=10;
        this.size=0;
        this.table=(Node<E>[])new Node[this.capacity];
        addFirst=null;
        addLast=null;
    }
    public MyLinkedHashSet(int capacityINIT){
        table = new Node[capacityINIT];
        size = 0;
    }
//    @Override
//    public String toString(){
//
//        if(isEmpty()) return "[]";
//        StringBuilder result = new StringBuilder();
//        for(Node<E> node:table){
//            Node<E> current = node;
//            while(current!=null){
//                result.append(current.value).append(", ");
//                current=current.next;
//            }
//            if (result.length()>2){
//                result.setLength(result.length()-2);
//            }
//        }
//        return "["+result+"]";
//    }
@Override
public String toString() {
    String res=new String();
    res+="[";
    Node<E> cur=addFirst;
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty())return false;
        int index = getIndex(o);
        Node<E> current = table[index];
        while(current!=null){
            if(current.value.equals(o)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public int getIndex(Object o){
        if (o==null) return 0;

        int hashcode = o.hashCode();
        return (hashcode&0x7FFFFFFF)%table.length;
    }
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int currI = 0;
            private Node<E> curr = table[currI];

            @Override
            public boolean hasNext() {
                while(currI<table.length&&curr == null){
                    currI++;
                    if(currI<table.length){
                        curr = table[currI];
                    }
                }

                return currI<table.length;
            }

            @Override
            public E next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                E value = curr.value;
                curr=curr.next;
                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

//    @Override
//    public boolean add(E e) {
//        if (e==null) return false;
//        if(contains(e)){
//            return false;
//        }
//        int index = getIndex(e);
//        Node<E> newnode = new Node<>(e);
//        newnode.next = table[index];
//        table[index] = newnode;
//        size++;
//        return true;
//    }
public void extend(){
    Node<E>[] exarray=this.table;
    capacity*=2;
    this.table=(Node<E>[]) new Node[capacity];
    this.size=0;
    Node<E> temp;
    for(int i=0;i<capacity/2;i++){
        temp=exarray[i];
        while(temp!=null){
            this.addWithoutCount(temp.value);
            temp=temp.next;
        }
    }
}
    public Node<E> addWithoutCount(E e){
        int index=e.hashCode() % capacity;
        Node<E> cur=this.table[index];
        if(cur==null){
            this.table[index]=new Node<>(e);
            this.size++;
            return this.table[index];
        }
        else {
            while(cur.next!=null){
                if(Objects.equals(e,cur.value))
                    return null;
                cur=cur.next;
            }
            if(Objects.equals(e,cur.value))
                return null;
            cur.next=new Node(e);
            size++;
            return cur.next;
        }
    }
    @Override
public boolean add(E e) {
    if(size+1>=capacity*1.5){
        extend();
    }
    Node<E> temp=this.addWithoutCount(e);
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
//    @Override
//    public boolean remove(Object o) {
//        if(o==null)return false;
//        int index = getIndex(o);
//        Node<E> current = this.table[index];
//        Node<E> prev = null;
//        while (current!=null){
//            if(o.equals(current.value)){
//                if(prev==null){
//                    table[index] = current.next;
//                } else{
//                    prev.next = current.next;
//                }
//                size--;
//                return true;
//            }
//            prev = current;
//            current = current.next;
//        }
//        return false;
//    }
@Override
public boolean remove(Object o) {
    int index=o.hashCode() % capacity;
    Node<E> temp=this.table[index];
    if(temp!=null){
        if(o.equals(temp.value)) {
            if(temp.addPrev==null){
                addFirst=temp.addNext;
            }
            else {
                this.table[index].addPrev.addNext = this.table[index].addNext;
            }
            if(temp.addNext==null){
                addLast=temp.addPrev;
            }
            else {
                this.table[index].addNext.addPrev = this.table[index].addPrev;
            }
            this.table[index]=this.table[index].next;
            size--;
            return true;
        }
        Node<E> prev;
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
    public boolean containsAll(Collection<?> c) {
        for(Object node : c){
            int index = getIndex(node);
            if(!contains(node)){return false;}
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int exsize=size;
        for(E node : c){
            add(node);
        }
        return size!=exsize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int exsize=this.size;
        Node<E> temp = addFirst;
        while(temp!=null){
            if(!c.contains(temp.value)){
                remove(temp.value);
            }
            temp=temp.addNext;
        }
        return this.size!=exsize;
    }



//    }
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean rem = false;
        for (Object o : c) {
            while (remove(o)) {
                rem = true;
            }
        }
        return rem;
    }

    @Override
    public void clear() {
     int i = 0;
     while(i<this.capacity){
         this.table[i]=null;
         i++;
     }
     this.size = 0;
     this.addFirst=null;
     this.addLast=null;


    }
}
