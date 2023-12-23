package by.it.group251001.zhidkov.lesson09;

import java.util.*;
import java.lang.StringBuilder;
public class ListC<E> implements List<E> {

    private Object[] data = new Object[0];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i == size - 1) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            return null;
        }
        E rem = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        return rem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size) {
            return null;
        }
        E tmp = (E) data[index];
        data[index] = element;
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        data = new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i]))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            return null;
        return (E) data[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(data[i]))
                return true;
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
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (o.equals(data[i]))
                return i;
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] tmp = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if (!this.contains(tmp[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        ensureCapacity(size + c.size());
        System.arraycopy(c.toArray(), 0, data, size, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        ensureCapacity(size + c.size());
        System.arraycopy(data, index, data, index + c.size(), size - index);
        System.arraycopy(c.toArray(), 0, data, index, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (c.contains(data[i])) {
                this.remove(i);
                edited = true;
            } else
                i++;
        }
        return edited;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(data[i])) {
                this.remove(i);
                edited = true;
            } else
                i++;
        }
        return edited;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length << 1, 10);
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    // Остальные методы можно оставить неизменными
}

