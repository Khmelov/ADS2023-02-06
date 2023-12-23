package by.it.group251003.dedov.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[] heap = (E[]) new Comparable[0];
    private int size = 0;
    private void growSize(){
        if (size == heap.length){
            E[] arrayClone = (E[]) new Comparable[size * 3/2 + 1];
            System.arraycopy(heap, 0, arrayClone, 0, size);
            heap = arrayClone;
        }
    }
    private void swap(int firstIndex, int secondIndex) {
        E buf = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = buf;
    }
    private void heapify() {
        int start = (size - 1) / 2;
        while (start >= 0){
            siftDown(start);
            start--;
        }
    }
    private void siftDown(int index) {
       int leftChild = index * 2 + 1;
       int rightChild = index * 2 + 2;
       if (leftChild < size) {
           if (rightChild >= size) {
               if (heap[index].compareTo(heap[leftChild]) > 0) {
                   swap(index, leftChild);
               }
           } else if (heap[index].compareTo(heap[leftChild]) > 0 || heap[index].compareTo(heap[rightChild]) > 0 ) {
               if (heap[leftChild].compareTo(heap[rightChild]) <= 0) {
                   swap(index, leftChild);
                   siftDown(leftChild);
               } else {
                   swap(index, rightChild);
                   siftDown(rightChild);
               }

           }
       }
    }
    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        if (parent >= 0) {
            if (heap[index].compareTo(heap[parent]) < 0) {
                swap(index, parent);
                siftUp(parent);
            }
        }
    }
    private int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(o))
                return i;
        }
        return -1;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        if (size > 0) {
            string.append(heap[0]);

            for (int i = 1; i < size; i++) {
                string.append(", ").append(heap[i]);
            }
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public void clear() {
        heap = (E[]) new Comparable[0];
        size = 0;
    }

    @Override
    public boolean add(E e) {
        growSize();
        heap[size] = e;
        siftUp(size);
        size++;
        return true;
    }
    private E removeAt(int index) {
        swap(index, --size);
        siftDown(index);
        return heap[size];
    }

    @Override
    public E remove() { return removeAt(0); }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
            if (index == -1)
                return false;
            removeAt(index);
            return true;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        return remove();
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;
        return heap[0];
    }

    @Override
    public E element() {
        if (size == 0)
            throw new NoSuchElementException();

        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var el : c)
            if (!contains(el))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E el : c)
            add(el);
        if (c.isEmpty())
           return false;
        else
            return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0, j = 0; j < size; i++) {
            if (c.contains(heap[i])) {
                size--;
                heap[i] = null;
                result = true;
            }
            else {
                heap[j] = heap[i];
                j++;
            }
        }
        heapify();
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0, j = 0; j < size; i++) {
            if (!c.contains(heap[i])) {
                size--;
                heap[i] = null;
                result = true;
            }
            else {
                heap[j] = heap[i];
                j++;
            }
        }
        heapify();
        return result;
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

