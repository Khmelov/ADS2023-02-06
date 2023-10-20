package by.it.group251003.snopko.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private final double LoadFactor = 0.75;

    private int findHashCode(Object o, int length){
        return Objects.hashCode(o) % length;
    }

    private Node<E>[]data;
    private int size;
    private Node<E> lastAdded;
    private Node<E> firstAdded;

    private static class Node<E>{
        E value;
        Node<E> next;
        Node<E> prevAdded;
        Node<E> nextAdded;

        Node(E val) {
            this.value = val;
        }
    }

    MyLinkedHashSet(){
        data = new Node[10];
        size = 0;
        lastAdded = null;
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
        Node<E> Iter;
        Iter = firstAdded;
        while(Iter != null){
            sb.append(delimeter).append(Iter.value);
            delimeter = ", ";
            Iter = Iter.nextAdded;
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
        tempNode.prevAdded = this.lastAdded;

        if (data[temp] == null) {
            data[temp] = tempNode;
            if (tempNode.prevAdded != null) tempNode.prevAdded.nextAdded = tempNode;
            this.lastAdded = tempNode;
            if (this.firstAdded == null){
                this.firstAdded = tempNode;
            }
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
            if (tempNode.prevAdded != null) tempNode.prevAdded.nextAdded = tempNode;
            this.lastAdded = tempNode;
            if (this.firstAdded == null){
                this.firstAdded = tempNode;
            }
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
                if (iter.prevAdded != null) iter.prevAdded.nextAdded = iter.nextAdded;
                if (iter.nextAdded != null) iter.nextAdded.prevAdded = iter.prevAdded;
                if (iter == lastAdded){
                    lastAdded = iter.prevAdded;
                }
                if (iter == firstAdded){
                    firstAdded = iter.nextAdded;
                }

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
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int LastSize = size;
        for (E item : c) {
            add(item);
        }
        return LastSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int LastSize = size;
        E temp;
        Node<E> Iter = firstAdded;
        while (Iter != null){
            if (!c.contains(Iter.value)){
                temp = Iter.value;
                Iter = Iter.nextAdded;
                remove(temp);
            } else {
                Iter = Iter.nextAdded;
            }

        }
        return LastSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int LastSize = size;
        E temp;
        Node<E> Iter = firstAdded;
        while (Iter != null){
            if (c.contains(Iter.value)){
                temp = Iter.value;
                Iter = Iter.nextAdded;
                remove(temp);
            } else {
                Iter = Iter.nextAdded;
            }

        }
        return LastSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++){
            data[i] = null;
        }
        this.lastAdded = null;
        this.firstAdded = null;
        size = 0;
    }
}
