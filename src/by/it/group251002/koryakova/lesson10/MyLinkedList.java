package by.it.group251002.koryakova.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Deque;

public class MyLinkedList<E> implements Deque<E>{
    int size = 0;
    private static class Knot<E>{
        Knot<E> prev;
        Knot<E> next;
        E data;
        private Knot(E data){
            this.prev = null;
            this.next = null;
            this.data = data;
        }
    }
    private Knot head;
    private Knot tail;

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        for (Knot<E> temp = head; temp != null; temp = temp.next){
            res.append(temp.data.toString()).append(", ");
        }
        if (head != null) {
            res.delete(res.length() - 2, res.length());
        }
        return res.append(']').toString();
    }
    @Override
    public void addFirst(E e) {
        Knot<E> temp = new Knot<E>(e);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        size = size + 1;
    }

    @Override
    public void addLast(E e) {
        Knot<E> temp = new Knot<E>(e);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }
        size = size + 1;
    }

    @Override
    public boolean add(E e){
        addLast(e);
        return true;
    }

    @Override
    public boolean offerFirst(E e){
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E peekFirst(){
        return null;
    }

    @Override
    public E peekLast(){
        return null;
    }

    @Override
    public E removeFirst(){
        Knot<E> temp = head;
        head = head.next;
        head.prev = null;
        size = size - 1;
        return temp.data;
    }

    @Override
    public E removeLast(){
        Knot<E> temp = tail;
        tail = tail.prev;
        tail.next = null;
        size = size - 1;
        return temp.data;
    }

    @Override
    public boolean remove(Object o) {
        Knot<E> temp = head;
        while((temp != null) && (!o.equals(temp.data))) {
            temp = temp.next;
        }
        if(temp != null){
            Knot<E> tmpP = temp.prev;
            Knot<E> tmpN = temp.next;
            if(tmpP != null) {
                tmpP.next = tmpN;
            }
            if(tmpN != null) {
                tmpN.prev = tmpP;
            }
            size = size - 1;
            return true;
        }
        return false;
    }

    public E remove(int index){
        Knot<E> temp = head;
        int i;
        for (i = 0; (i < index) && (temp != null); i++) {
            temp = temp.next;
        }
        if (temp != null){
            Knot<E> tmpP = temp.prev;
            Knot<E> tmpN = temp.next;
            if (tmpP != null) {
                tmpP.next = tmpN;
            }
            if (tmpN != null) {
                tmpN.prev = tmpP;
            }
            size = size - 1;
            return temp.data;
        }
        return null;
    }

    @Override
    public E remove() {
        return removeFirst();
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
    public E element() {
        return getFirst();
    }


    @Override
    public E getFirst() {
        return (E) head.data;
    }

    @Override
    public E getLast() {
        return (E) tail.data;
    }


    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}