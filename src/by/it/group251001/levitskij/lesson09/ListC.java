package by.it.group251001.levitskij.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    private int size = 0;
    private E []data = (E[])new Object[0];
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size; i++){
            sb.append(data[i]);
            if(i <size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if(size == data.length){
            E []temp = (E[])new Object[size*3/2+1];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        data[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldvalue = data[index];
        System.arraycopy(data, index+1, data, index, size-1-index);
        data[--size] = null;
        return oldvalue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size+1);
        if (size == data.length) {
            E[] temp = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(data, 0, temp, 0, index);
            System.arraycopy(data, index, temp, index+1, size-index);
            data = temp;
        } else {
            System.arraycopy(data, index, data, index+1, size-index);
        }
        data[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index!=-1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldvalue = data[index];
        data[index] = element;
        return oldvalue;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        data = (E[]) new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if(o == null){
            for(int i = 0; i < size; i++){
                if(data[i]==null){
                    return i;
                }
            }
        } else {
            for(int i = 0; i < size; i++){
                if(data[i].equals(o)){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(o == null){
            for(int i = size-1; i >= 0; i--){
                if(data[i]==null){
                    return i;
                }
            }
        } else {
            for(int i = size-1; i >= 0; i--){
                if(data[i].equals(o)){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object []cArray = c.toArray();
        for(int i = 0; i < cArray.length; i++){
            if(indexOf(cArray[i])<0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0){
            return false;
        }
        if(size+cArray.length>data.length){
            E []temp = (E[])new Object[size+cArray.length];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        System.arraycopy(cArray, 0, data, size, cArray.length);
        size +=cArray.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Objects.checkIndex(index, size+1);
        Object []cArray = c.toArray();
        if(cArray.length == 0){
            return false;
        }
        if(size+cArray.length>data.length){
            E []temp = (E[])new Object[size+cArray.length];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        System.arraycopy(data, index, data, index+cArray.length, size - index);
        System.arraycopy(cArray, 0, data, index, cArray.length);
        size +=cArray.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0){
            return false;
        }
        boolean result = false;
        for(int i = 0; i < cArray.length; i++){
            int index = indexOf(cArray[i]);
            if (index >= 0){
                remove(index);
                i--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for(int i = 0; i < size; i++){
            if(!c.contains(data[i])){
                remove(data[i]);
                i--;
                result = true;
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
        Object[] res = new Object[size];
        System.arraycopy(data, 0, res, 0, size);
        return res;
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
