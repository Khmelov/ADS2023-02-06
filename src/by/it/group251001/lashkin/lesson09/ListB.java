package by.it.group251001.lashkin.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E[] elem = (E[]) new Object[]{};
    private int size = 0;

    @Override
    public String toString() {
        String res = "[";
        if (size != 0) {
            res += elem[0];
            for (int i = 1; i < size; i++) {
                res = res + ", " + elem[i];
            }
        }
        res += "]";
        return res;
    }

    @Override
    public boolean add(E e) {
        if (size == elem.length) {
            int c = elem.length * 3 / 2 + 1;
            E[] newElem = (E[]) new Object[c];
            System.arraycopy(elem,0, newElem,0,size);
            elem = newElem;
        }
        elem[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = elem[index];
        System.arraycopy(elem,index + 1, elem, index, size - 1 - index);
        elem[--size] = null;
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elem.length) {
            int c = elem.length * 3 / 2 + 1;
            E[] newElem = (E[]) new Object[c];
            System.arraycopy(elem,0, newElem,0,size);
            elem = newElem;
        }
        System.arraycopy(elem,index,elem,index+1,size-index);
        elem[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx != -1) {
            remove(idx);
        }
        return (idx != -1);
    }

    @Override
    public E set(int index, E element) {
        E prev = elem[index];
        elem[index] = element;
        return prev;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elem[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (((o == null) && (elem[i] == null)) || Objects.equals(o, elem[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return elem[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public int lastIndexOf(Object o) {
        int last = -1;
        for (int i = 0; i < size; i++) {
            if ((o == null) && (elem[i] == null)) {
                last = i;
            }
            if (null != elem[i]) {
                if (elem[i].equals(o)) {
                    last = i;
                }
            } else {
                throw new AssertionError();
            }
        }
        return last;
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
