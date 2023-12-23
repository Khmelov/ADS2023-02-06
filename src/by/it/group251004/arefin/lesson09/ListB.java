package by.it.group251004.arefin.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {

    E[] arr = (E[]) new Object[]{};
    int capacity = 0;  // real size
    int size = 0; // size for user
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size == 0){
            return "[]";
        }
        for (int i = 0; i < size; i++) {
            result.append(arr[i].toString());
            result.append(", ");
        }
        if (arr.length > 0) {
            result.delete(result.length() - 2, result.length());
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public boolean add(E e) {
        if (capacity == size()) {
            capacity = (capacity * 3 / 2) + 1;
            E[] oldArr = arr;
            arr = (E[]) new Object[capacity];
            if (size >= 0) System.arraycopy(oldArr, 0, arr, 0, size);
        }
        arr[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E result = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index <= size && index >= 0){
            if (capacity == size()) {
                capacity = (capacity * 3 / 2) + 1;
                E[] oldArr = arr;
                arr = (E[]) new Object[capacity];
                System.arraycopy(oldArr, 0, arr, 0, size);
            }
            for (int i = size + 1; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = element;
            size++;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E result = arr[index];
        arr[index] = element;
        return result;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            //remove(i);
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        for (int i = 0; i < size; i++) {
            if (i == index)
                return arr[i];
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o))
                return true;
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(arr[i], o)){
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
