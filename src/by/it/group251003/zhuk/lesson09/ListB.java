package by.it.group251003.zhuk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {

    private E[] arr = (E[]) new Object[0];
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {

        StringBuilder out_string = new StringBuilder("[");
        if (size > 0) {
            String elem = "";
            for (int i = 0; i < size; i++) {
                out_string.append(elem).append(arr[i]);
                elem = ", ";
            }
        }
        out_string.append("]");

        return out_string.toString();
    }

    @Override
    public boolean add(E e) {

        if (size == arr.length) {
            E[] updated = (E[]) new Object[(arr.length * 3) / 2 + 1];
            System.arraycopy(arr, 0, updated, 0, size);
            arr = updated;
        }
        arr[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E deleted_el = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        size--;
        arr[size] = null;
        return deleted_el;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {

        if (size == arr.length) {
            E[] newArr = (E[]) new Object[(arr.length * 3) / 2 + 1];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        for (int i = size; i > index; i--)
            arr[i] = arr[i - 1];

        arr[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index_of_deleted = indexOf(o);
        if (index_of_deleted > -1) {
            remove(index_of_deleted);
        }
        return (index_of_deleted > -1);
    }

    @Override
    public E set(int index, E element) {
        E result = arr[index];
        arr[index] = element;
        return result;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        while (size != 0) {
            remove(0);
        }
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(arr[i]))
                return i;

        return -1;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (o.equals(arr[i]))
                return i;

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
