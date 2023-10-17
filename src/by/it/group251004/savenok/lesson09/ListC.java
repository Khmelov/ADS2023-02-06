package by.it.group251004.savenok.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    private E[] array = (E[]) new Object[]{};

    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            str.append(array[i]);
            if (i + 1 != size) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E elem = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {//size - текущий, length - возможный
        if (size == array.length) {
            array = Arrays.copyOf(array, (size * 3) / 2 + 1);//создает новый массив и присваивает ссылку на array
        }
        System.arraycopy(array, index, array, index + 1, size - index);//index в 1, потому что сдвигаем, а не удаляем
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E oldElem = array[index];
        array[index] = element;
        return oldElem;
    }


    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }


    @Override
    public void clear() {
        this.array = (E[]) new Object[]{};
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public boolean contains(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            if (o.equals(array[index])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() + size > array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        if (c.isEmpty()) {
            return false;
        }
        for (E element: c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.size() + size > array.length) {
            array = Arrays.copyOf(array, size * 3 / 2 + 1);
        }
        if (c.isEmpty()) {
            return false;
        }
        for (E element: c) {
            add(index, element);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isDeleted = false;
        int index = 0;
        while (index < size) {
            if (c.contains(array[index])) {
                remove(array[index]);
                isDeleted = true;
            } else {
                index++;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isDeleted = false;
        int index = 0;
        while (index < size) {
            if (!c.contains(array[index])) {
                remove(array[index]);
                isDeleted = true;
            } else {
                index++;
            }
        }
        return isDeleted;
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
