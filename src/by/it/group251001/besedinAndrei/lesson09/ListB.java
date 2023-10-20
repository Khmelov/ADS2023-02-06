package by.it.group251001.besedinAndrei.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {

    private E[] arr = (E[]) new Object[]{};
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size - 1; i++) {
            result += arr[i] + ", ";
        }
        if (size != 0) {
            result += arr[size - 1];
        }
        result += "]";
        return result;
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size * 3 / 2 + 1);
        }
        arr[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E removedElem = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        arr[--size] = null;
        return removedElem;
    }

    @Override
    public void add(int index, E element) {
        if (size == arr.length) {
            int newSize = arr.length * 3 / 2 + 1;
            E[] newElem = (E[]) new Object[newSize];
            System.arraycopy(arr, 0, newElem, 0, size);
            arr = newElem;
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        boolean valid = index != -1;
        if (valid) {
            remove(index);
        }
        return valid;
    }

    @Override
    public E set(int index, E element) {
        E prev = arr[index];
        arr[index] = element;
        return prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, arr[i]) || ((o == null) && (arr[i] == null))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, arr[i]) || ((o == null) && (arr[i] == null))) {
                return i;
            }
        }
        return -1;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


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
