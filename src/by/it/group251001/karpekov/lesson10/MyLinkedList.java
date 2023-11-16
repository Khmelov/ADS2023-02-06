package by.it.group251001.karpekov.lesson10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    E[] values = (E[]) new Object[]{};
    int size = 0, first = -1, last = -1;


    public String toString(){
        String s = "[";
        int i = first;
        for(int j = 0; j < size; ++j, i = (++i) % values.length){
            s = s + values[i];
            if(j != size - 1)  s = s + ", ";
        }
        s = s + "]";
        return  s;
    }

    public E pollFirst(){
        E res = values[first];
        first = ++first % values.length;
        --size;
        return res;
    }

    public E pollLast(){
        E res = values[last];
        last = last == 0 ? values.length - 1 : last - 1;
        --size;
        return res;
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
    public int size(){return size;}

    public boolean add(Object element){
        if(size == values.length){
            int prev_first = first, prev_size = values.length - first;
            values = Arrays.copyOf(values, (size * 3 / 2 + 1));
            if(size * 3 / 2 + 1 == 1) first = 0;
            else {
                if (last >= first) last = values.length - 1;
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

    public void addFirst(Object element){
        if(size == values.length){
            int prev_first = first, prev_size = values.length - first;
            values = Arrays.copyOf(values, (size * 3 / 2 + 1));
            if(last >= first) last = values.length - 1;
            first = values.length - prev_size;
            for(int i = prev_size; i > 0; --i){
                values[values.length - i] = values[prev_first++];
            }
        }
        first = first == 0 ? values.length - 1 : first - 1;
        values[first] = (E)element;
        ++size;
    }

    public void push(E element){add(element);}
    public E removeFirst(){return pollFirst();}
    public E pop(){return poll();}

    public E removeLast(){return poll();}

    public boolean removeFirstOccurrence(Object c){return  false;}

    public boolean removeLastOccurrence(Object c){return  false;}

    public boolean addAll(Collection <? extends E> c){return false;}

    public boolean contains(Object c){return  false;}

    public Iterator<E> iterator() {return null;}

    public Iterator<E> descendingIterator() {return null;}
    public void clear(){}

    public boolean isEmpty(){return (size == 0);}

    public Object[] toArray(){return null;}

    public boolean containsAll(Collection <?> c){return false;}

    public boolean retainAll(Collection <?> c){return false;}

    public boolean removeAll(Collection <?> c){return false;}

    public boolean offerFirst(E elem) {return false;}

    public boolean offer(E elem) {return false;}

    public boolean offerLast(E elem) {return false;}

    public <T> T[] toArray(T[] a) {return null;}

    public E remove() {return poll();}

    public void addLast(Object element){add(element);}

    public E element(){return getFirst();}

    public E peek(){return getFirst();}

    public E getFirst(){return values[first];}

    public E peekFirst(){return getFirst();}

    public E getLast(){return values[last];}

    public E peekLast(){return getLast();}

    public E poll(){return pollFirst();}


//    Там что-то с полями класса, так что даже не потестить


//    int size = 0;
//    Elem first = null;
//    Elem last = null;
//
//
//    private class Elem {
//        public Elem prev = null, next = null;
//        E value;
//    }
//
//    MyLinkedList(){
//
//    }
//
//    @Override
//    public E getFirst() {
//        if (size != 0)
//            return first.value;
//        else
//            return null;
//    }
//
//    @Override
//    public E getLast() {
//        if (size != 0)
//            return last.value;
//        else
//            return null;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("[");
//        Elem temp = first;
//        while (temp != null)
//        {
//            sb.append(temp.value);
//            if (temp!= last) sb.append(", ");
//            temp = temp.next;
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//    @Override
//    public void addFirst(E e) {
//        if (size == 0){
//            first = new Elem();
//            last = first;
//            first.next = null;
//        }
//        else{
//            first.prev = new Elem();
//            Elem temp = first;
//            first = first.prev;
//            first.next = temp;
//        }
//        first.value = e;
//        first.prev = null;
//        size++;
//    }
//
//    @Override
//    public void addLast(E e) {
//        if (size == 0){
//            last = new Elem();
//            first = last;
//            last.prev = null;
//        }
//        else{
//            last.next = new Elem();
//            Elem temp = last;
//            last = last.next;
//            last.prev = temp;
//        }
//        last.value = e;
//        last.next = null;
//        size++;
//    }
//
//    @Override
//    public E pollFirst() {
//        if (size != 0){
//            Elem temp = first;
//            if (size != 1){
//                first = first.next;
//                first.prev = null;
//            }
//            else first = last = null;
//            size--;
//            return temp.value;
//        }
//        else
//            return null;
//    }
//
//    @Override
//    public E pollLast() {
//        if (size != 0){
//            Elem temp = last;
//            if (size != 1){
//                last = last.prev;
//                last.next = null;
//            }
//            else first = last = null;
//            size--;
//            return temp.value;
//        }
//        else
//            return null;
//    }
//
//    @Override
//    public boolean add(E e) {
//        try {
//            addLast(e);
//            return true;
//        } catch (Exception ex){
//            return false;
//        }
//    }
//
//    @Override
//    public boolean offer(E e) {
//        return false;
//    }
//
//    @Override
//    public E remove() {
//        E temp = pollFirst();
//        if (temp == null) throw new RuntimeException();
//        return temp;
//    }
//
//    @Override
//    public E poll() {
//        return pollFirst();
//    }
//
//    @Override
//    public E element() {
//        return getFirst();
//    }
//
//    @Override
//    public E peek() {
//        return null;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {}
//
//    @Override
//    public void push(E e) {}
//
//    @Override
//    public E pop() {
//        return null;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {return false;}
//
//    @Override
//    public Iterator<E> iterator() {return null;}
//
//    @Override
//    public Object[] toArray() {return new Object[0];}
//
//    @Override
//    public <T> T[] toArray(T[] a) {return null;}
//
//    @Override
//    public Iterator<E> descendingIterator() {return null;}
//
//    @Override
//    public boolean offerFirst(E e) {
//        return false;
//    }
//
//    @Override
//    public boolean offerLast(E e) {
//        return false;
//    }
//
//    @Override
//    public E removeFirst() {
//        return null;
//    }
//
//    @Override
//    public E removeLast() {
//        return null;
//    }
//
//    @Override
//    public E peekFirst() {
//        return null;
//    }
//
//    @Override
//    public E peekLast() {
//        return null;
//    }
//
//    @Override
//    public boolean removeFirstOccurrence(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean removeLastOccurrence(Object o) {
//        return false;
//    }
}