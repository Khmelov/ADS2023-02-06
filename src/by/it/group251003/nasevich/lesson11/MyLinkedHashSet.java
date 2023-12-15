package by.it.group251003.nasevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    static class nodeList<E>{

        E data;

        public nodeList<E> next, prevNode, nextNode;

        public nodeList(E data){
            this.data = data;
        }

    }

    private float loadFactor = 0.75f;

    private nodeList<E>[] hashMap;

    public MyLinkedHashSet(){
        hashMap = new nodeList[16];
    }

    public MyLinkedHashSet(int initialCapacity){
        hashMap = new nodeList[initialCapacity];
    }

    public MyLinkedHashSet(int initialCapacity, float loadFactor){
        hashMap = new nodeList[initialCapacity];
        this.loadFactor = loadFactor;
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

        int hash = getHashCode(o);
        nodeList<E> curr = hashMap[hash];
        while (curr != null) {
            if (curr.data.equals(o))
                return true;
            curr = curr.next;
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

    private int getHashCode(Object element){
        return (element.hashCode() & (hashMap.length - 1));
    }

    private void Resize() {
        nodeList<E>[] newMap = new nodeList[hashMap.length * 2 + 1];

        for (nodeList<E> el : hashMap) {
            nodeList<E> curr = el;

            while (curr != null) {
                nodeList<E> next = curr.next;

                int hash = curr.data.hashCode() & (hashMap.length - 1);
                curr.next = newMap[hash];
                newMap[hash] = curr;

                curr = next;
            }
        }
        hashMap = newMap;
    }

    private void addNode(nodeList<E> node){
        if (head == null){
            head = node;
        }
        else{
            node.prevNode = tail;
            tail.nextNode = node;
        }

        tail = node;
    }

    @Override
    public boolean add(E e) {

        int hash = getHashCode(e);
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

        if (size == hashMap.length * loadFactor) Resize();

        return true;
    }

    private void removeNode(nodeList<E> node){

        if (node.nextNode != null)
            node.nextNode.prevNode = node.prevNode;
        else
            tail = node.prevNode;

        if (node.prevNode != null)
            node.prevNode.nextNode = node.nextNode;
        else
            head = node.nextNode;

    }

    @Override
    public boolean remove(Object o) {

        int hash = getHashCode(o);

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
        StringBuilder res = new StringBuilder("[");
        nodeList<E> curr = head;
        if (curr != null) {
            res.append(curr.data);
            curr = curr.nextNode;
        }

        while (curr != null) {
            res.append(", ").append(curr.data);
            curr = curr.nextNode;
        }

        return res.append("]").toString();
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

        boolean flag = false;
        for (E e : c) {
            flag = add(e) || flag;
        }

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean flag = false;
        MyLinkedHashSet<E> tmp = new MyLinkedHashSet<E>(hashMap.length);
        nodeList<E> curr = head;
        while (curr != null) {
            if (c.contains(curr.data)) {
                tmp.add(curr.data);
                flag = true;
            }
            curr = curr.nextNode;
        }

        hashMap = tmp.hashMap;
        head = tmp.head;
        tail = tmp.tail;
        size = tmp.size;

        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean flag = false;
        for (Object obj : c)
            flag = remove(obj) || flag;

        return flag;
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
