package by.it.group251004.rychyhin.lesson10;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    final int defaultSize = 8;
    E[] _items;
    int size;

    public MyPriorityQueue() {
        _items = (E[]) new Comparable[defaultSize];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(_items[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    void heapifyUp(int index) {
        int parent = (index - 1) / 2;

        if (parent >= 0 && _items[index].compareTo(_items[parent]) < 0) {
            E temp = _items[index];
            _items[index] = _items[parent];
            _items[parent] = temp;
            heapifyUp(parent);
        }
    }

    void heapifyDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = left;

        if (right < size && _items[right].compareTo(_items[largest]) < 0)
            largest = right;

        if (largest < size && _items[largest].compareTo(_items[index]) < 0) {
            E temp = _items[index];
            _items[index] = _items[largest];
            _items[largest] = temp;
            heapifyDown(largest);
        }
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
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (_items[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if (size == _items.length) {
            resize();
        }
        _items[size++] = e;
        heapifyUp(size - 1);
        return true;
    }

    void resize() {
        int newCapacity = _items.length * 2;
        E[] newItems = (E[]) new Comparable[newCapacity];
        System.arraycopy(_items, 0, newItems, 0, size);
        _items = newItems;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E item : c) {
            if (add(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object item : c) {
            if (remove(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(_items[i])) {
                remove(_items[i]);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, _items[i])) {
                _items[i] = _items[--size];
                heapifyDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalArgumentException("PriorityQueue is empty");
        }
        E root = _items[0];
        _items[0] = _items[--size];
        heapifyDown(0);
        return root;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }
        return _items[0];
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return element();
    }

    @Override
    public void clear() {
        size = 0;
    }

    ////////////////////////////////

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

}
