package by.it.group251001.hatalskiy.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {
    private Object[] arr = new Object[1];
    private int size = 0;
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString()
    {
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
        if (arr.length == size){
            arr = Arrays.copyOf(arr,size * 2);
        }
        arr[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if(index>= size()){
            return null;
        }
        E res = (E) arr[index];
        for (int i = index; i<size;i++)
        {
            arr[i] = arr[i+1];
        }
        size--;
        return res;
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
        if(arr.length == size())
            arr = Arrays.copyOf(arr,size()*2);
        for (int i = size()-1; i > index; i--){
            arr[i+1] = arr[i];
        }
        arr[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        while ((arr[index] != o) && (index < size()))
            index++;

        if(index >= size())
            return false;

        for (int i = index; i< size()-1;i++){
            arr[i]=arr[i+1];
        }
        return true;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size())
            return null;
        E res = (E) arr[index];
        arr[index] = res;
        return res;
    }

    @Override
    public boolean isEmpty() {
        if(size() == 0)
            return true;
        return false;
    }


    @Override
    public void clear() {
        arr = new Object[1];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        while ((arr[index]!=o)&&(index <size)){
            index++;
        }
        if(index >= size)
            return -1;
        return index;
    }

    @Override
    public E get(int index) {
        if(index >=size)
        return null;
        return (E)arr[index];
    }

    @Override
    public boolean contains(Object o) {
        int index=0;
        while ((arr[index]!=o)&&(index<size()))
            index++;
        return size < index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i< size();i++)
            if(arr[i] == o)
                index = i;
        return index;

    }

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
