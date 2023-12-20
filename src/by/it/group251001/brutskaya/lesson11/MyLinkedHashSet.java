package by.it.group251001.brutskaya.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    class Node<E> {
        E item;
        Node<E> next, before, after;

        public Node(E item) {
            this.item = item;
        }
    }

    int INITIAL_CAPACITY = 160;
    float LOAD_FACTOR = 0.75f;
    Node<E>[] array = new Node[INITIAL_CAPACITY];
    int size = 0;
    private Node<E> head, tail;

    int hash(E e, int length) {
        return e.hashCode() % length;
    }

    void setHeadTail(Node<E> node) {
        if (head == null) {
            head = node;
        } else {
            tail.after = node;
            node.before = tail;
        }
        tail = node;
    }

    void resize() {
        Node<E>[] newArray = new Node[size * 2];
        for (int i = 0; i < array.length; i++) {
            while (array[i] != null) {
                int index = hash(array[i].item, newArray.length);
                Node<E> newNode = new Node<>(array[i].item);
                if (newArray[index] == null)
                    newArray[index] = newNode;
                else {
                    Node<E> temp = newArray[index];
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = newNode;
                }
                array[i] = array[i].next;
            }
        }
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isEmpty())
            return "[]";
        s.append("[");
        var temp = head;
        while (temp != null) {
            s.append(temp.item).append(", ");
            temp = temp.after;
        }
        if (size != 0) {
            s.delete(s.length() - 2, s.length());
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = hash((E) o, array.length);

        if (array[index] == null)
            return false;
        else {
            Node<E> temp = array[index];
            while (temp != null) {
                if (temp.item.equals(o))
                    return true;
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        int index = hash(e, array.length);

        var node = new Node<>(e);
        if (array[index] == null) {
            array[index] = node;
        } else {
            var temp = array[index];
            while (temp.next != null) {
                if (temp.item.equals(e)) {
                    return false;
                }
                temp = temp.next;
            }
            if (temp.item.equals(e))
                return false;
            temp.next = node;
        }
        setHeadTail(node);
        if (++size > array.length * LOAD_FACTOR) {
            resize();
        }
        return true;
    }
    void deleteFromList(Node<E> node){
        if (node.before==null && node.after==null){
            head=null;
            tail=null;
            return;
        }
        if (node.before!=null && node.after!=null){
            node.before.after=node.after;
            node.after.before=node.before;
        } else if (node.after == null) {
            node.before.after=null;
            tail=node.before;
        } else if (node.before == null) {
            node.after.before=null;
            head=node.after;
        }
    }
    @Override
    public boolean remove(Object o) {
        int index = hash((E) o, array.length);

        if (array[index] == null)
            return false;
        else {
            Node<E> temp = array[index];
            Node<E> prev = null;
            while (temp != null) {
                if (temp.item.equals(o)) {
                    if (prev != null) {
                        prev.next = temp.next;
                    } else {
                        array[index] = temp.next;
                    }
                    deleteFromList(temp);
                    size--;
                    return true;
                }
                prev = temp;
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o :
                c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o:
             c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]!=null){
                var temp = array[i];
                while (temp!=null){
                    if(!c.contains(temp.item))
                        remove(temp.item);
                    temp=temp.next;
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o :
                c) {
            if (contains(o))
                remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i]=null;
        }
        head=null;
        tail=null;
        size=0;
    }

    /////////////////////////////////////////
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
