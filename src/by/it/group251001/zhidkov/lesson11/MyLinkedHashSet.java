package by.it.group251001.zhidkov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    public static class Knot<E>{
        E value;
        Knot<E> next;
        Knot<E> nextL;
        public Knot(E e){
            value = e;
            next = null;
            nextL = null;
        }
    }
    private int size;
    private Knot<E> head;
    private Knot<E> tail;
    private Knot<E>[] array;
    public MyLinkedHashSet(){
        size = 0;
        array = new Knot[64];
        head = null;
        tail = null;
    }
    MyLinkedHashSet(int size) {
        array = new Knot[size];
    }
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        boolean b = true;
        Knot<E> curr = head;
        while (curr != null) {
            if (!b)
                result.append(", ");

            result.append(curr.value);
            b = false;
            curr = curr.nextL;
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
        if ((int)o == 2430)
        {
            int i = 0;
        }
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
        Knot<E> curr = array[index];
        Knot<E> prev;
        while (curr != null)
        {
            if (curr.value.equals(e))
            {
                return false;
            }
            curr = curr.next;
        }

        Knot<E> newKnot = new Knot<>(e);
        newKnot.next = array[index];
        array[index] = newKnot;
        if (head == null) {
            head = newKnot;
            tail = head;
            tail.nextL = null;
        } else {
            tail.nextL = newKnot;
            tail = newKnot;
            tail.nextL = null;
        }


        size++;

        if (size > 0.6 * array.length)
        {
            Knot<E>[] newArray = new Knot[array.length * 2];
            for (Knot<E> nKnot : array) {
                Knot<E> curr2 = nKnot;

                while (curr2 != null) {
                    int hashC2 = (curr2.value.hashCode() & 0x7FFFFFFF) % newArray.length;
                    newArray[hashC2] = curr2;
                    curr2 = curr2.next;
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
        Knot<E> prev = curr;
        if (curr == null)
        {
            Knot<E> cur = head;
            Knot<E> pr = cur;
            while (cur != null)
            {
                if (cur.value.equals(o))
                {
                    if (pr == cur)
                    {
                        cur = cur.nextL;
                        head = cur;
                        pr = null;
                    }
                    else
                    {
                        Knot<E> tmp = cur;
                        pr.nextL = cur.nextL;
                        tmp = null;
                    }
                }
                pr = cur;
                cur = cur.nextL;
            }
        }
        while (curr != null)
        {
            if (curr.value.equals(o))
            {
                if (prev == curr)
                {
                    array[index] = curr.next;
                }
                else
                {
                    prev.next = curr.next;
                    array[index] = prev;
                }
                size--;
                Knot<E> cur = head;
                Knot<E> pr = cur;
                while (cur != null)
                {
                    if (cur.value.equals(o))
                    {
                        if (pr == cur)
                        {
                            cur = cur.nextL;
                            head = cur;
                            pr = null;
                        }
                        else
                        {
                            Knot<E> tmp = cur;
                            pr.nextL = cur.nextL;
                            tmp = null;
                        }
                        return true;
                    }
                    pr = cur;
                    cur = cur.nextL;
                }
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean WasAdded = false;
        for (E e : c) {
            if (add(e))
            {
                WasAdded = true;
            }
        }

        return WasAdded;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean res = false;
        MyLinkedHashSet<E> newHashSet = new MyLinkedHashSet<E>(array.length);
        Knot<E> curr = head;
        while (curr != null) {
            if (c.contains(curr.value))
            {
                newHashSet.add(curr.value);
                res = true;
            }
            curr = curr.nextL;
        }

        array = newHashSet.array;
        head = newHashSet.head;
        tail = newHashSet.tail;
        size = newHashSet.size;
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isDeleted = false;
        for (Object e : c) {
            if ((int)e == 1704)
            {
                int i = 0;
            }
            if (remove(e)) {
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        Knot<E> curr = head;
        Knot<E> Prev;
        while (curr != null)
        {
            Prev = curr;
            curr = curr.nextL;
            if (Prev == tail || Prev == head)
            {
                if (Prev == tail) {
                    tail = null;
                }
                else {
                    head = null;
                }
            }
            else
            {
                Prev = null;
            }
        }
        size = 0;
    }
}
