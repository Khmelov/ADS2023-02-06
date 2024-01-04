package by.it.group251003.pelih.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E [] array = (E[]) new Object[]{};

    private int size = 0;
    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result +=array[i];
            if(size-1 != i) result +=", ";

        }
        result += "]";
        return result;
    }

    @Override
    public boolean add(E e) {
        if(size == array.length){
            array = Arrays.copyOf(array,size*3+1);
        }

        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {

        E del = array[index];

        System.arraycopy(array,index+1,array,index,size-1-index);
        size--;
        return del;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int size() {

        return size;
    }

    /*Добавление по индексу*/
    @Override
    public void add(int index, E element) {

        if (size == array.length)
            array = Arrays.copyOf(array,size*3+1);

        System.arraycopy(array,index,array,index+1,size-index);
        array[index] = element;

        size++;
    }


    /*Удаление элемента по значению*/
    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);
        if(index > -1) remove(index);

        return index > -1;
    }

    @Override
    public E set(int index, E element) {

        E temp = array[index];

        array [index] = element;

        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = size-1; i >= 0; i--) {
            remove(array[i]);
        }

    }

    /*Возвращает индексы по элементу*/
    @Override
    public int indexOf(Object o) {
        if(o == null){
            for (int i = 0; i < size; i++) {
                if(array[i] == null){
                    return i;
                }
            }
        }
        else
        {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i]))
                    return i;
            }
        }

        return -1;
    }

    /*Элемент по индексу*/
    @Override
    public E get(int index) {
        return array[index];
    }

    /*Содержит ли массив объект*/
    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    /*Последний индекс по элементу*/
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size-1; i >= 0 ; i--) {
            if(o.equals(array[i]))
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