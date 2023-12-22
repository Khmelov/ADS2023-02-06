package by.it.group251001.churavskiy.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private E[] elements = (E[]) new Object[3];
    int size;
    int capacity=15;

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parentInd = parent(index);
        if (((Comparable<E>) elements[parentInd]).compareTo(elements[index]) < 0) {
            return;
        }

        E tmp = elements[parentInd];
        elements[parentInd] = elements[index];
        elements[index] = tmp;

        siftUp(parentInd);
    }

    private void siftDown(int index) {
        int l = leftChild(index), r = rightChild(index);
        if (l >= size) {
            return;
        }

        int goToChild = l;
        if (r < size && ((Comparable<E>) elements[r]).compareTo(elements[l]) < 0) {
            goToChild = r;
        }
        if (((Comparable<E>) elements[index]).compareTo(elements[goToChild]) < 0) {
            return;
        }

        E tmp = elements[goToChild];
        elements[goToChild] = elements[index];
        elements[index] = tmp;

        siftDown(goToChild);
    }

    private void grow() {
        E[] newElements = (E[]) new Comparable[size * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size - 1; i++) {
            s.append(elements[i]).append(", ");
        }
        if (size > 0)
            s.append(elements[size - 1]);
        s.append("]");
        return s.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean add(E element) {

        if (size == elements.length) {
            grow();
        }

        elements[size] = element;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {
        E element = elements[0];
        size--;
        elements[0] = elements[size];
        elements[size] = null;
        siftDown(0);
        return element;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        if (size == elements.length) {
            grow();
        }

        if (isEmpty()) {
            elements[size] = e;
            size++;
            return true;
        }

        elements[size] = e;
        siftUp(size);
        size++;

        return true;
    }

    @Override
    public E poll() {
        E element = elements[0];
        size--;
        elements[0] = elements[size];
        elements[size] = null;
        siftDown(0);
        return element;
    }


    @Override
    public E element() {
        return elements[0];
    }

    @Override
    public E peek() {
        return elements[0];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        E[] newElements = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if (!contains(newElements[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] newElements = (E[]) c.toArray();
        int prevSize = size;
        for (int i = 0; i < c.size(); i++) {
            add(newElements[i]);
        }
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int newSize = 0;
        capacity = size;
        E[] tmpData = (E[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                tmpData[newSize++] = elements[i];
            }
        }
        elements = tmpData;
        boolean res = newSize != size;
        size = newSize;
        for (int i = size / 2; i >= 0; i--)
            siftDown(i);
        return res;
    }

@Override
public boolean retainAll(Collection<?> c) {
    int newSize = 0;
    capacity = size;
    E[] tmpData = (E[]) (new Object[size]);
    for (int i = 0; i < size(); i++) {
        if (c.contains(elements[i])) {
            tmpData[newSize++] = elements[i];
        }
    }
    elements = tmpData;
    boolean res = newSize != size;
    size = newSize;
    for (int i = size / 2; i >= 0; i--)
        siftDown(i);
    return res;
}


    @Override
    public boolean remove(Object o) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    elements[i] = elements[--size];
                    siftDown(i);
                    return true;
                }
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
}
