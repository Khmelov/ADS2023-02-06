package by.it.group251004.mukhtasarov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {

    final int defaultSize = 8;
    int _front;
    int _rear;
    E[] _items;
    int Count;
    MyArrayDeque() {
        _items = (E[]) new Object[defaultSize];
        _rear = -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = _front, count = 0; count < Count; i++, count++) {
            sb.append(_items[i % _items.length]);
            if (count < Count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (Count == _items.length)
            Resize(_items.length * 2);
        _front = (_front - 1 + _items.length) % _items.length;
        _items[_front] = e;
        Count++;
    }

    @Override
    public void addLast(E e) {
        if (Count == _items.length)
            Resize(_items.length * 2);
        _rear = (_rear + 1) % _items.length;
        _items[_rear] = e;
        Count++;
    }

    void Resize(int max) {
        E[] tempItems = (E[]) new Object[max];
        int counter = 0;
        for (int i = _front; counter != Count; i++)
            tempItems[counter++] = _items[i % Count];
        _items = tempItems;
        _front = 0;
        _rear = Count - 1;
    }

    @Override
    public int size() {
        return Count;
    }

    @Override
    public E pollFirst() {
        if (Count == 0)
            return null;
        E item = _items[_front];
        _front = (_front + 1) % _items.length;
        Count--;
        return item;
    }

    @Override
    public E pollLast() {
        if (Count == 0)
            return null;
        E item = _items[_rear];
        _rear = (_rear - 1 + _items.length) % _items.length;
        Count--;
        return item;
    }

    @Override
    public E getFirst() {
        if (Count == 0)
            return null;
        return _items[_front];
    }

    @Override
    public E getLast() {
        if (_rear == -1 && Count > 0)
            return _items[_front];
        if (Count == 0)
            return null;
        return _items[_rear];
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    //////////////////////////

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
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
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
    public boolean isEmpty() {
        return Count == 0;
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
