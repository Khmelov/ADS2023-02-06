package by.it.group251001.politykina.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private static int DEFAULT_CAPACITY = 10;

    transient E[] elements;

    private int size=0;
    private int capacity = DEFAULT_CAPACITY;
    private void grow(){
        capacity = 2 * capacity;
        elements = Arrays.copyOf(elements, capacity);
    }
    public MyTreeSet() {
        elements = (E[]) (new Object[capacity]);
        size = 0;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (size==0) return "[]";
        s.append("[");
        for (int i = 0; i < size-1; i++) {
            s.append(elements[i]+", ");
        }
        s.append(elements[size-1]+"]");
        return s.toString();
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size ==0;
    }
    @Override
    public void clear() {
        size=0;
        elements = (E[]) (new Object[capacity]);
    }
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean add(E e) {
        int index = 0;
        while (index < size && ((Comparable<? super E>) elements[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < size && elements[index].equals(e))
            return false;
        if (size == capacity) 
            grow();
        System.arraycopy(elements, index, elements, index + 1, (size++) - index);
        elements[index] = e;
        return true;
    }
    @Override
    public boolean remove(Object o) {
        int index = 0;
        while (index < size && !elements[index].equals(o))
            index++;
        if (index == size)
            return false;
        System.arraycopy(elements, index + 1, elements, index, (size--) - index - 1);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int oldSize = size;
        for (E e : collection) {
            add(e);
        }
        return size!=oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        E[] newArr = (E[]) (new Object[capacity]);
        int newSize=0;
        for (int i = 0; i < size; i++)
            if (collection.contains(elements[i])) {
                newArr[newSize++]=elements[i];
            }
        boolean res = size != newSize;
        size=newSize;
        elements=newArr;
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        E[] newArr = (E[]) (new Object[capacity]);
        int newSize=0;
        for (int i = 0; i < size; i++)
            if (!collection.contains(elements[i])) {
                newArr[newSize++]=elements[i];
            }
        boolean res = size != newSize;
        size=newSize;
        elements=newArr;
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

}
