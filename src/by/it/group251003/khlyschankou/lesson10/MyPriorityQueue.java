package by.it.group251003.khlyschankou.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] arr = (E[]) new Comparable[0];

    private int size = 0;

    private void resize() {
        resize(arr.length);
    }

    private void resize(int newSize) {
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private boolean isInvalidType(Object o) {
        return o == null;
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private void swap(int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int min = index;

            if (left < size && arr[left].compareTo(arr[min]) < 0)
                min = left;

            if (right < size && arr[right].compareTo(arr[min]) < 0)
                min = right;

            if (min == index)
                break;

            swap(index, min);
            index = min;
        }
    }
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (arr[parentIndex].compareTo(arr[index]) < 0)
                break;

            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    private void remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        arr[index] = arr[--size];
        arr[size] = null;
        siftDown(index);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            result.append(arr[0]);

            for (int i = 1; i < size; i++)
                result.append(", ").append(arr[i]);
        }

        result.append("]");
        return result.toString();
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
        if (isInvalidType(o))
            throw new IllegalArgumentException("Element cannot be null");

        for (int i = 0; i < size; i++)
            if (o.equals(arr[i]))
                return true;

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
        return offer(e);
    }

    @Override
    public boolean remove(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Element cannot be null");

        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        for (E element : c)
            offer(element);

        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (int i = size - 1; i >= 0; i--)
            if (c.contains(arr[i])) {
                remove(i);
                result = true;
            }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (int i = size - 1; i >= 0; i--)
            if (!c.contains(arr[i])) {
                remove(i);
                result = true;
            }

        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == arr.length)
            resize();

        arr[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The queue is empty");
        return poll();
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
    public E element() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The queue is empty");
        return arr[0];
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return arr[0];
    }
}