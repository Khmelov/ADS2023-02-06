package by.it.group251003.nasevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {

    private E[] arr = (E[]) new Comparable[0];
    private int size = 0;

    private void resize(int newSize) {
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private int binSearch(E e) {

        int left = 0;
        int right = size - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (arr[mid].compareTo(e) == 0){
                return mid;
            }
            else if (arr[mid].compareTo(e) > 0) {
                right = mid - 1;
            }
            else
                left = mid + 1;

        }

        return -(left + 1);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("[");
        if (size > 0) {stringBuilder.append(arr[0]);
            for (int i = 1; i < size; i++) stringBuilder.append(", ").append(arr[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
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
        return binSearch((E)o) >= 0;
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

        int index = binSearch(e);
        if (index >= 0)
            return false;

        if (size == arr.length)
            resize(size);

        index = -(index + 1);
        System.arraycopy(arr, index, arr, index + 1, size++ - index);
        arr[index] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {

        int index = binSearch((E) o);
        if (index < 0)
            return false;

        remove(index);

        return true;
    }
    private void remove(int index) {

        System.arraycopy(arr, index + 1, arr, index, --size - index);
        arr[size] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element))
                return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        if (c.isEmpty())
            return false;

        int newSize = size + c.size();
        if (newSize > arr.length)
            resize(newSize);

        for (E element : c)
            add(element);

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;

        for (int i = 0; i < size; i++)
            if (!c.contains(arr[i])) {
                remove(i);
                i--;
            }

        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        int prevSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(arr[i])) {
                remove(i);
                i--;
            }
        }

        return prevSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            arr[i] = null;
        }
        size = 0;
    }
}