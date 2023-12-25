package by.it.group251001.sidorovich.lesson10;


import java.util.Deque;
import java.util.Iterator;
import java.util.Collection;

public class MyLinkedList<E> implements Deque<E> {
    private static class node<E> {
        E data;
        node<E> prev;
        node<E> next;

        private node(E data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }

    private int size = 0;
//node<E> head,tail;

   private node <E> head=new node<E>(null);
   private node <E> tail=new node<E>(null);




    @Override
    public String toString() {
StringBuilder sb= new StringBuilder("[");
if (size>0){
for (node<E> temp=head; temp!=tail;temp=temp.next){
    sb.append(temp.data+", ");
}
if (size>1){
    sb.append(tail.data);
}
}
sb.append("]");
return sb.toString();
    }


    @Override
    public void addFirst(E e) {
        node <E> temp=new node<E>(e);
        size++;
        if (size==1){
            head.data=e;
            head.next=tail;
            tail.prev=head;
        }
     else   if (size==2){
         tail.data=head.data;
            head.data=e;
        }
     else {

            temp.data = head.data;
            temp.next = head.next;
            temp.prev = head;
            head.next = temp;
            temp.next.prev = temp;
            head.data=e;
        }
    }

    @Override
    public void addLast(E e) {
      add(e);
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

        if (size>2){
            E temp=head.data;
            head=head.next;
            head.next.prev=head;
            size--;
            return temp;
        }
        if (size==1){
            E temp=head.data;
            head.data=tail.data;
            size--;
            return temp;
        }
       return null;

    }

    @Override
    public E pollLast() {
        E temp=tail.data;
        if (size>0){
            tail=tail.prev;
        }
        size--;
        return temp;
    }

    @Override
    public E getFirst() {
        return head.data;
    }

    @Override
    public E getLast() {
        return tail.data;
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
        node <E> temp=new node<E>(e);
        size++;
if (size==1){
    head.data=e;
    head.next=tail;
    tail.prev=head;
    return true;
}
if (size==2){
    tail.data=e;
    return true;
}

        temp.data=tail.data;
        temp.next=tail;
        temp.prev=tail.prev;
        tail.prev=temp;
        temp.prev.next=temp;
   tail.data=e;
        return true;
    }

    @Override
    public boolean offer(E e) {

        return false;
    }

    @Override
    public E remove() {
        E temp;
       if (size==0) return null;
       else{
           temp=head.data;
           head.next=head.next.next;
           head.next.prev=head;
           return temp;
       }
    }
    public E remove(int n) {
        E temp;
        node<E> t=new node<E>(null);
        t=head;
        if ((size-1<n)||(n<0)) return null;
        else{
            for (int i=0; i<n;i++)
             t=t.next;

            temp=t.data;
            t.next.prev=t.prev;
            t.prev.next=t.next;
            size--;
            return temp;
        }
    }

    @Override
    public E poll() {
       E temp=head.data;
       if (size>0){
           head=head.next;
        //   head.next.prev=head;
       }
        size--;
       return temp;
    }

    @Override
    public E element() {
        return head.data;
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

    @Override
    //////////////////////////////////////////////////////////////////////////
    public boolean remove(Object e) {
        if (size == 0)
            return false;

        if (e.equals(head.data)) {
            head = head.next;
            head.prev = null;

            size--;
            return true;
        }

        node<E> curr = head;
        while (curr != tail.prev) {
            if (e.equals(curr.data)) {
                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.next = null;
                curr.prev = null;

                size--;
                return true;
            }

            curr = curr.next;
        }
        if (e.equals(tail.prev.data)) {
            tail = tail.prev;
            tail.next = null;

            size--;
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