package by.it.group251004.shmargun.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

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

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            if(!this.contains(params[i]))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            this.add(params[i]);
        return prevSize != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            this.add(index + i, params[i]);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            while(this.contains(params[i]))
                this.remove(params[i]);
        return prevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        for(int i = size - 1; i >= 0; --i)
            if(!c.contains(Values[i]))
                this.remove(i);
        return prevSize != size;
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
