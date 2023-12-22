package by.it.group251002.zinovenko.lesson09;

import java.util.*;

public class ListA<E> implements List<E> {
    ListA.Node<E> first;
    ListA.Node<E> last;
    int size=0;
    private static class Node<E> {
        E item;
        ListA.Node<E> next;
        ListA.Node<E> prev;

        Node(ListA.Node<E> prev, E element, ListA.Node<E> next) {
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
        ListA.Node<E> e = first;
        s.append("[");
        while (e.next!=null){
            s.append(e.item).append(", ");
            e=e.next;
        }
        s.append(e.item);
        s.append("]");
        return s.toString();
    }

    @Override
    public boolean add(E e) {
        ListA.Node<E> l = last;
        ListA.Node<E> newNode = new ListA.Node<>(l,e,null);
        last = newNode;
        if(l==null)
            first = newNode;
        else
            l.next=newNode;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        ListA.Node<E> x =first;
        for (int i = 0; i < index; i++) {
            x=x.next;
        }
        E element = x.item;
        ListA.Node<E> next = x.next;
        ListA.Node<E> prev = x.prev;

        if(prev == null){
            first = next;
        } else{
            prev.next=next;
            x.prev=null;
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

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, E element) {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

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
