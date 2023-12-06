package by.it.group251001.kulchinskiy.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    private static class ListNode<E> {
        E m_data;
        ListNode<E> prev;
        ListNode<E> next;

        ListNode(E data) {
            m_data = data;
            prev = null;
            next = null;
        }
    }

    private ListNode<E> m_head;
    private ListNode<E> m_tail;
    private int m_size;

    ListA() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public String toString() {
        if (m_size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        var current = m_head;

        while (current != null) {
            stringBuilder.append(current.m_data);

            if (current.next == null) {
                return stringBuilder.append("]").toString();
            }

            stringBuilder.append(", ");

            current = current.next;
        }

        return "";
    }

    @Override
    public boolean add(E e) {
        var newNode = new ListNode<E>(e);
        m_size++;

        if (m_head == null) {
            m_head = newNode;
            m_tail = newNode;
            return true;
        }

        m_tail.next = newNode;
        newNode.prev = m_tail;
        m_tail = newNode;

        return true;
    }

    @Override
    public E remove(int index) {
        if (index > m_size || index < 0) {
            return null;
        }

        var current = m_head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            m_head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            m_tail = current.prev;
        }

        m_size--;
        return current.m_data;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public void add(int index, E element) {
        if (index > m_size) {
            return;
        }

        var newNode = new ListNode<E>(element);

        if (index == 0) {
            newNode.next = m_head;
            if (m_head != null) {
                m_head.prev = newNode;
            }
            m_head = newNode;

            if (m_tail == null) {
                m_tail = newNode;
            }
        } else {
            var current = m_head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            newNode.prev = current;
            newNode.next = current.next;

            if (current.next != null) {
                current.next.prev = newNode;
            }

            current.next = newNode;

            if (newNode.next == null) {
                m_tail = newNode;
            }
        }

        m_size++;
    }


    @Override
    public boolean remove(Object o) {
        var index = indexOf(o);
        if (index == -1) {
            return false;
        }

        return remove(index) != null;
    }

    @Override
    public E set(int index, E element) {
        if (index > m_size) {
            return null;
        }

        var current = m_head;
        var prev = m_head.prev;

        while (index > 0) {
            prev = current;
            current = current.next;
            index--;
        }

        var old = current.m_data;
        current.m_data = element;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public void clear() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public int indexOf(Object o) {
        var index = 0;
        var current = m_head;

        while (current != null && !current.m_data.equals(o)) {
            current = current.next;
            index++;
        }

        return current == null ? -1 : index;
    }

    @Override
    public E get(int index) {
        if (index > m_size) {
            return null;
        }

        var current = m_head;
        while (index > 0) {
            current = current.next;
            index--;
        }

        return current.m_data;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        var index = m_size - 1;
        var current = m_tail;

        while (current != null && !current.m_data.equals(o)) {
            current = current.prev;
            index--;
        }

        return current == null ? -1 : index;
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
        for (Object o : c) {
            add((E) o);
        }

        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > m_size) {
            return false;
        }

        var current = m_head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        for (E e : c) {
            var newNode = new ListNode<E>(e);
            newNode.prev = current;
            newNode.next = current.next;

            if (current.next != null) {
                current.next.prev = newNode;
            }

            current.next = newNode;

            if (newNode.next == null) {
                m_tail = newNode;
            }

            current = newNode;
            m_size++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

        var current = m_head;
        while (current != null) {
            if (c.contains(current.m_data)) {
                var next = current.next;
                var prev = current.prev;

                if (next != null) {
                    next.prev = prev;
                } else {
                    m_tail = prev;
                }

                if (prev != null) {
                    prev.next = next;
                } else {
                    m_head = next;
                }

                m_size--;

                current = next;
                modified = true;
            } else {
                current = current.next;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        var current = m_head;
        while (current != null) {
            if (c.contains(current.m_data)) {
                current = current.next;
            } else {
                var next = current.next;
                var prev = current.prev;

                if (next != null) {
                    next.prev = prev;
                } else {
                    m_tail = prev;
                }

                if (prev != null) {
                    prev.next = next;
                } else {
                    m_head = next;
                }

                m_size--;

                current = next;
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public ListB<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > m_size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        var subList = new ListB<E>();
        var current = m_head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.m_data);
            current = current.next;
        }

        return subList;
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

    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
