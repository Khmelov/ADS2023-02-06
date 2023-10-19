package by.it.group251002.belyatskiy.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    private E[] elements= (E[]) new Object[]{};
    private int size=0;
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        String delimiter="";
        for (int i=0;i<size;i++){
            sb.append(delimiter).append(elements[i]);
            delimiter=", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size==elements.length)
            elements= Arrays.copyOf(elements, (size * 3)/2+1);
        elements[size++]=e;
        return true;
    }

    @Override
    public E remove(int index) {
        E del=elements[index];
        System.arraycopy(elements,index+1,elements,index,size-1-index);
        size--;
        return del;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size==elements.length)
            elements= Arrays.copyOf(elements, (size * 3)/2+1);
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index]=element;
        size++;
    }

    @Override
    public boolean remove(Object Obj) {
        int index = indexOf(Obj);
        if (index > -1)
            remove(index);
        return (index > -1);
    }

    @Override
    public E set(int index, E element) {
        if (index>-1 && size>index) {
            E el = elements[index];
            elements[index] = element;
            return el;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return (size==0);
    }


    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            elements[i]=null;
        }
        size=0;
    }

    @Override
    public int indexOf(Object Obj) {
        if (Obj == null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null)
                    return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (Obj.equals(elements[i]))
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
    public boolean contains(Object Obj) {
        for(int i=0;i<size;i++){
            if(elements[i].equals(Obj)){
                return true;
            }
        }
        return false;
    }

    //Последнее вхождение элемента в список
    @Override
    public int lastIndexOf(Object Obj) {
        int lastIndex=-1;
        for(int i=0;i<size;i++){
            if(elements[i].equals(Obj)){
                lastIndex=i;
            }
        }
        return lastIndex;
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
