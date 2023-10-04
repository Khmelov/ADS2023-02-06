package by.it.group251002.alesina.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    E[] MyList;
    private int ListSize;
    private int capacity;
    ListB(){
        ListSize=0;
        capacity=10;
        MyList=(E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        String str=new String();
        if(this.isEmpty()){
            str="[]";
        }
        else{
            str+="[";
            for(int i=0;i<ListSize-1;i++)
                str=str+this.MyList[i]+", ";
            str+=this.MyList[ListSize-1]+"]";
        }
        return str;


    }

    @Override
    public boolean add(E e) {
        if(ListSize==capacity){
            capacity=(int)(capacity*1.5);
            E[] MyList=(E[]) new Object[capacity];
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

    @Override
    public void add(int index, E element) {
        while(ListSize==capacity || index>=capacity ){
            capacity=(int)(capacity*1.5);
            E[] MyList1=(E[]) new Object[capacity];
            System.arraycopy(this.MyList,0,MyList1,0,ListSize);
            this.MyList=MyList1;
        }
        System.arraycopy(MyList,index,MyList,index+1,ListSize-index);
        MyList[index]=element;
        ListSize++;
    }

    @Override
    public boolean remove(Object o) {

        boolean IsRemoved=false;
        if (this.contains(o)){
            this.remove(this.indexOf(o));
            IsRemoved=true;
        }
        return IsRemoved;
    }

    @Override
    public E set(int index, E element) {

        E Elem = MyList[index];
        MyList[index]=element;
        return Elem;
    }


    @Override
    public boolean isEmpty() {

        if (ListSize==0){
            return true;
        }
        return false;

    }


    @Override
    public void clear() {
        for(int i=0;i<ListSize;i++){
            MyList[i]=null;
        }
        ListSize=0;
    }

    @Override
    public int indexOf(Object o) {
        for(int i=0;i<ListSize;i++){
            if(MyList[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index<ListSize && index>=0)
            return MyList[index];
        return null;

    }

    @Override
    public boolean contains(Object o) {

        boolean IsInList=false;
        for(int i=0;i<ListSize;i++){
            if(MyList[i].equals(o)){
                IsInList=true;
            }
        }
        return IsInList;
    }

    @Override
    public int lastIndexOf(Object o) {
        int LastInd=-1;
        for(int i=0; i<ListSize;i++){
            if(MyList[i].equals(o)){
                LastInd=i;
            }
        }
        return LastInd;
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
