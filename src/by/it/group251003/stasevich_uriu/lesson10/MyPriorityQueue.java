package by.it.group251003.stasevich_uriu.lesson10;


import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private int minCapacity = 8;
    private E[] arr = (E[]) new Object[minCapacity];
    int size = 0;

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int rightElement(int i) {
        return i * 2 + 2;
    }

    private int leftElement(int i) {
        return i * 2 + 1;
    }

    private void newSize() {
        E[] newArr = (E[]) new Object[size * 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private void siftDown(int i) {
        int left = leftElement(i);
        int right = rightElement(i);

        if (left >= size)
            return;

        int largest = left;
        if (right < size && ((Comparable<E>) arr[right]).compareTo(arr[left]) < 0)
            largest = right;

        if (((Comparable<E>) arr[largest]).compareTo(arr[i]) >= 0)
            return;

        E tmp = arr[largest];
        arr[largest] = arr[i];
        arr[i] = tmp;

        siftDown(largest);
    }

    private void siftUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = parent(i);
        if (((Comparable<E>) (arr[parent])).compareTo(arr[i]) < 0) {
            return;
        }

        E tmp = arr[parent];
        arr[parent] = arr[i];
        arr[i] = tmp;

        siftUp(parent);
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        String del = "";

        for (int i = 0; i < size; i++) {
            res.append(del).append(arr[i]);
            del = ", ";
        }

        return res.append("]").toString();
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length)
            newSize();

        arr[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (size == 0) {
            return null;
        }

        size--;
        E elementToDelete = arr[0];
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return elementToDelete;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i]))
                return true;
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        E buf = arr[0];
        size--;
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return buf;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;
        return arr[0];
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] arrCollection = (E[]) new Object[c.size()];
        arrCollection = c.toArray(arrCollection);

        for (E e : arrCollection)
            if (!contains(e))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] arrCollection = (E[]) c.toArray();
        for (E e : arrCollection)
            add(e);

        return prevSize < size;
    }

    public void heapify(){
        int start = size - 1;
        while(start >= 0){
            siftDown(start);
            start--;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                arr[i] = arr[--size];
                siftDown(i);
                return true;
            }
        }

        return false;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean check = false;
        int i = 0;
        while (i < size) {
            if (c.contains(arr[i])) {
                System.arraycopy(arr, i+1, arr, i, size-i-1);
                size--;
                check = true;
            } else {
                i++;
            }
        }
        heapify();

        return check;
    }

    public boolean retainAll(Collection<?> c) {
        boolean check = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(arr[i])) {
                System.arraycopy(arr, i+1, arr, i, size-i-1);
                size--;
                check = true;
            } else {
                i++;
            }
        }
        heapify();

        return check;
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
