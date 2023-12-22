package by.it.group251002.matalyga.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] list = (E[]) new Object[0];
    private int size;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String temp = "";
        for(int i = 0; i < size; i++){
            str.append(temp).append(list[i]);
            temp = ", ";
        }
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(E e) {
        if(size==list.length){
            E[] new_list = (E[]) new Object[(list.length*3)/2+1];
            for (int i=0;i<list.length;i++)
                new_list[i] = list[i];
            list = new_list;
        }
        list[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index>=0 && index<size)
        {
            E removed = list[index];
            for (int i=index;i<size-1;i++)
                list[i] = list[i+1];
            size--;
            return removed;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == list.length) {
            E[] newList = (E[]) new Object[list.length *3/2+1];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
        if (index > -1 && index <= size) {
            for (int i = size; i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = element;
            size++;
        }
    }

    @Override
    public boolean remove(Object o) {
        int delMem = indexOf(o);
        if (delMem > -1){
            remove(delMem);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index>=0 && index<size){
            E replaced = list[index];
            list[index] = element;
            return replaced;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        for (int i=0;i<size;i++)
            list[i]=null;
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i=0;i<size;i++)
        {
            if (o.equals(list[i]))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index>=0 && index <size)
            return list[index];
        return null;
    }

    @Override
    public boolean contains(Object o) {
        for (int i=0;i<size;i++)
            if (o.equals(list[i])) return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i=size-1;i>=0;i--)
            if (o.equals(list[i])) return i;
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
