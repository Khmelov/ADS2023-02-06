package by.it.group251001.lashkin.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

//        toString()
//        size()
//        clear()
//        add(E element)
//        remove()
//        contains(E element)
//
//        offer(E element)
//        poll()
//        peek()
//        element()
//        isEmpty()
//
//        containsAll(Collection<E> c)
//        addAll(Collection<E> c)
//        removeAll(Collection<E> c)
//        retainAll(Collection<E> c)

public class MyPriorityQueue<E> implements Queue<E> {
    int size;
    E[] data = (E[]) new Object[0];

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s = s + data[i];
            if (i < size - 1) {
                s = s + ", ";
            }
        }
        s = s + "]";
        return s;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public E remove() {
        if (size == 0)
            return null;
        return poll();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean offer(E e) {
        if (size == data.length) {
            E []temp = (E[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        data[size] = e;
        var tsize = size;
        var item = (Comparable<? super E>) data[tsize];
        int prev = (tsize - 1) / 2;

        while (tsize > 0 && item.compareTo(data[prev]) < 0) {
            swap(tsize, prev);
            tsize = prev;
            prev = (tsize - 1) / 2;
        }
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;
        E element = data[0];
        data[0] = data[size - 1];
        data[--size] = null;
        var index = 0;
        var item = (Comparable<? super E>) data[0];
        int left = 2 * index + 1;

        while (left < size) {
            int right = left + 1;
            int child = left;

            if (right < size && ((Comparable<? super E>)data[right]).compareTo(data[left]) < 0) {
                child = right;
            }

            if (item.compareTo(data[child]) <= 0) {
                break;
            }

            swap(index, child);
            index = child;
            left = 2 * index + 1;
        }
        return element;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;
        return data[0];
    }

    @Override
    public E element() {
        if (size == 0)
            return null;
        return data[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] cArray = c.toArray();
        for (Object o : cArray) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E []cArray = (E[]) c.toArray();
        if (cArray.length == 0)
            return false;
        for (E e : cArray) {
            offer(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;
        while (i <size && !c.contains(data[i])) {
            i++;
        }
        if (i == size)
            return false;

        int finish = size;
        int start = i;
        int[] toSave = new int[finish - start];

        for (i = start + 1; i < finish; i++)
            toSave[i - start] = (c.contains(data[i])) ? 0 : 1;

        int w = start;
        for (i = start; i < finish; i++)
            if (toSave[i - start] == 1)
                data[w++] = data[i];
        size = w;

        for (i = size; i < finish; i++)
            data[i] = null;

        for (i = size / 2 - 1; i >= 0; i--) {
            var index = i;
            var item = (Comparable<? super E>) data[index];
            int left = 2 * index + 1;

            while (left < size) {
                int right = left + 1;
                int child = left;

                if (right < size && ((Comparable<? super E>)data[right]).compareTo(data[left]) < 0) {
                    child = right;
                }

                if (item.compareTo(data[child]) <= 0) {
                    break;
                }

                swap(index, child);
                index = child;
                left = 2 * index + 1;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i<size && c.contains(data[i])) {
            i++;
        }
        if (i == size)
            return false;

        int finish = size;
        int start = i;

        int[] toSave = new int[finish - start];
        for (i = start + 1; i < finish; i++)
            toSave[i - start] = (!c.contains(data[i])) ? 0 : 1;

        int w = start;
        for (i = start; i < finish; i++)
            if(toSave[i - start] == 1)
                data[w++] = data[i];

        size = w;
        for (i = size; i < finish; i++)
            data[i] = null;

        for (i = size / 2 - 1; i >= 0; i--) {
            var index = i;
            var item = (Comparable<? super E>) data[index];
            int left = 2 * index + 1;

            while (left < size) {
                int right = left + 1;
                int child = left;

                if (right < size && ((Comparable<? super E>)data[right]).compareTo(data[left]) < 0) {
                    child = right;
                }

                if (item.compareTo(data[child]) <= 0) {
                    break;
                }

                swap(index, child);
                index = child;
                left = 2 * index + 1;
            }
        }

        return true;
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
    public boolean remove(Object o) {
        return false;
    }

    private void swap(int i, int j) {
        var temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
