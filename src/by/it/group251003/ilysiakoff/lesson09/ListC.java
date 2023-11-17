package by.it.group251003.ilysiakoff.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] listArray = (E[]) new Object[0];
    private int size = 0;
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        String beginning = "[";
        String middle = "";
        String ending = "]";
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                middle += listArray[i] + ", ";
            } else {
                middle += listArray[i];
            }
        }
        return beginning + middle + ending;
    }

    @Override
    public boolean add(E e) {
        if (size == listArray.length) {
            E[] newListArray = (E[]) new Object[(listArray.length * 3) / 2 + 1];
            System.arraycopy(listArray, 0, newListArray, 0, size);
            listArray = newListArray;
        }
        listArray[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E elemToDelete = listArray[index];
        System.arraycopy(listArray, index + 1, listArray, index, size - index - 1);
        size--;
        listArray[size] = null;
        return elemToDelete;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == listArray.length) {
            E[] newListArray = (E[]) new Object[(size * 3) / 2 + 1];
            System.arraycopy(listArray, 0, newListArray, 0, size);
            listArray = newListArray;
        }
        for (int i = size; i > index; i--) {
            listArray[i] = listArray[i - 1];
        }
        listArray[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index_of = indexOf(o);
        if (index_of > -1)
            remove(index_of);
        return (index_of > -1);
    }

    @Override
    public E set(int index, E element) {
        E prevElem = listArray[index];
        listArray[index] = element;
        return prevElem;
    }


    @Override
    public boolean isEmpty() {
       return (size == 0);
    }


    @Override
    public void clear() {
        while(size != 0){
            remove(0);
        }
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (listArray[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (listArray[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return listArray[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (listArray[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if(!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (!c.isEmpty()) {
            for (E elem : c) {
                add(elem);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (!c.isEmpty()) {
            int i = 0;
            for (E elem : c) {
                add(index + i, elem);
                i++;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (int i = 0; i < size; i++) {
           if (c.contains(listArray[i])) {
               remove(i);
               i--;
               isRemoved = true;
           }
       }
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < size) {
            if (!c.contains(listArray[i]))
                remove(i);
            else
                i++;
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
