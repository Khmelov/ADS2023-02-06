package by.it.group251002.zhukovskaya.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E[] mas = (E[]) new Object[] {};
    private int size = 0;
    @Override
    public String toString() {
        if (size==0) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size - 1; i++) {
                sb.append(mas[i]).append(", ");
            }
            sb.append(mas[size - 1]).append("]");
            return sb.toString();
        }
    }

    @Override
    public boolean add(E e) {
        if (size== mas.length) {
            mas = Arrays.copyOf(mas, (size*3)/2+1);
        }
        mas[size]=e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E d=mas[index];
        System.arraycopy(mas, index+1, mas, index,size-1-index);
        size--;
        return d;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size== mas.length) {
            mas = Arrays.copyOf(mas, (size*3)/2+1);
        }
        System.arraycopy(mas, index, mas, index+1,size-index);
        mas[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index=indexOf(o);
        if (index!=-1) {
            remove(index);
            return true;
        }
        else
            return false;
    }

    @Override
    public E set(int index, E element) {
        E buf=mas[index];
        mas[index]=element;
        return buf;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        mas=null;
        mas=(E[]) new Object[] {};
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        int index=-1;
        if (o==null) {
            for (int i=0; i<size; i++) {
                if ((mas[i])==null) {
                    index = i;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(mas[i])) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public E get(int index) {
        return mas[index];
    }

    @Override
    public boolean contains(Object o) {
        int flag=0;
        for (int i=0; i<size; i++) {
            if (mas[i].equals(o))
                flag=1;
        }
        if (flag==1)
            return true;
        else
            return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i=size-1; i>-1; i--) {
            if (o.equals(mas[i]))
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
