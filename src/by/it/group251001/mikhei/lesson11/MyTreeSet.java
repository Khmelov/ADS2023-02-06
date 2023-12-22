package by.it.group251001.mikhei.lesson11;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {

    private E[] data = (E[]) Array.newInstance(Comparable.class, 0);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int index(Comparable o) {
        int l = 0, r = size;

        while (l < r) {
            int m = (l + r) / 2;

            if (o.compareTo(data[m]) > 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }

    @Override
    public boolean contains(Object o) {
        int i = index((Comparable) o);

        return i < size && data[i].equals(o);
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

    void growIfNeeded(int newSize) {
        if (newSize <= data.length) return;

        int newLength = max(data.length * 2, newSize);

        data = Arrays.copyOf(data, newLength);
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) return false;

        growIfNeeded(size + 1);

        int i = index(e);
        size++;
        for (int j = size - 1; j > i; j--) {
            data[j] = data[j - 1];
        }

        data[i] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;

        int i = index((Comparable) o);

        for (int j = i; j < size; j++) {
            data[j] = data[j + 1];
        }

        size--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var x : c) {
            if (!contains(x)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for (var x : c) {
            res |= add(x);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(data[i])) res |= remove(data[i]);
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (var x : c) {
            res |= remove(x);
        }
        return res;
    }

    @Override
    public void clear() {
        data = Arrays.copyOf(data, 0);
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append("]");

        return sb.toString();
    }
}
