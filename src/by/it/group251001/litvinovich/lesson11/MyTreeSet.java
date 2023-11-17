package by.it.group251001.litvinovich.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private Object[] arr = new Object[0];
    private int size = 0;

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
            res.append(arr[i].toString()).append(", ");
        return res + arr[size - 1].toString() + "]";
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
        for (int i = 0; i < size; i++)
            if (arr[i].equals(o))
                return true;
        return false;
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
        int index = 0;
        while (index < size && ((Comparable<? super E>) arr[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < size && arr[index].equals(e))
            return false;
        if (arr.length == size)
            arr = Arrays.copyOf(arr, size * 2 + 1);
        size++;
        for (int i = size - 1; i > index; i--)
            arr[i] = arr[i - 1];
        arr[index] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        while (index < size && !arr[index].equals(o))
            index++;
        if (index == size)
            return false;
        for (int i = index; i < size() - 1; i++)
            arr[i] = arr[i + 1];
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean toReturn = false;
        for (E o : c)
            if (add(o))
                toReturn = true;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] u = new boolean[size];
        int kol = 0, cnt = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                u[i] = true;
                kol++;
            }
        if (kol == size)
            return false;
        Object[] newArr = new Object[kol];
        for (int i = 0; i < size; i++)
            if (u[i])
                newArr[cnt++] = arr[i];
        arr = newArr;
        size = kol;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] u = new boolean[size];
        int kol = 0, cnt = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                u[i] = true;
                kol++;
            }
        if (kol == 0)
            return false;
        Object[] newArr = new Object[size - kol];
        for (int i = 0; i < size; i++)
            if (!u[i])
                newArr[cnt++] = arr[i];
        arr = newArr;
        size = size - kol;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        arr = new Object[0];
    }
}
