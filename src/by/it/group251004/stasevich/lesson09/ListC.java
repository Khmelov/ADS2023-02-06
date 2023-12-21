package by.it.group251004.stasevich.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    E[] list;
    private int size;
    private int capacity = 10;

    public ListC(){
        size = 0;
        list= (E[]) new Object[capacity];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(list[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == capacity)
        {
            capacity*=2;
            E[] newList = (E[]) new Object[capacity];
            for (int i=0;i<size;i++){
                newList[i]=list[i];
            }
            list = newList;
        }
        list[size]=e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (size == capacity)
        {
            capacity*=2;
            E[] newList = (E[]) new Object[capacity];
            for (int i=0;i<size;i++){
                newList[i]=list[i];
            }
            list = newList;
        }
        if (index > -1 && index < size)
        {
            E temp = list[index];
            for (int i=index;i<size;i++){
                list[i]=list[i+1];
            }
            size--;
            return temp;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == capacity)
        {
            capacity*=2;
            E[] newList = (E[]) new Object[capacity];
            for (int i=0;i<size;i++){
                newList[i]=list[i];
            }
            list = newList;
        }
        if (index>-1&&index<size){
            for (int i = size; i > index; i--){
                list[i]=list[i-1];
            }
            list[index]=element;
            size++;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index >-1&&index<size)
        {
            E temp = list[index];
            list[index]=element;
            return temp;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {

        return size==0;
    }


    @Override
    public void clear() {
        for (int i=0;i<size;i++){
            list[i]=null;
        }
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i=0;i<size;i++){
            if (Objects.equals(list[i], o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {

        if (index>-1&&index<size)
        {
            return list[index];
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o)!=-1;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int i=size-1;i>-1;i--){
            if (Objects.equals(o, list[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o:c){
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e:c){
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean f=false;
        if (index>-1&& index<size){
            for (E e:c){
                add(index, e);
                index++;
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(list[i])) {
                remove(i);
                i--;
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean f = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(list[i])) {
                remove(i);
                i--;
                f= true;
            }
        }
        return f;
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
