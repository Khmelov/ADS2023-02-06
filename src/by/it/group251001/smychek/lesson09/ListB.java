package by.it.group251001.smychek.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] Values = (E[])new Object[]{};
    private int size = 0;

    @Override
    public String toString() {
        String s = "[";
        for(int i = 0; i < size; ++i){
            s = s + Values[i].toString();
            if(i != size - 1)
                s = s + ", ";
        }
        s = s + ']';
        return s;
    }

    @Override
    public boolean add(E e) {
        if(size == Values.length)
            Values = Arrays.copyOf(Values, (size * 2 + 1));
        Values[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E deleted = Values[index];
        for(int i = index; i < size - 1; ++i){
            E temp = Values[i];
            Values[i] = Values[i + 1];
            Values[i + 1] = temp;
        }
        --size;
        return deleted;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        this.add(element);
        for(int i = size - 1; i > index; --i){
            E temp = Values[i];
            Values[i] = Values[i - 1];
            Values[i - 1] = temp;
        }
    }

    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < size; ++i)
            if(Values[i].equals(o)){
                this.remove(i);
                return true;
            }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E temp = Values[index];
        Values[index] = element;
        return  temp;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        while(!this.isEmpty())
            this.remove(size - 1);
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; ++i)
            if(Values[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public E get(int index) {
        return Values[index];
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; ++i)
            if(Values[i].equals(o))
                return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int ans = -1;
        for(int i = 0; i < size; ++i)
            if(Values[i].equals(o))
                ans = i;
        return ans;
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
