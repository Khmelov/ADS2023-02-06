package by.it.group251002.filistovich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private E[] elements;
    private int size;

    MyTreeSet(){
        size = 0;
        elements= (E[]) new Comparable[5];
    }

    private int BinSearch(int Left, int Right, Object Key){
        while (Left <= Right) {
            int middle = (Left + Right) /2;
            Comparable midVal = (Comparable) elements[middle];
            int result = midVal.compareTo(Key);

            if (result < 0)
                Left = middle + 1;
            else if (result > 0)
                Right = middle - 1;
            else
                return middle;
        }
        return -1 * Left - 1;

    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++){
            sb.append(delimiter).append(elements[i]);
            delimiter = ", ";
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
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return (BinSearch(0, size - 1, o) >= 0);
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
        if (size == elements.length){
            E[] newArr = (E[]) new Object[(size * 3 )/ 2 + 1];
            System.arraycopy(elements, 0,newArr,0,size);
            elements = newArr;
        }
        int index;
        if (size == 0){
            index = -1;
        } else {
            index = BinSearch(0, size - 1, e);

        }
        if (index >= 0){
            return false;
        } else {
            index *= -1;
            index--;
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = BinSearch(0, size - 1, o);
        if (index < 0){
            return false;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            size--;
            elements[size] = null;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object it:c){
            if (!contains(it)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevsize = size;
        for (Object it:c){
            add((E)it);
        }
        return (prevsize != size);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevsize = size;
        for (int i = size - 1; i > -1; i--){
            if (!c.contains(elements[i])){
                remove(elements[i]);
            }
        }
        return (prevsize != size);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevsize = size;
        for (Object it:c){
            if (contains(it)){
                remove(it);
            }
        }
        return (prevsize != size);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;

    }
}
