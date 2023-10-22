package by.it.group251001.voytov.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 10;

    transient E[] elementData;

    private int size, capacity = DEFAULT_CAPACITY;

    private int newCapacity(int oldCapacity) {
        capacity = oldCapacity + (oldCapacity >> 1);
        return capacity;
    }

    private void grow(int oldCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity(oldCapacity));
    }

    public MyTreeSet() {
        elementData = (E[]) (new Object[capacity]);
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            res.append(elementData[i].toString()).append(", ");
        }

        return res + elementData[size - 1].toString() + ']';
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
            if (elementData[i].equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        int index = 0;
        while (index < size && ((Comparable<? super E>) elementData[index]).compareTo(e) < 0)
            index++;
        if (!isEmpty() && index < size && elementData[index].equals(e))
            return false;
        if (size == capacity) {
            grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, (size++) - index);
        elementData[index] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        while (index < size && !elementData[index].equals(o))
            index++;
        if (index == size)
            return false;
        E tmp = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, (size--) - index - 1);
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
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] used = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(elementData[i])) {
                used[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (used[i])
                newArr[index++] = elementData[i];
        elementData = newArr;
        size = count;
        capacity = size;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] used = new boolean[size];
        int count = 0, index = 0;
        for (int i = 0; i < size; i++)
            if (!c.contains(elementData[i])) {
                used[i] = true;
                count++;
            }
        if (count == size)
            return false;
        E[] newArr = (E[]) (new Object[count]);
        for (int i = 0; i < size; i++)
            if (used[i])
                newArr[index++] = elementData[i];
        elementData = newArr;
        size = count;
        capacity = size;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        elementData = (E[]) (new Object[capacity]);
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
