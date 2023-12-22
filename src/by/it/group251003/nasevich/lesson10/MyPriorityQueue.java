package by.it.group251003.nasevich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private int minCapacity = 8;
    private E[] arr = (E[]) new Object[minCapacity];
    int size = 0;

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private void resize() {
        E[] newArr = (E[]) new Object[size * 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private void resize(int newSize){
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private void heapify() {
        for (int i = (size / 2) - 1; i >= 0; i--)
            siftDown(i);
    }

    private void siftDown(int i) {
        int left = left(i);
        int right = right(i);

        if (left >= size)
            return;

        int largest = left;
        if (right < size && ((Comparable<E>) arr[right]).compareTo(arr[left]) < 0)
            largest = right;

        if (((Comparable<E>) arr[largest]).compareTo(arr[i]) >= 0)
            return;

        E tmp = arr[largest];
        arr[largest] = arr[i];
        arr[i] = tmp;

        siftDown(largest);
    }

    private void siftUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = parent(i);
        if (((Comparable<E>) (arr[parent])).compareTo(arr[i]) < 0) {
            return;
        }

        E tmp = arr[parent];
        arr[parent] = arr[i];
        arr[i] = tmp;

        siftUp(parent);
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        String del = "";

        for (int i = 0; i < size; i++) {
            res.append(del).append(arr[i]);
            del = ", ";
        }

        return res.append("]").toString();
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {

        if (size == arr.length)
            resize();

        arr[size] = e;

        siftUp(size);

        size++;
        return true;
    }

    @Override
    public E remove() {

        if (size == 0) {
            return null;
        }

        E item = arr[0];
        arr[0] = arr[size - 1];
        arr[size - 1] = null;

        size--;

        siftDown(0);

        return item;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i]))
                return true;
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {

        if (isEmpty())
            return null;

        E result = arr[0];
        arr[0] = arr[--size];
        arr[size] = null;
        siftDown(0);
        return result;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;
        return arr[0];
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] a = (E[]) new Object[c.size()];

        a = c.toArray(a);

        for (E e : a)
            if (!contains(e))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        if (c.isEmpty())
            return false;

        int newSize = size + c.size();

        if (newSize > arr.length)
            resize(newSize);

        for (E e : c) {
            arr[size++] = e;
            siftUp(size - 1);
        }

        heapify();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        final int prevSize = size;

        for (int i = 0, j = 0; j < size; i++) {
            if (c.contains(arr[i])) {
                size--;
                arr[i] = null;
            }
            else {
                arr[j] = arr[i];
                j++;
            }
        }

        heapify();
        return prevSize != size;
    }

    public boolean retainAll(Collection<?> c) {

        final int prevSize = size;
        for (int i = 0, j = 0; j < size; i++) {
            if (!c.contains(arr[i])) {

                size--;
                arr[i] = null;
            }
            else {
                arr[j] = arr[i];
                j++;
            }
        }

        heapify();
        return prevSize != size;
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
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                arr[i] = arr[--size];
                siftDown(i);
                return true;
            }
        }

        return false;
    }
}
