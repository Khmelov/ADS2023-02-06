package by.it.group251002.klimovich.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private  E[] elems= (E[]) new Object[]{};
    private int size=0;
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String sep ="";
        for (int i=0;i < size; i++){
            sb.append(sep).append(elems[i]);
            sep=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size==elems.length){
            E[] arr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elems,0,arr,0,size);
            elems=arr;
        }
        elems[size]=e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E elem= elems[index];
        System.arraycopy(elems,index+1,elems,index,size-index-1);
        size--;
        elems[size]=null;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size==elems.length){
            E[] arr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elems,0,arr,0,size);
            elems=arr;
        }
        System.arraycopy(elems,index,elems,index+1,size-index);
        elems[index]=element;
        size++;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elems[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elems[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i==-1){
            return false;
        }
        else {
            remove(i);
            return true;
        }
    }

    @Override
    public E set(int index, E element) {
        E elem = elems[index];
        elems[index]=element;
        return elem;
    }


    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        elems = null;
        elems = (E[]) new Object[]{};
        size=0;
    }

    @Override
    public E get(int index) {
        return elems[index];
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o)==-1){
            return false;
        }
        return true;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index=-1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elems[i] == null) {
                    index=i;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elems[i])) {
                    index=i;
                }
            }
        }
        return index;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            if  (!contains(arr[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            add(arr[i]);
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=0;i<c.size();i++){
            add(index+i,arr[i]);
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize=size;
        E[] arr = (E[]) new Object[c.size()];
        arr = c.toArray(arr);
        for (int i=c.size()-1;i>-1;i--){
            if  (contains(arr[i])){
                while (indexOf(arr[i])!=-1){
                    remove(arr[i]);
                }
            }
        }
        if (prevSize!=size){
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize=size;
        for (int i=size-1;i>-1;i--){
            if  (!c.contains(elems[i])){
                remove(elems[i]);
            }
        }
        if (prevSize!=size){
            return true;
        }
        return false;
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
