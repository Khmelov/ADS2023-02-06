package by.it.group251002.yanucevich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    private E[] elems = (E[]) new Object[]{};
    private int size=0;

    private void resizeArr(){
        E[] newArr = (E[]) new Object[size*3/2+1];
        System.arraycopy(elems,0,newArr,0,size);
        elems=newArr;
    }
    private boolean compare(E elem1, E elem2){
        return ((Comparable<E>)elem1).compareTo(elem2) <= 0;
    }

    private void swap(int i1, int i2){
        E temp=elems[i1];
        elems[i1]=elems[i2];
        elems[i2]=temp;
    }
    private void siftUp(int index){
        while(index!=0&&!compare(elems[(index-1)>>1], elems[index])) {
            swap(index, (index-1)>>1);
            index=(index-1)>>1 ;
        }
    }

    private void heapify() {
        int i = (size - 1) / 2;
        while (i >=0) {
            siftDown(i);
            i--;
        }
    }
    private int leftChild(int value){
        return value*2+1;
    }
    private int rightChild(int value){
        return (value+1)*2;
    }
    private void siftDown(int index){
        int lastIndex=size-1;
        int minChild, tempIndex;
        // while children exist
        while(leftChild(index)<=lastIndex){
            minChild=leftChild(index);
            tempIndex=rightChild(index);
            if(tempIndex<=lastIndex){
                if(!compare(elems[minChild],elems[tempIndex])){
                    minChild=tempIndex;
                }
            }
            if(compare(elems[index],elems[minChild])){
                return;
            }
            else{
                swap(index,minChild);
                index=minChild;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder("[");
        String delimiter = "";
        for(int i=0;i<size;i++){
            res.append(delimiter).append(elems[i]);
            delimiter=", ";
        }

        res.append("]");
        return res.toString();
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
        for(int i=0;i<size;i++){
            if (elems[i].equals(o)){
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
        if(size==elems.length) {
            resizeArr();
        }
        elems[size]=e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for(int i=0;i<size;i++){
            if (elems[i].equals(o)){
                swap(i,size-1);
                size--;
                siftDown(i);
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c) {
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevsize=size;
        for (E o:c) {
            add(o);
        }
        return prevsize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevsize=size;
        int i=0,j=0;
        while(i!=prevsize){
            if(!c.contains(elems[i])) {
                elems[j++] = elems[i];
            }
            i++;
        }
        size=j;
        heapify();
        return prevsize!=size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize=size,j=0,i=0;
        while(i!=prevSize){
            if(c.contains(elems[i])){
                elems[j++]=elems[i];
            }
            i++;
        }
        size=j;
        heapify();
        return prevSize!=size;
    }

    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            elems[i]=null;
        }
        size=0;
    }

    @Override
    public boolean offer(E e) {
        if(size==elems.length){
            resizeArr();
        }
        elems[size]=e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {
        if(size==0){
            return null;
        }
        E elem=elems[0];
        swap(0,size-1);
        size--;
        siftDown(0);
        return elem;
    }

    @Override
    public E poll() {
        if(size==0) {
            return null;
        }
        E elem=elems[0];
        swap(0,size-1);
        size--;
        siftDown(0);
        return elem;
    }

    @Override
    public E element() {
        return elems[0];
    }

    @Override
    public E peek() {
        return elems[0];
    }
}
