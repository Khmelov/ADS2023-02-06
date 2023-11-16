package by.it.group251001.karpekov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyLinkedHashSet<E> implements Set<E>{

    private int size = 0;
    private int capacity = 100;

    private int currind = 0;

    private static class MyList<E>{
        public E value;
        public int ind;
        public MyList<E> next;
    }

    private MyList<E>[] arr = new MyList[capacity];

    {
        for (int i = 0; i < capacity; i++)
            arr[i] = new MyList<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return(0 == size);
    }

    @Override
    public boolean contains(Object o) {
        MyList<E> lis;
        int index = o.hashCode() % capacity;
        lis = arr[index].next;
        while (lis != null)
        {
            if (o.equals(lis.value)) return true;
            lis = lis.next;
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
    public String toString(){
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        int minind = Integer.MAX_VALUE;
        int prevminind = -1;
        E minindval = null;

        while (true)
        {
            minind = Integer.MAX_VALUE;
            for (int i = 0; i < capacity; i++)
            {
                MyList<E> lis = arr[i].next;
                while (lis != null) {
                    if (lis.ind < minind && lis.ind > prevminind)
                    {
                        minind = lis.ind;
                        minindval = lis.value;
                    }
                    lis = lis.next;
                }
            }
            if (minind != Integer.MAX_VALUE)
            {
                sb.append(minindval.toString()).append(", ");
                prevminind = minind;
            }
            else break;
        }

        sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        int index = e.hashCode() % capacity;
        MyList<E> lis = arr[index];
        while (null != lis.next)
        {
            lis = lis.next;
            if (e.equals(lis.value)) return false;
        }
        MyList<E> newlis = new MyList<E>();
        lis.next = newlis;
        size++;
        lis = lis.next;
        lis.value = e;
        lis.ind = currind++;
        lis.next = null;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = o.hashCode() % capacity;
        MyList<E> lis = arr[index];
        while (null != lis.next && !(o.equals(lis.next.value)))
        {
            lis = lis.next;
        }
        if (lis.next == null) return false;
        lis.next = lis.next.next;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int contained = 0;
        for (int i = 0; i < capacity; i++){
            MyList<E> lis = arr[i].next;
            while (lis != null){
                if (c.contains(lis.value))
                    contained++;
                lis = lis.next;
            }
        }

        return (contained == c.size());
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for(E o: c){
            if (add(o))
                res = true;
        }

        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (int i = 0; i < capacity; i++){
            MyList<E> lis = arr[i].next;
            while (lis != null){
                if (!c.contains(lis.value))
                    res = remove(lis.value);
                lis = lis.next;
            }
        }

        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (int i = 0; i < capacity; i++){
            MyList<E> lis = arr[i].next;
            while (lis != null){
                if (c.contains(lis.value))
                    res = remove(lis.value);
                lis = lis.next;
            }
        }

        return res;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++){
            while (arr[i].next != null){
                arr[i].next = arr[i].next.next;
            }
        }
        size = 0;
    }
}

