package by.it.group251002.voevoda.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private int size = 0;
    private E[] buffer = (E[]) new Object[]{};

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            builder.append(delimiter).append(buffer[i]);
            delimiter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void insertSort() {
        for (int i = 1; i < size; i++)
            for (int j = i; j > 0 && ((Comparable<? super E>) buffer[j]).compareTo(buffer[j - 1]) < 0; j--) {
                E temp = buffer[j - 1];
                buffer[j - 1] = buffer[j];
                buffer[j] = temp;
            }
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) return false;
        for (int i = 0; i < size; i++)
            if (buffer[i].equals(o))
                return true;
        return false;
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) return false;
        if (size == buffer.length)
            buffer = Arrays.copyOf(buffer, size * 2 + 1);
        buffer[size++] = element;
        insertSort();
        return true;
    }

    int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (buffer[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0 || !contains(o)) return false;
        int index = indexOf(o);
        System.arraycopy(buffer, index + 1, buffer, index, size - index - 1);
        size--;
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize=size;
        for (Object e : c)
            add((E)e);
        return prevSize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        for (int i = 0; i < size; i++)
            if (!c.contains(buffer[i]))
                remove(buffer[i--]);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        for (Object e : c)
            remove(e);
        return prevSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            remove(buffer[i--]);
        }
        size = 0;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}
