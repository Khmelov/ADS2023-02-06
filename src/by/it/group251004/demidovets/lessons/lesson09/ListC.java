package by.it.group251004.demidovets.lessons.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕК
    //////               Обязательные к реализации методы             ///////
    private E[] arr = (E[]) new Object[]{};
    private int list_size = 0;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < list_size; i++) {
            str.append(arr[i]);
            if (i + 1 != list_size) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean add(E e) {
        if (list_size == arr.length) {
            arr = Arrays.copyOf(arr, (list_size * 3 / 2 + 1));
        }
        arr[list_size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E elem = arr[index];
        System.arraycopy(arr, index + 1, arr, index, list_size - index - 1);
        list_size -= 1;
        return elem;
    }

    @Override
    public int size() {
        return list_size;
    }

    @Override
    public void add(int i, E element) {
        if (list_size == arr.length) {
            arr = Arrays.copyOf(arr,list_size * 3/2 + 1);
        }
        System.arraycopy(arr, i, arr, i + 1, list_size - i);
        arr[i] = element;
        list_size += 1;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i > -1) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if ((index < list_size) && (index > -1)){
            E out_element = arr[index];
            arr[index] = element;
            return out_element;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        if (list_size == 0)
            return true;
        else
            return false;
    }


    @Override
    public void clear() {
        do {
            remove(0);
        } while (list_size > 0);
        list_size = 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int k = 0; k < list_size; k++) {
            if (Objects.equals(arr[k], o)) {
                return k;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if ((index < list_size) && (index > -1))
            return arr[index];
        else
            return null;
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1)
            return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int k = list_size - 1; k > -1; k--)
            if (Objects.equals(arr[k], o))
                return k;
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        if (list_size + c.size() >= arr.length) {
            arr = Arrays.copyOf(arr, (list_size * 3 / 2 + 1));
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        if (list_size + c.size() >= arr.length) {
            arr = Arrays.copyOf(arr, (list_size * 3 / 2 + 1));
        }
        int i = index;
        for (E element : c) {
            add(i, element);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isCorrect = false;
        int i = 0;
        while (i < list_size) {
            if (c.contains(arr[i])) {
                remove(i);
                isCorrect = true;
            } else {
                i++;
            }
        }
        return isCorrect;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < list_size) {
            if (!c.contains(arr[i])) {
                remove(i);
            } else {
                i++;
            }
        }
        return true;
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
