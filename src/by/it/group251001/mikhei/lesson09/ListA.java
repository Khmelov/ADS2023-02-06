package by.it.group251001.mikhei.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {
    private Object[] data = new Object[0];

    private int size;

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for(int i = 0; i < size; i++){
            sb.append(data[i] == this ? "(this Collection)" : data[i]);

            if(i + 1 == size)
                return sb.append(']').toString();

            sb.append(',').append(' ');
        }

        return "";
    }

    @Override
    public boolean add(E e) {
        growIfNeeded(size + 1);

        data[size++] = e;

        return true;
    }

    private void growIfNeeded(int newSize) {
        if (newSize < data.length) return;

        Object[] newData;
        if(newSize > data.length * 3 / 2 + 1){
            newData = new Object[newSize];
        }else{
            newData = new Object[data.length * 3 / 2 + 1];
        }

        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    @Override
    public E remove(int index) {
        E oldValue = (E) data[index];

        data[index] = null;

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;

        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        growIfNeeded(size + 1);

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;

        size++;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if(i == -1) return false;
        remove(i);
        return true;
    }

    @Override
    public E set(int index, E element) {
        E oldValue = (E) data[index];

        data[index] = element;

        return oldValue;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i]))
                return i;

        }

        return -1;
    }

    @Override
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, data[i]))
                return i;

        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(var o: c){
            if(!contains(o)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.isEmpty()) return false;

        growIfNeeded(size + c.size());
        for(var e: c){
            data[size++] = e;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.isEmpty()) return false;

        growIfNeeded(size + c.size());
        size += c.size();
        for(int i = size - 1; i - c.size() >= index; i--){
            data[i] = data[i - c.size()];
        }

        for(var e: c){
            data[index++] = e;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for(var e: c){
            while(remove(e)) changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for(int i = size - 1; i >= 0; i--){
            if(!c.contains(data[i])) {
                remove(i);
                changed = true;
            }
        }
        return changed;
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
        return Arrays.copyOf(data, size);
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }


    private class Itr implements Iterator<E> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            return (E) data[index++];
        }
    }

}
