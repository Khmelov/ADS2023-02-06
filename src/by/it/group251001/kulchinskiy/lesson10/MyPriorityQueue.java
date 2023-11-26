package by.it.group251001.kulchinskiy.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    private E[] array;
    private int m_size;
    private int m_capacity;

    public MyPriorityQueue() {
        array = (E[]) new Object[8];
        m_size = 0;
        m_capacity = 8;
    }

    @Override
    public String toString() {
        if (m_size == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < m_size; i++) {
            result.append(array[i]);
            if (i < m_size - 1) {
                result.append(", ");
            } else {
                result.append("]");

                return result.toString();
            }
        }

        return "";
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < m_size; i++) {
            if (array[i].equals(o)) {
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
        if (m_size == m_capacity) {
            E[] newArray = (E[]) new Object[m_capacity * 2];
            for (int i = 0; i < m_size; i++) {
                newArray[i] = array[i];
            }

            array = newArray;
            m_capacity *= 2;
        }

        array[m_size] = e;
        m_size++;

        int i = m_size - 1;
        int j = (i - 1) / 2;
        while (i > 0 && ((Comparable<? super E>) array[i]).compareTo(array[j]) < 0) {
            E temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            i = j;
            j = (i - 1) / 2;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < m_size; i++) {
            if (array[i].equals(o)) {
                for (int j = i; j < m_size - 1; j++) {
                    array[j] = array[j + 1];
                }

                m_size--;

                for (int j = m_size / 2 - 1; j >= 0; j--) {
                    heapify(i);
                }

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
        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        var newSize = 0;
        m_capacity = m_size;
        var tempArr = (E[]) new Object[m_size];
        for (int i = 0; i < m_size; i++) {
            if (!c.contains(array[i])) {
                tempArr[newSize] = array[i];
                newSize++;
            }
        }

        boolean modified = newSize != m_size;
        m_size = newSize;
        array = tempArr;

        for (int i = m_size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        var newSize = 0;
        m_capacity = m_size;
        var tempArr = (E[]) new Object[m_size];
        for (int i = 0; i < m_size; i++) {
            if (c.contains(array[i])) {
                tempArr[newSize] = array[i];
                newSize++;
            }
        }

        boolean modified = newSize != m_size;
        m_size = newSize;
        array = tempArr;

        for (int i = m_size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }

        return modified;
    }

    @Override
    public void clear() {
        m_size = 0;
        array = (E[]) new Object[8];
        m_capacity = 8;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        if (m_size == 0) {
            return null;
        }

        m_size--;

        var result = array[0];
        var temp = array[m_size];
        array[m_size] = array[0];
        array[0] = temp;

        heapify(0);

        return result;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return array[0];
    }

    @Override
    public E peek() {
        return array[0];
    }

    private void heapify(int i) {
        while (true) {
            int leftChild = 2 * i + 1;
            int rightChild = leftChild + 1;
            int largestChild = i;

            if (leftChild < m_size && ((Comparable<? super E>) array[leftChild]).compareTo(array[largestChild]) < 0)
                largestChild = leftChild;

            if (rightChild < m_size && ((Comparable<? super E>) array[rightChild]).compareTo(array[largestChild]) < 0)
                largestChild = rightChild;

            if (largestChild == i)
                break;

            var temp = array[i];
            array[i] = array[largestChild];
            array[largestChild] = temp;

            i = largestChild;
        }
    }
}
