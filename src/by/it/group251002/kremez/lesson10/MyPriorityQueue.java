package by.it.group251002.kremez.lesson10;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {

    E[] values = (E[]) new Object[]{};
    int size = 0;

    private int heapify(int i){
        int largest = i;
        if((i * 2 + 1 < size) && (Integer)values[i * 2 + 1] < (Integer)values[largest])
            largest = i * 2 + 1;
        if((i * 2 + 2 < size) && (Integer)values[i * 2 + 2] < (Integer)values[largest])
            largest = i * 2 + 2;
        E temp = values[largest];
        values[largest] = values[i];
        values[i] = temp;
        return largest;
    }

    private int heapufy(int i){
        int largest = i;
        if(((i - 1) / 2 >= 0) && (Integer)values[(i - 1) / 2] > (Integer)values[largest])
            largest = (i - 1) / 2;
        E temp = values[largest];
        values[largest] = values[i];
        values[i] = temp;
        return largest;
    }

    private void pushup(int i){
        int largest = heapufy(i);
        if(largest != i)
            pushup(largest);
    }

    private void pushdown(int i){
        int largest = heapify(i);
        if(largest != i)
            pushdown(largest);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        String s ="[";
        for(int i = 0; i < size; ++i){
            s = s + values[i].toString();
            if(i != size - 1)
                s = s + ", ";
        }
        s = s + "]";
        return s;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; ++i)
            if(values[i].equals(o))
                return true;
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
        if (size == values.length)
            values = Arrays.copyOf(values, size * 3 / 2 + 1);
        values[size++] = e;
        pushup(size - 1);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int removing_index = -1;
        for(int i = 0; i < size; ++i)
            if(values[i].equals(o)){
                removing_index = i;
                break;
            }
        if(removing_index != -1){
            remove(removing_index);
            return true;
        }
        return false;
    }

    public boolean remove(int index) {
        --size;
        E temp = values[size];
        values[size] = values[index];
        values[index] = temp;
        if(size > 0) {
            pushdown(index);
            if (values[index].equals(temp))
                pushup(index);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < c.size(); ++i)
            if(!contains(params[i]))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            add(params[i]);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        clear();
        for(int i = 0; i < prevSize; ++i)
            if(!c.contains(values[i]))
                add(values[i]);
        return prevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        clear();
        for(int i = 0; i < prevSize; ++i)
            if(c.contains(values[i]))
                add(values[i]);
        return prevSize != size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        E res = values[0];
        remove(0);
        return res;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek() {
        return values[0];
    }
}
