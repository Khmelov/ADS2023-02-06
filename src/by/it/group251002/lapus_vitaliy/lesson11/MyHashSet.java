package by.it.group251002.lapus_vitaliy.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import by.it.group251002.lapus_vitaliy.lesson09.ListC;

public class MyHashSet<E> implements Set<E> {

    private ListC<E>[] elements = (ListC<E>[]) new ListC[]{};
    private Integer[] hashCodes = (Integer[]) new Integer[]{};

    int size=0;

    int numSize=0;

    //balance size ne pochinen
    public void balanceSize() {
        if(elements.length==size)
        {
            ListC<E>[] newAr = (ListC<E>[]) new ListC[size*3/2+1];
            System.arraycopy(elements, 0, newAr, 0, size);
            elements = newAr;
            Integer[] newHash = (Integer[]) new Integer[size*3/2+1];
            System.arraycopy(hashCodes, 0, newHash, 0, size);
            hashCodes = newHash;
        }

    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String razd = "";
        for (int i = 0; i < size; i++) {
            str.append(razd).append((elements[i]).toString().substring(1,(elements[i]).toString().length()-1));
            razd = ", ";
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public int size() {
        return numSize;
    }

    @Override
    public boolean isEmpty() {
        return numSize==0;
    }

    @Override
    public boolean contains(Object o) {
        int hash=o.hashCode();
        for (int i = 0; i < size; i++) {
            if (hash==hashCodes[i]) {
                return elements[i].contains(o);
            }
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
        balanceSize();
        int hash=e.hashCode();
        for(int i=0;i<size;i++)
        {
            if(hash==hashCodes[i])
            {
                if(!(elements[i].contains(e)))
                {
                    elements[i].add(e);
                    numSize++;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        elements[size] = new ListC<E>();
        elements[size].add(e);
        hashCodes[size]=hash;
        numSize++;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash=o.hashCode();
        for(int i=0;i<size;i++)
        {
            if(hash==hashCodes[i])
            {
                if((elements[i].contains(o)))
                {
                    elements[i].remove(o);
                    numSize--;
                    if(elements[i].size()==0)
                    {
                        elements[i]=elements[--size];
                        elements[size]=null;
                        hashCodes[i]=hashCodes[size];
                        hashCodes[size]=null;
                    }
                    return true;
                }
                else{
                    return false;
                }
            }
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
        for (int i=size-1; i>-1;i--)
        {
            elements[i].clear();
            hashCodes[i]=null;

        }
        size=0;
        numSize=0;
    }
}
