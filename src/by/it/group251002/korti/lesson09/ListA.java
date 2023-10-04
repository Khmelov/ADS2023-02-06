package by.it.group251002.korti.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    private E[] elements =(E[]) new Object[]{};
    private int size = 0;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter ="";
        for(int i = 0 ; i<size;i++){
            sb.append(delimiter).append(elements[i]);
            delimiter=", ";
        }
        sb.append("]");
        return sb.toString();

    }

    @Override
    public boolean add(E e) {
        if(size==elements.length)
            elements = Arrays.copyOf(elements, (size*3)/2+1);
        elements[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = elements[index];
        System.arraycopy(elements,index+1,elements,index,size-1-index);
        size--;
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
        if(size==elements.length)
            elements = Arrays.copyOf(elements, (size*3)/2+1);
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index=indexOf(o);
        if(index>-1) remove(index);
        return index>-1;
    }

    @Override
    public E set(int index, E element) {
        E elem = get(index);
        elements[index] = elem;
        return elem;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        size = 0;
        elements = null;
    }

    @Override
    public int indexOf(Object o) {
        if(o == null ){
            for(int i = 0; i<size;i++)
                if(elements[i]==null)
                    return i;
                }
            else{
                for(int i=0;i<size;i++)
                    if(o.equals(elements[i]))
                        return i;

                }

        return -1;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0;i< elements.length;i++){
            if(elements[i]==o)
                return true;
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        boolean isFind = false;
        int lastInd = 0;
        for(int i = 0;i< elements.length;i++){
            if(elements[i]==o){
                isFind = true;
                lastInd = i;
            }
        }
        if(!isFind){
            return -1;
        }
        return lastInd;
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
