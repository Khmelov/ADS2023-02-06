package by.it.group251002.sak.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyPriorityQueue<E> implements Deque<E> {
    private int minCapacity = 8;
    private E[] elements = (E[]) new Object[minCapacity];
    int size = 0;

    public void heapify(){
        int start = size - 1;
        while(start >= 0){
            siftDown(start);
            start--;
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int rightElement(int i) {
        return i * 2 + 2;
    }

    private int leftElement(int i) {
        return i * 2 + 1;
    }

    private void newSize() {
        E[] newArr = (E[]) new Object[size * 2 + 1];
        System.arraycopy(elements, 0, newArr, 0, size);
        elements = newArr;
    }

    private void siftDown(int i) {
        int left = leftElement(i);
        int right = rightElement(i);

        if (left >= size)
            return;

        int largest = left;
        if (right < size && ((Comparable<E>) elements[right]).compareTo(elements[left]) < 0)
            largest = right;

        if (((Comparable<E>) elements[largest]).compareTo(elements[i]) >= 0)
            return;

        E tmp = elements[largest];
        elements[largest] = elements[i];
        elements[i] = tmp;

        siftDown(largest);
    }

    private void siftUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = parent(i);
        if (((Comparable<E>) (elements[parent])).compareTo(elements[i]) < 0) {
            return;
        }

        E tmp = elements[parent];
        elements[parent] = elements[i];
        elements[i] = tmp;

        siftUp(parent);
    }

    public String toString(){
        StringBuilder res = new StringBuilder("[");
        String del = "";

        for (int i = 0; i < size; i++) {
            res.append(del).append(elements[i]);
            del = ", ";
        }

        return res.append("]").toString();
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        size = 0;
    }
    @Override
    public boolean add(E e) {
        if (size == elements.length)
            newSize();

        elements[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (size == 0) {
            return null;
        }

        size--;
        E elementToDelete = elements[0];
        elements[0] = elements[size];
        elements[size] = null;

        siftDown(0);

        return elementToDelete;
    }
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i]))
                return true;
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        E buf = elements[0];
        size--;
        elements[0] = elements[size];
        elements[size] = null;

        siftDown(0);

        return buf;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;
        return elements[0];
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] arrCollection = (E[]) new Object[c.size()];
        arrCollection = c.toArray(arrCollection);

        for (E e : arrCollection)
            if (!contains(e))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] arrCollection = (E[]) c.toArray();
        for (E e : arrCollection)
            add(e);

        return prevSize < size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean check = false;
        int i = 0;
        while (i < size) {
            if (c.contains(elements[i])) {
                System.arraycopy(elements, i+1, elements, i, size-i-1);
                size--;
                check = true;
            } else {
                i++;
            }
        }
        heapify();

        return check;
    }

    public boolean retainAll(Collection<?> c) {
        boolean check = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(elements[i])) {
                System.arraycopy(elements, i+1, elements, i, size-i-1);
                size--;
                check = true;
            } else {
                i++;
            }
        }
        heapify();

        return check;
    }
///////////////////////////////////////////////////////////////////////////
    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }





    @Override
    public boolean remove(Object o) {
        return false;
    }





    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
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
    public Iterator<E> descendingIterator() {
        return null;
    }
}
