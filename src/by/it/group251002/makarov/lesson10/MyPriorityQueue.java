package by.it.group251002.makarov.lesson10;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {


    private E[] queue;
    private int capacity = 10;
    private int size = 0;

    public MyPriorityQueue(){
        queue =(E[])new Comparable[capacity];
    }

//    public void siftUp(int index){
//        while(index > 0){
//            int parentInd = (index - 1)/2;
//            if(queue[index].compareTo(queue[parentInd])>=0){
//                break;
//            }
//            swap(index,parentInd);
//            index=parentInd;
//        }
//    }
//
//    public void siftDown(int index){
//        int leftChild;
//        int rightChild;
//        int minChild;
//        while(true){
//            leftChild = 2*index+1;
//            rightChild = 2*index+2;
//            minChild = index;
//            if(leftChild<size && queue[leftChild].compareTo(queue[minChild])<0){
//                minChild = leftChild;
//            }
//            if(rightChild<size&&queue[rightChild].compareTo(queue[minChild])<0){
//                minChild = rightChild;
//            }
//            if(index == minChild){
//                break;
//            }
//            swap(index,minChild);
//            index = minChild;
//        }
//    }
private int compare(E e1, E e2) {
    if (e1 == null && e2 == null) {
        return 0;
    } else if (e1 == null) {
        return -1;
    } else if (e2 == null) {
        return 1;
    }
    return e1.compareTo(e2);
}
public void siftUp(int index) {
    while (index > 0) {
        int parentIndex = (index - 1) / 2;
        if (compare(queue[index], queue[parentIndex]) >= 0) {
            break;
        }
        swap(index, parentIndex);
        index = parentIndex;
    }
}

    public void siftDown(int index) {
        int leftChild, rightChild, minChild;
        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minChild = index;

            if (leftChild < size && compare(queue[leftChild], queue[minChild]) < 0) {
                minChild = leftChild;
            }
            if (rightChild < size && compare(queue[rightChild], queue[minChild]) < 0) {
                minChild = rightChild;
            }

            if (index == minChild) {
                break;
            }

            swap(index, minChild);
            index = minChild;
        }
    }


    public void swap(int i1,int i2){
        E temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(queue[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
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
        for(int i = 0; i<size; i++){
            if(queue[i].equals(o)){
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

        return offer(e);
    }

    @Override
//    public boolean remove(Object o) {
//        for(int i = 0; i<size;i++){
//            if(o.equals(queue[i])){
//                removeAt(i);
//            }
//        }
//        return false;
//    }
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(queue[i], o)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        queue[index] = queue[size - 1];
        queue[size - 1] = null;
        size--;

        if (index < size) {
            siftDown(index);
            siftUp(index);
        }
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c){
            if(!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        for(E o:c){
            offer(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int size = this.size;
        int i = 0, j = 0;
        while (i != size) {
            if (!collection.contains(queue[i]))
                queue[j++] = queue[i];
            i++;
        }
        this.size=j;
        heapify();
        return j!=size;
    }
    public void heapify(){
        for(int i = (size-1) / 2; i>=0; i--){
            siftDown(i);
        }
    }
    @Override
    public boolean retainAll(Collection<?> collection) {
        int size=this.size;
        int i = 0, j = 0;
        while (i != size) {
            if (collection.contains(queue[i]))
                queue[j++] = queue[i];
            i++;
        }
        this.size=j;
        heapify();
        return j!=size;
    }

    @Override
    public void clear() {
    for(int i = 0; i<size; i++){
        queue[i]=null;
    }
    size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (e==null){
            return false;
        }
        if (size==capacity){
            capacity=capacity*3/2;
            E[] newArr = (E[]) new Comparable[capacity];
            System.arraycopy(queue,0,newArr,0,size);
            queue = newArr;
        }
        queue[size++]=e;
        siftUp(size-1);
        return true;
    }

    @Override
    public E remove() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return poll();
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E root = queue[0];
        size--;
        queue[0]=queue[size];
        siftDown(0);
        queue[size]=null;
        return root;
    }

    @Override
    public E element() {
        if (!isEmpty())
            return queue[0];
        throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return queue[0];
    }
}
