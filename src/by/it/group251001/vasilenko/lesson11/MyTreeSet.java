package by.it.group251001.vasilenko.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    E[] elements;

    private int size;

    public MyTreeSet() {
        elements = (E[]) (new Object[0]);
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            res.append(elements[i].toString()).append(", ");
        }

        return res + elements[size - 1].toString() + ']';
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
            int result = -1;
            int l = 0;
            int r = size - 1;
            boolean fl = false;
            while ((l <= r) && (!fl)) {
                int c = (l + r) / 2;
                if (((Comparable<? super E>) elements[c]).compareTo((E)o) > 0) {
                    r = c - 1;
                } else {
                    l = c + 1;
                    if (elements[c].equals(o)) {
                        fl = true;
                        result =  c;
                    }
                }
            }
            if (result == -1){
                return false ;
            }
            else {
                return true;
            }
    }

    @Override
    public boolean add(E e) {
        int index = 0;
        while (index < size && ((Comparable<? super E>) elements[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < size && elements[index].equals(e))
            return false;
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length*3/2+1);
        }
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
        E tmp = elements[index];
        System.arraycopy(elements, index + 1, elements, index, (size--) - index - 1);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean mod = false;
        for (E e : c)
            if (add(e))
                mod = true;
        return mod;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] used = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(elements[i])) {
                used[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (used[i])
                newArr[index++] = elements[i];
        elements = newArr;
        size = count;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] used = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (!c.contains(elements[i])) {
                used[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (used[i])
                newArr[index++] = elements[i];
        elements = newArr;
        size = count;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        elements= (E[]) (new Object[0]);
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
}

