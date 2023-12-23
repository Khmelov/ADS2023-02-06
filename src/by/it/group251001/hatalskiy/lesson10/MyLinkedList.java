package by.it.group251001.hatalskiy.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {

    protected static class Node<E>{
        public E data;
        public  Node<E> prev,next;

        public Node(){

        }

        public Node(E data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }
    private Node<E> head = null, tail = null;
    public int siz = 0;
    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Node<E> cur = head;
        while (cur.next != null) {
            res.append(cur.data.toString()).append(", ");
            cur = cur.next;
        }
        return res + cur.data.toString() + "]";
    }
    @Override
    public void addFirst(E e) {
    if (siz++ == 0){
        head = new Node<E>(e);
        tail = head;
        return;
    }
    Node<E> curr = new Node<E>(e);
    head.prev = curr;
    curr.next = head;
    head = curr;
    }

    @Override
    public void addLast(E e) {
        if (siz++ == 0){
            head = new Node<E>(e);
            tail = head;
            return;
        }
        Node<E> curr = new Node<E>(e);
        tail.next= curr;
        curr.prev = tail;
        tail = curr;

    }


    public E remove (int index){
        if(index <0 || index > siz)
            throw new IndexOutOfBoundsException();
        siz--;
        Node<E> curr = head;
        if (siz ==0 ){
            head = tail = null;
            return curr.data;
        }
        if (index < siz / 2)
            for (int i = 0; i<index; i++)
                curr = curr.next;
        else{
            curr = tail;
            for (int i = 0; i < siz - index; i++)
                curr = curr.prev;
        }
        if (curr == head) {
            head.next.prev = null;
            head = head.next;
            return curr.data;
        }
        if (curr == tail) {
            tail.prev.next = null;
            tail = tail.prev;
            return curr.data;
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        return curr.data;
    }
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
    public E pollFirst() {
        if (isEmpty())
            return null;
        E toRet = head.data;
        remove(0);
        return toRet;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        E toRet = tail.data;
        remove(siz - 1);
        return toRet;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.data;
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return tail.data;
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
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return null;
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
    public boolean remove(Object o) {
        Node<E> cur = head;
        while (cur != null && !((o == null && cur.data == null) || (o != null && o.equals(cur.data))))
            cur = cur.next;
        if (cur == null)
            return false;
        siz--;
        if (siz == 0) {
            head = tail = null;
            return true;
        }
        if (cur == head) {
            head.next.prev = null;
            head = head.next;
            return true;
        }
        if (cur == tail) {
            tail.prev.next = null;
            tail = tail.prev;
            return true;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        return true;
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
    public int size() {
        return siz;
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
