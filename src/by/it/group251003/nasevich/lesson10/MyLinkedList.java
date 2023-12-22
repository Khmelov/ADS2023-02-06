package by.it.group251003.nasevich.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {

    private int size = 0;

    private Node head;
    private Node tail;

    public MyLinkedList(){
        head = null;
        tail = head;
    }

    private class Node{

        public E data;
        public Node next;
        public Node prev;

        public Node(E data){
            this.data = data;
            next = null;
            prev = null;
        }

    }

    @Override
    public String toString(){

        String str = "[";

        Node currentNode = head;
        if (size != 0) {
            for (int i = 0; i < size - 1; i++) {
                str += currentNode.data;
                currentNode = currentNode.next;
                str += ", ";
            }

            str += currentNode.data;
        }

        str += "]";

        return str;
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
    public boolean contains(Object o) {
        return false;
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
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void addFirst(E e) {

        Node newNode = new Node(e);
        Node currentNode = head;

        if (head == null){
            head = newNode;
            tail = head;
        }
        else{
            newNode.next = currentNode;
            head = newNode;
            currentNode.prev = newNode;

            while (newNode.next != null){
                newNode = newNode.next;
            }
            tail = newNode;
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

        Node currentNode = head;
        E item = currentNode.data;
        try{
            if (currentNode.next == null){
                head = null;
                tail = head;
            }
            else{
                head = currentNode.next;
                head.prev = null;
            }
        }
        catch (NoSuchElementException error){
            System.out.println(error);
        }

        size--;
        return item;
    }

    public E remove(int pos){

        Node currentNode = head;
        Node prevNode = null;

        E item;

        if (pos >= size || pos < 0){
            throw new IndexOutOfBoundsException();
        }
        else{

            int i = 0;
            while (i != pos){
                prevNode = currentNode;
                currentNode = currentNode.next;
                i++;
            }

            item = currentNode.data;

            if (currentNode == head){
                head = currentNode.next;
                currentNode.prev = null;
            }
            else if (currentNode == tail) {
                prevNode.next = null;
                tail = prevNode;
            }
            else{
                prevNode.next = currentNode.next;
                currentNode = currentNode.next;
                currentNode.prev = prevNode;
            }

            size--;
        }
        return item;

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
        if (size == 0){
            return null;
        }
        else{

            Node currentNode = tail;
            Node prevNode = currentNode.prev;
            E item = currentNode.data;

            currentNode = prevNode;
            currentNode.next = null;
            tail = currentNode;

            size--;
            return item;
        }
    }

    @Override
    public E getFirst() {
        return head.data;
    }

    @Override
    public E getLast() {
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

        Node node = new Node(e);
        Node currentNode = tail;
        Node prevNode = null;

        size++;

        if (head == null){
            head = node;
            node.prev = null;
            tail = head;
        }
        else{
            currentNode.next = node;
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentNode.prev = prevNode;
            tail = node;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        
        Node currentNode = head;
        
        while (currentNode != null) {
            if (Objects.equals(o, currentNode.data)) {
                if (currentNode.next != null)
                    currentNode.next.prev = currentNode.prev;
                else
                    tail = currentNode.prev;

                if (currentNode.prev != null)
                    currentNode.prev.next = currentNode.next;
                else
                    head = currentNode.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
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
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {

        if (size == 0){
            return null;
        }
        else{

            Node currentNode = head;
            E item = currentNode.data;
            head = currentNode.next;

            size--;
            return item;
        }

    }

    @Override
    public E element() {

        if(size == 0){
            throw new NoSuchElementException();
        }

        return head.data;
    }

    @Override
    public E peek() {
        return null;
    }
}
