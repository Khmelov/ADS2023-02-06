package by.it.group251001.markostapchuk.lesson09;

import java.util.*;

@SuppressWarnings("unchecked")
public class ListA<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ListA() {
        data = new Object[DEFAULT_CAPACITY];
    }
    public ListA(int initialCapacity) {
        if (initialCapacity > 0) {
            data = new Object[initialCapacity];
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.append(data[size - 1]).append(']').toString();
    }

    @Override
    public boolean add(E e) {
        if (size == data.length)
            data = grow(data.length + 1);
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldVal = (E) data[index];
        fastRemove(data, index);
        return oldVal;
    }

    @Override
    public int size() {
        return size;
    }

    private Object[] grow(int minGrowth) {
        int oldCapacity = data.length;
        if (oldCapacity > 0) {
            int newCapacity = (oldCapacity * 3) >> 1;
            return data = Arrays.copyOf(data, Math.max(newCapacity, minGrowth));
        } else {
            return data = new Object[Math.max(DEFAULT_CAPACITY, minGrowth)];
        }
    }

    private void fastRemove(Object[] data, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(data, index + 1, data, index, newSize - index);
        data[size = newSize] = null;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
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
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
