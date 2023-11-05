package by.it.group251001.krivitsky.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    private int size = 0;
    private E[] elements = (E[]) new Object[10];

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++){
            sb.append(elements[i]).append(", ");
        }
        if (size > 0){
            sb.append(elements[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length){
            E[] newarr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
        elements[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E temp = elements[index];
        for (int i = index; i < size - 1; i++){
            elements[i] = elements[i+1];
        }
        size--;
        return temp;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length){
            E[] newarr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
        for (int i = size; i>index; i--){
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E temp = elements[index];
        elements[index] = element;
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        elements = (E[]) new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
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
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i>=0; i--){
            if (elements[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean cont = false;
        for (Object temp : c){
            if (!contains(temp)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()){
            return false;
        }
        for (E temp: c){
            add(temp);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()){
            return false;
        }
        for (E temp: c){
            add(index, temp);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;
        boolean f = false;
        while (i < size) {
            if (c.contains(elements[i])){
                remove(i);
                f = true;
            }
            else{
                i++;
            }
        }
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < size) {
            if (!c.contains(elements[i])){
                remove(i);
            }
            else{
                i++;
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
