package by.it.group251001.pavlkrat.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private Object[] arr = new Object[0];
    private int siz = 0;

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < siz - 1; i++)
            res.append(arr[i].toString()).append(", ");
        return res + arr[siz - 1].toString() + "]";
    }

    @Override
    public int size() {
        return siz;
    }

    @Override
    public boolean isEmpty() {
        return siz == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < siz; i++)
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
        while (index < siz && ((Comparable<? super E>) arr[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < siz && arr[index].equals(e))
            return false;
        if (arr.length == siz)
            arr = Arrays.copyOf(arr, siz * 2 + 1);
        siz++;
        for (int i = siz - 1; i > index; i--)
            arr[i] = arr[i - 1];
        arr[index] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        while (index < siz && !arr[index].equals(o))
            index++;
        if (index == siz)
            return false;
        for (int i = index; i < size() - 1; i++)
            arr[i] = arr[i + 1];
        siz--;
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
        boolean toRet = false;
        for (E o : c)
            if (add(o))
                toRet = true;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] u = new boolean[siz];
        int kol = 0, cnt = 0;
        for (int i = 0; i < siz; i++)
            if (c.contains(arr[i])) {
                u[i] = true;
                kol++;
            }
        if (kol == siz)
            return false;
        Object[] newArr = new Object[kol];
        for (int i = 0; i < siz; i++)
            if (u[i])
                newArr[cnt++] = arr[i];
        arr = newArr;
        siz = kol;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] u = new boolean[siz];
        int kol = 0, cnt = 0;
        for (int i = 0; i < siz; i++)
            if (c.contains(arr[i])) {
                u[i] = true;
                kol++;
            }
        if (kol == 0)
            return false;
        Object[] newArr = new Object[siz - kol];
        for (int i = 0; i < siz; i++)
            if (!u[i])
                newArr[cnt++] = arr[i];
        arr = newArr;
        siz = siz - kol;
        return true;
    }

    @Override
    public void clear() {
        siz = 0;
        arr = new Object[0];
    }
}
