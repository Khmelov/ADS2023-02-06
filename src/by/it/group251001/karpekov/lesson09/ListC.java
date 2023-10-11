package by.it.group251001.karpekov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    private E[] arr = (E[]) new Object[0];
    private int size = 0;
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            result.append(arr[i]);
            if (i < size - 1) result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public boolean add(E e) {
        try{
            if (size >= arr.length)
            {
                E[] temp = (E[]) new Object[arr.length != 0 ? arr.length << 1 : 10];
                System.arraycopy(arr, 0, temp, 0, size);
                arr = temp;
            }
            arr[size++] = e;
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            return null;
        }
        else
        {
            E removed = arr[index];
            System.arraycopy(arr, index+1, arr, index, size - index - 1);
            size--;
            return removed;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < size){
            if (size>= arr.length)
            {
                E[] temp = (E[]) new Object[arr.length != 0 ? arr.length << 1 : 10];
                System.arraycopy(arr, 0, temp, 0, size);
                arr = temp;
            }
            System.arraycopy(arr, index, arr, index+1, size - index);
            arr[index] = element;
            size++;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++){
            if (o.equals(arr[i]))
            {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < size)
        {
            E temp = arr[index];
            arr[index] = element;
            return temp;
        }
        else return null;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public void clear() {
        E[] temp = (E[]) new Object[0];
        arr = temp;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (o.equals(arr[i]))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++){
            if (o.equals(arr[i]))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        for (int i = 0; i < size; i++){
            if (o.equals(arr[i]))
            {
                result = i;
            }
        }
        return result;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] carr = c.toArray();
        for (int i = 0; i < c.size(); i++)
        {
            if (! this.contains(carr[i])) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] carr = (E[]) c.toArray();
        try{
            E[] temp = (E[]) new Object[arr.length + carr.length];
            System.arraycopy(arr, 0, temp, 0, size);
            arr = temp;
            System.arraycopy(carr, 0, arr, size, carr.length);
            size += carr.length;
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < size){
            E[] carr = (E[]) c.toArray();
            E[] temp = (E[]) new Object[arr.length + carr.length];
            System.arraycopy(arr, 0, temp, 0, index);
            System.arraycopy(carr, 0, temp, index, carr.length);
            System.arraycopy(arr, index, temp, index + carr.length, size - index);
            arr = temp;
            size += carr.length;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (c.contains(arr[i])) {
                this.remove(i);
                edited = true;
            }
            else
                i++;
        }

        return edited;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean edited = false;
        int i = 0;
        while (i < size) {
            if (! c.contains(arr[i])) {
                this.remove(i);
                edited = true;
            }
            else
                i++;
        }
        return edited;
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
