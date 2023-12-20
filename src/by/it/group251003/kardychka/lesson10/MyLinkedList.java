package by.it.group251003.kardychka.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {

    private int size = 0;
    private MyNode<E> first;
    private MyNode<E> last;
    static private class MyNode<E>{
        E data;
        MyNode<E> next;
        MyNode<E> prev;
        MyNode(E data, MyNode<E> prev, MyNode<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MyNode<E> cnode = first;
        if(cnode!=null){
            while(cnode.next!=null){
                sb.append(cnode.data);
                sb.append(", ");
                cnode = cnode.next;
            }
            sb.append(cnode.data);
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }
    private E deletenode(MyNode<E> x){
        E data = x.data;
        MyNode<E> next = x.next;
        MyNode<E> prev = x.prev;
        if(next != null){
            next.prev = prev;
            x.next = null;
        } else {
            last = prev;
        }
        if(prev != null){
            prev.next = next;
            x.prev = null;
        } else {
            first = next;
        }
        x.data = null;
        size--;
        return data;
    }
    public E remove(int index) {
        Objects.checkIndex(index, size);
        MyNode<E> rnode = first;
        for(int i = 0; i !=index; i++)
            rnode = rnode.next;
        return deletenode(rnode);
    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(MyNode<E> rnode = first; rnode!=null;rnode = rnode.next){
                if(rnode.data==null){
                    deletenode(rnode);
                    return true;
                }
            }
        } else {
            for(MyNode<E> rnode = first; rnode!=null;rnode = rnode.next){
                if(rnode.data.equals(o)){
                    deletenode(rnode);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(E e) {
        MyNode<E> newnode = new MyNode<>(e, null, first);
        MyNode<E> prevfirst = first;
        first = newnode;
        if (prevfirst == null) {
            last = newnode;
        } else{
            prevfirst.prev = newnode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        MyNode<E> newnode = new MyNode<>(e, last, null);
        MyNode<E> prevlast = last;
        last = newnode;
        if (prevlast == null) {
            first = newnode;
        } else{
            prevlast.next = newnode;
        }
        size++;
    }


    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if(first == null)
            throw new NoSuchElementException();
        return first.data;
    }

    @Override
    public E getLast() {
        if(last == null)
            throw new NoSuchElementException();
        return last.data;
    }
    @Override
    public E poll() {
        return pollFirst();
    }
    @Override
    public E pollFirst() {
        if(first == null)
            return null;
        E data = first.data;
        MyNode<E> next = first.next;
        first.data = null;
        first.next = null;
        first = next;
        if(first!=null)
            first.prev = null;
        else
            last = null;
        size--;
        return data;
    }

    @Override
    public E pollLast() {
        if(last == null)
            return null;
        E data = last.data;
        MyNode<E> prev = last.prev;
        last.data = null;
        last.prev = null;
        last = prev;
        if(last!=null)
            last.next = null;
        else
            first = null;
        size--;
        return data;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
