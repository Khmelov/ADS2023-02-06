package by.it.group251003.snopko.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {
    private E[] data;
    private int size;

    private int BinarySearch(int L, int R, Object Key){
        while (L <= R) {
            int mid = (L + R) /2;
            Comparable midValue = (Comparable) data[mid];
            int res = midValue.compareTo(Key);

            if (res < 0)
                L = mid + 1;
            else if (res > 0)
                R = mid - 1;
            else
                return mid;
        }
        return -1 * L - 1;

    }

    MyTreeSet(){
        data = (E[]) new Object[10];
        size = 0;
    }

    private  void grow(){
        int NewSize = size * 2 + 1;
        E[] newarray = (E[]) new Object[NewSize];
        if (size >= 0) System.arraycopy(data, 0, newarray, 0, size);
        data = newarray;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimeter = "";
        for (int i = 0; i < size; i++) {
            sb.append(delimeter).append(data[i]);
            delimeter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0){return false;}
        int index = BinarySearch(0,  size - 1, o);
        if (index < 0) {index *= -1;index--;}
        return data[index] == o;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean add(E e) {
        int index;
        if (size == 0)
            index = -1;
        else
             index = BinarySearch(0, size - 1, e);
        if (index >= 0) {return false;}
        if (index < 0) {index *= -1;index--;}
        if (size == data.length){
            grow();
        }
        System.arraycopy(data,index, data, index + 1,  Math.max(0,size - index));
        data[index] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {return false;}
        int index = BinarySearch(0, size - 1, o);
        if (index < 0) {return false;}
            System.arraycopy(data, index + 1, data, index,  Math.max(0,size - index - 1));
            size--;
            data[size] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object Iter: c){
            if(!contains(Iter)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int PrevSize = size;
        for (Object Iter: c){
            add((E)Iter);
        }
        return PrevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int PrevSize = size;
        for (int i = size - 1; i >= 0; i--){
            if (!c.contains(data[i])){
                remove(data[i]);
            }
        }
        return PrevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int PrevSize = size;
        for (int i = size - 1; i >= 0; i--){
            if (c.contains(data[i])){
                remove(data[i]);
            }
        }
        return PrevSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            data[i] = null;
        }
        size = 0;
    }
}
