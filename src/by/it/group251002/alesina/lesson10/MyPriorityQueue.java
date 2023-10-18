package by.it.group251002.alesina.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    private E[] Queue = (E[]) new Object[0];
    private int size = 0;

    private void siftDown(int index) {
        int leftChild, rightChild, indMin;
        boolean f = true;
        while (f) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            indMin = index;

            if (leftChild < size && (int)Queue[leftChild] < (int)Queue[indMin]) {
                indMin = leftChild;
            }

            if (rightChild < size && (int)Queue[rightChild] < (int)Queue[indMin]) {
                indMin = rightChild;
            }

            if (indMin != index) {
                E temp = Queue[index];
                Queue[index] = Queue[indMin];
                Queue[indMin] = temp;
                index = indMin;
            } else {
                f = false;
            }
        }
    }


    private void siftUp(int index) {
        boolean f = true;
        while (index >= 0 && f) {
            int parentIndex = (index - 1) / 2;
            if ((int)Queue[index] < (int)Queue[parentIndex]) {
                E temp = Queue[index];
                Queue[index] = Queue[parentIndex];
                Queue[parentIndex] = temp;
                index = parentIndex;
            } else {
                f = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        String comma = "";
        for (int i = 0; i < size;  i++) {
            str.append(comma).append(Queue[i]);
            comma = ", ";
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
        return size == 0;
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            remove(size--);
        }
    }
    @Override
    public boolean add(E e) {
        if (size == Queue.length) {
            E[] tmp = (E[]) new Object[2 * Queue.length + 1];
            System.arraycopy(Queue, 0, tmp, 0, size);
            Queue = tmp;
        }
        Queue[size++] = e;
        siftUp(size-1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty())
            return false;
        int ind=-1;
        for (int i = 0; i < size; i++){
            if (Queue[i]!=null && Queue[i].equals(o))
                ind=i;
        }
        if (ind < 0) {
            return false;
        }
        E tmp = Queue[size];
        Queue[size] = Queue[ind];
        Queue[ind] = tmp;
        siftDown(ind);
        --size;

        return true;
    }

    @Override
    public boolean contains(Object o) {
        int ind=-1;
        for (int i = 0; i < size; i++){
            if (Queue[i].equals(o))
                ind=i;
        }
        if (ind < 0) {
            return false;
        }
        else
            return true;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        E[] collArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if (!contains(collArr[i])) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty())
            return false;
        E[] collArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add(collArr[i]);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for(int i = 0; i < size; i++){
            if(c.contains(Queue[i])){
                remove(i);
                isRemoved = true;
                i--;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(Queue[i])) {
                remove(Queue[i]);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean offer(E e) {  //добавляет элемент и возвращает true, если он добавлен

        return add(e);
    }
    @Override
    public E peek() {  //возвращает значение 1го элемента
        if (isEmpty()) {
            return null;
        }

        return Queue[0];
    }

    @Override
    public E element() {
        if (isEmpty()) {
            return null;
        }

        return Queue[0];
    }
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E elem = (E) Queue[0];

        size--;
        Queue[0] = Queue[size];
        Queue[size] = null;

        siftDown(0);

        return elem;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        E elem = (E) Queue[0];

        size--;
        Queue[0] = Queue[size];
        Queue[size] = null;

        siftDown(0);

        return elem;
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
    public <E> E[] toArray(E[] a) {
        return null;
    }

}
