package by.it.group251001.dadush.lesson09;

import java.util.*;
import java.lang.StringBuilder;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E[] data = (E[])new Object[0];

    private int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i == size - 1) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        try {
            if (size >= data.length) {
                E[] ndata = (E[]) new Object[data.length != 0 ? data.length << 1 : 10];
                System.arraycopy(data, 0, ndata, 0, size);
                data = ndata;
            }
            data[size++] = e;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            return null;
        }
        E rem = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        return rem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size >= data.length) {
            E[] ndata = (E[]) new Object[data.length != 0 ? data.length << 1 : 10];
            System.arraycopy(data, 0, ndata, 0, size);
            data = ndata;
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size) {
            return null;
        }
        E tmp = data[index];
        data[index] = element;
        return tmp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        data = (E[]) new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i]))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            return null;
        return data[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(data[i]))
                return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (o.equals(data[i]))
                return i;
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] tmp = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if (! this.contains(tmp[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        E[] tmp = (E[]) c.toArray();
        if (size + tmp.length >= data.length) {
            E[] ndata = (E[]) new Object[size + tmp.length];
            System.arraycopy(data, 0, ndata, 0, size);
            data = ndata;
        }
        System.arraycopy(tmp, 0, data, size, tmp.length);
        size += tmp.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        E[] tmp = (E[]) c.toArray();
        if (size + tmp.length >= data.length) {
            E[] ndata = (E[]) new Object[size + tmp.length];
            System.arraycopy(data, 0, ndata, 0, size);
            data = ndata;
        }
        System.arraycopy(data, index, data, index + tmp.length, size - index);
        System.arraycopy(tmp, 0, data, index, tmp.length);
        size += tmp.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (c.contains(data[i])) {
                this.remove(i);
                edited = true;
            }
            else
                i++;
        }
        return edited;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (! c.contains(data[i])) {
                this.remove(i);
                edited = true;
            }
            else
                i++;
        }
        return edited;
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
