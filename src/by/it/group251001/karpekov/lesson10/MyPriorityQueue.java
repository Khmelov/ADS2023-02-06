package by.it.group251001.karpekov.lesson10;

import java.util.*;

public class MyPriorityQueue<E> implements Queue<E> {
    private Object[] arr = new Object[1];
    private int siz = 0;

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < siz - 1; i++)
            res.append(arr[i].toString()).append(", ");
        return res + arr[siz - 1].toString() + "]";
    }

    @Override
    public int size() {
        return siz;
    }

    @Override
    public boolean isEmpty() {
        return siz == 0;
    }

    private void swap(int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private void heapify(int i) {
        int leftChild;
        int rightChild;
        int largestChild;
        while (true) {
            leftChild = 2 * i + 1;
            rightChild = leftChild + 1;
            largestChild = i;
            if (leftChild < siz && ((Comparable<? super E>) arr[leftChild]).compareTo((E) arr[largestChild]) < 0)
                largestChild = leftChild;
            if (rightChild < siz && ((Comparable<? super E>) arr[rightChild]).compareTo((E) arr[largestChild]) < 0)
                largestChild = rightChild;
            if (largestChild == i)
                break;
            swap(i,largestChild);
            i = largestChild;
        }
    }

    public void rebuildHeap(Object[] newArr)
    {
        if(newArr.length == 0)
            arr = new Object[1];
        else
            arr = newArr;
        siz = newArr.length;
        for (int i = siz / 2 - 1; i >= 0; i--)
            heapify(i);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < siz; i++)
            if (arr[i].equals(o))
                return true;
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
        if (arr.length == siz)
            arr = Arrays.copyOf(arr, siz * 2);
        arr[siz++] = e;
        int i = siz - 1, par = (i-1)/2;
        while(i > 0 && ((Comparable<? super E>) arr[i]).compareTo((E) arr[par]) < 0){
            swap(i,par);
            i = par;
            par = (i-1)/2;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o : c)
            add(o);
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int kol = 0;
        for (int i = 0; i < siz; i++)
            if (!c.contains(arr[i]))
                kol++;
        if(kol == siz)
            return false;
        Object[] newArr = new Object[kol];
        int ci = 0;
        for (int i = 0; i < size(); i++)
            if (!c.contains(arr[i]))
                newArr[ci++] = arr[i];
        rebuildHeap(newArr);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int kol = 0;
        for (int i = 0; i < siz; i++)
            if (c.contains(arr[i]))
                kol++;
        if(kol == siz)
            return false;
        Object[] newArr = new Object[kol];
        int ci = 0;
        for (int i = 0; i < size(); i++)
            if (c.contains(arr[i]))
                newArr[ci++] = arr[i];
        rebuildHeap(newArr);
        return true;
    }

    @Override
    public void clear() {
        arr = new Object[1];
        siz = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        return poll();
    }

    @Override
    public E poll() {
        if(isEmpty())
            return null;
        E toRet = (E) arr[0];
        swap(0,--siz);
        heapify(0);
        return toRet;
    }

    @Override
    public E element() {
        if(isEmpty())
            throw new NoSuchElementException();
        return (E) arr[0];
    }

    @Override
    public E peek() {
        if(isEmpty())
            return null;
        return (E) arr[0];
    }
}
