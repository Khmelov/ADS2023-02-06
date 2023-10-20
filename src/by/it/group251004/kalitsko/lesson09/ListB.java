package by.it.group251004.kalitsko.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E[] mas = (E[]) new Object[]{};

    private int size = 0;

    @Override
    public String toString() {

        StringBuilder strb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            strb.append(mas[i]);

            if (i + 1 != size) {
                strb.append(", ");
            }
        }

        strb.append("]");

        return strb.toString();
    }

    @Override
    public boolean add(E e) {

        if (size == mas.length) {
            mas = Arrays.copyOf(mas, ((size * 3 / 2) + 1));
        }

        mas[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {

        E deleteElem = mas[index];

        System.arraycopy(mas, index + 1, mas, index, size - 1 - index);
        size--;
        return deleteElem;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public void add(int index, E element) {

        if (size == mas.length) {
            mas = Arrays.copyOf(mas, (size * 3 / 2 + 1));
        }

        System.arraycopy(mas, index, mas, index + 1, size - index);
        mas[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {

        int indx = indexOf(o);

        if (indx > -1) {
            remove(indx);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {

        E elem = mas[index];
        mas[index] = element;
        return elem;
    }


    @Override
    public boolean isEmpty() {

        return size == 0;
    }


    @Override
    public void clear() {

        for (int i = size - 1; i >= 0; i--){
            mas[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {

        for (int i = 0; i < size; i++)
            if (Objects.equals(mas[i], o))
                return i;
        return -1;
    }

    @Override
    public E get(int index) {

        return mas[index];
    }

    @Override
    public boolean contains(Object o) {

        return indexOf(o) > -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int i = size - 1; i >= 0; i--)
            if (Objects.equals(mas[i], o))
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
