package by.it.group251002.alesina.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private class Node<E> {
        Node next;
        E value;

        public Node(E e) {
           this.value = e;
           this.next = null;
        }

    }

    private int size = 0;
    private Node<E>[] arr = (Node<E>[]) new Node[]{};

    private void incSize() {
        Node<E>[] oldArr = arr;
        arr = (Node<E>[]) new Node[(size * 3) / 2 + 1];
        for(int i = 0; i < oldArr.length; i++) {
           Node<E> cur = oldArr[i];
           while(cur != null) {
               int index = cur.value.hashCode() % arr.length;
               addToList(index, cur.value);
               cur = cur.next;
           }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean addToList(int index, E e) {
        boolean done = false;

        if (arr[index] == null) {
            arr[index] = new Node<E>(e);
            done= true;
        } else {
            Node<E> cur = arr[index];
            while (cur.next != null) {
                if (e.equals(cur.value)) {
                    return false;
                }
                cur = cur.next;
            }
            if (!e.equals(cur.value)) {
                Node<E> newNode = new Node<E>(e);
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
        boolean done = addToList(index, e);
        if (done) {
            size++;
        }
        return done;
    }

    private boolean findToRemove(int index, Object o) {
        boolean found = false;
        Node<E> cur = arr[index];

        if (cur != null) {
            if (o.equals(cur.value)) {
                arr[index] = cur.next;
                found = true;
            } else {  //поиск нужного элемента
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
            size--;
        }
        return found;
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

    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String comma = "";
        for (int i = 0; i < arr.length;  i++) {
           Node<E> cur = arr[i];
           while (cur != null) {
               str.append(comma).append(cur.value);
               comma = ", ";
               cur = cur.next;
           }
        }
        str.append("]");
        return str.toString();
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
    public boolean removeAll(Collection<?> c) {
        return false;
    }


}
