package by.it.group251001.lashkin.lesson10;

import java.util.*;

/*             + toString()
               + add(E element)
               + remove(int)
               + remove(E element)
               + size()

               + addFirst(E element)
               + addLast(E element)

               + element()
               + getFirst()
               + getLast()

               + poll()
               + pollFirst()
               + pollLast()
* */

public class MyLinkedList<E> implements Deque<E> {

    Node<E> first;
    Node<E> last;
    int size;
    static class Node<E> {
        E elem;
        Node<E> next;
        Node<E> prev;
        Node(E data, Node<E> prev, Node<E> next) {
            this.elem = data;
            this.prev = prev;
            this.next = next;
        }
    }
    @Override
    public String toString() {
        String s = "[";
        Node<E> temp = first;
        if (temp != null) {
            while (temp.next != null) {
                s = s + temp.elem + ", ";
                temp = temp.next;
            }
            s = s + temp.elem;
        }
        s = s + "]";
        return s;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public E remove(int index) {
        Node<E> frt = first;
        for (int i = 0; i < index; i++)
            frt = frt.next;
        E data = frt.elem;
        Node<E> next = frt.next;
        Node<E> prev = frt.prev;
        if (next != null){
            next.prev = prev;
            frt.next = null;
        } else {
            last = prev;
        }
        if (prev != null){
            prev.next = next;
            frt.prev = null;
        } else {
            first = next;
        }
        frt.elem = null;
        size--;
        return data;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        Node<E> current = first;
        while (current != null) {
            if (Objects.equals(o, current.elem)) {
                if (current == first) {
                    removeFirst();
                } else if (current == last) {
                    removeLast();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null, first);
        Node<E> prevNode = first;
        first = newNode;
        if (prevNode == null) {
            last = newNode;
        } else{
            prevNode.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, last, null);
        Node<E> prevNode = last;
        last = newNode;
        if (prevNode == null) {
            first = newNode;
        } else{
            prevNode.next = newNode;
        }
        size++;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if (first == null)
            return null;
        return first.elem;
    }

    @Override
    public E getLast() {
        if (last == null)
            return null;
        return last.elem;
    }
    @Override
    public E poll() {
        return pollFirst();
    }
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E data = first.elem;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return data;
    }

    @Override
    public E pollLast() {
        if(last == null)
            return null;

        E data = last.elem;
        Node<E> prev = last.prev;

        last.elem = null;
        last.prev = null;
        last = prev;

        if (last != null)
            last.next = null;
        else
            first = null;

        size--;
        return data;
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
