package by.it.group251003.kopytok_mikhail.lesson10;

import java.awt.*;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;


public class MyLinkedList<E> implements Deque<E> {
    private int size = 0;
    myPointer<E> head = new myPointer<E>();
    public String toString(){
        StringBuilder str = new StringBuilder("[");
        if (size > 0) {
            myPointer<E> curr = head;
            for (int i = 0; i < size - 1; i++) {
                str.append(curr.data);
                curr = curr.next;
                str.append(',').append(' ');
            }
            str.append(curr.data);
        }
        str.append(']');
        return str.toString();
    }
    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }
    @Override
    public void addFirst(E e) {
        if (size == 0){
            head.data = e;
        } else {
            myPointer<E> curr = head;
            curr.prev = new myPointer<E>();
            curr = curr.prev;
            curr.data = e;
            curr.next = head;
            head = curr;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == 0){
            head.data = e;
            size++;
        } else {
            myPointer<E> curr = head;
            for (int i = 0; i < size - 1; i++) {
                curr = curr.next;
            }
            curr.next = new myPointer<E>();
            curr.next.prev = curr;
            curr = curr.next;
            curr.data = e;
            size++;
        }
    }

    @Override
    public boolean offerFirst(Object o) {
        return false; //-
    }

    @Override
    public boolean offerLast(Object o) {
        return false; // -
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == 1){
            E res = head.data;
            head.data = null;
            size--;
            return res;
        }
        myPointer<E> curr = head;
        curr = curr.next;
        E res = head.data;
        curr.prev = null;
        head = curr;
        size--;
        return res;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == 1){
            E res = head.data;
            head.data = null;
            size--;
            return res;
        }
        myPointer<E> curr = head;
        for (int i = 0; i < size - 2; i++){
            curr = curr.next;
        }
        E res = curr.next.data;
        curr.next = null;
        size--;
        return res;
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
    public E getFirst() {
        return head.data;
    }

    @Override
    public E getLast() {
        if (size == 0){
            return null;
        }
        myPointer<E> curr = head;
        for (int i = 1; i < size; i++){
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public E peekFirst() {
        return null; // -
    }

    @Override
    public E peekLast() {
        return null; // -
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false; // -
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false; // -
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(Object o) {
        return false; // -
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || size == 0){
            return false;
        }
        myPointer<E> curr = head;
        int i = 0;
        while (curr != null) {
            if (o.equals(curr.data)){
                if (i == 0){
                    removeFirst();
                } else if (i == size - 1){
                    removeLast();
                }
                else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    size--;
                }
                return true;
            }
            i++;
            curr = curr.next;
        }
        return false;
    }

    public E remove(int index){
        if (size == 0 || index >= size || index < 0){
            return null;
        }
        if (index == 0){
            return pollFirst();
        } else if (index == size - 1){
            return pollLast();
        } else {
            myPointer<E> curr = head;
            for (int i = 0; i < index; i++){
                curr = curr.next;
            }
            E res = curr.data;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
            return res;
        }
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
        return null; // -
    }

    @Override
    public boolean addAll(Collection c) {
        return false; // -
    }

    @Override
    public void clear() {
                    // -
    }

    @Override
    public boolean retainAll(Collection c) {
        return false; // -
    }

    @Override
    public boolean removeAll(Collection c) {
        return false; // -
    }

    @Override
    public void push(Object o) {
    // -
    }

    @Override
    public E pop() {
        return null; // -
    }


    @Override
    public boolean containsAll(Collection c) {
        return false; // -
    }

    @Override
    public boolean contains(Object o) {
        return false; // -
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false; // -
    }

    @Override
    public Iterator iterator() {
        return null; // -
    }

    @Override
    public Object[] toArray() {
        return new Object[0]; // -
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0]; // -
    }

    @Override
    public Iterator descendingIterator() {
        return null; // -
    }
}
