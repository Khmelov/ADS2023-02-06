package by.it.group251003.AlexanderKovalevich.lesson11;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private Node<E> []array = new Node[1];

    @Override
    public String toString() {
      String result = "[";

      int count = 0;
        for (int i = 0; i < array.length; i++) {
            Node<E> pointer = array[i];
            while (pointer != null)
            {
                result+=pointer.data;
                count++;
                pointer = pointer.next;
                if(count != size)
                    result+=", ";
            }
        }
        result+="]";
        return result;
    }

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

    private int gethashIndex(Object o)
    {
        return (o.hashCode()& 0x7FFFFFFF) % array.length;
    }

    private int size = 0;


    @Override
    public boolean add(E e) {

        if(!contains(e)) {
            int hashInd = gethashIndex(e);
            Node<E> newNode = new Node<>(e);
            newNode.next = array[hashInd];
            array[hashInd] = newNode;
            size++;

            if (size >array.length * 0.75)
                resize();
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
    public void clear() {

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
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
                    array[hashInd] = pointer.next;
                    size--;
                    return true;
                }
                if(pointer.next!=null && pointer.next.data.equals(o))
                {
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }


}
