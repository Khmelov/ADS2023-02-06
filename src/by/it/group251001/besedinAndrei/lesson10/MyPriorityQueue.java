package by.it.group251001.besedinAndrei.lesson10;

import java.lang.annotation.ElementType;
import java.util.*;

@SuppressWarnings("unchecked")
public class MyPriorityQueue<E> implements Queue<E> {


    transient E[] elementData;

    private int size;
    private int capacity = 10;


    private void extend() {
        int newCapacity = (size + 1) * 4;
        capacity = newCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public MyPriorityQueue() {
        elementData = (E[]) (new Object[capacity]);
        size = 0;
    }

    private int parent(int i) {
        if (i == 0) {
            return 0;
        }
        return (i - 1) / 2;
    }

    private int left(int i) {
        return (2 * i + 1);
    }

    private int right(int i) {
        return (2 * i + 2);
    }

    void swap(int x, int y) {
        E tempElement = elementData[x];
        elementData[x] = elementData[y];
        elementData[y] = tempElement;
    }

    private void downHeap(int i) {
        int left = left(i);
        int right = right(i);

        int largest = i;

        if (left < size && ((Comparable<? super E>) elementData[left]).compareTo((E) elementData[i]) < 0) {
            largest = left;
        }

        if (right < size && ((Comparable<? super E>) elementData[right]).compareTo((E) elementData[largest]) < 0) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);

            downHeap(largest);
        }
    }

    private void upHeap(int i) {
        if (i > 0 && ((Comparable<? super E>) elementData[parent(i)]).compareTo((E) elementData[i]) > 0) {
            swap(i, parent(i));

            upHeap(parent(i));
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            res.append(elementData[i].toString()).append(", ");
        }

        if (size != 0) {
            res.delete(res.length() - 2, res.length());
        }

        return res.append(']').toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == capacity) {
            extend();
        }
        elementData[size++] = e;
        upHeap(size - 1);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) return true;
        }
    
        return false;
    }

    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        E tmp = elementData[0];

        elementData[0] = elementData[--size];
        downHeap(0);

        return tmp;
    }


    @Override
    public E peek() {
        return elementData[0];
    }


    @Override
    public E element() {
        return elementData[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        int i = 0;
        for (; !elementData[i].equals(o); i++) ;
        elementData[i] = elementData[--size];
        downHeap(i);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean res = true;
        for (Object obj :
                c.toArray())
            res = res && this.contains(obj);
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int newSize = size;
        for (E obj :
                c)
            this.add(obj);
        return newSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int newSize = 0;
        capacity = size;
        E[] tmpData = (E[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            if (!c.contains(elementData[i])) {
                tmpData[newSize++] = elementData[i];
            }
        }
        elementData = tmpData;
        boolean res = newSize != size;
        size = newSize;
        for (int i = size / 2; i >= 0; i--) downHeap(i);
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int newSize = 0;
        capacity = size;
        E[] tmpData = (E[]) (new Object[size]);
        for (int i = 0; i < size(); i++) {
            if (c.contains(elementData[i])) {
                tmpData[newSize++] = elementData[i];
            }
        }
        elementData = tmpData;
        boolean res = newSize != size;
        size = newSize;
        for (int i = size / 2; i >= 0; i--) downHeap(i);
        return res;
    }
}
