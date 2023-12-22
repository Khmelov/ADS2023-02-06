package by.it.group251001.dadush.lesson10;

import jdk.internal.util.ArraysSupport;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.Collection;
import java.lang.Comparable;

public class MyPriorityQueue<E>  implements Queue<E> {
    private Object[] data = new Object[0];
    private int size = 0;

    private final int DEFAULT_CAPACITY = 10;

    private void swap(Object[] data, int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    private void siftUp(int pos) {
        int p;
        while (pos > 0) {
            p = (pos - 1) >> 1;
            if (((Comparable<E>)data[pos]).compareTo((E) data[p]) <= 0) {
                swap(data, pos, p);
                pos = p;
            }
            else
                pos = 0;
        }
    }

    private void siftDown(int pos) {
        int child, right;
        while ((child = (pos << 1) + 1) < size) {
            right = child + 1;
            if (right < size &&
                    ((Comparable<E>) data[right]).compareTo((E) data[child]) < 0)
                child = right;
            if (((Comparable<E>) data[pos]).compareTo((E) data[child]) <= 0)
                break;
            swap(data, pos, child);
            pos = child;
        }
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--)
            siftDown(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i == size - 1) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    public int size() {
        return size;
    }
    public void clear() {
        data = new Object[0];
        size = 0;
    }
    public boolean add(E e) {
        Object[] temp;
        if (size == data.length) {
            temp = new Object[data.length == 0 ? DEFAULT_CAPACITY : (data.length << 1)];
            System.arraycopy(data, 0, temp,0, data.length);
            data = temp;
        }
        data[size] = e;
        siftUp(size++);
        return true;
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (data.length == 0)
            throw new NullPointerException("Queue is empty");
        E res = (E) data[0];
        data[0] = data[--size];
        siftDown(0);
        return res;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o))
                return true;
        }
        return false;
    }

    public boolean offer(E e) {
        Object[] temp;
        if (size == data.length) {
            temp = new Object[data.length == 0 ? DEFAULT_CAPACITY : (data.length << 1)];
            System.arraycopy(data, 0, temp,0, data.length);
            data = temp;
        }
        data[size] = e;
        siftUp(size++);
        return true;
    }
    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0)
            return null;
        E res = (E) data[0];
        data[0] = data[--size];
        siftDown(0);
        return res;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (size != 0)
            return (E) data[0];
        return null;
    }

    @SuppressWarnings("unchecked")
    public E element() {
        if (size == 0)
            throw new NullPointerException("Queue is empty");
        return (E) data[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item))
                return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        Object[] temp;
        int nSize = c.size();
        if (nSize == 0)
            return false;
        if (size + nSize >= data.length) {
            temp = new Object[(nSize + data.length) << 1];
            System.arraycopy(data, 0, temp,0, data.length);
            data = temp;
        }
        for (Object item : c) {
            add((E) item);
        }
//        System.arraycopy(c.toArray(), 0, data, size, nSize);
//        size += nSize;
//        heapify();
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        Object[] temp = new Object[data.length];
        int nSize = 0;
        for (int i = 0; i < size; i++)
            if (!c.contains(data[i]))
                temp[nSize++] = data[i];
        if (size == nSize)
            return false;
        data = temp;
        size = nSize;
        heapify();
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        Object[] temp = new Object[data.length];
        int nSize = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(data[i]))
                temp[nSize++] = data[i];
        if (size == nSize)
            return false;
        data = temp;
        size = nSize;
        heapify();
        return true;
    }

    ////////////////////////////////////

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean remove(Object o) {
        return true;
    }

}
