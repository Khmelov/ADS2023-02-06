package by.it.group251002.alesina.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

   E[] MyList;
   private int ListSize;
   private int Capacity;
    ListA(){
        ListSize=0;
        Capacity=10;
        MyList=(E[]) new Object[Capacity];
    }
    @Override
    public String toString() {
        String str=new String();
        str+="[";
        for(int i=0;i<ListSize-1;i++){
            str=str+this.MyList[i]+", ";
        }
        str=str+this.MyList[ListSize-1]+"]";

        return str;
    }

    @Override
    public boolean add(E e) {
        if (ListSize==Capacity){
            Capacity=(int)(Capacity*1.5);
            E[] MyList=(E[]) new Object[Capacity];
            System.arraycopy(this.MyList,0,MyList,0,ListSize);
            this.MyList=MyList;
        }
        this.MyList[ListSize]=e;
        ListSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        E Element=null;
        if(index<ListSize) {
            ListSize--;
            Element = this.MyList[index];
            E[] l=(E[]) new Object[ListSize-index];
            System.arraycopy(MyList,index+1,l,0,l.length);
            System.arraycopy(l,0,MyList,index,l.length);
            this.MyList[ListSize] = null;
        }
        return Element;
    }

    @Override
    public int size() {
        return ListSize;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

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
