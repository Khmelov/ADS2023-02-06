package by.it.group251002.sak.lesson10;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
public class MyArrayDeque<E> implements Deque<E> {


    // +++toString
    // +++size

    //    add(E element)
    //    addFirst(E element)
    //    addLast(E element)

    //    element()
    //    getFirst()
    //    getLast()

    //    poll()
    //    pollFirst()
    //    pollLast()
    private E[] elements = (E[])new Object[0];
    private int size = 0;

    private void checkSize(){
        if(size == elements.length){
            E [] elementsAdd= (E[]) new Object[(size*3)/2+1];
            System.arraycopy(elements, 0, elementsAdd, 0, size);
            elements=elementsAdd;
        }
    }

    @Override
    public String toString() { // Готов
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        for (int i = 0; i < size; i++) {
            sb.append(delimiter).append(elements[i]);
            delimiter = ", ";

        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {  //готов
        return this.size;
    }


    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, (size * 3) / 2 + 1);
        }
        elements[size++] = e;
        return true;
    }

    @Override
    public void addFirst(E e) {
        checkSize();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[0]=e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, (size * 3) / 2 + 1);
        }
        elements[size++] = e;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if(this.size==0)
            return null;
        return elements[0];

    }

    @Override
    public E getLast() {
        if(this.size==0)
            return null;
        return elements[size-1];
    }


    @Override
    public E poll() {
        if(size==0){
            return null;
        } else {
            E buf = elements[0];
            System.arraycopy(elements, 1, elements, 0, --size);
            elements[size]=null;
            return buf;
        }
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if(size==0){
            return null;
        } else {
            E buf = elements[size-1];
            elements[--size]=null;
            return buf;
        }
    }









    //////////////////////////

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
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
    public Iterator<E> descendingIterator() {
        return null;
    }
}
