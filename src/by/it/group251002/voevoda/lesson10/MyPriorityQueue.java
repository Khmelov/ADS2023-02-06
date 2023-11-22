package by.it.group251002.voevoda.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    private E[] heap;
    private int length;
    private int capacity;
    private boolean isMinHeap;
    public MyPriorityQueue() {
        int initialSize = 16;
        heap = (E[]) new Object[initialSize];
        length = 0;
        capacity = initialSize;
        isMinHeap = true;
    }

    public MyPriorityQueue(int length) {
        E[] heap = (E[]) new Object[length];
        this.length = 0;
        capacity = length;
        isMinHeap = false;
    }

    public MyPriorityQueue(int length, int capacity) {
        E[] heap = (E[]) new Object[capacity];
        this.length = length;
        this.capacity = capacity;
        isMinHeap = false;
    }

    public MyPriorityQueue(E[] array) {
        heap = (E[]) new Object[array.length];
        System.arraycopy(array, 0, heap, 0, array.length);
        length = array.length;
        capacity = array.length;
        isMinHeap = false;
        buildHeap();
    }

    public MyPriorityQueue(boolean isMinHeap) {
        int initialSize = 16;
        heap = (E[]) new Object[initialSize];
        length = 0;
        capacity = initialSize;
        this.isMinHeap = isMinHeap;
    }

    public MyPriorityQueue(int length, boolean isMinHeap) {
        E[] heap = (E[]) new Object[length];
        this.length = 0;
        capacity = length;
        this.isMinHeap = isMinHeap;
    }

    public MyPriorityQueue(int length, int capacity, boolean isMinHeap) {
        E[] heap = (E[]) new Object[capacity];
        this.length = length;
        this.capacity = capacity;
        this.isMinHeap = isMinHeap;
    }

    public MyPriorityQueue(E[] array, boolean isMinHeap) {
        heap = (E[]) new Object[array.length];
        System.arraycopy(array, 0, heap, 0, array.length);
        length = array.length;
        capacity = array.length;
        this.isMinHeap = isMinHeap;
        buildHeap();
    }

    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////

    private boolean cmp(int i, int j) {
        if (isMinHeap) {
            return ((Comparable<E>)heap[i]).compareTo(heap[j]) < 0;
        }
        return ((Comparable<E>)heap[i]).compareTo(heap[j]) > 0;
    }

    private void siftUp(int i) {
        for (int parent = (i - 1) >> 1; i > 0; parent = (i - 1) >> 1) {
            if (parent >= 0 && cmp(i, parent)) {
                E tmp = heap[i];
                heap[i] = heap[parent];
                heap[parent] = tmp;
                i = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int i) {
        for (int child = (i << 1) | 1; i < length; child = (i << 1) | 1) {
            int best = i;

            if (child < length && cmp(child, best)) {
                best = child;
            }

            ++child;
            if (child < length && cmp(child, best)) {
                best = child;
            }

            if (i != best) {
                E tmp = heap[i];
                heap[i] = heap[best];
                heap[best] = tmp;
                i = best;
            } else {
                break;
            }
        }
    }

    private void buildHeap() {
        for (int i = length - 1; i >= 0; --i) {
            siftDown(i);
        }
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append('[');
        if (length > 0) {
            sb.append(heap[0]);
        }

        for (int i = 1; i < length; ++i) {
            sb.append(", ");
            sb.append(heap[i]);
        }

        sb.append(']');

        return sb.toString();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean add(E e) {

        if (length + 1 > capacity) {

            if (capacity == 0) {
                capacity = length + 1;
            }

            if (capacity < 1024) {
                capacity <<= 1;
            } else {
                capacity = capacity + (capacity / 4);
            }

            E[] heap = (E[]) new Object[capacity];
            for (int i = 0; i < length; ++i) {
                heap[i] = this.heap[i];
            }
            this.heap = heap;

        }

        this.heap[length] = e;
        siftUp(length++);

        return true;
    }

    @Override
    public E remove() {
        if (length == 0) {
            try {
                throw new Exception("Exception: priority queue is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        E res = heap[0];
        heap[0] = heap[--length];
        siftDown(0);

        return res;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; ++i) {
            if (heap[i].equals(o)) {
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
    public E poll() {
        if (length == 0) {
            return null;
        }
        E res = heap[0];
        heap[0] = heap[--length];
        siftDown(0);
        return res;
    }

    @Override
    public E peek() {
        if (length == 0) {
            return null;
        }
        return heap[0];
    }

    @Override
    public E element() {
        if (length == 0) {
            try {
                throw new Exception("Exception: priority queue is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c) {
            for (int i = 0; i < length; ++i) {
                if (!o.equals(heap[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int oldSize = length;
        for (Object o: c) {
            add((E)o);
        }
        return oldSize != length;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = length;
        length = 0;
        for(int i = 0; i < oldSize; ++i) {
            if(!c.contains(heap[i])) {
                add(heap[i]);
            }
        }
        return oldSize != length;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = length;
        length = 0;
        for (int i = 0; i < oldSize; ++i) {
            if (c.contains(heap[i])) {
                add(heap[i]);
            }
        }
        return oldSize != length;
    }

    /////////////////////////////////////////////////////////////////////////
    //////              Необязательные к реализации методы            ///////
    /////////////////////////////////////////////////////////////////////////

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
    public boolean remove(Object o) {
        return false;
    }

}
