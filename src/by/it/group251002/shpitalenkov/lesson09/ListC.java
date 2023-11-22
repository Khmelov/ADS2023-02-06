package by.it.group251002.shpitalenkov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ


    private E[] arr = (E[]) new Object[]{};
    private int size;

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
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
    public boolean add(E e) {
        if (size == arr.length) {
            E[] NewArr = (E[]) new Object[(size*3)/2+1];
            System.arraycopy(arr, 0, NewArr, 0, size);
            arr = NewArr;
        }
        arr[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E save = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        size--;
        return save;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == arr.length) {
            E[] NewArr = (E[]) new Object[(size*3)/2+1];
            System.arraycopy(arr, 0, NewArr, 0, size);
            arr = NewArr;
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
        }
        return index > -1;
    }

    @Override
    public E set(int index, E element) {
        E elem = get(index);
        arr[index] = element;
        return elem;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        size = 0;
        for(int i = 0; i < size; i++) {
            arr[i] = null;
        }
        arr = (E[]) new Object[]{};
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return arr[index];
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
    public int lastIndexOf(Object o) {
        int index = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    index = i;
                }
            }
        }
        return index;
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
    public boolean addAll(int index, Collection<? extends E> c) {
        int prevSize = size;
        boolean changed = false;
        E[] newArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add(index + i, newArr[i]);
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
                remove(i);
                changed = true;
            } else {
                i++;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(arr[i])) {
                this.remove(i);
                changed = true;
            } else {
                i++;
            }
        }
        return changed;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

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

}
