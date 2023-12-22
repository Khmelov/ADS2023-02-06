package by.it.group251003.kukhotskovolets.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private E[] arr = (E[]) new Comparable[0];
    private int size = 0;
    private void resize() {
        resize(arr.length);
    }
    private void resize(int newSize) {
        E[] newArr = (E[]) new Comparable[newSize * 3 / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private int binarySearch(E e) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = arr[mid].compareTo(e);

            if (comparison > 0)
                right = mid - 1;
            else if (comparison < 0)
                left = mid + 1;
            else
                return mid;
        }

        return - (left + 1);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (size > 0) {
            result.append(arr[0]);

            for (int i = 1; i < size; i++)
                result.append(", ").append(arr[i]);
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
        return binarySearch((E)o) >= 0;
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
        int index = binarySearch(e);
        if (index >= 0)
            return false;

        if (size == arr.length)
            resize();

        int insertionIndex = - (index + 1);
        System.arraycopy(arr, insertionIndex, arr, insertionIndex + 1, size++ - insertionIndex);
        arr[insertionIndex] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = binarySearch((E) o);
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
        for (Object element : c)
            if (!contains(element))
                return false;

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

        for (int i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                remove(i);
                i--;
            }

        return prevSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }
}































//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
//
//    private E[] elems = (E[]) new Comparable[0];
//    private int size = 0;
//
//
//
//    private void resize(){
//        E[] newElems = (E[]) new Object[(elems.length * 3) / 2 + 1];
//        System.arraycopy(elems, 0, newElems, 0, size);
//        elems = newElems;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("[");
//        String delimiter = "";
//        if (size > 0){
//            for (int i = 0; i < size; i++) {
//                sb.append(delimiter).append(elems[i]);
//                delimiter = ", ";
//            }
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//        return null;
//    }
//
//    @Override
//    public boolean add(E e) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//        System.arraycopy(elems, 0, elems, 0, 0);
//    }
//}
