package by.it.group251002.samoilenko.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private E[] queue;
    private int size;
    private int capacity;

    MyPriorityQueue(){
        size=0;
        capacity=10;
        queue=(E[]) new Comparable[capacity];
    }

    @Override
    public String toString() {
        String res=new String();
        res+="[";
        for(int i=0;i<size-1;i++)
            res=res+this.queue[i]+", ";
        if(size>0)
            res+=this.queue[size-1];
        res+="]";
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i=0;i<size;i++){
            queue[i]=null;
        }
        size=0;
    }

    void siftDown(int i) {
        int min,temp;
        while (i*2+1<size) {
            int left = 2 * i + 1;
            int right = i * 2 + 2;
            min = left;
            temp = right;
            if (temp <= size - 1) {
                if ((queue[min].compareTo(queue[temp]) > 0))
                    min = temp;
            }
            if(queue[i].compareTo(queue[min])<=0){
                return;
            }
            E tempEl=queue[i];
            queue[i]=queue[min];
            queue[min]=tempEl;
            i=min;
        }
    }

    void siftUp(int i) {
        while(queue[i].compareTo(queue[(i - 1) / 2])<0) {
            E temp= queue[(i-1)/2];
            queue[(i-1)/2]=queue[i];
            queue[i]=temp;
            i=(i-1)/2;
        }
    }
    void heapify(){
        int i=(size-1)/2;
        while (i>=0){
            siftDown(i);
            i--;
        }
    }

    @Override
    public boolean add(E e) {
        if(size==capacity){
            capacity=(int)(capacity*1.5);
            E[] list=(E[]) new Comparable[capacity];
            System.arraycopy(this.queue,0,list,0,this.queue.length);
            this.queue=list;
        }
        this.queue[size]=e;
        siftUp(size++);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0;i<size;i++){
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
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public E remove() {
        if(size==0) {
            return null;
        }
        E res=queue[0];
        if(size--==1){
            queue[0]=null;
            return res;
        }
        queue[0]=queue[size];
        siftDown(0);
        return res;
    }

    @Override
    public boolean isEmpty() {
        return (size==0)?true:false;
    }

    @Override
    public E element() {
        return queue[0];
    }

    @Override
    public E peek() {
        return this.isEmpty()?null:queue[0];
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public boolean offer(E e) {
        this.add(e);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object el:collection){
            if(!this.contains(el)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.size() == 0) {
            return false;
        }
        for (Object el : collection) {
            this.add((E) el);
        }
        return true;
    }
    @Override
    public boolean remove(Object o) {
        return false;
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
}