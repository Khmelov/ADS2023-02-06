package by.it.group251001.besedinAndrei.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private Object[] array = new Object[0];
    private int actSize = 0;

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < actSize - 1; i++) {
            res.append(array[i].toString()).append(", ");
        }

        return res + array[actSize - 1].toString() + "]";
    }

    @Override
    public int size() {
        return actSize;
    }

    @Override
    public void clear() {
        actSize = 0;
        array = new Object[0];
    }

    @Override
    public boolean isEmpty() {
        return actSize == 0;
    }

    @Override
    public boolean add(E e) {
        int index = 0;

        while (index < actSize && ((Comparable<? super E>) array[index]).compareTo(e) < 0) {
            index++;
        }

        if (!isEmpty() && index < actSize && array[index].equals(e)) {
            return false;
        }

        if (array.length == actSize) {
            array = Arrays.copyOf(array, actSize * 2 + 1);
        }

        actSize++;

        for (int i = actSize - 1; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        while (index < actSize && !array[index].equals(o))
            index++;
        if (index == actSize)
            return false;
        for (int i = index; i < size() - 1; i++)
            array[i] = array[i + 1];
        actSize--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < actSize; i++)
            if (array[i].equals(o)) {
                return true;
            }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E o : c) {
            if (add(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] u = new boolean[actSize];
        int amount = 0, cnt = 0;

        for (int i = 0; i < actSize; i++) {
            if (c.contains(array[i])) {
                u[i] = true;
                amount++;
            }
        }
            

        if (amount == 0) return false;
            

        Object[] newArr = new Object[actSize - amount];

        for (int i = 0; i < actSize; i++) {
            if (!u[i]) {
                newArr[cnt++] = array[i];
            }
        }

        array = newArr;
        actSize = actSize - amount;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] u = new boolean[actSize];
        int kol = 0, cnt = 0;
        for (int i = 0; i < actSize; i++) {
            if (c.contains(array[i])) {
                u[i] = true;
                kol++;
            }
        }
        if (kol == actSize)
            return false;
        Object[] newArr = new Object[kol];
        for (int i = 0; i < actSize; i++)
            if (u[i])
                newArr[cnt++] = array[i];
        array = newArr;
        actSize = kol;
        return true;
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
