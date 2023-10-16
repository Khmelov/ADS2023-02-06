package by.it.group251003.snopko.lesson09;

import java.lang.reflect.Array;
import java.util.*;

public class ListC<E> implements List<E> {
    private E[] elements =(E[]) new Object[]{};
    private int size = 0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimeter = "";
        for (int i = 0; i < size; i++) {
            sb.append(delimeter).append(elements[i]);
            delimeter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, (size * 3) / 2 + 1);
        elements[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E deleted = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return deleted;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length)
            elements = Arrays.copyOf(elements, (size * 3) / 2 + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

    }

    @Override
    public boolean remove(Object o) {
        int toDelete = indexOf(o);
        if (toDelete > -1) remove(toDelete);
        return toDelete > -1;
    }

    @Override
    public E set(int index, E element) {
        E temp = elements[index];
        elements[index] = element;
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = size - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object i :c)
            if (!contains(i))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        for (E a: c)
            add(a);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        if (size + c.size() >= elements.length)
            elements = Arrays.copyOf(elements, elements.length + c.size());
        System.arraycopy(elements, index, elements, index + c.size(), size - index);
        size += c.size();
        int i = 0;
        for (E a: c) {
            elements[index + i] = a;
            i++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i);
                changed = true;
                i--;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(i);
                changed = true;
                i--;
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
        ListA<E> Sub = new ListA<E> ();
        for (int i = fromIndex; i < toIndex; i++) {
            Sub.add(elements[i]);
        }
        return Sub;
    }


    private class MyListIterator implements ListIterator<E>{
        private int current = 0;
        public MyListIterator (int i){
            current = i;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            return get(current + 1);
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public E previous() {
            return get(current - 1);
        }

        @Override
        public int nextIndex() {
            return current + 1;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            ListC.this.remove(current);
        }

        @Override
        public void set(E e) {
            ListC.this.set(current, e);
        }

        @Override
        public void add(E e) {
            ListC.this.add(e);
        }

    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] temp = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        int index = 0;
        for (E i:elements) {
            temp[index++] = (T) i;
        }
        a = Arrays.copyOf(temp, size);
        return a;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[0];
        result = Arrays.copyOf(elements, size);
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
