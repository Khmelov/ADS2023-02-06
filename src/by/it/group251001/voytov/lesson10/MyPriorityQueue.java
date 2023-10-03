package by.it.group251001.voytov.lesson10;

import java.util.*;

public class MyPriorityQueue<T> implements Queue<T> {
    private Object[] queue;
    private int size;
    public MyPriorityQueue() {
        queue = new Object[10];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(queue[i]).append(", ");
        }

        return sb.append(queue[size - 1]).append("]").toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            queue[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(T t) {
        return offer(t);
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return poll();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean offer(T t) {

        if (t == null) {
            throw new NullPointerException();
        }

        if (size == queue.length) {
            grow();
        }

        this.siftUp(size, t);
        size++;

        return true;
    }

    @Override
    public T poll() {
        T element = (T) queue[0];
        if (element != null) {
            size--;
            T last = (T) queue[size];
            queue[size] = null;
            if (size > 0) {
                siftDown(0, last);
            }
        }
        return element;
    }

    @Override
    public T peek() {
        return (T) queue[0];
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return (T) queue[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(queue[i])) {
                remove(queue[i]);
                modified = true;
            }
        }
        return modified;
    }

    private void grow() {
        queue = Arrays.copyOf(queue, queue.length * 3 / 2);
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(queue[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void siftUp(int index, T item) {

        Comparable<? super T> comparable = (Comparable<? super T>) item;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Object parent = queue[parentIndex];
            if (comparable.compareTo((T) parent) >= 0) {
                break;
            }

            queue[index] = parent;
            index = parentIndex;
        }

        queue[index] = item;
    }

    private void siftDown(int index, T item) {

        Comparable<? super T> comparable = (Comparable<? super T>) item;
        while (index < size / 2) {
            int childIndex = 2 * index + 1;
            Object leastChild = queue[childIndex];

            if (childIndex + 1 < size &&
                    ((Comparable<? super T>) queue[childIndex]).compareTo((T) queue[childIndex + 1]) > 0) {
                childIndex = childIndex + 1;
                leastChild = queue[childIndex];
            }

            if (comparable.compareTo((T) leastChild) <= 0) {
                break;
            }

            queue[index] = leastChild;
            index = childIndex;
        }

        queue[index] = item;

    }

    //————————————————————

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        size--;
        T last = (T) queue[size];
        queue[size] = null;
        if (size > 0) {
            siftDown(index, last);
        }

        return true;
    }
}
