package by.it.group251001.mikhei.lesson10;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] heap = (E[]) new Comparable[8];
    private int size = 0;

    @Override
    public String toString() {
        if(isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i + 1 < size; i++){
            sb.append(heap[i]);
            sb.append(", ");
        }
        sb.append(heap[size - 1]);
        sb.append(']');
        return sb.toString();
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
        for(int i = 0; i < size; i++){
            if(Objects.equals(heap[i], o)) return true;
        }

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

    private void growIfNeeded(int newSize) {
        if (newSize < heap.length) return;

        E[] newHeap = (E[]) new Comparable[newSize * 3 / 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        heap = newHeap;
    }


    private void swap(int i, int j){
       E tmp = heap[i];
       heap[i] = heap[j];
       heap[j] = tmp;
    }

    private void siftUp(int i){
        while (i > 0 && heap[(i - 1) / 2].compareTo(heap[i]) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }
    @Override
    public boolean add(E e) {
        growIfNeeded(size + 1);

        heap[size] = e;
        siftUp(size++);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(var e: c){
            if(!contains(e)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(var e: c){
            add(e);
        }

        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            heap[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    private void siftDown(int i) {
        int minIndex = i;
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;

        if (leftChildIndex < size && heap[leftChildIndex].compareTo(heap[minIndex]) < 0) {
            minIndex = leftChildIndex;
        }

        if (rightChildIndex < size && heap[rightChildIndex].compareTo(heap[minIndex]) < 0) {
            minIndex = rightChildIndex;
        }

        if (minIndex != i) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }


    @Override
    public E remove() {
        E res = heap[0];

        heap[0] = heap[--size];
        heap[size] = null;

        siftDown(0);

        return res;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek() {
        return heap[0];
    }
}
