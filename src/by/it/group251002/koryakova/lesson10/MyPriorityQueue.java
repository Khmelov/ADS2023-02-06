package by.it.group251002.koryakova.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    transient E[] elementData;

    private int size;
    int capacity = 10;

    private int newCapacity(int oldCapacity){
        capacity = oldCapacity << 2;
        return capacity;
    }

    private void grow(int oldCap){
        elementData = Arrays.copyOf(elementData, newCapacity(oldCap));
    }

    public MyPriorityQueue(){
        elementData = (E[]) (new Object[capacity]);
        size = 0;
    }

    private int parent(int i){
        if (i == 0){
            return 0;
        }
        return (i - 1) / 2;
    }

    private int left(int i){
        return (2 * i + 1);
    }

    private int right(int i){
        return (2 * i + 2);
    }

    void swap(int x, int y){
        E temp = elementData[x];
        elementData[x] = elementData[y];
        elementData[y] = temp;
    }

    private void DownHeap(int i){
        int left = left(i);
        int right = right(i);
        int largest = i;
        if ((left < size) && (((Comparable<? super E>) elementData[left]).compareTo((E) elementData[i]) < 0)){
            largest = left;
        }
        if ((right < size) && (((Comparable<? super E>) elementData[right]).compareTo((E) elementData[largest]) < 0)){
            largest = right;
        }
        if (largest != i){
            swap(i, largest);
            DownHeap(largest);
        }
    }

    private void UpHeap(int i){
        if((i > 0) && (((Comparable<? super E>) elementData[parent(i)]).compareTo((E) elementData[i]) > 0)){
            swap(i, parent(i));
            UpHeap(parent(i));
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder("[");
        int i;
        for (i = 0; i < size; i++) {
            res.append(elementData[i].toString()).append(", ");
        }
        if (size != 0){
            res.delete(res.length() - 2, res.length());
        }
        return res.append(']').toString();
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public void clear(){
        int i;
        for (i = 0; i < size; i++){
            elementData[i] = null;
        }
        size = 0;
    }
    @Override
    public boolean add(E e){
        if (size == capacity){
            grow(size + 1);
        }
        elementData[size] = e;
        size = size + 1;
        UpHeap(size - 1);
        return true;
    }
    @Override
    public boolean contains(Object o){
        int i;
        for (i = 0; i < size; i++){
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean offer(E e){
        add(e);
        return true;
    }
    @Override
    public E remove(){
        return poll();
    }
    @Override
    public E poll(){
        E temp = elementData[0];
        size = size - 1;
        elementData[0] = elementData[size];
        DownHeap(0);
        return temp;
    }
    @Override
    public E peek(){
        return elementData[0];
    }
    @Override
    public E element(){
        return elementData[0];
    }
    @Override
    public boolean isEmpty(){
        return (size == 0);
    }
    @Override
    public Iterator<E> iterator(){
        return null;
    }
    @Override
    public Object[] toArray(){
        return new Object[0];
    }
    @Override
    public <T> T[] toArray(T[] a){
        return null;
    }
    @Override
    public boolean remove(Object o){
        int i = 0;
        for (; !elementData[i].equals(o); i++) ;
        elementData[i] = elementData[--size];
        DownHeap(i);
        return true;
    }
    @Override
    public boolean containsAll(Collection<?> c){
        boolean rez = true;
        for (Object obj : c.toArray()){
            rez = rez && this.contains(obj);
        }
        return rez;
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        int newSize = size;
        for (E obj : c) {
            this.add(obj);
        }
        return (newSize != size);
    }
    @Override
    public boolean removeAll(Collection<?> c){
        int newSize = 0;
        capacity = size;
        E[] tempData = (E[]) (new Object[size]);
        int i;
        for (i = 0; i < size; i++){
            if (!c.contains(elementData[i])){
                tempData[newSize] = elementData[i];
                newSize = newSize + 1;
            }
        }
        elementData = tempData;
        boolean rez = (newSize != size);
        size = newSize;
        for (i = size / 2; i >= 0; i--) {
            DownHeap(i);
        }
        return rez;
    }
    @Override
    public boolean retainAll(Collection<?> c){
        int newSize = 0;
        capacity = size;
        E[] tempData = (E[]) (new Object[size]);
        int i;
        for (i = 0; i < size(); i++){
            if (c.contains(elementData[i])){
                tempData[newSize] = elementData[i];
                newSize = newSize + 1;
            }
        }
        elementData = tempData;
        boolean rez = (newSize != size);
        size = newSize;
        for (i = size / 2; i >= 0; i--) {
            DownHeap(i);
        }
        return rez;
    }
}