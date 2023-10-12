package by.it.group251004.shmargun.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    E[] listElems;

    public ListB(){
        listElems = (E[]) new Object[0];
    }

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < listElems.length; i++) {
            str.append(listElems[i]);
            if (i < listElems.length - 1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean add(E e) {
        E[] newListElems;
        try {
            newListElems = (E[]) new Object[listElems.length + 1];
        } catch (OutOfMemoryError error) {
            return false;
        }
        System.arraycopy(listElems, 0, newListElems, 0, listElems.length);
        newListElems[listElems.length] = e;
        listElems = newListElems;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > listElems.length - 1)
            return null;
        E elem = listElems[index];
        E[] newListElems = (E[]) new Object[listElems.length - 1];
        System.arraycopy(listElems, 0, newListElems, 0, index);
        System.arraycopy(listElems, index + 1, newListElems, index, listElems.length - 1 - index);
        listElems = newListElems;
        return elem;
    }

    @Override
    public int size() {
        return listElems.length;
    }

    @Override
    public void add(int index, E element) {
        E[] newListElems;
        try {
            newListElems = (E[]) new Object[listElems.length + 1];
        } catch (OutOfMemoryError error) {
            return;
        }
        System.arraycopy(listElems, 0, newListElems, 0, index);
        System.arraycopy(listElems, index, newListElems, index + 1, listElems.length - index);
        newListElems[index] = element;
        listElems = newListElems;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E deleted = null;
        if (index > -1 && index < listElems.length){
            deleted = listElems[index];
            listElems[index] = element;
        }
        return deleted;
    }


    @Override
    public boolean isEmpty() {
        return listElems.length == 0;
    }


    @Override
    public void clear() {
        listElems = (E[]) new Object[0];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < listElems.length; i++) {
            if (Objects.equals(listElems[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index > -1 && index < listElems.length)
            return listElems[index];
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = listElems.length - 1; i > -1; i--) {
            if (Objects.equals(o, listElems[i]))
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
