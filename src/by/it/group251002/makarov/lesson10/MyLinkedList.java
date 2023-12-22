package by.it.group251002.makarov.lesson10;



import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Deque<E> {

    private static class Node<E>{
        E data;
        Node<E> prev;
        Node<E> next;
        Node(Node prev,E data, Node next){
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }

    private int size = 0;
    private Node<E> first;
    private Node<E> last;


    public E remove(int pos) {
        if(pos>=size) return null;
        Node temp;
        size--;
        if(pos==0){
            temp = first;
            first = first.next;
            first.prev = null;
            return (E) temp.data;
        }
        if(pos == size){
            temp = last;
            last = last.prev;
            last.next = null;
            return (E)temp.data;
        }
        if(pos < size / 2){
            temp = first;
            for(int i = 0; i< pos;i++){
                temp = temp.next;
            }
        }else {
            temp = last;
            for(int i = size; i>pos;i--){
                temp=temp.prev;
            }
        }
        temp.prev.next = temp.next;
        temp.next.prev=temp.prev;
        return (E)temp.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void addFirst(E e) {
        Node<E> newnode = new Node<>(null,e,null);
        if(first==null){
            last = newnode;
            first = newnode;
        }else {
            newnode.next = first;
            first.prev = newnode;
            first = newnode;
        }
        size++;
    }


    @Override
    public void addLast(E e) {
        Node<E> newnode = new Node<>(null,e,null);
        if(last == null){
            last = newnode;
            first = newnode;
        } else{
            last.next = newnode;
            newnode.prev = last;
            last = newnode;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E removed = first.data;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return removed;
    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> rem = last;
        if(last==first){
            first = null;
            last = null;
        }
        else {

            last = last.prev;
            last.next = null;
        }
        size--;
        return rem.data;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        return removeFirst();
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        return removeLast();

//            if (size == 0)
//                return null;
//
//            size--;
//            E el = last.data;
//
//            if (size == 0) {
//                last = null;
//                first = null;
//            } else {
//                last = last.prev;
//                last.next = null;
//            }
//            return el;
    }


    @Override
    public E getFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        return first.data;
    }


    @Override
    public E getLast() {
        if(isEmpty())
            throw new NoSuchElementException();

        return last.data;
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

//    @Override
//    public boolean add(E e) {
//        addLast(e);
//        return false;
//    }
@Override
public boolean add(E e){
    if(size==0) {
        first = new Node(null, e, null);
        last=first;
    }
    else {
        last.next=new Node(last,e,null);
        last=last.next;
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
    public E poll() {
        if(isEmpty()){
            return null;
        }
        else return removeFirst();
    }

    @Override
    public E element() {
        if(isEmpty()){
            return null;
        } else return first.data;
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
    public boolean remove(Object o) {
        Node temp=first;
        for(int i=0;i<size;i++){
            if(temp.data.equals(o)){
                remove(i);
                return true;
            }
            temp=temp.next;
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

