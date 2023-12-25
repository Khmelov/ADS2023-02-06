package by.it.group251001.politykina.lesson10;

import java.util.*;

//                contains(E element)
public class MyPriorityQueue<E> implements Deque<E> {
    private int size;
    private int capacity=15;
    private E[] elements= (E[]) new Object[capacity];
    private void grow(){
        capacity=capacity*2;
        elements =Arrays.copyOf(elements, (capacity));
    }

    private int parent(int child){
        int par = (child-1)/2;
        if (par>=0 &&par<size) return par;
        return 0;
    }


    private void change( int param1, int param2){
        E temp = elements[param1];
        elements[param1]=elements[param2];
        elements[param2]=temp;
    }
    private void shiftUp(int indx){
        int _parent =parent(indx);
        if  ( ( (Comparable<? super E>)elements[_parent] ).compareTo( (E)elements[indx])>0 ) {
           change(_parent,indx);
           shiftUp(_parent);
        }
    }
    private void heapify(int indx){
        int left = indx*2+1;
        int right = indx*2+2;

        int largest = indx;

        if (left < size && ((Comparable<? super E>) elements[left]).compareTo((E) elements[indx]) < 0) {
            largest = left;
        }

        if (right < size && ((Comparable<? super E>) elements[right]).compareTo((E) elements[largest]) < 0) {
            largest = right;
        }

        if (largest != indx) {
            change(indx, largest);
            heapify(largest);
        }
    }

    @Override
    public String toString() {
        if (size!=0){
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size-1; i++) {
            s.append(elements[i]).append(", ");
        }
        s.append(elements[size-1]).append("]");
        return s.toString();
        }else return "[]";
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        size=0;
    }
    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            grow();
        }

        elements[size] = e;
        shiftUp(size);
        size++;
        return true;
    }
    @Override
    public E remove() {
        if (size!=0){
            size--;
            E temp = elements[0];
            elements[0]=elements[size];
            elements[size]=null;
            heapify(0);
            return temp;
        }
        return null;
    }
    @Override
    public boolean containsAll(Collection<?> collection) {
        E[] newElements = (E[]) collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            if (!contains(newElements[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)){
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }
    @Override
    public E poll() {
        return remove();}
    @Override
    public E peek() {
        return elements[0];
    }
    @Override
    public E element() {
        return elements[0];
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int newSize = size;
        for (E elem : collection)
            add(elem);
        return newSize != size;
    }
    @Override
    public boolean removeAll(Collection<?> collection) {
        int newSize = 0;
        capacity = size;
        E[] tmpData = (E[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            if (!collection.contains(elements[i])) {
                tmpData[newSize++] = elements[i];
            }
        }
        elements = tmpData;
        boolean res = newSize != size;
        size = newSize;
        for (int i = size / 2 -1; i >= 0; i--)
            heapify(i);

        return res;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        int newSize = 0;
        capacity = size;
        E[] tmpData = (E[]) (new Object[size]);
        for (int i = 0; i < size(); i++) {
            if (c.contains(elements[i])) {
                tmpData[newSize++] = elements[i];
            }
        }
        elements = tmpData;
        boolean res = newSize != size;
        size = newSize;
        for (int i = size / 2 -1; i >= 0; i--)
            heapify(i);
        return res;
    }
    /////////////////////////////////
    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
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
    public Iterator<E> descendingIterator() {
        return null;
    }
}
