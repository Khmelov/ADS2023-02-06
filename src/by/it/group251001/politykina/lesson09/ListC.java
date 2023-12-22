package by.it.group251001.politykina.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size=0;

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
        for (int i = 0; i <size ; i++) {
            sb.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size==elements.length)
            elements = Arrays.copyOf(elements,(size*3)/2+1);
        elements[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index<size){
            E del = elements[index];
            System.arraycopy(elements,index+1,elements, index, size-index-1);
            size--;
            return del;
        }
        else  return null;
    }
    @Override
    public int size() {
        return size;
    }
    ///////////////////////////////////////////////////////
    @Override
    public void add(int index, E element) {
        if (size==elements.length)
            elements = Arrays.copyOf(elements,(size*3)/2+1);
        System.arraycopy(elements,index,elements, index+1, size-index);
        size++;
        elements[index]= element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index==-1) return false;
        else {
            remove(index);
            return true;
        }
    }

    @Override
    public E set(int index, E element) {
        E save = elements[index];
        elements[index]=element;
        return save;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        elements = (E[]) new Object[]{};
        size=0;
    }

    @Override
    public int indexOf(Object o) {
        if (o== null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        }else{
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index>=size) return null;
        else return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o)>-1) return true;
        else return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o== null) {
            for (int i = size-1; i >= 0; i--) {
                if (elements[i] == null) return i;
            }
        }else{
            for (int i = size-1; i >= 0; i--) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

/////////////////////////
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object elem : c){
            if(!contains(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        int i=0;
        for (E element : c) {
            add(index+i,element);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = false;
        for(int i =0; i < size; i++){
            if(c.contains(elements[i])){
                remove(i);
                f = true;
                i--;
            }
        }
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        int i = 0;
        while(i < size){
            if(!c.contains(elements[i])){
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
