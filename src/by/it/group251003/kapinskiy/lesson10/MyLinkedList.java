package by.it.group251003.kapinskiy.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    public static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (size != 0) {
            Node<E> curr = first;
            for (int i = 0; i < size - 1; i++) {
                sb.append(curr.item + ", ");
                curr = curr.next;
            }
            sb.append(curr.item);
        }
        return sb.append(']').toString();
    }

    @Override
    public void addFirst(E e) {
        if (size == 0){
            first = new Node(e,last, null);
            last = first;
        }
        else {
            first.prev = new Node<>(e, first, null);
            first = first.prev;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == 0){
            last = new Node(e, first, null);
            first = last;
        }
        else {
            last.next = new Node<>(e, null, last);
            last = last.next;
        }
        size++;
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
        E save = (E) first.item;
        first = first.next;
        first.prev = null;
        size--;
        return save;
    }

    @Override
    public E pollLast() {
        E save = (E) last.item;
        last = last.prev;
        last.next = null;
        size--;
        return save;
    }

    @Override
    public E getFirst() {
        return (E) first.item;
    }

    @Override
    public E getLast() {
        return (E) last.item;
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
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        Node curr = first;
        for (int i = 0; i < size; i++){
            if (curr.item.equals(o)){
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public E remove(int index){
        Node curr = first;
        int i = 0;
        E save = null;
        while(curr != null && i != index) {
            curr = curr.next;
            i++;
        }
        if (curr != null){
            save = (E)curr.item;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
            return save;
        }
        return save;
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
    public boolean containsAll(Collection<?> c) {
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
}
