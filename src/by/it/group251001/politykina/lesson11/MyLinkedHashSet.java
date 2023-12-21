package by.it.group251001.politykina.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private static int size;
    private Node<E> head;
    private Node<E> tail;
    private static class Node<E> {
        E item;
        Node next;
        Node after;
        Node before;
        private Node(E _item) {
            this.item = _item;
        }
    }
    private void newTail(Node node){
        if(head == null){
            head=node;
            tail=node;
        }else{
            tail.after=node;
            node.before=tail;
            tail=tail.after;
        }
    }
    int capacity=100;
    Node<E>[] array;

    float SingToGrow = 0.75F;

    public MyLinkedHashSet(){
        size=0;
        array=new Node[capacity];
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
    public String toString() {
        StringBuilder s =new StringBuilder();
        if(size==0)return "[]";
        Node temp = head;
        s.append("[");
        while (temp!=null){
            s.append(temp.item+", ");
            temp=temp.after;
        }
        s.delete(s.length()-2, s.length());
        s.append("]");
        return s.toString();
    }

    @Override
    public boolean add(E e) {
        Node<E> newN = new Node<>(e);
        int indx=e.hashCode()%capacity;
        if (array[indx]==null){
            array[indx]= newN;
            size++;
            newTail(newN);
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
            newTail(newN);
        }
        if(size*SingToGrow > capacity)
            grow();
        return true;
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
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            array[i]=null;
        }
        size = 0;
        head=null;
        tail=null;
    }


    public boolean remove(Object o) {
        int pos = o.hashCode() % capacity;
        Node prev = null;
        Node e = array[pos];
        while (e!=null){
            if (e.item.equals(o)) {
                if (prev == null) {///удаление из таблицы
                    array[pos] = array[pos].next;
                } else {
                    prev.next = e.next;
                    e.next = null;
                }

                Node<E> after = e.after;///удаление из списка
                Node<E> before = e.before;
                if(after != null){
                    after.before = before;
                } else {
                    tail = before;
                }
                if(before != null){
                    before.after = after;
                } else {
                    head = after;
                }
                e.after = null;
                e.before = null;
                e.item = null;
                size--;
                return true;
            }
            prev = e;
            e = e.next;
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o:
                collection) {
            if(!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int oldSize = size;
        for (E o:
                collection) {
            add(o);
        }
        return size!=oldSize;
    }
    @Override
    public boolean retainAll(Collection<?> collection) {
        int oldSize = size;
        for (int i = 0; i < capacity; i++) {
            Node temp = array[i];
            while (temp!=null){
                if (!collection.contains(temp.item)) {
                    Node x = temp;
                    temp=temp.next;
                    remove(x.item);
                }else{
                temp=temp.next;}
            }
        }
        boolean res = oldSize != size;
        return res;
    }
    @Override
    public boolean removeAll(Collection<?> collection) {
        int oldSize = size;
        for (Object o:
             collection) {
            remove(o);
        }
        return size!=oldSize;
    }
    ///////////////////
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




}
