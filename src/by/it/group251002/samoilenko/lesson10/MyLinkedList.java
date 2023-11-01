package by.it.group251002.samoilenko.lesson10;

import java.util.*;

class Element<E>{
    Element <E> prev;
    Element<E> next;
    E el;
    Element(Element prev,E el,Element next){
        this.next=next;
        this.prev=prev;
        this.el=el;
    }
}
public class MyLinkedList<E> implements Deque<E>,List<E> {
    private int size;
    private Element head;
    private Element tail;
    MyLinkedList() {
        size=0;
        head=null;
        tail=null;
    }

    @Override
    public String toString() {
        String res=new String();
        res+="[";
        Element temp=head;
        for(int i=1;i<=size;i++) {
            res +=temp.el;
            if(i!=size)
                res+= ", ";
            temp=temp.next;
        }
        res+="]";
        return res;
    }
    @Override
    public boolean add(E e){
        if(size==0) {
            head = new Element(null, e, null);
            tail=head;
        }
        else {
            tail.next=new Element(tail,e,null);
            tail=tail.next;
        }
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
    public E remove(int index) {
        if(index>=size) return null;
        Element temp;
        size--;
        if(index==0){
            temp=head;
            head=head.next;
            head.prev=null;
            return (E) temp.el;
        }
        if(index==size){
            temp=tail;
            tail=tail.prev;
            tail.next=null;
            return (E)temp.el;
        }
        if (index<size/2){
            temp=head;
            for(int i=0;i<index;i++){
                temp=temp.next;
            }
        } else {
            temp=tail;
            for(int i=size;i>index;i--){
                temp=temp.prev;
            }
        }
        temp.prev.next=temp.next;
        temp.next.prev=temp.prev;
        return (E)temp.el;
    }

    @Override
    public void addFirst(E e) {
        if(size==0){
            head=new Element(null,e,null);
            tail=head;
        } else{
            head.prev=new Element(null,e,head);
            head=head.prev;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
      if(size==0) {
          head = new Element(null, e, null);
          tail=head;
      }
      else {
          tail.next=new Element(tail,e,null);
          tail=tail.next;
      }
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
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return null;
    }

    @Override
    public List<E> subList(int i, int i1) {
        return null;
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
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        Element temp=head;
        for(int i=0;i<size;i++){
            if(temp.el.equals(o)){
                remove(i);
                return true;
            }
            temp=temp.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E element() {
        return (E) head.el;
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
    public boolean addAll(int i, Collection<? extends E> collection) {
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

    @Override
    public E get(int i) {
        return null;
    }

    @Override
    public E set(int i, E e) {
        return null;
    }

    @Override
    public void add(int i, E e) {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E getLast() {
        return (E) tail.el;
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
    public E getFirst() {
        return (E) head.el;
    }

    @Override
    public E pollLast() {
        return remove(size-1);
    }

    @Override
    public E pollFirst() {
        return  remove(0);
    }

    @Override
    public E poll() {
        E el=(E) head.el;
        remove(0);
        return el;
    }
}

