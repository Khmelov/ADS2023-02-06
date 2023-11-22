package by.it.group251002.lapus_vitaliy.lesson11;

import by.it.group251002.lapus_vitaliy.lesson09.ListC;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private ListC<E>[] elements = (ListC<E>[]) new ListC[]{};
    private Integer[] hashCodes = (Integer[]) new Integer[]{};

    private ListC<E>[] elementsToString = (ListC<E>[]) new ListC[]{null};


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
        return elementsToString[0].toString();
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
        if(elementsToString[0]==null)
            elementsToString[0]=new ListC<>();
        int hash=e.hashCode();
        for(int i=0;i<size;i++)
        {
            if(hash==hashCodes[i])
            {
                if(!(elements[i].contains(e)))
                {
                    elementsToString[0].add(e);
                    elements[i].add(e);
                    numSize++;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        elementsToString[0].add(e);
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
                    elementsToString[0].remove(o);
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
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < c.size(); i++) {
            if(!(contains(bufAr[i])))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(elementsToString[0]==null)
            elementsToString[0]=new ListC<>();
        int lastSize=numSize;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            add(bufAr[i]);
        }
        return lastSize!=numSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int lastSize=numSize;
        numSize=0;
        int bufSize=0;
        for (int i = size-1; i > -1; i--) {
            elements[i].retainAll(c);
            numSize+=elements[i].size();
            if(elements[i].size()==0)
            {
                elements[i]=elements[--size];
                elements[size]=null;
                hashCodes[i]=hashCodes[size];
                hashCodes[size]=null;
            }
            else{
                bufSize++;
            }
        }
        elementsToString[0].retainAll(c);
        size=bufSize;
        return lastSize!=numSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int lastSize=numSize;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            remove(bufAr[i]);
        }
        return lastSize!=numSize;
    }

    @Override
    public void clear() {
        for (int i=size-1; i>-1;i--)
        {
            elements[i].clear();
            elementsToString[0].clear();
            hashCodes[i]=null;

        }
        size=0;
        numSize=0;
    }
}
