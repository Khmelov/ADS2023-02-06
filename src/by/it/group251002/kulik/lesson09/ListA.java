package by.it.group251002.kulik.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] elements = (E[])  new Object[]{};
    private int size = 0;
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i=0;i<size;i++) {
                    sb.append(delimiter).append(elements[i]);
                    delimiter=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size==elements.length) {
        E[] NewElements = (E[]) new Object[(elements.length*3)/2+1];
        System.arraycopy(elements,0,NewElements,0,size);
        elements=NewElements;}
        elements[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = elements[index];
        System.arraycopy(elements,index+1,elements,index,size-1-index);
        size--;
        elements[size]=null; //????
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {
        if (size==elements.length) {
            E[] NewElements = (E[])  new Object[(elements.length*3)/2+1];
            System.arraycopy(elements,0,NewElements,0,size);
            elements=NewElements;}
        System.arraycopy(elements,index,elements,index+1,index+1);
        elements[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index>-1) remove(index);
        return (index>-1);
    }

    @Override
    public E set(int index, E element) {
        E prev = elements[index];
        elements[index]=element;
        return prev;
    }


    @Override
    public boolean isEmpty() {
        return (size==0);
    }


    @Override
    public void clear() {
        while (size!=0)
            remove(0);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i=0;i<size;i++)
                if (elements[i]==null)
                    return i;
        } else {
            for (int i=0;i<size;i++)
                if (elements[i].equals(o))
                    return i;}
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
        int last = -1;
        for (int i=0;i<size;i++) {
            if (o == null) {
                if (elements[i]==null) last=i;
            } else
                if (elements[i].equals(o)) last=i;
        }
        return last;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i=0;i<c.size();i++)
            if (!contains(c.stream().toList().get(i))) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (int i=0;i<c.size();i++)
            add(c.stream().toList().get(i));
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (int i=0;i<c.size();i++)
            add(index+i,c.stream().toList().get(i));
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i=0;i<c.size();i++)
            remove(c.stream().toList().get(i));
        return true;
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
