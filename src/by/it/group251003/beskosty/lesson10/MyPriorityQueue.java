package by.it.group251003.beskosty.lesson10;
import java.util.*;

public class MyPriorityQueue<E> implements Queue<E> {
    private E[] Queue = (E[]) new Object[0];
    private int size = 0;

    public void heapifyUp(int index) {
        boolean flag = true;
        while (index >= 0 && flag) {
            int parentIndex = (index - 1) / 2;
            if ((int)Queue[index] < (int)Queue[parentIndex]) {
                E temp = Queue[index];
                Queue[index] = Queue[parentIndex];
                Queue[parentIndex] = temp;
                index = parentIndex;
            } else {
                flag = false;
            }
        }
    }

    public void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;
        boolean flag = true;
        while (flag) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;

            if (leftChild < size && (int)Queue[leftChild] < (int)Queue[minIndex]) {
                minIndex = leftChild;
            }

            if (rightChild < size && (int)Queue[rightChild] < (int)Queue[minIndex]) {
                minIndex = rightChild;
            }

            if (minIndex != index) {
                E temp = Queue[index];
                Queue[index] = Queue[minIndex];
                Queue[minIndex] = temp;
                index = minIndex;
            } else {
                flag = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        String temp = "";
        for (int i = 0; i < size; i++){
            string.append(temp).append(Queue[i]);
            temp = ", ";
        }
        string.append("]");
        return string.toString();
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
        return (indexOf(o)>-1);
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
        if (size == Queue.length) {
        E[] newQueue = (E[]) new Object[2 * Queue.length + 1];
        System.arraycopy(Queue, 0, newQueue, 0, size);
        Queue = newQueue;
    }
        Queue[size++] = e;
        heapifyUp(size-1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int Oind = indexOf(o);
        if (Oind < 0) {
            return false;
        }

        remove(Oind);

        return true;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (Queue[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c){
            if (!(contains(obj))) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        for (E NewEl : c){
            add(NewEl);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean Flag = false;
        for(int i = 0; i < size; i++){
            if(c.contains(Queue[i])){
                remove(i);
                Flag = true;
                i--;
            }
        }
        return Flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        while (i < size){
            if(!(c.contains(Queue[i]))) remove(i);
            else i++;
        }
        return true;
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            remove(size--);
        }
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        E temp = Queue[0];
        remove(0);
        return temp;
    }
    public boolean remove(int index) {
        if (!isEmpty()){
        --size;
        E temp = Queue[size];
        Queue[size] = Queue[index];
        Queue[index] = temp;
        heapifyDown(index);
        return true;}
    else return false;}

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return Queue[0];
    }

    @Override
    public E peek() {
        return Queue[0];
    }
}
