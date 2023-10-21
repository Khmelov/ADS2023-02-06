package by.it.group251001.zhidkov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private E[] array = (E[]) new Comparable[0];
    private int size;
    private int binarySearch(E key) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comp = array[mid].compareTo(key);

            if (comp < 0) {
                left = mid + 1;
            }
            else
            {
                if (comp > 0)
                {
                    right = mid - 1;
                }
                else
                {
                    return -(mid); // Элемент найден
                }
            }
        }
        return left + 1; // Элемент не найден
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                if (i != size - 1)
                    result.append(array[i]).append(", ");
                else
                    result.append(array[i]);
            }
        }
        result.append("]");
        return result.toString();
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
        if (size > 0) {
            return binarySearch((E) o) <= 0;
        }
        else
        {
            return false;
        }
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
        if (size == array.length)
        {
            E[] newArray = (E[]) new Comparable[size * 2 + 1];
            for (int i = 0; i < size; i++)
            {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        if (e != null && !isEmpty()) {
            int index = binarySearch(e);
            if (index <= 0) // Элемент уже есть в массиве
            {
                return false;
            }
            else
            {
                index -= 1;
                System.arraycopy(array, index, array, index + 1, size - index);
                array[index] = e;
            }
            size++;
            return true;
        }
        else
        {
            if (e == null)
            {
                return false;
            }
            else
            {
                array[0] = e;
                size++;
                return true;
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || isEmpty())
        {
            return false;
        }
        int index = -(binarySearch((E) o));
        if (index < 0)
        {
            return  false;
        }
        for (int i = index; i < size; i++)
        {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean b = true;
        for (Object o : c)
        {
            if (!contains(o))
            {
                b = false;
            }
        }
        return b;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean b = false;
        for (E e : c)
        {
            if (add(e))
            {
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }
        boolean b = false;
        int i = 0;
        while (i < size)
        {
            if (!c.contains(array[i]))
            {
                remove(array[i]);
                b = true;
                i--;
            }
            i++;
        }
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b = false;
        for (Object o : c)
        {
            if (remove(o))
            {
                b = true;
            }
        }
        return b;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
        {
            array[i] = null;
        }
        size = 0;
    }
}
