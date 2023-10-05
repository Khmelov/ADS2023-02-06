package by.it.group251001.churavskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {
    by.it.group251001.churavskiy.lesson09.ListB.Node<E> first;
    by.it.group251001.churavskiy.lesson09.ListB.Node<E> last;
    int size = 0;

    private static class Node<E> {
        E item;
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> next;
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> prev;

        Node(by.it.group251001.churavskiy.lesson09.ListB.Node<E> prev, E element, by.it.group251001.churavskiy.lesson09.ListB.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> e = first;
        s.append("[");
        if (e != null) {
            while (e.next != null) {
                s.append(e.item).append(", ");
                e = e.next;
            }
            s.append(e.item);
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public boolean add(E e) {

        by.it.group251001.churavskiy.lesson09.ListB.Node<E> l = last;
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> newNode = new by.it.group251001.churavskiy.lesson09.ListB.Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        E element = x.item;
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> next = x.next;
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            final by.it.group251001.churavskiy.lesson09.ListB.Node<E> l = last;
            final by.it.group251001.churavskiy.lesson09.ListB.Node<E> newNode = new by.it.group251001.churavskiy.lesson09.ListB.Node<>(l, element, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
        } else {
            by.it.group251001.churavskiy.lesson09.ListB.Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            final by.it.group251001.churavskiy.lesson09.ListB.Node<E> pred = node.prev;
            final by.it.group251001.churavskiy.lesson09.ListB.Node<E> newNode = new by.it.group251001.churavskiy.lesson09.ListB.Node<>(pred, element, node);
            node.prev = newNode;
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            size++;
        }
    }

    E unlink(by.it.group251001.churavskiy.lesson09.ListB.Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final by.it.group251001.churavskiy.lesson09.ListB.Node<E> next = x.next;
        final by.it.group251001.churavskiy.lesson09.ListB.Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E oldVal = node.item;
        node.item = element;
        return oldVal;
    }


    @Override
    public boolean isEmpty() {
        if (last == null & first == null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void clear() {
        for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first; x != null; ) {
            by.it.group251001.churavskiy.lesson09.ListB.Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        by.it.group251001.churavskiy.lesson09.ListB.Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (by.it.group251001.churavskiy.lesson09.ListB.Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
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

}
