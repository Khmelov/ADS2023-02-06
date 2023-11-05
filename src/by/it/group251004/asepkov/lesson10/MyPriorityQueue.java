package by.it.group251004.asepkov.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] array;
    private int size;

    public MyPriorityQueue(){
        array = (E[]) new Comparable[0];
        size = 0;
    }

    private void resize() {
        resize(array.length);
    }


    private void resize(int newSize) {
        E[] newArray = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }


    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void siftDown(int index) {
        while (true) {
            int childLeftIndex = 2 * index + 1;
            int childRightIndex = 2 * index + 2;
            int min = index;

            if (childLeftIndex < size && array[childLeftIndex].compareTo(array[min]) < 0)
                min = childLeftIndex;

            if (childRightIndex < size && array[childRightIndex].compareTo(array[min]) < 0)
                min = childRightIndex;

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
    private void remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Exception: out of bounds");

        array[index] = array[--size];
        array[size] = null;
        siftDown(index);
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("[");
        if (size > 0) {
            SB.append(array[0]);

            for (int i = 1; i < size; i++)
                SB.append(", ").append(array[i]);
        }

        SB.append("]");
        return SB.toString();
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
        if (o == null)
            throw new IllegalArgumentException("Exception: null argument");

        for (int i = 0; i < size; i++)
            if (o.equals(array[i]))
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
        if (o == null)
            throw new IllegalArgumentException("Exception: null argument");

        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
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
        int i = 0;
        while (i < size) {
            if (c.contains(array[i])) {
                System.arraycopy(array, i+1, array, i, size-i-1);
                size--;
                result = true;
            } else {
                i++;
            }
        }
        for(i = size - 1; i >= 0; i--){
            siftDown(i);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(array[i])) {
                System.arraycopy(array, i+1, array, i, size-i-1);
                size--;
                result = true;
            } else {
                i++;
            }
        }
        for(i = size - 1; i >= 0; i--){
            siftDown(i);
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new IllegalArgumentException("Exception: null argument");

        if (size == array.length)
            resize();

        array[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Exception: empty queue");
        return poll();
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

    @Override
    public E element() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("The queue is empty");
        return array[0];
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return array[0];
    }
}