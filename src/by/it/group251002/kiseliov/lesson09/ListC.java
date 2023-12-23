package by.it.group251002.kiseliov.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    static final int default_list_size = 10;
    E[] list;
    int list_size;

    public ListC() {
        this(default_list_size);
    }


    public ListC(int size) {
        list = (E[]) new Object[size];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < list_size; i++) {
            sb.append(list[i]);
            if (i < list_size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (list_size == list.length) {
            E[] newList = (E[]) new Object[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
        list[list_size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index > -1 && index < list_size) {
            E elem = list[index];
            for (int i = index; i < list_size - 1; i++)
                list[i] = list[i + 1];
            list_size--;
            return elem;
        }
        return null;
    }

    @Override
    public int size() {
        return list_size;
    }

    @Override
    public void add(int index, E element) {
        if (list_size == list.length) {
            E[] newList = (E[]) new Object[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
        if (index > -1 && index <= list_size) {
            for (int i = list_size; i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = element;
            list_size++;
        }
    }


    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E item = null;
        if (index > -1 && index < list_size) {
            item = list[index];
            list[index] = element;
        }
        return item;
    }


    @Override
    public boolean isEmpty() {
        return list_size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < list_size; i++) {
            list[i] = null;
        }
        list_size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < list_size; i++) {
            if (Objects.equals(list[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index > -1 && index < list_size)
            return list[index];
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = list_size - 1; i > -1; i--) {
            if (Objects.equals(o, list[i]))
                return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c)
            if (!contains(item))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c)
            add(item);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean modified = false;
        for (E item : c) {
            if (index > -1 && index <= list_size) {
                add(index, item);
                index++;
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean deleted = false;

        for (int i = 0; i < list_size; i++) {
            if (c.contains(list[i])) {
                remove(i);
                i--; // Уменьшаем счетчик, так как размер списка уменьшился
                deleted = true;
            }
        }
        return deleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean retained = false;

        for (int i = 0; i < list_size; i++) {
            if (!c.contains(list[i])) {
                remove(i);
                i--;
                retained = true;
            }
        }
        return retained;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > list_size || fromIndex > toIndex) {
            ListC<E> subList = new ListC<>(toIndex - fromIndex);
            for (int i = fromIndex; i < toIndex; i++)
                subList.add(list[i]);
            return subList;
        }
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
        Object[] result = new Object[list_size];
        for (int i = 0; i < list_size; i++) {
            result[i] = list[i];
        }
        return result;
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
