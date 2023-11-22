package by.it.group251002.urbanovich.lesson10;

import java.util.*;

public class MyPriorityQueue<E> implements Queue<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            builder.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public int size() {
        return size;
    }

    private void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    private void siftUp(int index) {
        while (((Comparable<? super E>) elements[index]).compareTo(elements[(index - 1) / 2]) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void siftDown(int index) {
        boolean isinplace = false;
        while (2 * index + 1 < size && !isinplace) {
            int left = 2 * index + 1;
            int right = left + 1;
            int child = left;
            if (right < size && ((Comparable<? super E>) elements[right]).compareTo(elements[left]) < 0)
                child = right;
            if (((Comparable<? super E>) elements[index]).compareTo(elements[child]) < 0)
                isinplace = true;
            if (!isinplace)
                swap(index, child);
            index = child;
        }
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                return true;
        return false;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;
        E element = elements[0];
        elements[0] = elements[size - 1];
        elements[--size] = null;
        siftDown(0);
        return element;
    }

    @Override
    public E peek() {
        return elements[0];
    }

    @Override
    public E element() {
        return elements[0];
    }

    @Override
    public boolean offer(E e) {
        if (size == elements.length) {
            E[] temp = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elements, 0, temp, 0, size);
            elements = temp;
        }
        elements[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++) {
            if (!contains(temp[i])) {
                return false;
            }
        }
        return true;
    }


    public E remove(int index) {
        E deletedElem = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deletedElem;
    }

    public void reSift() {
        for (int i = size / 2 - 1; i >= 0; i--)
            siftDown(i);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean hasChanged = false;
        for (int i = 0; i < size; i++)
            if (c.contains(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                hasChanged = true;
                size--;
                i--;
            }
        reSift();
        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasChanged = false;
        for (int i = 0; i < size; i++)
            if (!c.contains(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                hasChanged = true;
                size--;
                i--;
            }
        reSift();
        return hasChanged;

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

    public boolean addAll(Collection<? extends E> c) {
        E []cArray =(E[]) c.toArray();
        if(cArray.length == 0)
            return false;
        for(int i = 0; i < cArray.length; i++){
            offer(cArray[i]);
        }
        return true;
    }

}
