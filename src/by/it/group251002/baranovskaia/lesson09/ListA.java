package by.it.group251002.baranovskaia.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.*;
import java.util.function.UnaryOperator;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] m_elements;
    private int m_size;

    //Default constructor
    public ListA() {
        m_size = 0;
        m_elements = (E[]) new Object[0];
    }

    //Constructor with objects
    public ListA(Collection<? extends E> newElements) {
        Object[] elementsCopy = newElements.toArray();
        m_size = elementsCopy.length;
        m_elements = Arrays.copyOf((E[])elementsCopy, elementsCopy.length);
    }

    //Constructor with initial list size
    public ListA(int initialSize) {
        m_size = initialSize;
        m_elements = (E[]) new Object[initialSize];
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringJoiner txt = new StringJoiner(", ", "[", "]"); //Output in format: [element1, element2, element3, ..., elementN]
        for (int i = 0; i < m_size; i++) {
            if (m_elements[i] != null) {
                txt.add(m_elements[i].toString());
            } else {
                txt.add(null);
            }
        }
        return txt.toString();
    }

    @Override
    public boolean add(E e) {
        if (e != null) {
            m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
            m_elements[m_size++] = e;
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        E returnValue = m_elements[index];
        System.arraycopy(m_elements, index + 1, m_elements, index, m_size - index-1); // Copy (m_size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        m_elements[--m_size] = null;
        return returnValue;
    }

    @Override
    public int size() {
        return m_size;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {
        m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
        System.arraycopy(m_elements, index, m_elements, index + 1, m_size - index); // Copy (m_size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        m_elements[index] = element;
        m_size++;
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
        return m_size == 0;
    }


    @Override
    public void clear() {
        m_size = 0;
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
