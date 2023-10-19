package by.it.group251004.savenok.lesson10;


import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[] array = (E[]) new Comparable[8];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (size - 1 != i) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    void swap(int index1, int index2) {
        E buf = array[index1];
        array[index1] = array[index2];
        array[index2] = buf;
    }

    private void siftUp(int i) {
        while (i != 0) {
            int parentI = (i - 1) / 2;
            if (array[parentI].compareTo(array[i]) > 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    private void siftDown(int i) {
        while (i != size / 2) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int min = i;

            if (left < size && array[left].compareTo(array[min]) < 0) {
                min = left;
            }
            if (right < size && array[right].compareTo(array[min]) < 0) {
                min = right;
            }
            if (min != i) {
                swap(i, min);
                i = min;
            } else {
                break;
            }
        }
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], o)) {
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

    @Override
    public boolean add(E e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
        array[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, array[i])) {
                array[i] = array[--size];
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element: c) {
            add(element);
        }
        return true;
    }

    private void remove(int i) {
        array[i] = array[--size];
        siftDown(i);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;
        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(array[i])) {
                remove(i);
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRetain = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(array[i])) {
                remove(array[i]);
                isRetain = true;
            }
        }

        return isRetain;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }
    @Override
    public E remove() {
        E element = array[0];
        array[0] = array[--size];
        siftDown(0);
        return element;
    }

    @Override
    public E poll() {
        E element = array[0];
        remove();
        return element;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        } else {
            return array[0];
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return array[0];
        }
    }
}