package by.it.group251003.kukhotskovolets.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E[] elems = (E[]) new Comparable[0];
    private int size = 0;
    private void resize(){
        E[] newElems = (E[]) new Comparable[(elems.length * 3) / 2 + 1];
        System.arraycopy(elems, 0, newElems, 0, size);
        elems = newElems;
    }

    private void heapify() {
        for (int i = (size / 2) - 1; i >= 0; i--)
            siftDown(i);
    }

    private void siftUp(int index){
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (elems[parentIndex].compareTo(elems[index]) < 0)
                break;

            swap(index, parentIndex);
            index = parentIndex;

        }
    }

    private void siftDown(int index){
        for (;;){
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int min = index;

            if (leftChild < size && elems[leftChild].compareTo(elems[min]) < 0)
                min = leftChild;

            if (rightChild < size && elems[rightChild].compareTo(elems[min]) < 0)
                min = rightChild;

            if (min == index)
                break;

            swap(index, min);
            index = min;
        }
    }

    private void swap(int i, int j){
        E temp = elems[i];
        elems[i] = elems[j];
        elems[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        if (size > 0){
            for (int i = 0; i < size; i++) {
                sb.append(delimiter).append(elems[i]);
                delimiter = ", ";
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void clear() {
        System.arraycopy(elems, 0, elems, 0, 0);
        size = 0;
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
    public boolean add(E e) {
        if (size == elems.length){
            resize();
        }
        elems[size] = e;
        siftUp(size);
        size++;

        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()){
            throw new IllegalArgumentException("The queue is empty!");
        }
        return poll();
    }

    @Override
    public boolean offer(E e) {
        if (size == elems.length){
            throw new IllegalArgumentException("Violated capacity restrictions!");
        }
        add(e);
        return true;
    }

    @Override
    public E poll() {
        if (!isEmpty()){
            E deletedElement = elems[0];
            elems[0] = elems[--size];
            elems[size] = null;
            siftDown(0);
            return deletedElement;
        }
        else return null;
    }

    @Override
    public E element() {
        return elems[0];
    }

    @Override
    public E peek() {
        if (!isEmpty()){
            return elems[0];
        } else {
            return null;
        }
    }


    @Override
    public boolean contains(Object o) {
        for (E elem : elems) {
            if (o.equals(elem)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isModified = false;
        for (E e : c) {
            add(e);
            isModified = true;
        }

        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (int i = 0, j = 0; j < size; i++) {
            if (c.contains(elems[i])) {
                elems[i] = null;
                size--;
                isModified = true;
            }
            else {
                elems[j] = elems[i];
                j++;
            }
        }
        heapify();
        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        for (int i = 0, j = 0; j < size; i++) {
            if (!c.contains(elems[i])){
                elems[i] = null;
                size--;
                isModified = true;
            }
            else {
                elems[j] = elems[i];
                j++;
            }
        }
        heapify();
        return isModified;
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
        return false;
    }
}
