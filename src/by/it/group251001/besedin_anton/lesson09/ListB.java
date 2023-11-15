package by.it.group251001.besedin_anton.lesson09;


import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] elements = (E[]) new Object[0];

    private int size;

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder str = new StringBuilder("[");

        str.append(elements[0]);
        for (int i = 1; i < size; i++) {
            str.append(", ").append(elements[i]);
        }

        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            int newCapacity = (elements.length * 3 / 2) + 1;
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size++] = e;
        return true;
    }


    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        E deleteElem = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;

        return deleteElem;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length) {
            int newCapacity = (size * 3 / 2) + 1;
            E[] newElements = (E[]) new Object[newCapacity];

            for (int i = 0; i < index; i++) {
                newElements[i] = elements[i];
            }

            newElements[index] = element;

            for (int i = index; i < size; i++) {
                newElements[i + 1] = elements[i];
            }

            elements = newElements;
            size++;
        } else {
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }

            elements[index] = element;
            size++;
        }
    }


    @Override
    public boolean remove(Object o) {
        int deleteElem = indexOf(o);

        if (deleteElem > -1){
            remove(deleteElem);
        }

        return (deleteElem > -1);
    }

    @Override
    public E set(int index, E element) {
        E prevElem = elements[index];
        elements[index] = element;
        return prevElem;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        while(size > 0){
            remove(0);
        }
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) > -1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--){
            if (elements[i].equals(o))
                return i;
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
