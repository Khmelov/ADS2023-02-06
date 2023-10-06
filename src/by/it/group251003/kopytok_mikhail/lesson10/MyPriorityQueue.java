package by.it.group251003.kopytok_mikhail.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private int size = 0;
    private int capacity = 4;
    private E[] arr = (E[]) new Comparable[capacity];

    private void resize(){
        capacity = capacity * 3 / 2 + 1;
        E[] newArr = (E[]) new Comparable[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
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
    private void swap(int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void remove(int index) {
        arr[index] = arr[--size];
        arr[size] = null;
        siftDown(index);
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            return false;
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
        if (e == null)
            return false;
        if (size == arr.length)
            resize();

        arr[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            return false;

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
            add(element);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                remove(i);
            }
        return prevSize != size;
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
        return add(e);
    }

    @Override
    public E remove() {
        if (isEmpty())
            return null;

        E result = arr[0];
        arr[0] = arr[--size];
        arr[size] = null;
        siftDown(0);
        return result;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        if (isEmpty())
            return null;
        return arr[0];
    }

    @Override
    public E peek() {
        return element();
    }
}
