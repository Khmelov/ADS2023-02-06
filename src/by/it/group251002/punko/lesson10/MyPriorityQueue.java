package by.it.group251002.punko.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[]array = (E[]) new Comparable[0];
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
        array[index] = array[--size];
        array[size] = null;
        siftDown(index);
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
        int prevSize = size;
        for (E element : c)
            offer(element);

        return prevSize != size;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;
        while(i<size && !c.contains(array[i]))
            i++;
        if(i==size)
            return false;
        int end = size;
        int begin = i;
        int[] tosave = new int[end-begin];
        for(i = begin+1; i<end;i++)
            tosave[i-begin] = (c.contains(array[i]))?0:1;
        int w = begin;
        for(i = begin; i < end; i++)
            if(tosave[i-begin]==1)
                array[w++]=array[i];
        size = w;
        for(i = size; i < end; i++)
            array[i]=null;
        for(i = size/2-1;i>=0;i--)
            siftDown(i);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        for(;i<size && c.contains(array[i]);i++)
            ;
        if(i==size)
            return false;
        int end = size;
        int begin = i;
        int[] tosave = new int[end-begin];
        for(i = begin+1; i<end;i++)
            tosave[i-begin] = (!c.contains(array[i]))?0:1;
        int w = begin;
        for(i = begin; i < end; i++)
            if(tosave[i-begin]==1)
                array[w++]=array[i];
        size = w;
        for(i = size; i < end; i++)
            array[i]=null;
        for(i = size/2-1;i>=0;i--)
            siftDown(i);
        return true;
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
