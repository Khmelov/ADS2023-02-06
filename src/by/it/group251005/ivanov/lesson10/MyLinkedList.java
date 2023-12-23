package by.it.group251005.ivanov.lesson10;

import java.util.*;

class Node<E> {
    Node<E> next, prev;
    E elem;
}

public class MyLinkedList<E> implements Deque<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public String toString() {          //возращает стринг [e1, e2, e3, ... , en]
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        Node cur = head;
        while (cur != tail) {
            sb.append(delimiter).append(cur.elem);
            cur = cur.next;
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {           //Добавляет элемент в конец очереди
        if (isEmpty()) {
            tail = null;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.elem = e;
            return true;
        }

        size++;
        tail.elem = e;

        Node tmp = new Node();
        tail.next = tmp;
        tmp.prev = tail;
        tail = tmp;
        return true;
    }

    @Override
    public void addFirst(E e) {          //Добавляет элемент e в начало очереди
        if (isEmpty()) {
            tail = null;
            head = new Node();
            tail = new Node();
            tail.prev = head;
            head.next = tail;
            head.prev = null;
            tail.next = null;
            size++;
            head.elem = e;
            return;
        }

        size++;

        Node tmp = new Node();
        head.prev = tmp;
        tmp.next = head;
        tmp.elem = e;
        head = tmp;
    }

    @Override
    public void addLast(E e) {         //Добавляет элемент e в конец очереди
        if (isEmpty()) {
            tail = null;
            head = new Node();
            tail = new Node();
            tail.prev = head;
            head.next = tail;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.elem = e;
            return ;
        }

        size++;
        tail.elem = e;

        Node tmp = new Node();
        tail.next = tmp;
        tmp.prev = tail;
        tail = tmp;
    }

    @Override
    public int size() {
        return size;        ////возвращает кол-во элементов массива
    }

    @Override
    public boolean isEmpty() {
        return size == 0;       //возвращает тру если он пуст
    }

    @Override
    public E getFirst() {               //Возвращает первый элемент из очереди
        if (isEmpty()) {
            return null;
        }

        return head.elem;
    }

    @Override
    public E getLast() {                //Возвращает последний элемент из очереди
        if (isEmpty()) {
            return null;
        }

        return tail.prev.elem;
    }

    @Override
    public E poll() {           //Извлекает и удаляет начало очереди, представленное этим списком (другими
        if (isEmpty()) {        //словами, первый элемент этого списка), или возвращает null, если этот список пуст.
            return null;
        }

        size--;
        E element = head.elem;
        head = head.next;
        head.prev = null;
        return element;
    }

    @Override
    public E pollFirst() {      //Возвращает и удаляет первый элемент из очереди, вернет null, если нет элементов
        if (isEmpty()) {
            return null;
        }

        size--;
        E element = head.elem;
        head = head.next;
        head.prev = null;
        return element;
    }

    @Override
    public E pollLast() {       //Возвращает и удаляет последний элемент из очереди, вернет null, если нет элементов
        if (isEmpty()) {
            return null;
        }

        size--;
        E element = (E) tail.prev.elem;
        tail = tail.prev;
        tail.next = null;
        return element;
    }

    @Override
    public E element() {       // Извлекает, но не удаляет начало очереди, представленное этим списком (другими словами, первый элемент
        if (isEmpty()) {       // этого списка). Этот метод отличается от peek только тем, что он генерирует исключение, если этот deque пуст.
            return null;
        }

        return head.elem;
    }

    public E remove(int index) {            //Извлекает и удаляет начало очереди, представленное этим
        if (index >= size || isEmpty()) {   //списком (другими словами, первый элемент этого списка).
            return null;
        }

        if (index == 0) {
            size--;
            E element = head.elem;
            head = head.next;
            head.prev = null;
            return element;
        }

        Node<E> cur = head;
        int j = 0;
        while(cur != tail.prev) {
            if (j == index) {
                size--;
                E element = cur.elem;
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                cur.next = null;
                cur.prev = null;
                return element;
            }
            cur = cur.next;
            j++;
        }

        if (index == j) {
            size--;
            E element = tail.prev.elem;
            tail = tail.prev;
            tail.next = null;
            return element;
        }

        return null;
    }

    @Override
    public E remove() {     //Retrieves and removes the head of the queue represented by this deque (in other words, the first element of this deque).
        if (isEmpty()) {
            return null;
        }

        E element = head.elem;
        head = head.next;
        head.prev = null;
        return element;
    }

    @Override
    public boolean remove(Object o) {   //Удаляет первое вхождение указанного элемента из этого списка.
        if (isEmpty()) {
            return false;
        }

        if (o.equals(head.elem)) {
            size--;
            head = head.next;
            head.prev = null;
            return true;
        }

        Node<E> cur = head;
        while (cur != tail.prev) {
            if (o.equals(cur.elem)) {
                size--;
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                cur.next = null;
                cur.prev = null;
                return true;
            }

            cur = cur.next;
        }

        if (o.equals(tail.prev.elem)) {
            size--;
            tail = tail.prev;
            tail.next = null;
            return true;
        }

        return false;
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
