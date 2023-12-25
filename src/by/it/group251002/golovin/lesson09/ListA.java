package by.it.group251002.golovin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] arr = (E[]) new Object[0];
    private int size = 0;
    private void resize() {
        resize(arr.length);
    }
    private void resize(int newSize) {
        E[] newArr = (E[]) new Object[newSize * (3 / 2) + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }
    private boolean isInvalidType(Object o) {
        return o == null;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            result.append(arr[0]);

            for (int i = 1; i < size; i++)
                result.append(", ").append(arr[i]);
        }

        result.append("]");
        return result.toString();
    }

    @Override
    public boolean add(E e) {
        if (isInvalidType(e))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == arr.length)
            resize();

        arr[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        E result = arr[index];

        for (int i = index; i < size - 1; i++)
            arr[i] = arr[i + 1];

        arr[--size] = null;

        return result;
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
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
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
