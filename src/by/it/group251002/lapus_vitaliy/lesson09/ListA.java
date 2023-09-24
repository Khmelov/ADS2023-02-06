package by.it.group251002.lapus_vitaliy.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {

    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    public void balanceSize() {
        if(elements.length==size)
        {
            E[] newAr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elements, 0, newAr, 0, size);
            elements = newAr;
        }

    }

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String razd = "";
        for (int i = 0; i < size; i++) {
            str.append(razd).append(elements[i]);
            razd=", ";
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean add(E e) {
        balanceSize();
        elements[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E elem = elements[index];
        System.arraycopy(elements, index+1, elements, index, size-index-1);
        size--;
        elements[size] = null;
        return elem;
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
        balanceSize();
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int ans = indexOf(o);
        if(ans!=-1)
        {
            remove(ans);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E elem = elements[index];
        elements[index]=element;
        return elem;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        elements = null;
        elements = (E[]) new Object[]{};
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        if(Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(elements[i]))
                    return i;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o))
                    return i;
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
        return indexOf(o)!=-1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(Objects.isNull(o)) {
            for (int i = size-1; i > -1; i--) {
                if (Objects.isNull(elements[i]))
                    return i;
            }
        }
        else {
            for (int i = size-1; i > -1; i--) {
                if (elements[i]!=null && elements[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < c.size(); i++) {
            if(!(contains(bufAr[i])))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            add(bufAr[i]);
        }
        return lastSize!=size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = bufAr.length-1; i >-1 ; i--) {
            add(index,bufAr[i]);
        }
        return lastSize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
                while(remove(bufAr[i]));
        }
        return lastSize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int lastSize=size;
        for (int i = size-1; i > -1; i--) {
            if(!(c.contains(elements[i])))
                remove(i);
        }
        return lastSize!=size;
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
