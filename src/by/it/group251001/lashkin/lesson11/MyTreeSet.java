package by.it.group251001.lashkin.lesson11;

import java.util.*;

/*
                toString()
                size()
                clear()
                isEmpty()
                add(Object)
                remove(Object)
                contains(Object)

                containsAll(Collection)
                addAll(Collection)
                removeAll(Collection)
                retainAll(Collection)
*/

public class MyTreeSet<E> implements Set<E> {
    int ARRAY_SIZE;
    Object[] ARRAY = new Object[0];


    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < ARRAY_SIZE - 1; i++)
            res.append(ARRAY[i].toString()).append(", ");
        res.append(ARRAY[ARRAY_SIZE - 1].toString()).append("]");
        return res.toString();
    }

    @Override
    public int size() {
        return ARRAY_SIZE;
    }

    @Override
    public void clear() {
        ARRAY_SIZE = 0;
        ARRAY = new Object[0];
    }

    @Override
    public boolean isEmpty() {
        return ARRAY_SIZE == 0;
    }

    @Override
    public boolean add(E e) {
        int i = 0;
        if (i < ARRAY_SIZE && ((Comparable<? super E>) ARRAY[i]).compareTo(e) < 0) {
            do i++;
            while (i < ARRAY_SIZE && ((Comparable<? super E>) ARRAY[i]).compareTo(e) < 0);
        }
        if (isEmpty() || i >= ARRAY_SIZE || !ARRAY[i].equals(e)) {
            if (ARRAY.length == ARRAY_SIZE) {
                ARRAY = Arrays.copyOf(ARRAY, ARRAY_SIZE * 2 + 1);
            }
            ARRAY_SIZE++;
            int j = ARRAY_SIZE - 1;
            while (j > i) {
                ARRAY[j] = ARRAY[j - 1];
                j--;
            }
            ARRAY[i] = e;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        if (i < ARRAY_SIZE && !ARRAY[i].equals(o)) {
            do i++;
            while (i < ARRAY_SIZE && !ARRAY[i].equals(o));
        }
        if (i != ARRAY_SIZE) {
            int j = i;
            while (j < size() - 1) {
                ARRAY[j] = ARRAY[j + 1];
                j++;
            }
            ARRAY_SIZE--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        int i = 0;
        while (i < ARRAY_SIZE) {
            if (!ARRAY[i].equals(o)) {
                i++;
            } else {
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
        boolean flag = false;
        for (Iterator<? extends E> iterator = c.iterator(); iterator.hasNext(); ) {
            E o = iterator.next();
            if (add(o))
                flag = true;
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean[] flags = new boolean[ARRAY_SIZE];
        var term = 0;
        var count = 0;
        int i;
        i = 0;
        while (i < ARRAY_SIZE) {
            if (c.contains(ARRAY[i])) {
                flags[i] = true;
                term++;
            }
            i++;
        }
        if (term == 0) {
            return false;
        }
        Object[] newArr = new Object[ARRAY_SIZE - term];
        i = 0;
        while (i < ARRAY_SIZE) {
            if (!flags[i]) {
                newArr[count++] = ARRAY[i];
            }
            i++;
        }
        ARRAY = newArr;
        ARRAY_SIZE = ARRAY_SIZE - term;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] flags = new boolean[ARRAY_SIZE];
        var term = 0;
        var count = 0;
        int i;
        i = 0;
        while (i < ARRAY_SIZE) {
            if (c.contains(ARRAY[i])) {
                flags[i] = true;
                term++;
            }
            i++;
        }
        if (term == ARRAY_SIZE) {
            return false;
        }
        Object[] newArr = new Object[term];
        i = 0;
        while (i < ARRAY_SIZE) {
            if (flags[i])
                newArr[count++] = ARRAY[i];
            i++;
        }
        ARRAY = newArr;
        ARRAY_SIZE = term;
        return true;
    }

    /////////////////////////////////////////////

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
