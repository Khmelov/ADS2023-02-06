package by.it.group251003.nasevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E>{

    static class nodeList<E>{

        E data;

        public nodeList<E> next;

        public nodeList(E data){
            this.data = data;
        }

    }

    private float loadFactor = 0.75f;

    private nodeList<E>[] hashMap;

    public MyHashSet(){
        hashMap = new nodeList[16];
    }

    public MyHashSet(int initialCapacity){
        hashMap = new nodeList[initialCapacity];
    }

    public MyHashSet(int initialCapacity, float loadFactor){
        hashMap = new nodeList[initialCapacity];
        this.loadFactor = loadFactor;
    }

    private int size = 0;

    private void Resize(){

        nodeList<E>[] newMap = new nodeList[size * 2 + 1];

        for (nodeList<E> element : hashMap){

            nodeList<E> curNode = element;

            while (curNode != null){
                nodeList<E> nextNode = curNode.next;

                int hashCode = curNode.data.hashCode() & (newMap.length - 1);
                curNode.next = newMap[hashCode];
                newMap[hashCode] = curNode;

                curNode = nextNode;

            }

        }

        hashMap = newMap;

    }

    private int getHashCode(Object o) {
        return (o.hashCode()) & (hashMap.length - 1);
    }

    @Override
    public String toString(){

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < hashMap.length; i++){

            nodeList<E> curNode = hashMap[i];

            while (curNode != null){
                stringBuilder.append(curNode.data);
                curNode = curNode.next;
                stringBuilder.append(", ");
            }

        }

        if (stringBuilder.length() > 1) stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");

        stringBuilder.append("]");

        return stringBuilder.toString();
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

        int hashCode = getHashCode(o);

        nodeList<E> curNode = hashMap[hashCode];

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

        int hashCode = getHashCode(e);

        nodeList<E> curNode = hashMap[hashCode];

        while (curNode != null){
            if (curNode.data.equals(e)) return false;

            curNode = curNode.next;
        }

        size++;

        if (size == hashMap.length * loadFactor) Resize();

        nodeList<E> newNode = new nodeList<>(e);
        newNode.next = hashMap[hashCode];
        hashMap[hashCode] = newNode;

        return true;
    }

    @Override
    public boolean remove(Object o) {

        nodeList<E> curNode = hashMap[getHashCode(o)];
        nodeList<E> prevNode = null;

        while(curNode != null){

            if (curNode.data.equals(o)){

                if (prevNode != null){
                    prevNode.next = curNode.next;
                }
                else{
                    hashMap[getHashCode(o)] = curNode.next;
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
