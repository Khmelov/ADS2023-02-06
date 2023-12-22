package by.it.group251003.Trukhan.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    private E[]array = (E[])new Object[10];
    private int size = 0;

    private void resize() {
        E[] tmpArray = (E[]) new Object[array.length * 2 + 1];
        System.arraycopy(array, 0, tmpArray, 0, array.length);
        array = tmpArray;
    }
    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < size; i++) {
            res += array[i].toString();
            if (i != size - 1) res +=  ", ";
        }
        return res + "]";
    }

    @Override
    public boolean add(E e) {
        if (size >= array.length)
            resize();

        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (checkIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds");

        E result = array[index];

        System.arraycopy(array, index+1, array, index, size-index-1);
        size--;
        array[size] = null;

        return result;
    }

    @Override
    public int size() {
        return size;
    }

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
