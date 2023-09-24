package by.it.group251003.kapinskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    private E[] list = (E[]) new Object[10];
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder str = new StringBuilder();

        str.append("[");
        for (int i = 0; i < size - 1; i++)
            str.append(list[i] + ", ");

        return str.append(list[size - 1] + "]").toString();
    }

    @Override
    public boolean add(E e) {
        if(size == list.length){
            E[] newlist = (E[]) new Object[size + 10];
            System.arraycopy(list, 0, newlist, 0, size);
            list = newlist;
        }
        list[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        E deleted = list[index];
        for (int i = index; i < size - 1; i++)
            list[i] = list [i + 1];
        size--;
        return deleted;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void add(int index, E element) {
        if(size == list.length){
            E[] newlist = (E[]) new Object[size + 10];
            System.arraycopy(list, 0, newlist, 0, size);
            list = newlist;
        }

        for (int i = size; i > index; i --)
            list[i] = list [i - 1];
        list[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        boolean flag = true;
        while (i < size && flag) {
            if (o.equals(list[i])) {
                flag = false;
                break;
            }
            i++;
        }
        if (flag) return false;
        while (i < size - 1){
            list[i] = list[i + 1];
            i++;
        }
        size--;
        return true;
    }

    @Override
    public E set(int index, E element) {
        if (index >= 0 && index < size){
            E deleted = list[index];
            list[index] = element;
            return deleted;
        }
        else return null;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        E[] newlist = (E[]) new Object[10];
        list = newlist;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(list[i]))
                return i;
        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size) return list[index];
        return null;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(list[i])) return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = 0, mem = -1;
        while (i < size){
            if (list[i].equals(o))
                mem = i;
            i++;
        }
        return mem;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o: c)
           add(o);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int i = 0;
        for (E o: c) {
            add(index + i, o);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o: c)
            while (contains(o))
                remove(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < size) {
            if (!c.contains(list[i]))
                remove(i);
            else i++;
        }
        return true;
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
