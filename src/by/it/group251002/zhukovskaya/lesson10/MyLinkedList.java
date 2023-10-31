package by.it.group251002.zhukovskaya.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

class Node<E> {
    E x;
    Node<E> next;
    Node<E> prev;
}
public class MyLinkedList<E> implements Deque<E> {
    int size = 0;
    private Node<E> tail;
    private Node<E> front;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String d = "";
        Node current = front;
        while (current != tail) {
            sb.append(d).append(current.x);
            d = ", ";
            current = current.next;
        }
        sb.append("]");
        return sb.toString();

    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            front = new Node();
            tail = new Node();
            front.next = tail;
            tail.prev = front;
            front.prev = null;
            tail.next = null;
            front.x = e;
            size++;
        }
        else {
            Node temp = new Node();
            tail.x = e;
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            size++;
        }
        return true;
    }

    public E remove(int index) {
        if (index>=size || size==0) {
            return null;
        }
        if (index==0) {
            E e=front.x;
            front=front.next;
            front.prev=null;
            size--;
            return e;
        }
        Node<E> cur= front;
        int c=0;
        while (c<index) {
            cur=cur.next;
            c++;
        }
        cur.prev.next=cur.next;
        if (cur==tail) {
            if (front.next==null) {
                front=null;
            }
            else {
                tail.prev.next=null;
            }
            tail=tail.prev;
        }
        else  {
            cur.next.prev=cur.prev;
        }
        size--;
        return cur.x;
    }
    @Override
    public boolean remove(Object o) {
        Node<E> cur=front;
        if (o.equals(front.x)) {
            front=front.next;
            front.prev=null;
            size--;
            return true;
        }
        if (size==0)
            return false;
        while (cur!=tail.prev) {
            if (o.equals(cur.x)) {
                cur.prev.next=cur.next;
                cur.next.prev=cur.prev;
                cur.prev=null;
                cur.next=null;
                size--;
                return true;
            }
            cur=cur.next;
        }
        if (o.equals(tail.prev.x)) {
            tail=tail.prev;
            tail.next=null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E e) {
        Node<E> temp= new Node();
        if (size == 0) {
            front = new Node();
            tail = new Node();
            front.next = tail;
            tail.prev = front;
            front.prev = null;
            tail.next = null;
            front.x = e;
            size++;
        }
        else {
            front.prev=temp;
            temp.next=front;
            temp.x=e;
            front=temp;
            size++;
        }
    }

    @Override
    public void addLast(E e) {
        if (size == 0) {
            front = new Node();
            tail = new Node();
            front.next = tail;
            tail.prev = front;
            front.prev = null;
            tail.next = null;
            front.x = e;
            size++;
        }
        else {
            Node temp = new Node();
            tail.x = e;
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            size++;
        }
    }

    @Override
    public E element() {
        if (size==0)
            return null;
        return front.x;
    }

    @Override
    public E getFirst() {
        if (size==0)
            return null;
        return front.x;
    }

    @Override
    public E getLast() {
        if (size==0)
            return null;
        return tail.prev.x;
    }

    @Override
    public E poll() {
        if (size==0)
            return null;
        E e=front.x;
        front=front.next;
        front.prev=null;
        size--;
        return e;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size==0)
            return null;
        E e=tail.prev.x;
        tail=tail.prev;
        tail.next=null;
        size--;
        return e;
    }

    ///////////////////////////////////////////////////

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
    public E remove() {
        return null;
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
    //////////////////////////////////////
}



