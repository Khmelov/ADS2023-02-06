package by.it.group251001.vasilenko.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    int size = 0;

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        private Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node<E> first;
    Node<E> last;
    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder("[");
        for(Node<E> tmp = first; tmp != null; tmp = tmp.next){
            sb.append(tmp.data.toString()).append(", ");
        }
        if (first != null) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public void addFirst(E e) {
        Node<E> x= new Node<E>(e);
        if (first == null) {
            first = x;
            last = x;
        } else {
            x.next = first;
            first.prev = x;
            first = x;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> x= new Node<E>(e);
        if (first == null) {
            first = x;
            last = x;
        } else {
            last.next = x;
            x.prev = last;
            last = x;
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

    public E remove(int index) {
        int i =0;
        if (index == 0){
            pollFirst();
        }
        if (index == size-1){
            pollLast();
        }
        for (Node<E> x = first;x!=null; x = x.next) {
            if (i == index) {
                Node<E> tmp = x;
                if (x.prev!=null) x = x.prev;
                else{
                    x = x.next;
                    x.prev = null;
                    size--;
                    return tmp.data;
                }
                x.next = tmp.next;
                x = tmp.next;
                if(x!=null) x.prev = tmp.prev;
                size--;
                return tmp.data;
            }
            i++;
        }
        return null;
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
        Node<E> x = first;
        first = first.next;
        first.prev = null;
        size--;
        return x.data;
    }

    @Override
    public E pollLast() {
        Node<E> x = last;
        last = last.prev;
        last.next = null;
        size--;
        return x.data;
    }

    @Override
    public E getFirst() {
        return first.data;
    }

    @Override
    public E getLast() {
        return last.data;
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
        return first.data;
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
        for (Node<E> x = first;x!=null;x = x.next){
            if (o.equals(x.data)) {
                Node<E> tmp = x;
                if (x.prev!=null) x= x.prev;
                else{
                    x = x.next;
                    x.prev = null;
                    size--;
                    return true;
                }
                x.next = tmp.next;
                x = x.next;
                if(x!=null) x.prev = tmp.prev;
                size--;
                return true;
            }
        }
        return false;
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
