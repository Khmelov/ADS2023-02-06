package by.it.group251002.kulik.lesson10;

import java.util.*;

class dequeElem<E>
{
    public  E elem;
    dequeElem<E> nextElem;
    dequeElem<E> prevElem;
    public dequeElem(E e)
    {
        elem=e;
    }
}

public class MyLinkedList<E> implements Deque<E> {



    dequeElem<E> head=null;
    dequeElem<E> tail=null;

    @Override
    public String toString() {
        if(head==null)
        {return "[]";}
        StringBuilder str = new StringBuilder("[");
        String razd = "";
        dequeElem<E> element=head;
        while(element.nextElem!=null) {
            str.append(razd).append(element.elem);
            razd = ", ";
            element=element.nextElem;
        }
        str.append(razd).append(element.elem);
        str.append("]");
        return str.toString();
    }

    @Override
    public void addFirst(E e) {
        dequeElem<E> element= new dequeElem<E>(e);
        element.elem=e;
        if (head==null)
        {
            head=element;
            tail=element;
        }
        else {
            element.nextElem=head;
            head.prevElem=element;
            head=element;
            head.prevElem=null;
        }
    }

    @Override
    public void addLast(E e) {
        dequeElem<E> element= new dequeElem<E>(e);
        element.elem=e;
        if (head==null)
        {
            head=element;
            tail=element;
        }
        else {
            element.prevElem=tail;
            tail.nextElem=element;
            tail=element;
            tail.nextElem=null;
        }
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
        return remove(0);
    }

    @Override
    public E pollLast() {
        return remove(size()-1);
    }

    @Override
    public E getFirst() {
        if(head==null){return null;}
        return head.elem;
    }

    @Override
    public E getLast() {
        if(head==null){return null;}
        return tail.elem;
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
        addLast(e);
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


    public E remove(int index){
        if(head==null){return null;}
        dequeElem<E> element=head;
        int timer=0;
        while(timer!=index)
        {
            if(element==null)
            {return null;}
            timer++;
            element=element.nextElem;
        }
        E buf=element.elem;
        if(index==0)
        {
            head=element.nextElem;
            head.prevElem=null;
            element=null;
        }
        else{
            if(index+1==size()){
                element=tail;
                tail=element.prevElem;
                tail.nextElem=null;
                element=null;
            }
            else {
                element.prevElem.nextElem=element.nextElem;
                element.nextElem.prevElem=element.prevElem;
                element=null;
            }
        }
        return buf;
    }

    @Override
    public boolean remove(Object o) {
        if(head==null){return false;}
        dequeElem<E> element=head;
        int timer=0;
        while(element.elem!=o)
        {
            element=element.nextElem;
            timer++;
            if(element.nextElem==null)
            {return false;}
        }
        if(timer==0)
        {
            head=element.nextElem;
            head.prevElem=null;
            element=null;
        }
        else{
            if(timer+1==size()){
                element=tail;
                tail=element.prevElem;
                tail.nextElem=null;
                element=null;
            }
            else {
                element.prevElem.nextElem=element.nextElem;
                element.nextElem.prevElem=element.prevElem;
                element=null;
            }
        }
        return true;
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
        int timer=0;
        if(head==null)
        {return 0;}
        dequeElem<E> element=head;
        while(element.nextElem!=null) {
            element=element.nextElem;
            timer++;
        }
        timer++;
        return timer;
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

