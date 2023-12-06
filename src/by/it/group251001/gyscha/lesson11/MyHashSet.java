package by.it.group251001.gyscha.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private class LNode<E> {
        LNode next;
        E value;

        public LNode(E e) {
            this.value = e;
            this.next = null;
        }
    }

    private int _size = 0;
    private LNode<E>[]_buf = (LNode<E>[]) new LNode[]{};

    private boolean addToList(int index, E e) {
        boolean ok = false;
        if (_buf[index] == null) {
            _buf[index] = new LNode<E>(e);
            ok = true;
        }
        else {
            LNode<E> current = _buf[index];
            while (current.next != null) {
                if (e.equals(current.value)) {return false;}
                current = current.next;
            }
            if (!e.equals(current.value)) {
                LNode<E> newLNode = new LNode<E>(e);
                current.next = newLNode;
                ok = true;
            }
        }

        return ok;
    }

    private boolean removeFromList(int index, Object o) {
        boolean ok = false;
        LNode<E> current = _buf[index];
        if (current != null) {
            if (o.equals(current.value)) {
                _buf[index] = current.next;
                ok = true;
            }
            else {
                while (current.next != null && !o.equals(current.next.value)) {current = current.next;}
                if (current.next != null) {
                    LNode<E> temp = current.next.next;
                    current.next = temp;
                    ok = true;
                }
            }
        }
        return ok;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < _buf.length;  i++) {
            LNode<E> current = _buf[i];
            while (current != null) {
                sb.append(delimiter).append(current.value);
                delimiter = ", ";
                current = current.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean contains(Object o) {
        boolean ok = false;
        int index = o.hashCode() % _buf.length;
        LNode<E> current = _buf[index];
        while (current != null) {
            if (o.equals(current.value)) {
                ok = true;
                break;
            }
            current = current.next;
        }
        return ok;
    }

    @Override
    public boolean add(E e) {
        if (_size == _buf.length) {grow();}
        int index = e.hashCode() % _buf.length;
        boolean ok = addToList(index, e);
        if (ok) {_size++;}
        return ok;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {return false;}
        int index = o.hashCode() % _buf.length;
        boolean ok = removeFromList(index, o);
        if (ok) {_size--;}
        return ok;
    }

    @Override
    public void clear() {
        for(int i = 0; i < _buf.length; i++) {
            _buf[i] = null;
        }
        _size = 0;
    }

    private void grow() {
        LNode<E>[] oldBuf = _buf;
        _buf = (LNode<E>[]) new LNode[(_size * 3) / 2 + 1];
        for(int i = 0; i < oldBuf.length; i++) {
            LNode<E> cur = oldBuf[i];
            while(cur != null) {
                int index = cur.value.hashCode() % _buf.length;
                addToList(index, cur.value);
                cur = cur.next;
            }
        }
    }
    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {return false;}

}
