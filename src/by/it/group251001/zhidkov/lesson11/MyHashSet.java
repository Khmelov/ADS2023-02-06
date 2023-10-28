package by.it.group251001.zhidkov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    public static class Knot<E>{
        E value;
        Knot<E> next;

        public Knot(E e){
            value = e;
            next = null;
        }
    }
    private int size;
    private Knot<E>[] array;
    public MyHashSet(){
        size = 0;
        array = new Knot[64];
    }
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        boolean b = true;
        for (int i = 0; i < array.length; i++) {
            Knot<E> curr = (Knot<E>)array[i];
            while (curr != null) {
                if (!b)
                    result.append(", ");

                result.append(curr.value);
                b = false;
                curr = curr.next;
            }
        }
        result.append("]");
        return result.toString();
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
        int hashC = o.hashCode();
        int index = (hashC & 0x7FFFFFFF) % array.length;
        Knot<E> curr = array[index];
        while (curr != null)
        {
            if (curr.value.equals(o))
            {
                return  true;
            }
            curr = curr.next;
        }
        return false;
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
    public boolean add(E e) {
        int hashC = e.hashCode();
        int index = (hashC & 0x7FFFFFFF) % array.length;
        if (e == null)
        {
            index = 0;
        }

        Knot<E> curr = array[index];
        Knot<E> prev = null;
        while (curr != null)
        {
            if (curr.value.equals(e))
            {
                return false;
            }
            prev = curr;
            curr = curr.next;
        }

        Knot<E> newKnot = new Knot<>(e);
        if (prev != null)
        {
            prev.next = newKnot;
        }
        else
        {
            array[index] = newKnot;
        }
        size++;

        if (size > 0.6 * array.length)
        {
            Knot<E>[] newArray = new Knot[array.length * 2];
            for (Knot<E> nKnot : array) {
                Knot<E> curr2 = nKnot;

                while (curr2 != null) {
                    Knot<E> next = curr2.next;

                    int hashC2 = (curr2.value.hashCode() & 0x7FFFFFFF) % newArray.length;
                    curr2.next = newArray[hashC2];
                    newArray[hashC2] = curr2;
                    curr2 = next;
                }
            }
            array = newArray;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hashC = o.hashCode();
        int index = (hashC & 0x7FFFFFFF) % array.length;
        Knot<E> curr = array[index];
        Knot<E> prev = null;
        while (curr != null)
        {
            if (curr.value.equals(o))
            {
                if (prev != null)
                {
                    prev.next = curr.next;
                }
                else
                {
                    array[index] = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
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

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
