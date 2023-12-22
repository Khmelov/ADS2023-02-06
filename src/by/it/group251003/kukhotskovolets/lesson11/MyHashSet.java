package by.it.group251003.kukhotskovolets.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private static class Node<E>{
        public E data;
        public Node<E> next = null;
        public Node(E data){
            this.data = data;
        }
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size = 0;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private double loadFactor = DEFAULT_LOAD_FACTOR;
    private Node<E>[] buckets = new Node[capacity];


    private void rehash(){
        capacity *= 2;
        Node<E>[] newBuckets = new Node[capacity];

        for (Node<E> bucket : buckets) {
            while (bucket != null){
                int index = getIndex(bucket.data);
                Node<E> newNode = new Node<>(bucket.data);
                if (newBuckets[index] != null){
                    Node<E> tempNode = newBuckets[index];
                    while (tempNode.next != null){
                        tempNode = tempNode.next;
                    }
                    tempNode.next = newNode;
                }
                else {
                    newBuckets[index] = newNode;
                }
                bucket = bucket.next;
            }
        }
        buckets = newBuckets;
    }

    private int getIndex(Object o){
        return (o.hashCode() & 0x7FFFFFFF) % capacity;
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
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimeter = "";
        Node<E> currentNode = buckets[0];

        if (size > 0){
            for (int i = 1; i < buckets.length; i++) {
                while (currentNode != null){
                    sb.append(delimeter).append(currentNode.data);
                    delimeter = ", ";
                    currentNode = currentNode.next;
                }
                currentNode = buckets[i];
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        Node<E> newNode = new Node<>(e);
        if (buckets[index] != null){
            Node<E> currentNode = buckets[index];
            while (currentNode.next != null){
                if (e.equals(currentNode.data)){
                    return false;
                }
                currentNode = currentNode.next;
            }
            if (e.equals(currentNode.data)){
                return false;
            }
            currentNode.next = newNode;
        } else {
            buckets[index] = newNode;
        }



        if (++size > loadFactor * capacity){
            rehash();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        Node<E> currentNode = buckets[index];
        Node<E> prevNode = null;

        while (currentNode != null){
            if (o.equals(currentNode.data)){
                if (prevNode == null){
                    buckets[index] = currentNode.next;
                }
                else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return true;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);
        Node<E> currentNode = buckets[index];
        while (currentNode != null){
            if (o.equals(currentNode.data)){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = DEFAULT_INITIAL_CAPACITY;
        buckets = new Node[capacity];
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
}
