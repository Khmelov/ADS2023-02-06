package by.it.group251004.krotsyuk.lesson10;

import java.util.Deque;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Deque<E> {

    Node head;
    Node tail;
    private int size = 0;

    class Node{
        public E data;
        public Node next;
        public Node prev;

        public Node(E data){
            this.data = data;
        }
    }

    @Override
    public String toString(){
        Node currNode = this.head;

        StringBuilder string = new StringBuilder("[");

        while (currNode != null){
            string.append(currNode.data);
            currNode = currNode.next;

            if (currNode != null)
                string.append(", ");
        }

        string.append("]");
        return string.toString();
    }

    @Override
    public void addFirst(E element) {
        Node newNode = new Node(element);

        if (head == null)
            tail = newNode;
        else {
            newNode.next = head;
            head.prev = newNode.prev;
        }
        head = newNode;

        size++;
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public boolean offerFirst(Object o) {
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
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
        if (tail == null)
            return null;

        Node node = tail;
        if (tail.prev == null){
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return node.data;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if (tail == null)
            throw new NoSuchElementException("Trying to get element from the empty list.");

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
    public boolean add(E element) {
        Node newNode = new Node(element);

        if (tail == null)
            head = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

        size++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    public E remove(int index) {
        if (index == 0)
            return pollFirst();
        else if (index == size - 1)
            return pollLast();

        Node currNode = head;

        for (int i = 0; i < index; i++)
            currNode = currNode.next;
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;

        size--;
        return currNode.data;
    }

    @Override
    public E poll() {
        if (head == null)
            return null;

        Node node = head;
        if (head.next == null){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return node.data;
    }

    @Override
    public E element() {
        if (head == null)
            throw new NoSuchElementException("Trying to get element from the empty list.");

        return head.data;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void push(Object o) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null)
            return false;

        Node currNode = head;
        while (currNode != null){
            if (o.equals(currNode.data)){
                if (currNode == head){
                    pollFirst();
                } else if (currNode == tail) {
                    pollLast();
                } else {
                    currNode.prev.next = currNode.next;
                    currNode.next.prev = currNode.prev;
                }

                size--;
                return true;
            }

            currNode = currNode.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
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
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator descendingIterator() {
        return null;
    }
}
