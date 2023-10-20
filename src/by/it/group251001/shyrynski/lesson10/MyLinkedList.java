package by.it.group251001.shyrynski.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
public class MyLinkedList<E> implements Deque<E>{
    E[] values = (E[]) new Object[]{};

    int size = 0, first = -1, last = -1;

    public void clear(){
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public Object[] toArray(){
        return null;
    }

    public boolean containsAll(Collection <?> c){
        return false;
    }

    public boolean retainAll(Collection <?> c){
        return false;
    }

    public boolean removeAll(Collection <?> c){
        return false;
    }

    public boolean offerFirst(E elem) {
        return false;
    }

    public boolean offer(E elem) {
        return false;
    }

    public boolean offerLast(E elem) {
        return false;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public E remove() {
        return poll();
    }

    public boolean remove(Object c) {
        for(int i = first, j = 0; j < size; ++j, i = (++i) % values.length){
            if(values[i].equals(c)){
                remove(j);
                return true;
            }
        }
        return false;
    }

    public E remove(int index){
        int i = (first + index) % values.length;
        for(int j = 0; j < index; ++j, i = i == 0 ? values.length - 1 : i-1) {
            E temp = values[i];
            values[i] = values[i == 0 ? values.length - 1 : i - 1];
            values[i == 0 ? values.length - 1 : i - 1] = temp;
        }
        return removeFirst();
    }

    public String toString(){
        String s = "[";
        int i = first;
        for(int j = 0; j < size; ++j, i = (++i) % values.length){
            s = s + values[i];
            if(j != size - 1)
                s = s + ", ";
        }
        s = s + "]";
        return  s;
    }
    public int size(){
        return size;
    }

    public boolean add(Object element){
        if(size == values.length){
            int prev_first = first, prev_size = values.length - first;
            values = Arrays.copyOf(values, (size * 3 / 2 + 1));
            if(size * 3 / 2 + 1 == 1)
                first = 0;
            else {
                if (last >= first)
                    last = values.length - 1;
                first = values.length - prev_size;
                for (int i = prev_size; i > 0; --i) {
                    values[values.length - i] = values[prev_first++];
                }
            }
        }
        last = ++last % values.length;
        values[last] = (E)element;
        ++size;
        return true;
    }


    public void push(E element){
        add(element);
    }

    public void addFirst(Object element){
        if(size == values.length){
            int prev_first = first, prev_size = values.length - first;
            values = Arrays.copyOf(values, (size * 3 / 2 + 1));
            if(last >= first)
                last = values.length - 1;
            first = values.length - prev_size;
            for(int i = prev_size; i > 0; --i){
                values[values.length - i] = values[prev_first++];
            }
        }
        first = first == 0 ? values.length - 1 : first - 1;
        values[first] = (E)element;
        ++size;
    }
    public void addLast(Object element){
        add(element);
    }

    public E element(){
        return getFirst();
    }

    public E peek(){
        return getFirst();
    }

    public E getFirst(){
        return values[first];
    }

    public E peekFirst(){
        return getFirst();
    }

    public E getLast(){
        return values[last];
    }

    public E peekLast(){
        return getLast();
    }

    public E poll(){
        return pollFirst();
    }

    public E pollFirst(){
        E res = values[first];
        first = ++first % values.length;
        --size;
        return res;
    }

    public E removeFirst(){
        return pollFirst();
    }

    public E pollLast(){
        E res = values[last];
        last = last == 0 ? values.length - 1 : last - 1;
        --size;
        return res;
    }

    public E pop(){
        return poll();
    }

    public E removeLast(){
        return poll();
    }

    public boolean removeFirstOccurrence(Object c){
        return  false;
    }

    public boolean removeLastOccurrence(Object c){
        return  false;
    }

    public boolean addAll(Collection <? extends E> c){
        return false;
    }

    public boolean contains(Object c){
        return  false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Iterator<E> descendingIterator() {
        return null;
    }
}
