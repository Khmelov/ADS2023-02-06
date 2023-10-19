package by.it.group251001.stolbov.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ListB() {
        data = new Object[DEFAULT_CAPACITY];
    }
    public ListB(int initialCapacity) {
        if (initialCapacity > 0) {
            data = new Object[initialCapacity];
        }
        else
            throw new IllegalArgumentException();
    }


    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.append(data[size - 1]).append(']').toString();
    }

    @Override
    public boolean add(E e) {
        if (size == data.length)
            data = grow(data.length + 1);
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldVal = (E) data[index];
        fastRemove(data, index);
        return oldVal;
    }

    @Override
    public int size() {
        return size;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {
        if (data.length == size) {
            data = grow(data.length + 1);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    fastRemove(data, i);
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    fastRemove(data, i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldVal = (E) data[index];
        data[index] = element;
        return oldVal;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) data[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
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
        Object[] arr = c.toArray();
        if (arr.length == 0) {
            return false;
        }

        if (data.length < size + arr.length) {
            data = grow(size + arr.length);
        }

        System.arraycopy(arr, 0, data, size, arr.length);
        size = arr.length + size;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] arr = c.toArray();
        int numNew = arr.length;
        if (arr.length == 0)
            return false;
        if (numNew > data.length - size)
            data = grow(size + arr.length);

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(data, index,
                    data, index + numNew,
                    numMoved);
        System.arraycopy(arr, 0, data, index, numNew);
        size = size + numNew;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false, 0, size);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true, 0, size);
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private Object[] grow(int minGrowth) {
        int oldCapacity = data.length;
        if (oldCapacity > 0) {
            int newCapacity = (oldCapacity * 3) >> 1;
            return data = Arrays.copyOf(data, Math.max(newCapacity, minGrowth));
        } else {
            return data = new Object[Math.max(DEFAULT_CAPACITY, minGrowth)];
        }
    }

    private void fastRemove(Object[] data, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(data, index + 1, data, index, newSize - index);
        data[size = newSize] = null;
    }

    private boolean batchRemove(Collection<?> c, boolean complement,
                                final int from, final int end) {
        Objects.requireNonNull(c);
        final Object[] es = data;
        int r;
        for (r = from;; r++) {
            if (r == end)
                return false;
            if (c.contains(es[r]) != complement)
                break;
        }
        int w = r++;
        try {
            for (Object e; r < end; r++)
                if (c.contains(e = es[r]) == complement)
                    es[w++] = e;
        } catch (Throwable ex) {
            System.arraycopy(es, r, es, w, end - r);
            w += end - r;
            throw ex;
        } finally {
            shiftTailOverGap(es, w, end);
        }
        return true;
    }

    private void shiftTailOverGap(Object[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, size - hi);
        for (int to = size, i = (size -= hi - lo); i < to; i++)
            es[i] = null;
    }

}