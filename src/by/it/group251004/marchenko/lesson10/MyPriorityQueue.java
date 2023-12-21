package by.it.group251004.marchenko.lesson10;

import java.util.*;

public class MyPriorityQueue <E extends Comparable<E>>  implements Queue<E> {
    int size;
    int maxSize;
    E[] data;
    MyPriorityQueue(){
        maxSize = 5;
        data = (E[]) new Comparable[maxSize];
    }
    private void Swap (int indexA, int indexB){
        E temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }
    private void SiftDown(int index){
        int indexChild = index * 2 + 1;

        if((indexChild + 1) < size && data[indexChild + 1].compareTo(data[indexChild]) < 0){
            indexChild++;
        }
        if (indexChild < size && data[indexChild].compareTo(data[index]) < 0) {
            Swap(index, indexChild);
            SiftDown(indexChild);
        }
    }

    private void SiftUp(int index){
        int indexParent = (index - 1) / 2;
        if (index != 0 && data[index].compareTo(data[indexParent]) < 0){
            Swap(index, indexParent);
            SiftUp(indexParent);
        }
    }

    private void grow(){
        maxSize *= 2;
        E[] newarray = (E[]) new Comparable[maxSize];
        for (int i = 0; i < size; i++){
            newarray[i] = data[i];
        }
        data = newarray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimeter = "";
        for (int i = 0; i < size; i++) {
            sb.append(delimeter).append(data[i]);
            delimeter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++){
            if (data[i].equals(o)){
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
        if(size == maxSize){
            grow();
        }
        data[size++] = e;
        SiftUp(size - 1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                data[i] = data[--size];
                data[size] = null;
                SiftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E i:c){
            add(i);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        int index = 0;
        while (index < size) {
            if (c.contains(data[i])) {
                i++;
                size--;
                changed = true;
            } else {
                data[index] = data[i];
                index++;
                i++;
            }
        }
        for (int k = size; k < data.length; k++){
            data[k] = null;
        }
        for (int j = size / 2; j >= 0; j--)
            SiftDown(j);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        int i = 0;
        int index = 0;
        while (index < size){
            if (!c.contains(data[i])) {
                i++;
                size--;
                changed = true;
            } else {
                data[index] = data[i];
                index++;
                i++;
            }
        }
        for (int j = size / 2; j >= 0; j--)
            SiftDown(j);
        return changed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        E result = data[0];
        size--;
        Swap(0,size);
        SiftDown(0);
        return result;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E result = data[0];
        size--;
        Swap(0,size);
        SiftDown(0);
        return result;
    }

    @Override
    public E element() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return data[0];
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return data[0];
    }
}
