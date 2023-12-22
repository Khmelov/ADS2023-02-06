package by.it.group251003.kukhotskovolets.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {

    private DLLNode<E> first = null;
    private DLLNode<E> last = null;
    private int size = 0;

    private static class DLLNode<E>{
        public E data;
        public DLLNode<E> next;
        public DLLNode<E> prev;

        DLLNode(E data){
            this(null, data, null);
        }

        DLLNode(DLLNode<E> prev, E data, DLLNode<E> next){
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private DLLNode<E> getNode(int index){
        DLLNode<E> currentNode = first;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (size > 0){
            DLLNode<E> currentNode = first;
            String delimeter = "";

            for (int i = 0; i < size; i++) {
                sb.append(delimeter).append(currentNode.data);
                currentNode = currentNode.next;
                delimeter = ", ";
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public E remove(int index){

        E deletedElement;
        if (index == 0){
            deletedElement = removeFirst();
        } else if (index == --size) {
            deletedElement = removeLast();
        }
        else {
            DLLNode<E> currentNode = getNode(index);
            deletedElement = currentNode.data;
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
        }
        return deletedElement;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        DLLNode<E> currentNode = first;
        while (currentNode != null){
            if (o.equals(currentNode.data)){
                if (currentNode == first){
                    removeFirst();
                } else if (currentNode == last) {
                    removeLast();
                }
                else {
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                    size--;
                }
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void addFirst(E e) {
        DLLNode<E> newNode = new DLLNode<E>(e);
        if (!isEmpty()){
            first.prev = newNode;
            newNode.next = first;
        }
        else {
            last = newNode;
        }
        first = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        DLLNode<E> newNode = new DLLNode<E>(e);
        if (!isEmpty()){
            newNode.prev = last;
            last.next = newNode;
        }
        else {
            first = newNode;
        }
        last = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E pollFirst() {
        E deletedNode = first.data;
        first = first.next;
        size--;
        return deletedNode;
    }

    @Override
    public E pollLast() {
        E deletedNode = last.data;
        last = last.prev;
        size--;
        return deletedNode;
    }

    @Override
    public E element() {
        return first.data;
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
    public E poll() {
        return removeFirst();
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
        return pollFirst();
    }

    @Override
    public E removeLast() {
        return pollLast();
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
