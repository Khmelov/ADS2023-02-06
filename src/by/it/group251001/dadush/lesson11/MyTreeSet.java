package by.it.group251001.dadush.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public class MyTreeSet<E> implements Set<E> {

    private E[] data = (E[]) new Object[10];

    int size;

    // --- Service methods

    private void resize(int n) {
        E[] nData = (E[]) new Object[n];
        System.arraycopy(data, 0, nData, 0, size);
        data = nData;
    }

    private int bSearch(E e, int l, int r) {

        return -1;
    }

    // --- Main methods

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i].toString());
            if (i == size - 1)
                break;
            sb.append(", ");
        }
        return sb.append("]").toString();
    }

    public int size() {
        return size;
    }

    public void clear() {
        data = (E[]) new Object[10];
        size = 0;
    }

    public boolean isEmpty() {return size == 0;}

    public boolean add(E e) {
        if (size == data.length)
            resize(data.length << 1);
        int l = 0, r = size - 1, mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            switch (((Comparable<E>) e).compareTo(data[mid])) {
                case -1:
                    r = mid - 1;
                    break;
                case 1:
                    l = mid + 1;
                    break;
                case 0:
                    return false;
            }
        }
        if (l == size)
            data[size++] = e;
        else {
            System.arraycopy(data, l, data, l + 1, size - l);
            data[l] = e;
            size++;
        }
        return true;
    }

    public boolean remove(Object o) {
        int l = 0, r = size - 1, mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            switch (((Comparable<E>) o).compareTo(data[mid])) {
                case -1:
                    r = mid - 1;
                    break;
                case 1:
                    l = mid + 1;
                    break;
                case 0:
                    System.arraycopy(data, mid + 1, data, mid, size - mid - 1);
                    size--;
                    return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        int l = 0, r = size -1, mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            switch (((Comparable<E>) o).compareTo(data[mid])) {
                case -1:
                    r = mid - 1;
                    break;
                case 1:
                    l = mid + 1;
                    break;
                case 0:
                    return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object item : c)
            if (! contains(item))
                return false;
        return true;
    }
    public boolean addAll(Collection<? extends E> c) {
        boolean edited = false;
        for (E item : c)
            edited |= add(item);
        return edited;
    }
    public boolean retainAll(Collection<?> c) {
        int nSize = 0;
        E[] nData = data;
        for (int i = 0; i < size; i++)
            if (c.contains(data[i]))
                nData[nSize++] = data[i];
        if (size == nSize)
            return false;
        else {
            size = nSize;
            return true;
        }
    }
    public boolean removeAll(Collection<?> c) {
        int nSize = 0;
        E[] nData = data;
        for (int i = 0; i < size; i++)
            if (! c.contains(data[i]))
                nData[nSize++] = data[i];
        if (size == nSize)
            return false;
        else {
            size = nSize;
            return true;
        }
    }

    ////////////////////////
    public Iterator<E> iterator() {
        return null;
    }
    public Object[] toArray() {
        return null;
    }
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
