package by.it.group251002.alesina.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private class Node<E> {
        Node next;
        E value;
        int ord;

        public Node(E e, int cnt) {
            this.value = e;
            this.next = null;
            this.ord = ord; //каким по порядку элемент был добавлен
        }

    }
    private int norm = 0;
    private int size = 0;
    private int ind = 0; //переменная, отвечающая за номер элемента в очереди на добавление
    private Node<E>[] arr = (Node<E>[]) new Node[]{};

    private void incSize() {
        ind = 0;
        norm = 0;
        Node<E>[] oldArr = arr;
        arr = (Node<E>[]) new Node[(size * 3) / 2 + 1];
        for(int i = 0; i < oldArr.length; i++) {
            Node<E> cur = oldArr[i];
            while(cur != null) {
                int index = cur.value.hashCode() % arr.length;
                addToList(index, ind, cur.value);
                ind++;
                cur = cur.next;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private boolean addToList(int index, int cnt, E e) {
        boolean done = false;

        if (arr[index] == null) {
            arr[index] = new Node<E>(e, ind);
            done = true;
        } else {
            Node<E> cur = arr[index];
            while (cur.next != null) {
                if (e.equals(cur.value)) {
                    return false;
                }
                cur = cur.next;
            }
            if (!e.equals(cur.value)) {
                Node<E> newNode = new Node<E>(e, cnt);
                cur.next = newNode;
                done = true;
            }
        }

        return done;
    }
    @Override
    public boolean add(E e) {
        if (size == arr.length) {
            incSize();
        }
        int index = e.hashCode() % arr.length;
        boolean done = addToList(index, ind, e);
        if (done) {
            ind++; //увеличиваем число добавленных элементов
            size++;
        }
        return done;
    }


    @Override
    public boolean contains(Object o) {
        boolean found = false;

        int index = o.hashCode() % arr.length;
        Node<E> cur = arr[index];
        while (cur != null) {
            if (o.equals(cur.value)) {
                found = true;
                break;
            }
            cur = cur.next;
        }

        return found;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] data = (E[]) c.toArray();
        int prev_size = size;
        for(int i = 0; i < data.length; i++) {
            add(data[i]);
        }

        if (prev_size != size) {
            return true;
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] data = c.toArray();
        for(int i = 0; i < data.length; i++) {
            if (!contains(data[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean findToRemove(int index, Object o) {
        boolean found = false;
        Node<E> cur = arr[index];

        if (cur != null) {
            if (o.equals(cur.value)) {
                arr[index] = cur.next;
                found = true;
            } else {
                while (cur.next != null && !o.equals(cur.next.value)) {
                    cur = cur.next;
                }
                if (cur.next != null) {
                    Node<E> tmp = cur.next.next;
                    cur.next = tmp;
                    found = true;
                }
            }
        }

        return found;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int index = o.hashCode() % arr.length;
        boolean found = findToRemove(index, o);
        if (found) {
            norm--;
            size--;
        }
        return found;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean done = false;

        for(int i = 0; i < arr.length; i ++) {
            Node<E> cur = arr[i];
            while(cur != null) {
                if (c.contains(cur.value)) {
                    remove(cur.value);
                    done = true;
                }
                cur = cur.next;
            }
        }

        return done;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean done = false;

        for(int i = 0; i < arr.length; i ++) {
            Node<E> cur = arr[i];
            while(cur != null) {
                if (!c.contains(cur.value)) {
                    remove(cur.value);
                    done = true;
                }
                cur = cur.next;
            }
        }

        return done;
    }


    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String comma = "";
        E[] data = (E[]) new Object[ind - norm];
        for (int i = 0; i < arr.length; i++) {
            Node<E> cur = arr[i];
            while (cur != null) {
                data[cur.ord -norm] = cur.value;
                cur = cur.next;
            }
        }
        for (int i = 0; i < data.length;  i++) {
            if (data[i] != null) {
                str.append(comma).append(data[i]);
                comma = ", ";
            }
        }
        str.append("]");
        return str.toString();
    }


    @Override
    public void clear() {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        ind = 0;
        size = 0;
        norm=0;
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
