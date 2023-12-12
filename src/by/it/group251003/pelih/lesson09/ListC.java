package by.it.group251003.pelih.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    private E[] array = (E[]) new Object[]{};

    private int size = 0;
    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result+=array[i];
            if(size-1 != i) result+=", ";
        }
        result+="]";
        return result;
    }

    @Override
    public boolean add(E e) {
        if (size == array.length)
            array = Arrays.copyOf(array,size*3+1);

        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E del = array[index];
        System.arraycopy(array,index+1,array,index,size-index);
        size--;
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {

        if(size == array.length)
            array = Arrays.copyOf(array,size*3+1);
        System.arraycopy(array,index,array,index+1,size-index);
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);

        if(index > -1) remove(index);

        return index > -1;
    }

    @Override
    public E set(int index, E element) {

        E temp = array[index];

        array[index] = element;
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {

        for (int i = size-1; i >= 0; i--) {
            remove(array[i]);
        }
    }

    @Override
    public int indexOf(Object o) {
        if(o == null){
            for (int i = 0; i < size; i++) {
                if (array[i] == null) return i;
            }
        }
        else
        {
            for (int i = 0; i < size; i++) {
                if(o.equals(array[i])) return i;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int i = size-1; i >= 0; i--) {
            if(o.equals(array[i])) return i;
        }

        return -1;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*Содержит ли список все элементы из множества*/
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object NewObject: c) {
            if(!contains(NewObject)) return false;
        }

        return true;
    }

    /*Добавляет все элементы из множества в список*/
    @Override
    public boolean addAll(Collection<? extends E> c) {

        if(c.isEmpty()) return false;
        for (E NewObject:c) {
            add(NewObject);
        }
        return true;
    }

    /*Добавить начиная с индекса*/
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        if(c.isEmpty()) return false;
        for (E NewObject:c) {
            add(index,NewObject);
            index++;
        }
        return true;
    }

    /*Удаляет элементы колекции из списка*/
    @Override
    public boolean removeAll(Collection<?> c) {
        if(c.isEmpty()) return false;

        for (int i = 0; i < size; i++) {
            if(c.contains(array[i])) remove(i--);
        }


        return true;
    }

    /*Удаляет элеметы списка, не принадлежащие колекции*/
    @Override
    public boolean retainAll(Collection<?> c) {

        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(array[i])) {
                remove(i--);
                changed = true;
            }
        }
        return changed;
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