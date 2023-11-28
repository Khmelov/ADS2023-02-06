package by.it.group251003.stasevich_uriu.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    static class nodeList<E>{

        E data;

        public nodeList<E> next, prevANode, nextANode;

        public nodeList(E data){
            this.data = data;
        }

    }


    private nodeList<E>[] hashMap;
    private double factor = 0.75;

    public MyLinkedHashSet(){
        hashMap = new nodeList[16];
    }

    public MyLinkedHashSet(int Size){
        hashMap = new nodeList[Size];
    }


    private int size = 0;

    private nodeList<E> head = null, tail = null;

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

        int hash = getHash(o);
        nodeList<E> currentNode = hashMap[hash];
        while (currentNode != null) {
            if (currentNode.data.equals(o))
                return true;
            currentNode = currentNode.next;
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

    private int getHash(Object element){
        return (element.hashCode() & (hashMap.length - 1));
    }

    private void Resize() {
        nodeList<E>[] newMap = new nodeList[hashMap.length * 2 + 1];

        for (nodeList<E> el : hashMap) {
            nodeList<E> currentNode = el;

            while (currentNode != null) {
                nodeList<E> next = currentNode.next;

                int hash = currentNode.data.hashCode() & (hashMap.length - 1);
                currentNode.next = newMap[hash];
                newMap[hash] = currentNode;

                currentNode = next;
            }
        }
        hashMap = newMap;
    }

    private void addNode(nodeList<E> node){
        if (head == null){
            head = node;
        }
        else{
            node.prevANode = tail;
            tail.nextANode = node;
        }

        tail = node;
    }

    @Override
    public boolean add(E e) {

        int hash = getHash(e);
        nodeList<E> curNode = hashMap[hash];

        while (curNode != null){

            if (curNode.data.equals(e)) return false;

            curNode = curNode.next;

        }

        nodeList<E> newNode = new nodeList<>(e);
        newNode.next = hashMap[hash];
        hashMap[hash] = newNode;
        addNode(newNode);

        size++;

        if (size == hashMap.length*factor) Resize();

        return true;
    }

    private void removeNode(nodeList<E> node){

        if (node.nextANode != null)
            node.nextANode.prevANode = node.prevANode;
        else
            tail = node.prevANode;

        if (node.prevANode != null)
            node.prevANode.nextANode = node.nextANode;
        else
            head = node.nextANode;

    }

    @Override
    public boolean remove(Object o) {

        int hash = getHash(o);

        nodeList<E> curNode = hashMap[hash];
        nodeList<E> prevNode = null;

        while (curNode != null){

            if (curNode.data.equals(o)){

                removeNode(curNode);

                if (prevNode == null){
                    hashMap[hash] = curNode.next;
                }
                else prevNode.next = curNode.next;

                size--;

                return true;
            }

            prevNode = curNode;
            curNode = curNode.next;
        }

        return false;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        nodeList<E> currentNode = head;
        if (currentNode != null) {
            s.append(currentNode.data);
            currentNode = currentNode.nextANode;
        }

        while (currentNode != null) {
            s.append(", ").append(currentNode.data);
            currentNode = currentNode.nextANode;
        }

        return s.append("]").toString();
    }
    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object o : c)
            if (!contains(o))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        boolean check = false;
        for (E e : c) {
            check = add(e) || check;
        }

        return check;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean check = false;
        MyLinkedHashSet<E> tmp = new MyLinkedHashSet<E>(hashMap.length);
        nodeList<E> currentNode = head;
        while (currentNode != null) {
            if (c.contains(currentNode.data)) {
                tmp.add(currentNode.data);
                check = true;
            }
            currentNode = currentNode.nextANode;
        }

        hashMap = tmp.hashMap;
        head = tmp.head;
        tail = tmp.tail;
        size = tmp.size;

        return check;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean check = false;
        for (Object o : c) {
            if (remove(o)) {
                check=true;
            }
        }

        return check;
    }

    @Override
    public void clear() {

        for (int i = 0; i < hashMap.length; i++){
            hashMap[i] = null;
        }

        head = null;
        tail = null;

        size = 0;

    }
}

