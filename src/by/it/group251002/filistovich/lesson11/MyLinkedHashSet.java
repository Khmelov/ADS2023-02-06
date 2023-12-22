package by.it.group251002.filistovich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E>[] elements;
    private int size;
    private int solveHash(E e, int maxIndex){
        return (e.hashCode() % maxIndex);
    }



    private class Node<E>{
        E elem;
        Node<E> next;
        Node<E> numNext;
        Node<E> numPrev;
        Node(E e){
            elem = e;
            next = null;
            numNext = null;
            numPrev = null;

        }
    }
    MyLinkedHashSet(){
        size = 0;
        elements = new Node[5];
        first = null;
        last = null;
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
        Node<E> it = first;
        while(it != null){
            sb.append(delimiter).append(it.elem);
            delimiter = ", ";
            it = it.numNext;
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
        int index = solveHash((E)o, elements.length);
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
        if (size == elements.length){
            reHash();
        }
        int index = solveHash(e, elements.length);
        if (elements[index] == null){
            elements[index] = new Node(e);
            size++;
            if (last == null){
                last = elements[index];
                first = elements[index];
                last.numPrev = first;
            } else {
                last.numNext = elements[index];
                elements[index].numPrev =last;
                last = last.numNext;
            }
        } else {
            Node<E> tempNode = new Node(e);
            Node<E> curNode = elements[index];
            if (e.equals(curNode.elem)){
                return false;
            }
            while (curNode.next != null){

                curNode = curNode.next;
                if (e.equals(curNode.elem)){
                    return false;
                }
            }

            curNode.next = tempNode;
            if (last == null){
                last = tempNode;
                first = tempNode;
                last.numPrev = first;
            } else {
                last.numNext = tempNode;
                tempNode.numPrev =last;
                last = last.numNext;
            }
            size++;
        }


        return true;

    }

    @Override
    public boolean remove(Object o) {
        int index = solveHash((E)o, elements.length);

        Node<E> curNode = elements[index];
        Node<E> parNode = null;
        while (curNode != null){

            if (o.equals(curNode.elem)){
                if (parNode != null){
                    parNode.next = curNode.next;
                } else {
                    elements[index] = curNode.next;
                }

                if (curNode == last){
                    if (curNode != first){
                        last = curNode.numPrev;
                        last.numNext = curNode.numNext;
                    } else {
                        first = null;
                        last = null;
                    }
                } else if (curNode == first) {
                    first = curNode.numNext;
                } else if (curNode != null){
                    curNode.numPrev.numNext = curNode.numNext;
                    curNode.numNext.numPrev = curNode.numPrev;
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
    public boolean containsAll(Collection<?> c) {
        for (Object it:c){
            if (!contains(it)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        for (E it:c){
            add(it);
        }
        if (prevSize == size){
            return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        Node<E> tempNode = first;
        Node<E> nextNode = null;
        while (tempNode != null){
            nextNode = tempNode.numNext;
            if (!c.contains(tempNode.elem)){
                remove(tempNode.elem);
            }
            tempNode = nextNode;
        }
        if (prevSize == size){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        for (Object it:c){
            if (contains(it)){
                remove(it);
            }
        }
        if (prevSize == size){
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++){
            elements[i] = null;
        }
        size = 0;
        first = null;
        last = null;

    }
}
