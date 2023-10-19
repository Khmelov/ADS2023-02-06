package by.it.group251002.sazonov.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    static private int minCapacity = 1;
    private int size = 0;
    private E[] arr = (E[]) new Object[minCapacity];

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void siftDown(int index) {
        int l = leftChild(index), r = rightChild(index);
        if (l >= size) {
            return ;
        }

        int goToChild = l;
        if (r < size && ((Comparable<E>)arr[r]).compareTo(arr[l]) < 0) {
            goToChild = r;
        }
        if (((Comparable<E>)arr[goToChild]).compareTo(arr[index]) >= 0) {
            return ;
        }

        E tmp = arr[goToChild];
        arr[goToChild] = arr[index];
        arr[index] = tmp;

        siftDown(goToChild);
    }


    private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parentInd = parent(index);
        if (((Comparable<E>)arr[parentInd]).compareTo(arr[index]) < 0) {
            return ;
        }

        E tmp = arr[parentInd];
        arr[parentInd] = arr[index];
        arr[index] = tmp;

        siftUp(parentInd);
    }

    private void heapRebuild() {
        for(int i = size - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void grow() {
        E[] NewArr = (E[]) new Comparable[size * 2];
        System.arraycopy(arr, 0, NewArr, 0, size);
        arr = NewArr;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size;  i++) {
            sb.append(delimiter).append(arr[i]);
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return true;
                }
            }
        }
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
    public <E> E[] toArray(E[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length) {
            grow();
        }

        if (isEmpty()) {
            arr[size] = e;
            size++;
            return true;
        }

        arr[size] = e;
        siftUp(size);
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    arr[i] = arr[--size];
                    siftDown(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    arr[i] = arr[--size];
                    siftDown(i);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] newArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if (!contains(newArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        boolean changed = false;
        E[] newArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add(newArr[i]);
            if (prevSize < size) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (c.contains(arr[i])) {
                System.arraycopy(arr, i + 1, arr, i, size-- - 1 - i);
                changed = true;
            } else {
                i++;
            }
        }

        if (changed) {
            heapRebuild();
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(arr[i])) {
                System.arraycopy(arr, i + 1, arr, i, size-- - 1 - i);
                changed = true;
            }
        }

        if (changed) {
            heapRebuild();
        }
        return changed;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == arr.length) {
            grow();
        }

        if (isEmpty()) {
            arr[size] = e;
            size++;
            return true;
        }

        arr[size] = e;
        siftUp(size);
        size++;

        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        E element = arr[0];

        size--;
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return element;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E element = (E) arr[0];

        size--;
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return element;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        }

        return arr[0];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return arr[0];
    }
}
