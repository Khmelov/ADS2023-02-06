package by.it.group251002.zinovenko.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private E[] elements = (E[]) new Object[]{};
    private int size = 0;

    private void buildHeap()
    {
        for (int i = size / 2; i >= 0; i--)
        {
            heapify(i);
        }
    }

    private void heapify(int i)
    {
        int leftChild;
        int rightChild;
        int largestChild;

        for (; ; )
        {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < size && ((Comparable<? super E>) elements[leftChild]).compareTo((E) elements[largestChild]) < 0)
            {
                largestChild = leftChild;
            }

            if (rightChild < size && ((Comparable<? super E>) elements[rightChild]).compareTo((E) elements[largestChild]) < 0)
            {
                largestChild = rightChild;
            }

            if (largestChild == i)
            {
                break;
            }

            E temp = elements[i];
            elements[i] = elements[largestChild];
            elements[largestChild] = temp;
            i = largestChild;
        }
    }

    private void heapifyUp(int i) {
        int x = 0;
        if (i !=0) x =(i - 1) / 2;
        if (i > 0 && ((Comparable<? super E>) elements[x]).compareTo((E) elements[i]) > 0) {
            E temp = elements[i];
            elements[i] = elements[x];
            elements[x] = temp;
            heapifyUp(x);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder("[");
        String del = "";
        for (int i = 0; i< size; i++){
            sb.append(del).append(elements[i]);
            del = ", ";
        }
        sb.append("]");
        return sb.toString();
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
        for(int i = 0; i<size;i++) {
            if (o.equals(elements[i])) {
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
        if (size == elements.length) elements = Arrays.copyOf(elements,size*3/2+1);
        elements[size] = e;
        heapifyUp(size);
        size++;
        return true;
    }




    @Override
    public boolean remove(Object o) {
        for(int i = 0; i<size;i++){
            if (o.equals(elements[i])){
                System.arraycopy(elements,i+1,elements ,i,size - 1 -i);
                size--;
                buildHeap();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] arr = c.toArray();
        for (int i = 0; i<arr.length;i++){
            if (!contains(arr[i])) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int newSize = size;
        for (E obj : c)
            this.add(obj);
        return newSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int newSize = 0;
        E[] tmp = (E[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                tmp[newSize++] = elements[i];
            }
        }
        elements = tmp;
        boolean res = newSize != size;
        size = newSize;
        buildHeap();
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int newSize = 0;
        E[] tmp = (E[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                tmp[newSize++] = elements[i];
            }
        }
        elements = tmp;
        boolean res = newSize != size;
        size = newSize;
        buildHeap();
        return res;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        E tmp = elements[0];
        elements[0] = elements[size-1];
        size--;
        heapify(0);
        return tmp;
    }

    @Override
    public E element() {
        return elements[0];
    }

    @Override
    public E peek() {
        return elements[0];
    }
}
