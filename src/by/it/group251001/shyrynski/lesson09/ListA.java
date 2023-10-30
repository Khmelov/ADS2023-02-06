package by.it.group251001.shyrynski.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {

    private Object[] arr = new Object[1];
    private int siz = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String razd = "";
        for (int i = 0; i < size(); i++) {
            sb.append(razd).append(arr[i]);
            razd = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (arr.length == siz)
            arr = Arrays.copyOf(arr, siz * 2);
        arr[siz++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index >= size())
            return null;
        E res = (E) arr[index];
        for (int i = index; i < size() - 1; i++)
            arr[i] = arr[i + 1];
        siz--;
        return res;
    }

    @Override
    public int size() {
        return siz;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {
        if (arr.length == siz)
            arr = Arrays.copyOf(arr, siz * 2);
        siz++;
        for (int i = siz - 1; i > index; i--)
            arr[i] = arr[i - 1];
        arr[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        int cnt = 0;
        while (cnt < size() && !arr[cnt].equals(o))
            cnt++;
        if (cnt == size())
            return false;
        remove(cnt);
        return true;
    }

    @Override
    public E set(int index, E element) {
        E res = (E) arr[index];
        arr[index] = element;
        return res;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public void clear() {
        arr = new Object[1];
        siz = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++)
            if (arr[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public E get(int index) {
        return (E) arr[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++)
            if (arr[i].equals(o))
                return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--)
            if (arr[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object o : c)
            add((E) o);
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (arr.length - size() < c.size()) {
            int newlen = arr.length;
            while (newlen - size() < c.size())
                newlen *= 2;
            arr = Arrays.copyOf(arr, newlen);
        }
        for (int i = size() - 1; i >= index; i--)
            arr[i + c.size()] = arr[i];
        Iterator<? extends E> it = c.iterator();
        for (int i = 0; i < c.size(); i++)
            arr[index + i] = it.next();
        siz += c.size();
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        ListA<E> nw = new ListA<E>();
        for (int i = 0; i < size(); i++)
            if (!c.contains(arr[i]))
                nw.add((E) arr[i]);
        if (size() == nw.size())
            return false;
        arr = nw.arr;
        siz = nw.size();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ListA<E> nw = new ListA<E>();
        for (int i = 0; i < size(); i++)
            if (c.contains(arr[i]))
                nw.add((E) arr[i]);
        if (size() == nw.size())
            return false;
        arr = nw.arr;
        siz = nw.size();
        return true;
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
