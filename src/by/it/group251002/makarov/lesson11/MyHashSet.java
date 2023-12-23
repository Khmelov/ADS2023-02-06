package by.it.group251002.makarov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private int size = 0;
    private static final int  capacity = 16;
    private Node<E>[] table;

    public class Node<E>{
        E value;
        Node<E> next;
        Node(E value){
            this.next = null;
            this.value = value;
        }
    }

    public MyHashSet(){
        table = new Node[capacity];
        size = 0;
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for (Node<E> node : table) {
            Node<E> current = node;
            while (current != null) {
                result.append(current.value).append(", ");
                current = current.next;
            }
        }

        // Удалите последнюю запятую и пробел из строки
        if (result.length() > 2) {
            result.setLength(result.length() - 2);
        }

        return "[" + result.toString() + "]";
    }
    public MyHashSet(int capacityINIT){
        table = new Node[capacityINIT];
        size = 0;
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
        if (null==o){
            return false;
        }
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
        if(contains(e)) return false;
        int index = getIndex(e);
        Node<E> newNode = new Node<>(e);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        return true;
    }
    public int getIndex(Object e){
        if (e==null) return 0;
        int hashcode = e.hashCode();
        return (hashcode & 0x7FFFFFFF)%table.length;
    }

    @Override
    public boolean remove(Object o) {
        if (o==null) return false;
        int index = getIndex(o);
        Node<E> current = table[index];
        Node<E> prev = null;
        while (current!=null){
            if(current.value.equals(o)){
                if(prev == null){
                    table[index]=current.next;
                } else{
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
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
        table = new Node[capacity];
        size = 0;
    }
}
