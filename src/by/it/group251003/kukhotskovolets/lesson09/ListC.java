package by.it.group251003.kukhotskovolets.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] elems = (E[]) new Object[0];
    private int size = 0;

    private void resize(){
        E[] newElems = (E[]) new Object[(elems.length * 3) / 2 + 1];
        System.arraycopy(elems, 0, newElems, 0, size);
        elems = newElems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        if (size > 0){
            for (int i = 0; i < size; i++) {
                sb.append(delimiter).append(elems[i]);
                delimiter = ", ";
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elems.length){
            resize();
        }
        elems[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E deletedElem = elems[index];
        if (size > 0){
            System.arraycopy(elems, index + 1, elems, index, size - index - 1);
        }
        elems[--size] = null;
        return deletedElem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elems.length){
            resize();
        }
        System.arraycopy(elems, index, elems, index + 1, size - index);
        elems[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int elemIndex = indexOf(o);
        if (elemIndex == -1){
            return false;
        }
        remove(elemIndex);
        return true;
    }

    @Override
    public E set(int index, E element) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        E deletedElem = elems[index];
        elems[index] = element;
        return deletedElem;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        System.arraycopy(elems, 0, elems, 0, 0 );
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elems[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return elems[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0 ; i--) {
            if (o.equals(elems[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c) {
            if (!contains(element)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()){
            return false;
        }

        int newSize = c.size() + size;
        while (newSize > elems.length){
            resize();
        }

        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()){
            return false;
        }

        int newSize = c.size() + size;
        while (newSize > elems.length){
            resize();
        }

        for (E e : c) {
            add(index, e);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (int i = 0; i < size; i++) {
            if(c.contains(elems[i])){
                remove(i);
                i--;
                res = true;
            }
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (int i = 0; i < size; i++) {
            if(!c.contains(elems[i])){
                remove(i);
                res = true;
                i--;
            }
        }
        return res;
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
