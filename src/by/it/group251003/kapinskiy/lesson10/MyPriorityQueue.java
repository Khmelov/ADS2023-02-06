package by.it.group251003.kapinskiy.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable> implements Queue<E> {

    private E[] arr = (E[]) new Comparable[11];
    private int size = 0;


    private void resize() {
        E[] newarr = (E[]) new Comparable[size * 2];
        System.arraycopy(arr, 0, newarr, 0, size);
        arr = newarr;
    }

    private void swap(int a, int b) {
        E tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private void siftUp(int ind) {
        while (ind > 0) {
            int parent = (ind - 1) / 2;
            if (arr[parent].compareTo(arr[ind]) < 0)
                break;
            swap(parent, ind);
            ind = parent;
        }
    }

    private void siftDown(int ind) {
        while (true) {
            int left = ind * 2 + 1;
            int right = ind * 2 + 2;
            int min = ind;

            if (left < size && arr[left].compareTo(arr[min]) < 0)
                min = left;
            if (right < size && arr[right].compareTo(arr[min]) < 0)
                min = right;

            if (min == ind)
                break;

            swap(min, ind);
            ind = min;

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++)
            sb.append(arr[i] + ", ");
        if (size != 0)
            sb.append(arr[size - 1]);
        return sb.append(']').toString();
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
        for (int i = 0; i < size; i++)
            if (o.equals(arr[i])) return true;
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
        offer(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o)) {
                swap(--size, i);
                arr[size] = null;
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) return true;
        for (Object o : c)
            if (!contains(o))
                return false;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prev = size;
        for (E o : c)
            offer(o);
        return prev != size;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return remove(c, false);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return remove(c, true);
    }

    private boolean remove(Collection<?> c, boolean retain) {
        boolean modified = false;
        int newSize = 0;

        for (int i = 0; i < size; i++) {
            if (c.contains(arr[i]) == retain) {
                arr[newSize++] = arr[i];
            } else {
                modified = true;
            }
        }

        for (int i = newSize; i < size; i++) {
            arr[i] = null;
        }

        size = newSize;

        if (modified) {
            for (int i = size / 2 - 1; i >= 0; i--) {
                siftDown(i);
            }
        }

        return modified;
    }


    @Override
    public void clear() {
        E[] newarr = (E[]) new Comparable[11];
        arr = newarr;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == arr.length)
            resize();
        arr[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException("Priority Queue is empty");
        E save = arr[0];
        swap(0, --size);
        siftDown(0);
        return save;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E save = arr[0];
        swap(0, --size);
        siftDown(0);
        return save;
    }

    @Override
    public E element() {
        if (isEmpty())
            throw new NoSuchElementException("PriorityQueue is empty");
        return arr[0];
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return arr[0];
    }
}
