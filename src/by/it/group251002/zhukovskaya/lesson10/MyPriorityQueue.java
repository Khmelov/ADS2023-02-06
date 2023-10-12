package by.it.group251002.zhukovskaya.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    static private int minCapacity=1;
    private E[] mas = (E[]) new Object[minCapacity] ;
    private int size=0;
    private int findLeft(int index) {
        return 2*index+1;
    }
    private int findRight(int index) {
        return 2*index+2;
    }
    private int findParent(int index) {
        return (index-1)/2;
    }

    private void down(int index) {
        int left=findLeft(index);
        int right=findRight(index);
        if(left>=size) {
            return;
        }
        int child=left;
        if (right<size && ((Comparable<E>)mas[right]).compareTo(mas[left])<0) {
            child=right;
        }
        if (((Comparable<E>)mas[child]).compareTo(mas[index])>=0) {
            return;
        }
        E temp = mas[index];
        mas[index]=mas[child];
        mas[child]=temp;
        down(child);
    }

    private void up(int index) {
        if (index==0)
            return;
        int p=findParent(index);
        if (((Comparable<E>)mas[p]).compareTo(mas[index])<0) {
            return;
        }
        E temp = mas[index];
        mas[index]=mas[p];
        mas[p]=temp;
        up(p);
    }
    public void heapify() {
        int start=size-1;
        while (start>=0){
            down(start);
            start--;
        }
    }
    private void grow() {
        E[] newMas=(E[]) new Comparable[size*2];
        System.arraycopy(mas,0,newMas,0,size);
        mas=newMas;
    }
    @Override
    public String toString() {
        if (size==0) {
            return "[]";
        }
        else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size - 1; i++) {
                sb.append(mas[i]).append(", ");
            }
            sb.append(mas[size - 1]).append("]");
            return sb.toString();
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        size=0;
    }

    @Override
    public boolean add(E e) {
        if (size==mas.length) {
            grow();
        }
        if (size==0) {
            mas[0]=e;
            size++;
            return true;
        }
        mas[size]=e;
        up(size);
        size++;
        return true;
    }
    @Override
    public E remove() {
        if (size==0) {
            return null;
        }
        E e=mas[0];
        mas[0]=mas[size-1];
        mas[size-1]=null;
        size--;
        down(0);
        return e;
    }
    @Override
    public boolean contains(Object o) {
        if (o==null) {
            for (int i=0; i<size;i++) {
                if (mas[i]==null) {
                    return true;
                }
            }
        }
        else {
        for (int i=0; i<size;i++) {
            if (o.equals(mas[i])) {
                return true;
            }
        }
        }
        return false;
    }
    @Override
    public boolean offer(E e) {
        if (size==mas.length) {
            grow();
        }
        if (size==0) {
            mas[0]=e;
            size++;
            return true;
        }
        mas[size]=e;
        up(size);
        size++;
        return true;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        if (size==0)
            return null;
        return mas[0];
    }

    @Override
    public E peek() {
        if (size==0) {
            return null;
        }
        return mas[0];

    }
    @Override
    public boolean isEmpty() {
        if (size==0)
            return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] newMas=(E[]) c.toArray();
        for (int i=0;i<c.size(); i++) {
            if (!contains(newMas[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag=false;
        int flagSize=size;
        E[] newMas=(E[]) c.toArray();
        for (int i=0;i<c.size(); i++) {
            add(newMas[i]);
        }
        if (flagSize<size)
            flag=true;
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag=false;
        int i=0;
        while (i<size) {
            if (c.contains(mas[i])) {
                System.arraycopy(mas, i + 1, mas, i, size - i - 1);
                size--;
                flag = true;
            } else {
                i++;
            }
        }
        heapify();
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag=false;
        int i=0;
        while (i<size) {
            if (!c.contains(mas[i])) {
                System.arraycopy(mas, i + 1, mas, i, size - i - 1);
                size--;
                flag = true;
            } else {
                i++;
            }
        }
            heapify();
        return flag;
    }
    ////////////////////////////////////////


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (o==null) {
            for (int i=0;i<size;i++) {
                if (mas[i]==null) {
                    mas[i]=mas[--size];
                    down(i);
                    return true;
                }
            }
        }
        else {
            for (int i=0;i<size;i++) {
                if (o.equals(mas[i])) {
                    mas[i]=mas[--size];
                    down(i);
                    return true;
                }
            }
        }
        return false;
    }



}
