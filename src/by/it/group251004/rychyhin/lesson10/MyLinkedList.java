package by.it.group251004.rychyhin.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<E> implements Deque<E> {

    class DoublyNode<E>
    {
        public E Data;
        public DoublyNode<E> Previous;
        public DoublyNode<E> Next;
        public DoublyNode(E data) {
            Data = data;
        }
    }

    DoublyNode<E> _head;
    DoublyNode<E> _tail;
    int Count;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        DoublyNode<E> tempHead = _head;
        while (tempHead.Next != null) {
            sb.append(tempHead.Data);
            sb.append(", ");
            tempHead = tempHead.Next;
        }
        sb.append(tempHead.Data);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        DoublyNode<E> node = new DoublyNode(e);

        if (_head == null)
            _head = node;
        else
        {
            _tail.Next = node;
            node.Previous = _tail;
        }
        _tail = node;
        Count++;
        return true;
    }

    @Override
    public void addFirst(E e) {
        DoublyNode<E> node = new DoublyNode(e);
        node.Next = _head;
        if (Count == 0)
            _head = _tail = node;
        else
        {
            _head.Previous = node;
            _head = node;
        }
        Count++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E pollFirst() {
        if (Count == 0)
            return null;
        E output = _head.Data;
        if (Count == 1)
            _head = _tail = null;
        else
        {
            _head = _head.Next;
            _head.Previous = null;
        }
        Count--;
        return output;
    }

    @Override
    public E pollLast() {
        if (Count == 0)
            return null;
        E output = _tail.Data;
        if (Count == 1)
            _head = _tail = null;
        else
        {
            _tail = _tail.Previous;
            _tail.Next = null;
        }
        Count--;
        return output;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E getFirst() {
        if (Count == 0)
            return null;
        return _head.Data;
    }

    @Override
    public E getLast() {
        if (Count == 0)
            return null;
        return _tail.Data;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public int size() {
        return Count;
    }

    @Override
    public boolean remove(Object o) {
        DoublyNode<E> tempHead = _head;
        while (tempHead != null) {
            if (Objects.equals(o, tempHead.Data)) {
                if (tempHead.Next != null)
                    tempHead.Next.Previous = tempHead.Previous;
                else
                    _tail = tempHead.Previous;

                if (tempHead.Previous != null)
                    tempHead.Previous.Next = tempHead.Next;
                else
                    _head = tempHead.Next;
                Count--;
                return true;
            }
            tempHead = tempHead.Next;
        }
        return false;
    }

    public void remove(int index) {
        DoublyNode<E> tempHead = _head;
        if (index > -1 && index < Count) {
            int count = 0;
            while (count++ < index) {
                tempHead = tempHead.Next;
            }
            if (tempHead.Next != null)
                tempHead.Next.Previous = tempHead.Previous;
            else
                _tail = tempHead.Previous;

            if (tempHead.Previous != null)
                tempHead.Previous.Next = tempHead.Next;
            else
                _head = tempHead.Next;
            Count--;
        }
    }

    //////////////////////////////////////////

    @Override
    public E remove() {
        return null;
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
