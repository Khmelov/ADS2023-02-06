package by.it.group251002.urbanovich.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            builder.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2 + 1);
        elements[size++] = e;
        return true;

    }

    @Override
    public E remove(int index) {
        E deletedElem = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deletedElem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2 + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
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
        E replacedElem = elements[index];
        elements[index] = element;
        return replacedElem;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                index = i;
        return index;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for (Object elem : c)
            if (contains(elem))
                count++;
        return count == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E elem : c)
            add(elem);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.size()==0)
            return false;
        for (E elem : c) {
            add(index, elem);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasRemoved = false;
        for (int i = 0; i < size; i++) {
            if(c.contains(elements[i]))
            {
                remove(elements[i]);
                i--;
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean retained = false;
        for (int i = 0; i < size; i++) {
            if(!c.contains(elements[i])) {
                remove(elements[i]);
                i--;
                retained = true;
            }
        }
        return retained;
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
