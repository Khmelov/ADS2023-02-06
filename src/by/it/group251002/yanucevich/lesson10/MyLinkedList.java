package by.it.group251002.yanucevich.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;


class Lister<E>{
    Lister<E> next;
    Lister<E> prev;
    E value;
    public Lister(E e){
        value=e;
        prev=null;
        next=null;
    }
}
public class MyLinkedList<E> implements Deque<E> {


    Lister<E> elems = new Lister<>(null);
    Lister<E> head=elems;
    Lister<E> tail=elems;
    int size=0;


    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[");
        String inter="";
        Lister<E> temp=head;
        while(temp.prev!=null){
            temp=temp.prev;
            str.append(inter).append(temp.value);
            inter=", ";
        }
        str.append("]");
        return str.toString();

    }

    @Override
    public void addFirst(E e) {
        head.value=e;
        Lister<E> temp= new Lister<>(null);
        temp.prev=head;
        head.next=temp;
        head=temp;
        size++;
    }

    @Override
    public void addLast(E e) {
        Lister<E> temp=new Lister<>(e);
        tail.prev=temp;
        temp.next=tail;
        tail=temp;
        size++;
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
        head=head.prev;
        head.next.prev=null;
        head.next=null;
        size--;
        return head.value;
    }

    @Override
    public E pollLast() {
        E value = tail.value;
        tail=tail.next;
        tail.prev.next=null;
        tail.prev=null;
        size--;
        return value;
    }

    @Override
    public E getFirst() {
        return head.prev.value;
    }

    @Override
    public E getLast() {
        return tail.value;
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
    public boolean add(E e) {
        Lister<E> temp= new Lister<>(e);
        temp.next=tail;
        tail.prev=temp;
        tail=temp;
        size++;
        return true;
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
    public E poll() {

        head=head.prev;
        head.next.prev=null;
        head.next=null;
        size--;
        return head.value;
    }

    @Override
    public E element() {
        return head.prev.value;
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
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }


    private E theRemover(Lister<E> elem){
        Lister<E> swap = elem.next;
        swap.prev=elem.prev;
        if(elem.prev==null){
            elem.next=null;
        }
        else {
            elem.prev.next = swap;
        }
        size--;
        return elem.value;
    }
    public E remove(int i){
        Lister<E> temp=head.prev;
        for(int j=0;j<i;j++){
            temp=temp.prev;
        }
        return theRemover(temp);
    }

    @Override
    public boolean remove(Object o) {
        Lister<E> temp=head.prev;
        while (temp.prev!=null&&!(temp.value.equals(o))){
            temp=temp.prev;
        }
        if (temp.value.equals(o)){
            theRemover(temp);
            return true;
        }
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
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
    public Iterator<E> descendingIterator() {
        return null;
    }
}
