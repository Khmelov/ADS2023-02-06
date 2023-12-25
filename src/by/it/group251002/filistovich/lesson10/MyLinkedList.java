package by.it.group251002.filistovich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    private class Node<E>{
        E elem;
        Node<E> prev;
        Node<E> next;
        Node(E e){
            elem = e;
            prev = null;
            next = null;
        }
    }
    private Node<E> last;
    private Node<E> first;
    private int size;
    MyLinkedList (){
        last = null;
        first = null;
        size = 0;
    }

    public E remove(int i){
        int it = 0;
        Node<E> temp = first;
        if ((i < size) && (i > -1)){
            while (it != i){
                temp = temp.next;
                it++;
            }
            E e = temp.elem;
            if (temp.next != null){
                temp.next.prev = temp.prev;
            }
            else {
                last = temp.prev;
            }
            if (temp.prev != null){
                temp.prev.next = temp.next;
            }
            else {
                first = temp.next;
            }
            size--;
            return e;
        }
        return null;
    }


    @Override
    public String toString(){
        Node<E> temp = first;
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";

        while (temp != null){
            sb.append(delimiter).append(temp.elem);
            delimiter = ", ";
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public void addFirst(E e) {
        Node<E> temp = new Node<>(e);
        if (first == null){
            first = temp;
            last = temp;
        } else {
            temp.next = first;
            first.prev = temp;
            first = temp;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> temp = new Node<>(e);
        if (first == null){
            first = temp;
            last = temp;
        } else {
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        if (last.prev != null){
            if (last.prev.prev != null){
                if (last.prev.prev.next == null){
                    int error = 1;
                }
            }

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

        return poll();
    }

    @Override
    public E pollLast() {
        Node<E> temp = last;
        last = last.prev;
        last.next = null;
        size--;

        return temp.elem;
    }

    @Override
    public E getFirst() {

        return first.elem;
    }

    @Override
    public E getLast() {
        return last.elem;
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
        Node<E> temp = first;
        first = first.next;
        first.prev = null;
        size--;
        return temp.elem;
    }

    @Override
    public E element() {

        return first.elem;
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
        Node<E> temp = first;
        while (temp.next != null && temp.elem != o){
            temp = temp.next;


        }
        if (temp.elem == o){
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size--;
            return true;
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
