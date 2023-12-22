package by.it.group251003.nasevich.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    private E[] array = (E[]) new Object[0];
    private int size;
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String temp = "";
        for(int i = 0; i < size; i++){
            str.append(temp).append(array[i]);
            temp = ", ";
        }
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean add(E e) {
        if(size== array.length){
            E[] newArray = (E[]) new Object[(array.length*3)/2+1];
            System.arraycopy(array,0,newArray,0,size);
            array =newArray;
        }
        array[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E item = array[index];
        System.arraycopy(array,index+1, array,index,size-1-index);
        size--;
        array[size]=null;
        return item;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == array.length){
            E[] newArray = (E[]) new Object[size * 2 + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        for (int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int delMem = indexOf(o);
        if (delMem > -1){
            remove(delMem);
        }
        return (delMem > -1);
    }


    @Override
    public E set(int index, E element) {
        E prev = array[index];
        array[index]=element;
        return prev;
    }


    @Override
    public boolean isEmpty() {

        return size==0;
    }


    @Override
    public void clear() {
        while(size != 0){
            remove(0);
        }
    }

    @Override
    public int indexOf(Object o) {
        for (int k = 0; k < size; k++){
            if (array[k].equals(o)) return k;
        }

        return -1;
    }

    @Override
    public E get(int index) {

        return array[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) > -1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int k = size - 1; k >= 0; k--){
            if (array[k].equals(o)) return k;
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object newobj : c){
            if(!contains(newobj)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        int k=0;
        for (E element : c) {
            add(index+k,element);
            k++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag1 = false;
        for(int k =0; k < size; k++){
            if(c.contains(array[k])){
                remove(k);
                flag1 = true;
                k--;
            }
        }
        return flag1;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int k = 0;
        while(k < size){
            if(!c.contains(array[k])){
                remove(k);
            }
            else {
                k++;
            }
        }
        return true;
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
