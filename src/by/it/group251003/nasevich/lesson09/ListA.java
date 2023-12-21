package by.it.group251003.nasevich.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    E[] array = (E[])new Object[0];
    E[] newArr;

    private int size = 0;

    private int copacity = 0;

    ListA(){
        array = (E[])new Object[10];
    }

    ListA(int copacity){

        array = (E[])new Object[copacity];
        this.copacity = copacity;

    }

    @Override
    public String toString() {

        String str = "[";

        str += array[0];

        for (int i = 1; i < size; i++) {
            str += ", " + array[i];
        }

        str += "]";

        return str;
    }

    @Override
    public boolean add(E e) {

        size++;

        if (array.length == size){

            newArr = (E[])new Object[size * 2];

            for (int i = 0; i < size; i++){
                newArr[i] = array[i];
            }
                array = newArr;

        }

            array[size - 1] = e;

        return true;
    }

    @Override
    public E remove(int index) {

        if (index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }

        E item = array[index];

        for (int i = index; i < size; i++) {

            array[i] = array[i + 1];

        }

        array[size - 1] = null;

        size--;

        return item;
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


    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public E set(int index, E element) {

        if (index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }

        E item = array[index];

        array[index] = element;

        return item;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
