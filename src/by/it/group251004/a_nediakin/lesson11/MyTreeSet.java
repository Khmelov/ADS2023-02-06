package by.it.group251004.a_nediakin.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyTreeSet<E> implements Set<E> {
    E[] value = (E[]) new Object[]{};
    int size = 0;

    private int binary_search_to_add(int l, int r, E val){
        if(l >= r)
            return l;
        int c = (l + r) / 2;
        if((Integer)value[c] > (Integer)val)
            return binary_search_to_add(l ,c, val);
        return binary_search_to_add(c + 1, r, val);
    }

    private int binary_search_to_find(int l, int r, E val){
        if(l >= r)
            return l;
        int c = (l + r) / 2;
        if((Integer)value[c] >= (Integer)val)
            return binary_search_to_find(l ,c, val);
        return binary_search_to_find(c + 1, r, val);
    }


    @Override
    public String toString() {
        String s = "[";
        for(int i = 0; i < size; ++i){
            s += value[i].toString();
            if(i != size - 1)
                s += ", ";
        }
        s += "]";
        return s;
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
        return size != 0 && value[binary_search_to_find(0, size - 1, (E)o)].equals(o);
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
        if(contains(e))
            return false;
        if(size == value.length)
            value = Arrays.copyOf(value, size * 3 / 2 + 1);
        int index = binary_search_to_add(0, size, e);
        for(int i = size; i > index; --i)
            value[i] = value[i - 1];
        value[index] = e;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o))
            return false;
        int index = binary_search_to_find(0, size - 1, (E)o);
        for(int i = index; i < size - 1; ++i)
            value[i] = value[i + 1];
        --size;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            if(!contains(params[i]))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            add(params[i]);
        return prevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        for(int i = size - 1; i >= 0; --i)
            if(!c.contains(value[i]))
                remove(value[i]);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            if(contains(params[i]))
                remove(params[i]);
        return prevSize != size;
    }

    @Override
    public void clear() {
        size = 0;
    }
}
