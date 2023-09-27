package by.it.group251004.stasevich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class MyPriorityQueue <E extends Comparable<E>> implements Queue<E> {

    private int size=0;
    private int capacity=30;
    private E[] heap=(E[]) new Comparable[capacity];


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    void siftDown(int index) { //просеивание вверх
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int larg = left;
        if (right < size && heap[right].compareTo(heap[larg]) < 0)
            larg = right;
        if (larg < size && heap[larg].compareTo(heap[index]) < 0) {
            E temp = heap[index];
            heap[index] = heap[larg];
            heap[larg] = temp;
            siftDown(larg);
        }
    }


    void siftUp(int index) { //просеивание вниз
        int parent = (index - 1) / 2;
        if (parent >= 0 && heap[index].compareTo(heap[parent]) < 0) {
            E temp = heap[index];
            heap[index] = heap[parent];
            heap[parent] = temp;
            siftUp(parent);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(o)) {
                return true;
            }
        }
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
    public boolean add(E e) {
        if (size == capacity) {
            capacity *= 2;
            E[] newItems = (E[]) new Comparable[capacity];
            System.arraycopy(heap, 0, newItems, 0, size);
            heap = newItems;
        }
        heap[size++] = e;
        siftUp(size - 1);
        return true;
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
        boolean f = false;
        for (E item : c) {
            if (add(item)) {
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = false;
        for (Object item : c) {
            if (remove(item)) {
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean f= false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(heap[i])) {
                remove(heap[i]);
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, heap[i])) {
                heap[i] = heap[--size];
                siftDown(i);
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
            return null;
        }
        E root = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
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
            return null;
        }
        return heap[0];
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
}
