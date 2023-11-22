package by.it.group251003.snopko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private final double LoadFactor = 0.75;

    private int findHashCode(Object o, int length){
        return Objects.hashCode(o) % length;
    }

    private Node<E> []data;
    private int size;

    private static class Node<E>{
        E value;
        Node<E> next;

        Node(E val) {
            this.value = val;
        }
    }

    MyHashSet(){
        data = new Node[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimeter = "";
        for (Node<E> i:data) {
            Node<E> tempIter = i;
            while(tempIter != null) {
                sb.append(delimeter).append(tempIter.value);
                delimeter = ", ";
                tempIter = tempIter.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = findHashCode(o, data.length);
        Node<E> iter = data[index];
        while(iter!=null){
            if (Objects.equals(o,iter.value)){
                return true;
            }
            iter = iter.next;
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
        int temp = findHashCode(e, data.length);
        Node<E> tempNode = new Node<>(e);
        if (data[temp] == null) {
            data[temp] = tempNode;
        } else {
            Node<E> iterator = data[temp];
            while (iterator.next != null) {
                if (Objects.equals(iterator.value, e)) {
                    return false;
                }
                iterator = iterator.next;
            }
            if (Objects.equals(iterator.value, e)) {
                return false;
            }
            iterator.next = tempNode;
        }
        size++;
        if ((double) size / (double) data.length >= LoadFactor) {
            grow();
        }
        return true;
    }
    private void grow() {
        int newSize = data.length * 2 + 1;
        Node<E>[] biggerData = new Node[newSize];

        for (Node<E> tempIter : data) {
            Node<E> current = tempIter;
            while (current != null) {
                int newIndex = findHashCode(current.value, newSize);
                Node<E> next = current.next;
                current.next = biggerData[newIndex];
                biggerData[newIndex] = current;
                current = next;
            }
        }

        data = biggerData;
    }

    @Override
    public boolean remove(Object o) {
        int index = findHashCode(o, data.length);
        Node<E> iter = data[index];
        Node<E> previous = null;

        while(iter!=null){
            if (Objects.equals(o,iter.value)){
                if(previous != null){
                    previous.next = iter.next;
                } else {
                    data[index] = iter.next;
                }
                size--;
                return true;
            }
            previous = iter;
            iter = iter.next;
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
        for (int i = 0; i < data.length; i++){
            data[i] = null;
        }

        size = 0;
    }
}
