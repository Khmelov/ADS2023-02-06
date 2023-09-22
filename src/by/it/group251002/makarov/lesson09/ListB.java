package by.it.group251002.makarov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String del = ", ";
        sb.append("[");
        for (int i = 0; i<size; i++){
            if (i == size - 1) del = "";
            sb.append(elements[i]).append(del);
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length){
            E[] newelem = (E[]) new Object[(size * 3 / 2 + 1)];
            System.arraycopy(elements,0,newelem,0,size);
            elements=newelem;
        }
        elements[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E m = elements[index];
        System.arraycopy(elements,index+1,elements,index,size-1-index);
        elements[--size]=null;
        return m;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size==elements.length) {
            E[] newelem = (E[]) new Object[(elements.length*3)/2+1];
            System.arraycopy(elements,0,newelem,0,size);
            elements=newelem;}
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >-1) remove (index);
        return (index>-1);
    }

    @Override
    public E set(int index, E element) {
        E m = elements[index];
        elements[index]=element;
        return m;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        for (int i = 0; i<size; i++){
            elements[i]=null;
        }
        size=0;
    }

    @Override
    public int indexOf(Object o) {
    if (null == o){
        for (int i = 0; i<size; i++){
            if (elements[i]==null)
                return i;
        }
    } else {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) return i;
        }
    }
    return -1;
    }
    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o)>-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i<size; i++){
            if (o==null){
                if (elements[i]==null)return i;
            } else
            if (elements[i].equals(o))index = i;
        }
        return index;
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
