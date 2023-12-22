package by.it.group251002.klimovich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
class Node<E>{
    E elem;
    Node<E> next=null;
    Node<E> prev=null;

}
public class MyLinkedList<E> implements Deque<E> {

    private Node<E> head=null;
    private Node<E> tail=null;

    private int size=0;

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String sep ="";
        Node<E> elem=head;
        while (elem!=tail.next) {
            sb.append(sep).append(elem.elem);
            elem=elem.next;
            sep=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void addFirst(E e) {
        Node<E> elem = new Node();
        elem.elem=e;
        if (head==null){
            head=elem;
            tail=elem;
        }
        else{
            elem.next=head;
            head.prev=elem;
            head=elem;
            elem.prev=null;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> elem = new Node();
        elem.elem=e;
        if (head==null){
            head=elem;
            tail=elem;
        }
        else{
            elem.prev=tail;
            tail.next=elem;
            tail=elem;
            elem.next=null;
        }
        size++;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public E remove(int index){
        Node<E> elem=head;
        while (elem!=null && index!=0){
            elem=elem.next;
            index--;
        }
        if (elem==null){
            return null;
        }
        E res=elem.elem;
        if (elem.prev!=null){
            if (elem.next!=null){
                elem.prev.next=elem.next;
                elem.next.prev=elem.prev;
                elem=null;
            }
            else{
                elem.prev.next=elem.next;
                tail=elem.prev;
                elem=null;
            }
        }
        else{
            elem.next.prev=elem.prev;
            head=elem.next;
            elem=null;
        }
        size--;
        return res;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> elem=head;
        while (elem!=null){
            if(elem.elem==o){
                if (elem.prev!=null){
                    if (elem.next!=null){
                        elem.prev.next=elem.next;
                        elem.next.prev=elem.prev;
                        elem=null;
                    }
                    else{
                        elem.prev.next=elem.next;
                        tail=elem.prev;
                        elem=null;
                    }
                }
                else{
                    elem.next.prev=elem.prev;
                    head=elem.next;
                    elem=null;
                }
                size--;
                return true;
            }
            elem=elem.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getFirst() {
        if (head!=null) {
            return head.elem;
        }
        return null;
    }

    @Override
    public E getLast() {
        if (tail!=null) {
            return tail.elem;
        }
        return null;
    }

    @Override
    public E pollFirst() {
        return remove(0);
    }

    @Override
    public E pollLast() {
        return remove(size-1);
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
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
}

