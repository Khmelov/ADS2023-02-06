package by.it.group251002.verbol.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {
    private Node<E> head;
    private int size;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            sb.append(ptr.value);
            if (ptr.next != null) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean add(E e) {

        Node<E> second = new Node<>(e);

        if (this.size == 0) {
            this.head = second;
            this.size++;
            return true;
        }

        Node<E> ptr = this.head;
        for (;ptr.next != null;ptr = ptr.next) {
        }

        ptr.next = second;
        this.size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }


        int oldSize = this.size--;

        if (index == 0) {
            E result = this.head.value;
            if (oldSize == 1) {
                this.head = null;
            } else {
                this.head = this.head.next;
            }
            return result;
        }

        Node<E> ptr = this.head;
        for (int i = 1; i < index; i++) {
            ptr = ptr.next;
        }

        E result = ptr.next.value;

        if (index + 1 == oldSize) {
            ptr.next = null;
        } else {
            ptr.next = ptr.next.next;
        }

        return result;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            return;
        }

        int oldSize = this.size++;

        Node<E> second = new Node<>(element);

        if (index == 0) {
            if (oldSize > 0) {
                second.next = this.head;
            }
            this.head = second;
            return;
        }

        Node<E> ptr = this.head;
        for (int i = 1; i < index; i++) {
            ptr = ptr.next;
        }

        if (index == this.size) {
            ptr.next = second;
        } else {
            second.next = ptr.next;
            ptr.next = second;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || this.size == 0) {
            return false;
        }

        if (o.equals(this.head.value)) {
            if (this.size == 1) {
                this.head = null;
            } else {
                this.head = this.head.next;
            }
            --this.size;
            return true;
        }

        if (this.size == 1) {
            return false;
        }

        Node<E> prev = this.head;
        for (Node<E> ptr = this.head.next; ptr != null; ptr = ptr.next) {
            if (o.equals(ptr.value)) {
                if (ptr.next == null) {
                    prev.next = null;
                } else {
                    prev.next = prev.next.next;
                }
                --this.size;
                return true;
            }
            prev = prev.next;
        }

        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        Node<E> ptr = this.head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }

        E result = ptr.value;
        ptr.value = element;
        return result;
    }


    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null || this.size == 0) {
            return -1;
        }

        int i = 0;
        for (Node<E> ptr = this.head; ptr != null; ptr = ptr.next) {
            if (o.equals(ptr.value)) {
                return i;
            }
            ++i;
        }

        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        Node<E> ptr = this.head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }

        return ptr.value;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null || this.size == 0) {
            return false;
        }

        for (Node<E> ptr = this.head; ptr != null; ptr = ptr.next) {
            if (o.equals(ptr.value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null || this.size == 0) {
            return -1;
        }

        int i = 0;
        int lastIndex = -1;
        for (Node<E> ptr = this.head; ptr != null; ptr = ptr.next) {
            if (o.equals(ptr.value)) {
                lastIndex = i;
            }
            ++i;
        }

        return lastIndex;
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
