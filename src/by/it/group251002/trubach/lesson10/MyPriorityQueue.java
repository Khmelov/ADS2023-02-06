package by.it.group251002.trubach.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    private E[] ELEMENTS = (E[]) new Object[]{};
    private int SIZE_OF_ELEMENTS = 0;

    private void resizeArr() {
        E[] newArr = (E[]) new Object[SIZE_OF_ELEMENTS * 3 / 2 + 1];
        System.arraycopy(ELEMENTS, 0, newArr, 0, SIZE_OF_ELEMENTS);
        ELEMENTS = newArr;
    }

    private boolean compare(E elem1, E elem2) {
        return ((Comparable<E>) elem1).compareTo(elem2) <= 0;
    }

    private void swap(int i1, int i2) {
        E temp = ELEMENTS[i1];
        ELEMENTS[i1] = ELEMENTS[i2];
        ELEMENTS[i2] = temp;
    }

    private void siftUp(int index) {
        while (index != 0 && !compare(ELEMENTS[(index - 1) >> 1], ELEMENTS[index])) {
            swap(index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    private void heapify() {
        int i = (SIZE_OF_ELEMENTS - 1) / 2;
        while (i >= 0) {
            siftDown(i);
            i--;
        }
    }

    private int leftChild(int value) {
        return value * 2 + 1;
    }

    private int rightChild(int value) {
        return (value + 1) * 2;
    }

    private void siftDown(int index) {
        int lastIndex = SIZE_OF_ELEMENTS - 1;
        int minChild, tempIndex;
        // while children exist
        while (leftChild(index) <= lastIndex) {
            minChild = leftChild(index);
            tempIndex = rightChild(index);
            if (tempIndex <= lastIndex) {
                if (!compare(ELEMENTS[minChild], ELEMENTS[tempIndex])) {
                    minChild = tempIndex;
                }
            }
            if (compare(ELEMENTS[index], ELEMENTS[minChild])) {
                return;
            } else {
                swap(index, minChild);
                index = minChild;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < SIZE_OF_ELEMENTS; i++) {
            res.append(delimiter).append(ELEMENTS[i]);
            delimiter = ", ";
        }

        res.append("]");
        return res.toString();
    }

    @Override
    public int size() {
        return SIZE_OF_ELEMENTS;
    }

    @Override
    public boolean isEmpty() {
        return SIZE_OF_ELEMENTS == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < SIZE_OF_ELEMENTS; i++) {
            if (ELEMENTS[i].equals(o)) {
                return true;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (SIZE_OF_ELEMENTS == ELEMENTS.length) {
            resizeArr();
        }
        ELEMENTS[SIZE_OF_ELEMENTS] = e;
        siftUp(SIZE_OF_ELEMENTS);
        SIZE_OF_ELEMENTS++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < SIZE_OF_ELEMENTS; i++) {
            if (ELEMENTS[i].equals(o)) {
                swap(i, SIZE_OF_ELEMENTS - 1);
                SIZE_OF_ELEMENTS--;
                siftDown(i);
                return true;
            }
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
        int prevsize = SIZE_OF_ELEMENTS;
        for (E o : c) {
            add(o);
        }
        return prevsize != SIZE_OF_ELEMENTS;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevsize = SIZE_OF_ELEMENTS;
        int i = 0, j = 0;
        while (i != prevsize) {
            if (!c.contains(ELEMENTS[i])) {
                ELEMENTS[j++] = ELEMENTS[i];
            }
            i++;
        }
        SIZE_OF_ELEMENTS = j;
        heapify();
        return prevsize != SIZE_OF_ELEMENTS;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = SIZE_OF_ELEMENTS, j = 0, i = 0;
        while (i != prevSize) {
            if (c.contains(ELEMENTS[i])) {
                ELEMENTS[j++] = ELEMENTS[i];
            }
            i++;
        }
        SIZE_OF_ELEMENTS = j;
        heapify();
        return prevSize != SIZE_OF_ELEMENTS;
    }

    @Override
    public void clear() {
        for (int i = 0; i < SIZE_OF_ELEMENTS; i++) {
            ELEMENTS[i] = null;
        }
        SIZE_OF_ELEMENTS = 0;
    }

    @Override
    public boolean offer(E e) {
        if (SIZE_OF_ELEMENTS == ELEMENTS.length) {
            resizeArr();
        }
        ELEMENTS[SIZE_OF_ELEMENTS] = e;
        siftUp(SIZE_OF_ELEMENTS);
        SIZE_OF_ELEMENTS++;
        return true;
    }

    @Override
    public E remove() {
        if (SIZE_OF_ELEMENTS == 0) {
            return null;
        }
        E elem = ELEMENTS[0];
        swap(0, SIZE_OF_ELEMENTS - 1);
        SIZE_OF_ELEMENTS--;
        siftDown(0);
        return elem;
    }

    @Override
    public E poll() {
        if (SIZE_OF_ELEMENTS == 0) {
            return null;
        }
        E elem = ELEMENTS[0];
        swap(0, SIZE_OF_ELEMENTS - 1);
        SIZE_OF_ELEMENTS--;
        siftDown(0);
        return elem;
    }

    @Override
    public E element() {
        return ELEMENTS[0];
    }

    @Override
    public E peek() {
        return ELEMENTS[0];
    }
}
