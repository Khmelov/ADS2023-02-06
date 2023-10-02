package by.it.group251002.markouskii.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

class Node<E>{
    public E elem;
    public Node<E> next,prev;
    public Node(E data){
        elem=data;
    }
}
public class MyLinkedList<E> implements Deque<E> {
    Node<E> front;
    Node<E> back;
    int count=0;
    public E remove(int pos){
        int i=0;
        Node<E> temp=null,pose=front;
        while (i<pos) {
            temp=pose;
            pose=pose.prev;
            i++;
        }
        if(pose.prev!=null)
        pose.prev.next=pose.next;
        else
            back=pose.next;
        if(pose.next!=null)
        pose.next.prev=pose.prev;
        else
            front=pose.prev;
        count--;
        return pose.elem;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String delimiter = "";
        Node<E> setter=front;
        for (int i=0;i<count;i++){
            sb.append(delimiter).append(setter.elem);
            setter=setter.prev;
            delimiter=", ";
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public void addFirst(E e) {
        Node<E> temp=new Node<E>(e);
        temp.prev=front;
        if (count==0) {
            front=back=temp;
        }
        else {
            front.next=temp;
            front=temp;
        }
        count++;
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
        if (count==0)
        return null;
        E output=front.elem;
        if (count==1)
            front=back=null;
        else
        {
            front=front.prev;
            front.next=null;
        }
        count--;
        return output;
    }

    @Override
    public E pollLast() {
        if (count==0)
            return null;
        E output=back.elem;
        if (count==1)
            front=back=null;
        else
        {
            back=back.next;
            back.prev=null;
        }
        count--;
        return output;
    }

    @Override
    public E getFirst() {
        if (count==0)
        return null;
        return front.elem;
    }

    @Override
    public E getLast() {
        if (count==0)
            return null;
        return back.elem;
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
        Node<E> node = new Node<E>(e);
        if (front==null)
            front=node;
        else {
            back.prev=node;
            node.next=back;
        }
        back=node;
        count++;
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
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
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
    public boolean remove(Object o) {

        int i=0;
        Node<E> temp=null,pose=front;
        while (i<count) {
            if (pose!=null && pose.elem==o) {
                if(pose.next!=null)
                    pose.next.prev=pose.prev;
                else
                    front=pose.prev;
                if(pose.prev!=null)
                    pose.prev.next=pose.next;
                else
                    back=pose.next;
                count--;
                return true;
            }
            pose=pose.prev;
            i++;
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
        return count;
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
