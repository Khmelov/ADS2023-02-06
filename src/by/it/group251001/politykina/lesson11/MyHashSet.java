package by.it.group251001.politykina.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    class Node<E> {
        E item;
        Node<E> next;
        public Node(E item) {
            this.item = item;
        }
    }
    int capacity=100;
    Node<E>[] array;
    float SingToGrow = 0.75F;
    int size;
    public MyHashSet(){
        this.size=0;
        this.array=new Node[capacity];
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isEmpty())
            return "[]";
        s.append("[");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Node<E> temp = array[i];
                while (temp != null) {
                    s.append(temp.item).append(", ");
                    temp = temp.next;
                }
            }
        }
        if (size != 0) {
            s.delete(s.length() - 2, s.length());
        }
        s.append("]");
        return s.toString();
    }
    private void grow(){
        Node[] tempArr = new Node[size*2];
        int newCapacity = size *2;
        for (int i = 0; i < capacity; i++) {
            while(array[i]!=null){
                E _item = array[i].item;
                Node newN = new Node(_item);
                int indx=_item.hashCode()%newCapacity;
                if (tempArr[indx]==null){
                    tempArr[indx]= newN;
                }else{
                    Node temp =tempArr[indx];
                    while (temp.next!=null)
                        temp=temp.next;
                    temp.next=newN;
                }
                array[i]=array[i].next;
            }
        }
        array=tempArr;
        capacity=newCapacity;
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
        int indx = o.hashCode()%capacity;
        if(array[indx]==null)
            return false;
        Node temp =array[indx];
        while (temp!=null) {
            if (temp.item == o) {
                return true;
            }
            temp=temp.next;
        }
        return false;
    }
    @Override
    public boolean add(E e) {
        Node<E> newN = new Node<>(e);
        int indx=e.hashCode()%capacity;
        if (array[indx]==null){
            array[indx]= newN;
            size++;
        }else{
            Node<E> temp =array[indx];
            Node<E> parent=temp;
            while (temp!=null) {
                if (temp.item.equals(e)){
                    return false;}
                parent = temp;
                temp = temp.next;
            }
             parent.next=newN;
             size++;

        }

        if(size*SingToGrow > capacity)
            grow();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int indx=o.hashCode()%capacity;
        if (array[indx]==null){
            return false;
        }else{
            Node temp =array[indx];
            if (temp.item.equals(o)){
                array[indx]=array[indx].next;
                size--;
                return true;
            }
            while (temp.next!=null) {
                if (temp.next.item.equals( o) ){
                    temp.next= temp.next.next;
                    size--;
                    return true;
                }
                temp = temp.next;
            }

            return false;
        }


    }
    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            array[i]=null;
        }
        size = 0;
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
