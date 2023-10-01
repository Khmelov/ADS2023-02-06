package by.it.group251002.baranovskaia.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] m_elements = (E[]) new Object[0];
    private int m_size;

    //Default constructor
    public ListB() {
        m_size = 0;
        m_elements = (E[]) new Object[0];
    }

    //Constructor with objects
    public ListB(Collection<? extends E> newElements) {
        Object[] elementsCopy = newElements.toArray();
        m_size = elementsCopy.length;
        m_elements = Arrays.copyOf((E[])elementsCopy, elementsCopy.length);
    }

    //Constructor with initial list size
    public ListB(int initialSize) {
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
        StringJoiner txt = new StringJoiner(", ", "[", "]");
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
        System.arraycopy(m_elements, index + 1, m_elements, index, m_size - index - 1); // Copy (m_size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
        m_elements[--m_size] = null;
        return returnValue;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public void add(int index, E element) {
        m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
        System.arraycopy(m_elements, index, m_elements, index + 1, m_size - index);
        m_elements[index] = element;
        m_size++;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < m_size; index++) {
            if (Objects.equals(m_elements[index], o)) {
                System.arraycopy(m_elements, index + 1, m_elements, index, m_size - index - 1); // Copy (m_size - index - 1) elements from position (index + 1) from arr1 to position (index) from arr2
                m_elements[--m_size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E oldValue = m_elements[index];
        m_elements[index] = element;
        return oldValue;
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
        for (int index = 0; index < m_size; index++) {
            if (Objects.equals(m_elements[index], o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return m_elements[index];
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) > -1) {
            return true;
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int index = m_size - 1; index >= 0; index--) {
            if (Objects.equals(m_elements[index], o)) {
                return index;
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
