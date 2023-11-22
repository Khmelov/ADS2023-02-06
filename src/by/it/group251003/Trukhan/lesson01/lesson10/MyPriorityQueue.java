package by.it.group251003.Trukhan.lesson01.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[]array = (E[]) new Comparable[10];
    private int size = 0;
    private void resize() {
        E[] tmpArray = (E[]) new Comparable[size * 2 + 1];
        System.arraycopy(array, 0, tmpArray, 0, size);
        array = tmpArray;
    }
    private boolean checkType(Object o) {
        return o == null;
    }
    private void swap(int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int min = index;

            if (left < size && array[left].compareTo(array[min]) < 0)
                min = left;

            if (right < size && array[right].compareTo(array[min]) < 0)
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

            if (array[parentIndex].compareTo(array[index]) < 0)
                break;

            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    public void heapify(){
        int start = size - 1;
        while(start >= 0){
            siftDown(start);
            start--;
        }
    }
    @Override
    public boolean offer(E e) {
        if (checkType(e))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == array.length)
            resize();

        array[size++] = e;
        siftUp(size - 1);
        return true;
    }
    @Override
    public E poll() {
        if (isEmpty())
            return null;

        E result = array[0];
        array[0] = array[--size];
        array[size] = null;
        siftDown(0);
        return result;
    }
    private void remove(int index) {
        --size;
        array[index] = array[size];
        E moved = array[size];
        array[size] = null;
        siftDown(index);
        if (array[index] == moved)
            siftUp(index);
    }
    public String toString() {
        String res = "[";
        for (int i = 0; i < size; i++) {
            res += array[i];
            if (i + 1 != size) res += ", ";
        }

        return res + "]";
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;
        size = 0;
    }
    @Override
    public E remove() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The queue is empty");
        return poll();
    }
    @Override
    public boolean contains(Object o) {
        if (checkType(o))
            throw new IllegalArgumentException("Element cannot be null");

        for (int i = 0; i < size; i++)
            if (o.equals(array[i]))
                return true;
        return false;
    }
    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return array[0];
    }
    @Override
    public boolean add(E e) {
        return offer(e);
    }
    @Override
    public E element() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The queue is empty");
        return array[0];
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        if (c.isEmpty()) return false;
        for (E element : c)
            offer(element);
        return true;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (c.contains(array[i])) {
                System.arraycopy(array, i+1, array, i, size-i-1);
                size--;
                changed = true;
            } else {
                i++;
            }
        }
        heapify();

        return changed;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(array[i])) {
                System.arraycopy(array, i+1, array, i, size-i-1);
                size--;
                changed = true;
            } else {
                i++;
            }
        }
        heapify();

        return changed;
    }

    @Override
    public boolean remove(Object o) {
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
}
