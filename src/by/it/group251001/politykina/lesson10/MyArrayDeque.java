package by.it.group251001.politykina.lesson10;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyArrayDeque<E> implements Deque<E>{

    private int size=10;
    private E[] elements = (E[]) new Object[size];
    private int head=5;
    private int tail=5;

    private int inc(int indx){
        indx++;
        if (indx == elements.length){
            indx= 0;
        }
        return indx;
    }
    private int dec(int indx){

        indx--;
        if (indx < 0){
            indx=elements.length-1;
        }
        return indx;
    }
    private void grow(){
        int newLength = (elements.length) * 3/2;
        int oldLength = elements.length;
        E [] temp = (E[]) new Object[newLength] ;
        int j = 0;
        for (int i = head; i != tail; i=inc(i)) {
            temp[j] = elements[i];
            elements[i] = null;
            j++;
        }
        temp[j]=elements[tail];
        this.head = 0;
        this.tail = j;
        elements = temp;
    }
    @Override
    public void addFirst(E e) {
        if (e==null){
            throw new NullPointerException();
        }
        else {
           head= dec(head);
            if (head==tail){
               head= inc(head);
                grow();
                head=dec(head);
            }

            elements[head]=e;
        }
    }

    @Override
    public void addLast(E e) {
        if (e==null){
            throw new NullPointerException();
        }
        else {
            elements[tail]=e;
            tail=inc(tail);
            if (tail==head){
                tail=dec(tail);
                grow();
                tail=inc(tail);
            }
        }
    }
    @Override
    public boolean add(E e) {
        if (e==null){
            return false;
        }
        else {
            elements[tail]=e;
            tail=inc(tail);
            if (tail==head){
                tail=dec(tail);
                grow();
                tail=inc(tail);
            }
            return true;
        }
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = head; i != dec(tail); i=inc(i)) {
            s.append(elements[i]).append(", ");
        }
        s.append(elements[dec(tail)]);
        s.append("]");
        return s.toString();
    }
    @Override
    public E pollFirst() {
        if (head==tail){
            return null;
        }
        else{
            E temp=elements[head];
            elements[head]=null;
            head=inc(head);
            return temp;
        }
    }

    @Override
    public E pollLast() {
        if (head==tail){
            return null;
        }
        else{
            tail=dec(tail);
            E temp=elements[tail];
            elements[tail]=null;

            return temp;
        }
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E getFirst() {
        if (head==tail){
            throw new NoSuchElementException();
        } else {
            return elements[head];
        }
    }

    @Override
    public E getLast() {
        if (head==tail){
            throw new NoSuchElementException();
        } else {
            return elements[dec(tail)];
        }
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public int size() {
        if(head<=tail){
            return (tail-head);
        }else{
            return elements.length-head+tail;
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return Deque.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return Deque.super.removeIf(filter);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Deque.super.spliterator();
    }

    @Override
    public Stream<E> stream() {
        return Deque.super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return Deque.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Deque.super.forEach(action);
    }

    public MyArrayDeque() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
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
    public boolean addAll(Collection<? extends E> collection) {
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}
