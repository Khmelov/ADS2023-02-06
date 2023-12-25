package by.it.group251001.politykina.lesson10;

import java.util.*;
public class MyLinkedList<E> implements  Deque<E> {
    private int size =0;
    private Node<E> first;
    private Node<E> last;
    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E elem, Node<E> next){
            this.item = elem;
            this.prev = prev;
            this.next = next;
        }

    }
    MyLinkedList(){
        this.size=0;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        int count = size -1;
        Node curr =first;
        for (int i = 0; i < count; i++) {
            s.append(curr.item).append(", ");
            curr=curr.next;
        }
        s.append(last.item);
        s.append("]");
        return s.toString();
    }



//    public E remove(int index) {
//        Node<E> x = first;
//        for (int i = 0; i < index; i++) {
//            x = x.next;
//        }
//        E element = x.item;
//        Node<E> next = x.next;
//        Node<E> prev = x.prev;
//
//        if (prev == null) {
//            first = next;
//        } else {
//            prev.next = next;
//            x.prev = null;
//        }
//
//        if (next == null) {
//            last = prev;
//        } else {
//            next.prev = prev;
//            x.next = null;
//        }
//        size--;
//        return element;
//    }

    public E remove(int index){
        if (index>=0 && index<size){
            E temp;
            size--;
            if (index==0){
                temp =first.item;
                if (first==last){
                    first=null;
                    last=null;
                }else{
                    first=first.next;
                    first.prev=null;
                }
            }else if(index==size){
                temp =last.item;
                last=last.prev;
                last.next=null;
            } else {
                Node curr =first;
                for (int i = 0; i <index; i++) {
                    curr =curr.next;
                }
                temp = (E) curr.item;
                curr.prev.next=curr.next;
                curr.next.prev=curr.prev;
            }
        return temp;
        }
        return null;
    }


    public boolean remove(Object o) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        return false;
    }
    @Override
    public E remove() {
        if (this.first==null){
          return null;
        }else if(this.first==this.last){
            E temp = first.item;
            first=null;
            last=null;
            this.size--;
            return temp;

        }else {
            E temp = first.item;
            first.next.prev=null;
            first=first.next;
            this.size--;
            return temp;
        }
    }

    private E unlink(Node<E> x){
        E element = x.item;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;

    }
    @Override
    public boolean add(E e) {
        this.addLast(e);
        return true;
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public void addFirst(E e) {
        Node<E> temp = this.first;
        Node<E> newNode = new Node((Node) null, e, temp);
        this.first = newNode;
        if (temp == null) {
            this.last = newNode;
        } else {
            temp.prev = newNode;
        }

        ++this.size;
    }
    @Override
    public void addLast(E e) {
        Node<E> temp = this.last;
        Node<E> newNode = new Node((Node) temp, e, null);
        this.last = newNode;
        if (temp == null) {
            this.first = newNode;
        } else {
            temp.next = newNode;
        }

        ++this.size;


    }
    @Override
    public E getFirst() {
        if (this.first== null){
           throw new NoSuchElementException();
        } else {
            return first.item;
        }
    }
    @Override
    public E getLast() {
        if (this.last== null){
            throw new NoSuchElementException();
        } else {
            return last.item;
        }
    }
    @Override
    public E element() {
        return this.getFirst();
    }
    @Override
    public E poll() {
        if (this.first==null){
            return null;
        }else{
        return unlink(this.first);}
    }
    @Override
    public E pollFirst() {
        return poll();
    }
    @Override
    public E pollLast() {
        if (this.last==null){
            return null;
        }else{
            return unlink(this.last);}
    }

/////////////////////////////////////////////
    @Override
    public boolean offerFirst(E e) {
        return false;
    }
    @Override
    public boolean offerLast(E e) {
        return false;
    }
    @Override
    public E removeFirst() {
        return null;
    }
    @Override
    public E removeLast() {
        return null;
    }





    @Override
    public E peekFirst() {
        return null;
    }
    @Override
    public E peekLast() {
        return null;
    }
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }
    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }
    @Override
    public boolean offer(E e) {
        return false;
    }


    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }



    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }



    @Override
    public boolean isEmpty() {
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
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }


}
