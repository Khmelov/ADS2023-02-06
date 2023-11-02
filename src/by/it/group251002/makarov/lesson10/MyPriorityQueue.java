package by.it.group251002.makarov.lesson10;

import java.util.*;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {


    private E[] queue;
    private int capacity = 10;
    private int size = 0;

    public MyPriorityQueue(){
        queue =(E[])new Comparable[capacity];
    }

    public void siftUp(int index){
        while(index > 0){
            int parentInd = (index - 1)/2;
            if(queue[index].compareTo(queue[parentInd])>=0){
                break;
            }
            swap(index,parentInd);
            index=parentInd;
        }
    }

    public void siftDown(int index){
        int leftChild;
        int rightChild;
        int minChild;
        while(true){
            leftChild = 2*index;
            rightChild = 2*index+1;
            minChild = index;
            if(leftChild<size && queue[leftChild].compareTo(queue[minChild])<0){
                minChild = leftChild;
            }
            if(rightChild<size&&queue[rightChild].compareTo(queue[minChild])<0){
                minChild = rightChild;
            }
            if(index == minChild){
                break;
            }
            swap(index,minChild);
            index = minChild;
        }
    }

    public void swap(int i1,int i2){
        E temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0;i<queue.length;i++){
            sb.append(queue[i]);
            if(i!=queue.length-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return queue.length;
    }

    @Override
    public boolean isEmpty() {
        return queue.length==0;
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
    public boolean remove(Object o) {
        return false;
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
        if(size+c.size()>=capacity){
            capacity=(capacity+c.size())*3/2;
            E[] newArr = (E[])new Comparable[capacity];
            System.arraycopy(queue,0,newArr,0,size);
            queue = newArr;
        }
        for(E o:c){
            offer(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean rem = false;
        for(Object o:c){
            if(contains(o)){
                remove(o);
                rem = true;
            }
        }
        heapify();
        return rem;
    }
    public void heapify(){
        for(int i = size / 2 - 1; i>=0; i--){
            siftDown(i);
        }
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean rem = false;
        for(Object o:c){
            if(!contains(o)){
                remove(o);
                rem = true;
            }
        }
        heapify();
        return rem;
    }

    @Override
    public void clear() {
        size=0;
        queue = (E[])new Object[capacity];
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
