package by.it.group251003.stasevich_uriu.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E>{

    static class Node<E>{

        E data;

        public Node<E> next;

        public Node(E data){
            this.data = data;
        }

    }

    private Node<E>[] hashMap;

    public MyHashSet(){
        hashMap = new Node[16];
    }


    private int size = 0;
    private double factor = 0.75;


    private void Resize(){

        Node<E>[] newMap = new Node[size * 2 + 1];

        for (Node<E> element : hashMap){

            Node<E> curNode = element;

            while (curNode != null){
                Node<E> nextNode = curNode.next;

                int hashCode = curNode.data.hashCode() & (newMap.length - 1);
                curNode.next = newMap[hashCode];
                newMap[hashCode] = curNode;

                curNode = nextNode;

            }

        }

        hashMap = newMap;

    }

    private int getHash(Object o) {
        return (o.hashCode()) & (hashMap.length - 1);
    }

    @Override
    public String toString(){

        StringBuilder s = new StringBuilder("[");
        boolean check = true;
        for (int i = 0; i < hashMap.length; i++) {
            Node<E> curr = hashMap[i];
            while (curr != null) {
                if (!check)
                    s.append(", ");

                s.append(curr.data);
                check = false;
                curr = curr.next;
            }
        }

        return s.append("]").toString();

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        int hashCode = getHash(o);

        Node<E> curNode = hashMap[hashCode];

        while (curNode != null){

            if (curNode.data.equals(o)){
                return true;
            }

            curNode = curNode.next;
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

        int hashCode = getHash(e);

        Node<E> curNode = hashMap[hashCode];

        while (curNode != null){
            if (curNode.data.equals(e)) return false;

            curNode = curNode.next;
        }

        size++;

        if (size == hashMap.length*factor) Resize();

        Node<E> newNode = new Node<>(e);
        newNode.next = hashMap[hashCode];
        hashMap[hashCode] = newNode;

        return true;
    }

    @Override
    public boolean remove(Object o) {

        Node<E> curNode = hashMap[getHash(o)];
        Node<E> prevNode = null;

        while(curNode != null){

            if (curNode.data.equals(o)){

                if (prevNode != null){
                    prevNode.next = curNode.next;
                }
                else{
                    hashMap[getHash(o)] = curNode.next;
                }

                size--;
                return true;

            }

            prevNode = curNode;
            curNode = curNode.next;

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

        for (int i = hashMap.length - 1;  i >= 0; i--) {
            hashMap[i] = null;
        }

        size = 0;
    }
}

