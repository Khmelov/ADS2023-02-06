package by.it.group251003.kopytok_mikhail.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {
    private E[] arr = (E[]) new Object[]{};
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder str_b = new StringBuilder();
        str_b.append("[");
        if (size > 0){
            str_b.append(arr[0]);
        }
        for (int i = 1; i < size; i++){
            str_b.append(", ").append(arr[i]);
        }
        str_b.append("]");
        return str_b.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length){
            arr = Arrays.copyOf(arr, size * 3 / 2 + 1);
        }
        arr[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = arr[index];
        for (int i = index; i < size - 1; i++){
            arr[i] = arr[i + 1];
        }
        size--;
        arr[size] = null;
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == arr.length){
            arr = Arrays.copyOf(arr, size * 3 / 2 + 1);
        }
        for (int i = size; i > index; i-- ){
            arr[i] = arr[i-1];
        }
        size++;
        arr[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1){
            remove(index);
        }
        return index != -1;
    }

    @Override
    public E set(int index, E element) {
        E prev = arr[index];
        arr[index] = element;
        return prev;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++){
                if (arr[i] == null)
                    return i;
            }
            return -1;
        }
        for (int i = 0; i < size; i++){
            if (o.equals(arr[i]))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public boolean contains(Object o) {
        int index = indexOf(o);
        return index != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = size - 1; i >= 0; i--){
                if (arr[i] == null){
                    return i;
                }
            }
            return -1;
        }
        else{
            for (int i = size-1; i>=0; i--){
                if(arr[i].equals(o)){
                    return i;
                }
            }
        }
        return -1;
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
