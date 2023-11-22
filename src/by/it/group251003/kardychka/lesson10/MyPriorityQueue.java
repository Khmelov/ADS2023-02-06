package by.it.group251003.kardychka.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private int size = 0;
    private E[] data = (E[])new Object[0];

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size; i++){
            sb.append(data[i]);
            if(i < size-1){
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
    public void clear() {
        for(int i = 0; i < size; i++)
            data[i]=null;
        size = 0;
    }

    private void swap(int i, int j){
        E temp = data[i];
        data[i]=data[j];
        data[j]=temp;
    }
    private void siftup(int index){
        while(((Comparable<? super E>) data[index]).compareTo(data[(index-1)/2])<0){
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    private void siftdown(int index){
        boolean isinplace = false;
        while(2*index+1<size && !isinplace){
            int left = 2*index+1;
            int right = left+1;
            int child = left;
            if(right < size && ((Comparable<? super E>) data[right]).compareTo(data[left])<0)
                child = right;
            if(((Comparable<? super E>) data[index]).compareTo(data[child])<0)
                isinplace = true;
            if (!isinplace)
                swap(index, child);
            index = child;
        }
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public E remove() {
        if(size == 0)
            throw new NoSuchElementException();
        return poll();
    }

    @Override
    public boolean contains(Object o) {
        if (o != null)
            for (int i = 0; i < size; i++)
                if (data[i].equals(o))
                    return true;
        return false;
    }
    @Override
    public boolean offer(E e) {
        if(size == data.length){
            E []temp = (E[])new Object[size*3/2+1];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        data[size]=e;
        siftup(size);
        size++;
        return true;
    }
    @Override
    public E poll() {
        if(size == 0)
            return null;
        E element = data[0];
        data[0] = data[size-1];
        data[--size] = null;
        siftdown(0);
        return element;
    }

    @Override
    public E peek() {
        if(size == 0)
            return null;
        return data[0];
    }

    @Override
    public E element() {
        if(size == 0)
            throw new NoSuchElementException();
        return data[0];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object []cArray = c.toArray();
        for(int i = 0; i < cArray.length; i++){
            if(!contains(cArray[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E []cArray =(E[]) c.toArray();
        if(cArray.length == 0)
            return false;
        for(int i = 0; i < cArray.length; i++){
            offer(cArray[i]);
        }
        return true;
    }

    public void reroll(){
        for(int i = size/2-1;i>=0;i--)
            siftdown(i);
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        /* E []cArray =(E[]) c.toArray();
        if(cArray.length == 0)
            return false;
        boolean result = false;
        E []temp = (E[])new Object[size];
        System.arraycopy(data, 0, temp, 0, size);
        clear();
        for(int i = 0; i < temp.length;i++)
            if(!c.contains(temp[i])) {
                add(temp[i]);
            } else {
                result = true;
            }
        return result;*/
        int i = 0;
        for(;i<size && !c.contains(data[i]);i++)
            ;
        if(i==size)
            return false;
        int end = size;
        int begin = i;
        int[] tosave = new int[end-begin];
        for(i = begin+1; i<end;i++)
            tosave[i-begin] = (c.contains(data[i]))?0:1;
        int w = begin;
        for(i = begin; i < end; i++)
            if(tosave[i-begin]==1)
                data[w++]=data[i];
        size = w;
        for(i = size; i < end; i++)
            data[i]=null;
        reroll();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        /*E []cArray =(E[]) c.toArray();
        if(cArray.length == 0)
            return false;
        boolean result = false;
        E []temp = (E[])new Object[size];
        System.arraycopy(data, 0, temp, 0, size);
        clear();
        for(int i = 0; i < temp.length;i++)
            if(c.contains(temp[i])) {
                add(temp[i]);
            } else {
                result = true;
            }
        return result;*/
        int i = 0;
        for(;i<size && c.contains(data[i]);i++)
            ;
        if(i==size)
            return false;
        int end = size;
        int begin = i;
        int[] tosave = new int[end-begin];
        for(i = begin+1; i<end;i++)
            tosave[i-begin] = (!c.contains(data[i]))?0:1;
        int w = begin;
        for(i = begin; i < end; i++)
            if(tosave[i-begin]==1)
                data[w++]=data[i];
        size = w;
        for(i = size; i < end; i++)
            data[i]=null;
        reroll();
        return true;
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
    public boolean remove(Object o) {
        return false;
    }

}
