package by.it.group251003.ilysiakoff.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] queueArray = (E[]) new Comparable[0];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        if (size > 0) {
            res.append(queueArray[0]);
            for (int i = 1; i < size; i++)
                res.append(", ").append(queueArray[i]);
        }
        res.append("]");
        return res.toString();
    }

    private void resize(int newSize) {
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(queueArray, 0, newArr, 0, size);
        queueArray = newArr;
    }

    private void swap(int i, int j) {
        E temp = queueArray[i];
        queueArray[i] = queueArray[j];
        queueArray[j] = temp;
    }
    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int min = index;
            if (left < size && queueArray[left].compareTo(queueArray[min]) < 0)
                min = left;
            if (right < size && queueArray[right].compareTo(queueArray[min]) < 0)
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
            if (queueArray[parentIndex].compareTo(queueArray[index]) < 0)
                break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    private void remove(int index) {
        queueArray[index] = queueArray[--size];
        queueArray[size] = null;
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

    private int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(queueArray[i]))
                return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
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
        for (int i = 0; i < size; i++) {
            if (o == queueArray[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c){
            if (!contains(element)){
                return false;}
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;

        for (E element : c) {
            offer(element);
        }
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (int i = size - 1; i >= 0; i--){
            if (c.contains(queueArray[i])) {
                remove(i);
                isRemoved = true;
            }}

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(queueArray[i])) {
                remove(i);
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            queueArray[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == queueArray.length) {
            resize(size);
        }
        queueArray[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        if (size==0) {
            return null;
        }
        E head = queueArray[0];
        queueArray[0] = queueArray[--size];
        queueArray[size] = null;
        siftDown(0);
        return head;
    }

    @Override
    public E element() {
        return queueArray[0];
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;

        return queueArray[0];

    }
}
