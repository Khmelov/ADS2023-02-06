package by.it.group251001.vasilenko.lesson09;

import java.util.*;

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
        StringBuilder sb  = new StringBuilder("[");
        String del = "";
        for (int i = 0; i< size; i++){
            sb.append(del).append(elements[i]);
            del = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) elements = Arrays.copyOf(elements,size*3/2+1);
        elements[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = elements[index];
        System.arraycopy(elements,index+1,elements ,index,size - 1 -index);
        size--;
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length) elements = Arrays.copyOf(elements,size*3/2+1);
        System.arraycopy(elements,index,elements,index+1,size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int ind = indexOf(o);
        if (ind != -1 ) {
            remove(ind);
            return true;
        }
        else
            return false;
    }

    @Override
    public E set(int index, E element) {
        E del = elements[index];
        elements[index] = element;
        return del;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {
        for (int i =0 ;i < size;i++){
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0;i<size;i++){
                if (elements[i] == null){
                    return i;
                }
            }
        }
        else{
            for (int i = 0;i<size;i++){
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
         return indexOf(o)!=-1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        if (o == null){
            for (int i = 0;i<size;i++){
                if (elements[i] == null){
                    result = i;
                }
            }
        }
        else{
            for (int i = 0;i<size;i++){
                if (o.equals(elements[i])) result = i;
            }
        }

        return result;
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
