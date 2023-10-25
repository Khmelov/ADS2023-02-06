package by.it.group251001.korotkaya.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[] arr = (E[]) new Comparable[0];
    private int size = 0;
    private void resize(int newSize) {
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
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
        arr[index] = arr[--size];
        arr[size] = null;
        siftDown(index);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        if (size > 0) {
            res.append(arr[0]);
            for (int i = 1; i < size; i++)
                res.append(", ").append(arr[i]);
        }
        res.append("]");
        return res.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == arr[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        if (size == arr.length) {
            resize(size);
        }
        arr[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public E poll() {
        if (size==0) {
            return null;
        }
        E res = arr[0];
        arr[0] = arr[--size];
        arr[size] = null;
        siftDown(0);
        return res;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return arr[0];
    }

    @Override
    public E element() {
        return arr[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c){
            if (!contains(element)){
                return false;}}
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
        boolean res = false;
        for (int i = size - 1; i >= 0; i--){
            if (c.contains(arr[i])) {
                remove(i);
                res = true;
            }}

        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (int i = size - 1; i >= 0; i--){
            if (!c.contains(arr[i])) {
                remove(i);
                res = true;
            }}
        return res;
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
    public E remove() {
        return poll();
    }
}
