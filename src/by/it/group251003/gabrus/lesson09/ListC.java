package by.it.group251003.gabrus.lesson09;

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
    private E[] array = (E[]) new Object[0];
    private int size = 0;
    private void growSize(){
        E[] arrayClone = (E[]) new Object[size * 3 / 2 + 1];
        System.arraycopy(array, 0, arrayClone, 0, size);
        array = arrayClone;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            result.append(array[0]);

            for (int i = 1; i < size; i++)
                result.append(", ").append(array[i]);
        }

        result.append("]");
        return result.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == array.length) growSize();

        if (e != null) {
            array[size++] = e;
        }

        return true;
    }

    @Override
    public E remove(int index) {
        E result = array[index];
        for (int i = index; i < size - 1; i++){
            array[i] = array[i + 1];
        }
        array[--size] = null;

        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == array.length) growSize();
        for (int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        boolean isFound = false;
        for (int i = 0; i < size; i++){
            if (isFound) array[i - 1] = array[i];
            if (array[i].equals(o)) isFound = true;
        }
        if (isFound) {
            array[--size] = null;
        }

        return isFound;
    }

    @Override
    public E set(int index, E element) {
        E erasedElement = array[index];
        array [index] = element;
        return erasedElement;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (array[i].equals(o)) return i;
        }

        return -1;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--){
            if (array[i].equals(o)) return i;
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c){
            if (!contains(element)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isAdded = false;
        for (E element: c){
            if (element != null) {
                add(element);
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean isAdded = false;
        for (E element: c){
            if (element != null) {
                add(index++, element);
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (Object element: c){
            int shift = 0;
            for (int i = 0; i < size; i++) {
                array[i - shift] = array[i];
                if (array[i].equals(element)) {
                    shift++;
                    isRemoved = true;
                }
            }

            for (int i = size - shift; i < size; i++)
                array[i] = null;
            size -= shift;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;
        int i = 0;
        while (i < size){
            if (!c.contains(array[i])) {
                remove(array[i]);
                isRemoved = true;
            } else {
                i++;
            }
        }

        return isRemoved;
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
