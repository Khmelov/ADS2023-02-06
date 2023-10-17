package by.it.group251003.beskosty.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] List = (E[]) new Object[0];
    private int size = 0;
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        String temp = "";
        for (int i = 0; i < size; i++){
            string.append(temp).append(List[i]);
            temp = ", ";
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == List.length) {
            E[] newList = (E[]) new Object[2 * List.length + 1];
            System.arraycopy(List, 0, newList, 0, size);
            List = newList;
        }
        List[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E DelEl = List[index];
        for (int i = index; i < size; i++) {
            List[i] = List[i + 1];
        }

        List[size - 1] = null;
        size--;

        return DelEl;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == List.length) {
            E[] newList = (E[]) new Object[2 * List.length + 1];
            System.arraycopy(List, 0, newList, 0, size);
            List = newList;
        }
        for(int i = size; i > index; i--){
            List[i] = List[i-1];
        }
        List[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int Oind = indexOf(o);
        if (Oind < 0) {
            return false;
        }

        remove(Oind);
        return true;
    }

    @Override
    public E set(int index, E element) {
        E ElPrev = List[index];
        List[index] = element;
        return ElPrev;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        while(size != 0){remove(0);}
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (List[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return List[index];
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o)>-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--){
            if (List[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c){
            if (!(contains(obj))) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        for (E NewEl : c){
            add(NewEl);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        int i = 0;
        for (E NewEl : c){
            add(index+i++, NewEl);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean Flag = false;
        for(int i = 0; i < size; i++){
            if(c.contains(List[i])){
                remove(i);
                Flag = true;
                i--;
            }
        }
        return Flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < size){
            if(!(c.contains(List[i]))) remove(i);
            else i++;
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
