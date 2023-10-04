package by.it.group251003.khlyschankou.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    private E[] elements = (E[]) new Object[]{};
    private int size = 0;
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++){
            sb.append(delimiter).append(elements[i].toString());
            delimiter = ", ";
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean add(E e) {

        if (size == elements.length){
            elements = Arrays.copyOf(elements, (size * 3 / 2) + 1);
        }
        elements[size] = e;
        size++;

        return true;
    }

    @Override
    public E remove(int index) {

        E del = elements[index];
        System.arraycopy(elements,index + 1, elements,index,size - index - 1);
        size--;
        elements[size] = null;

        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {

        if (size == elements.length){
            elements = Arrays.copyOf(elements, (size * 3 / 2) + 1);
        }
        System.arraycopy(elements, index, elements,index + 1,size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);
        if (index > -1)
            remove(index);

        return index > -1;
    }

    @Override
    public E set(int index, E element) {

        E prev = elements[index];
        elements[index] = element;

        return prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {

        for (int i = size - 1; i >= 0; i--){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {

        if (o == null) {
            for (int i = 0; i < size; i++){
                if (elements[i] == null)
                    return i;
            }
        }
        else {
            for (int i = 0; i < size; i++){
                if (o.equals(elements[i]))
                    return i;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        int index = indexOf(o);

        return index > -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        if (o == null){
            for (int i = size - 1; i >= 0; i--){
                if (elements[i] == null){
                    return i;
                }
            }
        }
        else {
            for (int i = size-1; i>=0; i--){
                if(elements[i].equals(o)){
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object o : c) {
            if (indexOf(o) == -1){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        int prev = size;
        for (E e : c) {
            add(e);
        }

        return prev != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        int prev = size;
        for (E e : c) {
            add(index, e);
            index++;
        }

        return prev != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        int prev = size;
        for (Object o : c) {
            while (remove(o)){}
        }

        return prev != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        int prev = size;
        int i = 0;

        while(i != size) {
            if (!(c.contains(elements[i]))) {
                remove(i);
            }
            else {
                i++;
            }
        }

        return prev != size;
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
