package by.it.group251002.filistovich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.*;

public class MyHashSet<E> implements Set{

    private Node<E>[] elements;
    private int size;
    private int solveHash(Object o, int maxIndex){
        return (o.hashCode() % maxIndex);
    }



    private class Node<E>{
        E elem;
        Node<E> next;
        Node(E e){
            elem = e;
            next = null;
        }
    }
    MyHashSet(){
        size = 0;
        elements = new Node[5];
    }

    private void reHash(){
        int newSize = (size * 3 )/ 2 + 1;
        Node<E>[] newElemnts = new Node[newSize];

        for (Node<E> it : elements){
            Node<E> curElem = it;
            while (curElem != null){
                int index = solveHash(curElem.elem, newSize);
                Node<E> temp = curElem;
                curElem = curElem.next;
                temp.next = null;
                if (newElemnts[index] == null){
                    newElemnts[index] = temp;
                } else {
                    Node<E> last = newElemnts[index];
                    while (last.next != null){
                        last = last.next;
                    }
                    last.next = temp;
                }
            }
        }

        elements = newElemnts;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (Node<E> it : elements){
            Node<E> tempNode = it;
            while (tempNode != null){
                sb.append(delimiter).append(tempNode.elem);
                delimiter = ", ";
                tempNode = tempNode.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        int index = solveHash(o, elements.length);
        Node<E> curNode = elements[index];
        while (curNode != null){
            if (o.equals(curNode.elem)){
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        if (size == elements.length){
            reHash();
        }
        int index = solveHash(o, elements.length);
        if (elements[index] == null){
            elements[index] = new Node(o);
            size++;
        } else {
            Node<E> tempNode = new Node(o);
            Node<E> curNode = elements[index];
            if (o.equals(curNode.elem)){
                return false;
            }
            while (curNode.next != null){

                curNode = curNode.next;
                if (o.equals(curNode.elem)){
                    return false;
                }
            }
            curNode.next = tempNode;
            size++;
        }


        return true;
    }

    @Override
    public boolean remove(Object o) {

        int index = solveHash(o, elements.length);

        Node<E> curNode = elements[index];
        Node<E> parNode = null;
        while (curNode != null){

            if (o.equals(curNode.elem)){
                if (parNode != null){
                    parNode.next = curNode.next;
                } else {
                    elements[index] = curNode.next;
                }
                curNode = null;
                size--;
                return true;
            }
            parNode = curNode;
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++){
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
