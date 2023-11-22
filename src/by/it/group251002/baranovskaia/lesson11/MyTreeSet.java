package by.it.group251002.baranovskaia.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private E[] arr;

    private int size, capacity = 10;

    private void setCapacity(int oldCapacity) {
        capacity = oldCapacity * 2;
        arr = Arrays.copyOf(arr, capacity);
    }

    public MyTreeSet() {
        arr = (E[]) (new Object[capacity]);
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            res.append(arr[i].toString()).append(", ");
        }

        return res + arr[size - 1].toString() + ']';
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
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        int index = 0;
        while (index < size && ((Comparable<? super E>) arr[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < size && arr[index].equals(e))
            return false;
        if (size == capacity) {
            setCapacity(size + 1);
        }
        System.arraycopy(arr, index, arr, index + 1, (size++) - index);
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
        System.arraycopy(arr, index + 1, arr, index, (size--) - index - 1);
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
        boolean isAdded = false;
        for (E e : c)
            if (add(e))
                isAdded = true;
        return isAdded;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] isContained = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                isContained[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (isContained[i])
                newArr[index++] = arr[i];
        arr = newArr;
        size = count;
        capacity = size;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] isUnique = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (!c.contains(arr[i])) {
                isUnique[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (isUnique[i])
                newArr[index++] = arr[i];
        arr = newArr;
        size = count;
        capacity = size;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        arr = (E[]) (new Object[capacity]);
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
