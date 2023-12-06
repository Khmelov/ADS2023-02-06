package by.it.group251001.gyscha.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private class LNode<E> {
        LNode next;
        E value;
        int cnt;

        public LNode(E e, int cnt) {
            this.value = e;
            this.next = null;
            this.cnt = cnt;
        }
    }

    private int _size = 0;
    private int ind = 0;
    private int norm = 0;
    private LNode<E>[] _buf = (LNode<E>[]) new LNode[]{};

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        E[] data = (E[]) new Object[ind - norm];
        for (int i = 0; i < _buf.length; i++) {
            LNode<E> cur = _buf[i];
            while (cur != null) {
                data[cur.cnt - norm] = cur.value;
                cur = cur.next;
            }
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {sb.append(delimiter).append(data[i]);delimiter = ", ";}
        }
        sb.append("]");
        return sb.toString();
    }

    private boolean addToList(int index, int cnt, E e) {
        boolean ok = false;
        if (_buf[index] == null) {
            _buf[index] = new LNode<E>(e, ind);
            ok = true;
        }
        else {
            LNode<E> cur = _buf[index];
            while (cur.next != null) {
                if (e.equals(cur.value)) {return false;}
                cur = cur.next;
            }
            if (!e.equals(cur.value)) {
                LNode<E> newNode = new LNode<E>(e, cnt);
                cur.next = newNode;
                ok = true;
            }
        }
        return ok;
    }

    private boolean removeFromList(int index, Object o) {
        boolean ok = false;
        LNode<E> cur = _buf[index];
        if (cur != null) {
            if (o.equals(cur.value)) {
                _buf[index] = cur.next;
                ok = true;
            }
            else {
                while (cur.next != null && !o.equals(cur.next.value)) {cur = cur.next;}
                if (cur.next != null) {
                    LNode<E> tmp = cur.next.next;
                    cur.next = tmp;
                    ok = true;
                }
            }
        }
        return ok;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ok = false;
        for (int i = 0; i < _buf.length; i++) {
            LNode<E> current = _buf[i];
            while (current != null) {
                if (c.contains(current.value)) {
                    remove(current.value);
                    ok = true;
                }
                current = current.next;
            }
        }
        return ok;
    }

    @Override
    public void clear() {
        for (int i = 0; i < _buf.length; i++) {_buf[i] = null;}
        ind = 0;
        norm = 0;
        _size = 0;
    }

    @Override
    public boolean add(E e) {
        if (_size == _buf.length) {grow();}
        int index = e.hashCode() % _buf.length;
        boolean ok = addToList(index, ind, e);
        if (ok) {ind++;_size++;}
        return ok;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {return false;}
        int index = o.hashCode() % _buf.length;
        boolean ok = removeFromList(index, o);
        if (ok) {norm--;_size--;}
        return ok;
    }

    @Override
    public boolean contains(Object o) {
        boolean ok = false;
        int index = o.hashCode() % _buf.length;
        LNode<E> cur = _buf[index];
        while (cur != null) {
            if (o.equals(cur.value)) {ok = true;break;}
            cur = cur.next;
        }
        return ok;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] data = c.toArray();
        for (int i = 0; i < data.length; i++) {if (!contains(data[i])) {return false;}}
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] data = (E[]) c.toArray();
        int prev_size = _size;
        for (int i = 0; i < data.length; i++) {add(data[i]);}
        if (prev_size != _size) {return true;}
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean ok = false;
        for (int i = 0; i < _buf.length; i++) {
            LNode<E> current = _buf[i];
            while (current != null) {
                if (!c.contains(current.value)) {
                    remove(current.value);
                    ok = true;
                }
                current = current.next;
            }
        }
        return ok;
    }

    private void grow() {
        ind = 0;
        norm = 0;
        LNode<E>[] oldBuf = _buf;
        _buf = (LNode<E>[]) new LNode[(_size * 3) / 2 + 1];
        for (int i = 0; i < oldBuf.length; i++) {
            LNode<E> cur = oldBuf[i];
            while (cur != null) {
                int index = cur.value.hashCode() % _buf.length;
                addToList(index, ind, cur.value);
                ind++;
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

}

