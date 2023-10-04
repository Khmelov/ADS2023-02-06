package by.it.group251001.sevamisiul.lesson09;

import java.lang.reflect.Array;
import java.util.*;

public class ListA<E> implements List<E> {

    private static int DEFAULT_CAPACITY = 10;

    transient E[] elementData;

    private int size, capacity = DEFAULT_CAPACITY;

    private int newCapacity(int oldCapacity) {
        capacity = oldCapacity + (oldCapacity >> 1);
        return capacity;
    }

    private void grow(int oldCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity(oldCapacity));
    }

    public ListA() {
        elementData = (E[])(new Object[capacity]);
        size = 0;
    }

    @Override
    public String toString() {
        String res = "[";

        for (int i = 0; i < size - 1; i++) {
            res += elementData[i].toString() + ", ";
        }

        return res + elementData[size - 1].toString() + ']';
    }

    @Override
    public boolean add(E e) {
        if (size == capacity) {
            grow(size + 1);
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E tmp = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, (size--) - index - 1);
        return tmp;
    }

    @Override
    public int size() {
        return size;
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
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i]))
                return true;
        }
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
