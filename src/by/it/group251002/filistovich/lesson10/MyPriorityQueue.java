package by.it.group251002.filistovich.lesson10;


import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.*;

public class MyPriorityQueue <E extends Comparable<E>>  implements Queue<E> {
    int size = 0;
    E[] elements = (E[]) new Comparable[5];


    void swap(int a, int b){
        E temp = elements[a];
        elements[a] = elements[b];
        elements[b] = temp;
    }

    void siftDown(int index){
        int childIndex = index * 2 + 1;
        if ((childIndex + 1 < size) && (elements[childIndex].compareTo(elements[childIndex + 1]) > 0)){
            childIndex++;
        }
        if ((childIndex < size) && (elements[index].compareTo(elements[childIndex]) > 0)){
            swap(index, childIndex);
            siftDown(childIndex);
        }
    }

    void siftUp(int index){
        int parentIndex = (index - 2 + (index % 2)) / 2;
        if ((parentIndex > -1) && (elements[parentIndex].compareTo(elements[index]) > 0)){
            swap(index, parentIndex);
            siftUp(parentIndex);
        }


    }

    @Override
    public E poll() {
        if (size != 0){
            size--;
            swap(0, size);
            siftDown(0);
            return elements[size];
        }
        return null;
    }

    @Override
    public E element() {
        if (size != 0){
            return elements[0];
        }
        return null;
    }

    @Override
    public E peek() {
        return element();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++){
            sb.append(delimiter).append(elements[i]);
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;

    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
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
        if (size == elements.length){
            E[] newArr = (E[]) new Comparable[(size * 3 )/ 2 + 1];
            System.arraycopy(elements, 0, newArr,0, size);
            elements = newArr;
        }
        elements[size] = e;
        size++;
        siftUp(size - 1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] tempArr = (E[]) new Comparable[]{};
        tempArr = c.toArray(tempArr);
        if (tempArr.length <= size){
            for (int i = 0; i < tempArr.length; i++){
                if (!contains(tempArr[i])){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        E[] tempArr = (E[]) new Comparable[]{};
        tempArr = c.toArray(tempArr);
        for(int i = 0; i < tempArr.length; i++){
            add(tempArr[i]);
            flag  = true;
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        int prevsize = size;
        int left = 0;
        int i = 0;
        while (left < size){
            if (c.contains(elements[i])){
                size--;
                i++;
                flag = true;
            } else {
                elements[left] = elements[i];
                i++;
                left++;
            }
        }
        for (int j = size; j < prevsize; j++){
            elements[j] = null;

        }
        for (int j = size / 2; j > -1; j--){
            siftDown(j);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        int prevsize = size;
        int left = 0;
        int i = 0;
        while (left < size){
            if (!c.contains(elements[i])){
                size--;
                i++;
                flag = true;
            } else {
                elements[left] = elements[i];
                i++;
                left++;
            }
        }
        for (int j = size; j < prevsize; j++){
            elements[j] = null;

        }
        for (int j = size / 2; j > -1; j--){
            siftDown(j);
        }
        return flag;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
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


}