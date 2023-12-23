package by.it.group251002.golovin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

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

    @Override
    public void add(int index, E element) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        if (isInvalidType(element))
            throw new IllegalArgumentException("Element cannot be null");

        if (size == arr.length)
            resize();

        for (int i = size; i > index; i--)
            arr[i] = arr[i - 1];

        arr[index] = element;
        size++;

    }

    @Override
    public boolean remove(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Invalid object type");

        for (int i = 0; i < size; i++)
            if (o.equals(arr[i])) {
                remove(i);
                return true;
            }

        return false;
    }

    @Override
    public E set(int index, E element) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        if (isInvalidType(element))
            throw new IllegalArgumentException("Element cannot be null");

        E result = arr[index];
        arr[index] = element;
        return result;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;

    }

    @Override
    public int indexOf(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Invalid object type");

        for (int i = 0; i < size; i++)
            if (o.equals(arr[i]))
                return i;

        return -1;
    }

    @Override
    public E get(int index) {
        if (isInvalidIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        return arr[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (isInvalidType(o))
            throw new IllegalArgumentException("Invalid object type");

        for (int i = size - 1; i >= 0; i--)
            if (o.equals(arr[i]))
                return i;

        return -1;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty())
            return false;

        int newSize = size + c.size();
        if (newSize > arr.length)
            resize(newSize);

        for (E element : c) {
            if (isInvalidType(element))
                throw new IllegalArgumentException("Element cannot be null");
            arr[size++] = element;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (isInvalidIndex(index))
        throw new IndexOutOfBoundsException("Index out of bounds");

        if (c.isEmpty())
            return false;

        int newSize = size + c.size();
        if (newSize > arr.length)
            resize(newSize);

        for (int i = size - 1; i >= index; i--)
            arr[i + c.size()] = arr[i];

        int i = 0;
        for (E element : c) {
            arr[index + i] = element;
            i++;
        }

        size = newSize;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                remove(i);
                result = true;
                i--;
            }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0; i < size; i++)
            if (!c.contains(arr[i])) {
                remove(i);
                result = true;
                i--;
            }

        return result;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

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
