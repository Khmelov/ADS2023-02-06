package by.it.group251004.stasevich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

class Node<E> {
    Node<E> next;
    Node<E> prev;
    E elem;
}

public class MyLinkedList<E> implements Deque<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    MyLinkedList(){
        tail=null;
        head = null;
        size=0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<E> temp = head;
        while (temp.next != null) {
            sb.append(temp.elem);
            sb.append(", ");
            temp = temp.next;
        }
        sb.append(temp.elem);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void addFirst(E e) {
        Node<E> tmp = new Node();
        tmp.elem=e;
        if (size==0) {
            head = tmp;
            tail = tmp;
            head.prev = null;
            tail.next = null;
        }else {
            head.prev = tmp;
            tmp.next = head;
            head = tmp;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
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
        if (size == 0)
            return null;
        E output = head.elem;
        if (size == 1)
            head = tail = null;
        else
        {
            head = head.next;
            head.prev = null;
        }
        size--;
        return output;
    }

    @Override
    public E pollLast() {
        if (size == 0)
            return null;
        E output = tail.elem;
        if (size == 1)
            head = tail = null;
        else
        {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return output;
    }

    @Override
    public E getFirst() {
        if (size==0)
            return null;
        else
            return head.elem;
    }

    @Override
    public E getLast() {
        if (size==0)
            return null;
        else
            return tail.elem;
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
        Node<E> tmp = new Node();
        tmp.elem=e;
        if (size==0) {
            head = tmp;
            tail = tmp;
            head.prev = null;
            tail.next = null;
        }else {
            tail.next = tmp;
            tmp.prev = tail;
            tail = tmp;
            tail.next=null;
        }
        size++;
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
        Node<E> temp = head;
        while (temp != null) {
            if (o.equals(temp.elem)) {
                if (temp.next != null)
                    temp.next.prev = temp.prev;
                else
                    tail = temp.prev;
                if (temp.prev != null)
                    temp.prev.next = temp.next;
                else
                    head = temp.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void remove(int index) {
        Node<E> temp = head;
        if (index > -1 && index < size) {
            int count = 0;
            while (count++ < index) {
                temp = temp.next;
            }
            if (temp.next != null)
                temp.next.prev = temp.prev;
            else
                tail = temp.prev;

            if (temp.prev != null)
                temp.prev.next = temp.next;
            else
                head = temp.next;
            size--;
        }
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
        return size;
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
