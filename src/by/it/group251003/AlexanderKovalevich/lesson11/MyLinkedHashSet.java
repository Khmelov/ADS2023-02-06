package by.it.group251003.AlexanderKovalevich.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {



    static class Node<E> {
        E data;
        public Node<E> next;
        Node(E e) {
            this.data = e;
        }
    }

    private void resize()
    {
        Node<E>[] temparray = new Node[array.length*2+1];
        for (Node<E> pointer:array) {
            Node<E> curptr = pointer;
            while (curptr != null)
            {
                Node<E> oldNext = curptr.next;
                int hashInd = (curptr.data.hashCode() & 0x7FFFFFFF) % temparray.length;
                curptr.next = temparray[hashInd];
                temparray[hashInd] = curptr;

                curptr = oldNext;
            }
        }
        array = temparray;
    }


    private int size = 0;
    private Node<E> []array = new Node[1];
    private int gethashIndex(Object o)
    {
        return (o.hashCode()& 0x7FFFFFFF) % array.length;
    }


    public Node<E> elementsQueue = null;
    private void addToQueue(E e)
    {
        if(elementsQueue == null){
           elementsQueue = new Node<>(e);}
        else {
            Node<E> pointer = elementsQueue;
            while(pointer.next != null)
            {
                pointer = pointer.next;
            }
            pointer.next = new Node<>(e);
        }
    }

    private void deleteFromQueue(E e)
    {
        Node<E> pointer = elementsQueue;
        boolean flag = true;
        boolean IsEnd = true;
        while (pointer!= null && IsEnd) {

            if (pointer.data.equals(e) && flag) {
                elementsQueue = pointer.next;
                flag = false;
                IsEnd = false;
            }

            if(pointer.next!= null && pointer.next.data.equals(e))
            {
                pointer.next = pointer.next.next;
                IsEnd = false;
            }
            flag = false;
            pointer = pointer.next;
        }

    }

    @Override
    public boolean add(E e) {
        if(!contains(e)) {
            int hashInd = gethashIndex(e);
            Node<E> newNode = new Node<>(e);
            addToQueue(e);
            newNode.next = array[hashInd];
            array[hashInd] = newNode;
            size++;
            if (size >array.length * 0.75)
                resize();
            return true;
        }else return false;
    }

    @Override
    public String toString() {
        String result = "[";
        Node<E> pointer = elementsQueue;
        while (pointer != null)
        {
            result+=pointer.data;
            if(pointer.next!=null)
                result+=", ";
            pointer = pointer.next;
        }
        result+="]";
        return result;
    }
    @Override
    public boolean remove(Object o) {
        if(contains(o))
        {
            int hashInd = gethashIndex(o);
            Node<E> pointer = array[hashInd];
            boolean flag = true;
            while(pointer != null)
            {
                if(pointer.data.equals(o) && flag){
                    deleteFromQueue(pointer.data);
                    array[hashInd] = pointer.next;
                    size--;
                    return true;
                }
                if(pointer.next!=null && pointer.next.data.equals(o))
                {
                    deleteFromQueue(pointer.next.data);
                    pointer.next = pointer.next.next;
                    size--;
                    return true;

                }
                flag = false;
                pointer = pointer.next;
            }
            return true;

        }else return false;
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
    public boolean contains(Object o) {
        int hashInd = gethashIndex(o);

        Node<E> pointer = array[hashInd];
        while (pointer != null)
        {
            if(pointer.data.equals(o))
                return true;
            pointer = pointer.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o:c) {
            if(!contains(o))
                return false;

        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        size = 0;
        elementsQueue = null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E o:c) {
            if(!contains(o)) {
                add(o);
                flag = true;
            }
        }
        return flag;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object o:c) {
            if(contains(o)) {
                remove(o);
                flag = true;
            }
        }
        return flag;
    }


private boolean Contains(E e, Collection<?> c)
{
    for (Object o:c) {
        if(e.equals(o))
            return true;
    }
    return false;
}

    @Override
    public boolean retainAll(Collection<?> c) {

        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            Node<E> pointer = array[i];
            while(pointer!= null){

                if(!Contains(pointer.data, c))
                {
                    remove(pointer.data);
                    flag = true;
                }
                pointer = pointer.next;
            }

        }
        return flag;
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















}
