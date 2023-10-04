package by.it.group251002.berezun.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {

    private E[] elems = (E[]) new Object[]{};
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
        String inBetweenElem = "";
        for(int i=0;i<size;i++) {
            sb.append(inBetweenElem).append(elems[i].toString());
            inBetweenElem=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elems.length){
            E[] newArr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elems,0,newArr,0,size);
            elems = newArr;
        }
        elems[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E deletedItem = elems[index];
        System.arraycopy(elems,index+1,elems,index,size-index-1);
        elems[--size]=null;
        return deletedItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if(size==elems.length){
            E[] newArr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elems,0,newArr,0,size);
            elems = newArr;
        }
        System.arraycopy(elems,index,elems,index+1,size-index);
        elems[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index>-1){
            remove(index);
        }
        return (index>-1);
    }

    @Override
    public E set(int index, E element) {
        E prevElem = elems[index];
        elems[index]=element;
        return prevElem;
    }


    @Override
    public boolean isEmpty() {
        return (size==0);
    }


    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            elems[i]=null;
        }
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        int index=-1;
        if (o==null){
            for (int i=0;i<size;i++){
                if (elems[i]==null){
                    return i;
                }
            }
        }
        else{
            for(int i=0;i<size;i++){
                if(o.equals(elems[i])){
                    return i;
                }
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
        int index = indexOf(o);
        return index>-1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(o==null){
            for(int i=size-1;i>=0;i--){
                if(elems[i]==null){
                    return i;
                }
            }
        }
        else{
            for(int i=size-1;i>=0;i--){
                if(elems[i].equals(o)){
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

