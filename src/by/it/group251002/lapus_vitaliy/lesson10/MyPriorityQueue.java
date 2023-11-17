package by.it.group251002.lapus_vitaliy.lesson10;

import java.util.*;


public class MyPriorityQueue<E> implements Queue<E> {
    void siftup(E[] arr, int large) {
        int now=large;
        int high=(now-1) / 2;

        if ((now>=0)&&(((Comparable<E>)arr[high]).compareTo((E) arr[now])>0))
        {
            now=high;
        }

        if (now!=large)
        {
            E buf=arr[now];
            arr[now]=arr[large];
            arr[large]=buf;
            siftup(arr,now);
        }
    }

    void siftdown(E[] arr, int large, int max) {
        int now=large;
        int left=now*2+1;
        int right=2*now+2;

        if ((left<max) && (((Comparable<E>)arr[left]).compareTo(arr[now])<0))
        {
            now=left;
        }
        if ((right<max) && (((Comparable<E>)arr[right]).compareTo(arr[now])<0))
        {
            now=right;
        }

        if (now!=large)
        {
            E buf=arr[now];
            arr[now]=arr[large];
            arr[large]=buf;
            siftdown(arr,now,max);
        }
    }

    void heapify(E[] arr, int size)
    {
        for (int i = size / 2 - 1; i >= 0; i--)
            siftdown(arr, i, size);
    }




    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    public void balanceSize() {
        if(elements.length==size)
        {
            E[] newAr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elements, 0, newAr, 0, size);
            elements = newAr;
        }

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String razd = "";
        for (int i = 0; i < size; i++) {
            str.append(razd).append(elements[i]);
            razd = ", ";
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        if(Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(elements[i]))
                    return true;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o))
                    return true;
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
        elements[size++]=e;
        siftup(elements,size-1);
        return true;
    }

    public E remove(int index) {
        E elem = elements[index];
        size--;
        elements[index]=elements[size];
        elements[size] = null;
        siftdown(elements,index,size);
        siftup(elements,size-1);
        return elem;
    }

    @Override
    public boolean remove(Object o) {
        for(int index=0;index<size;index++) {
            if (Objects.equals(o, elements[index])) {
                elements[index] = elements[--size];
                siftdown(elements,index,size);
                siftup(elements,size-1);
                return true;
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
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            add(bufAr[i]);
        }
        return lastSize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int lastSize=size;
        for (int i = size-1; i > -1; i--) {
            if((c.contains(elements[i]))) {
                E elem = elements[i];
                System.arraycopy(elements, i+1, elements, i, size-i-1);
                size--;
                elements[size] = null;
            }
        }
        heapify(elements,size);
        return lastSize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int lastSize=size;
        for (int i = size-1; i > -1; i--) {
            if(!(c.contains(elements[i]))) {
                E elem = elements[i];
                System.arraycopy(elements, i+1, elements, i, size-i-1);
                size--;
                elements[size] = null;
            }
        }
        heapify(elements,size);
        return lastSize!=size;
    }

    @Override
    public void clear() {
        for (int i=size-1; i>-1;i--)
        {
            elements[i]=null;
        }
        size=0;
    }

    @Override
    public boolean offer(E e) {
        balanceSize();
        elements[size++]=e;
        siftup(elements,size-1);
        return true;
    }

    @Override
    public E remove() {
    if(size==0){return null;}
    return poll();
    }

    @Override
    public E poll() {
        if(size==0)
        {return null;}
        E buf=elements[0];
        elements[0]=elements[size-1];
        size--;
        elements[size]=null;
        siftdown(elements,0,size);
        return buf;
    }

    @Override
    public E element() {
        if(size==0)
        {return null;}
        return elements[0];
    }

    @Override
    public E peek() {
        if(size==0)
        {return null;}
        return elements[0];
    }
}
