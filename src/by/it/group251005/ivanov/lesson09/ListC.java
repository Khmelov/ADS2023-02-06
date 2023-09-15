package by.it.group251005.ivanov.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {
    public class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
            this.next = null;
        }

        Node() {
            this.value = null;
            this.next = null;
        }
    }
    private Node<E> head;
    private int size;
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        String sb = new String();
        //StringBuilder sb = new StringBuilder();
        sb += "[";
        //sb.append('[');

        Node<E> ptr = head;
        if(ptr != null){
            //sb.append(ptr.value);
            sb += ptr.value;
            ptr = ptr.next;
        }

        while(ptr != null){
            //sb.append(", ").append(ptr.value);
            sb += ", " + ptr.value;
            ptr = ptr.next;
        }

        /*

        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            sb.append(ptr.value);
            if (ptr.next != null) {
                sb.append(", ");
            }
        }

         */

        //sb.append(']');
        sb += "]";
        return sb;
    }

    @Override
    public boolean add(E e) {
        Node<E> second = new Node<>(e);

        if(this.size == 0){
            this.head = second;
            this.size = this.size + 1;
            return true;
        }
        Node<E> ptr = this.head;
        while (ptr.next != null){           //!!!
            ptr = ptr.next;
        }
        ptr.next = second;
        this.size = this.size + 1;

        return true;
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index >= this.size) {
            return null;
        }


        int oldSize = this.size--;

        if (index == 0) {
            E result = this.head.value;
            if (oldSize == 1) {
                this.head = null;
            } else {
                this.head = this.head.next;
            }
            return result;
        }


        Node<E> ptr = this.head;
        for (int i = 1; i < index; i++) {
            ptr = ptr.next;
        }

        E result = ptr.next.value;

        if (index + 1 == oldSize) {
            ptr.next = null;
        } else {
            ptr.next = ptr.next.next;
        }

        return result;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(int index, E element) {
        //Node<E> second = new Node<E>;
        if(index < 0 || index > this.size){
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> ptr = this.head;
        if(index == 0){
            newNode.next = this.head;
            this.head = newNode;
            //this.head = ptr;
            this.size++;
            return;
        }
        for(int i = 0;i < index - 1;i++){
            ptr = ptr.next;
            //this.head = this.head.next;
        }

        newNode.next = ptr.next;
        ptr.next = newNode;
        //ptr.next.value = element;

        this.size++;

    }

    @Override
    public boolean remove(Object o) {
        /*
        Node<E> ptr = this.head;
        if(ptr.value == o){     //для первого элемента
            this.head = this.head.next;
        }
        while(ptr != null){
            if(ptr.next.value == o){
                ptr.next = ptr.next.next;
                size--;
                return false;
            }
            ptr = ptr.next;
        }
        return false;

         */

        if (o == null || this.size == 0) {
            return false;
        }
        if (o.equals(this.head.value)) {
            if (this.size == 1) {
                this.head = null;
            } else {
                this.head = this.head.next;
            }
            --this.size;
            return true;
        }
        if (this.size == 1) {
            return false;
        }
        Node<E> prev = this.head;
        for (Node<E> ptr = this.head.next; ptr != null; ptr = ptr.next) {
            if (o.equals(ptr.value)) {
                if (ptr.next == null) {
                    prev.next = null;
                } else {
                    prev.next = prev.next.next;
                }
                --this.size;
                return true;
            }
            prev = prev.next;
        }
        return false;

    }

    @Override
    public E set(int index, E element) {
        Node<E> ptr = this.head;
        for(int i = 0; i < index;i++){
            ptr = ptr.next;
        }
        E answer = ptr.value;
        ptr.value = element;
        return answer;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void clear() {

        this.head = null;
        this.size = 0;

    }

    @Override
    public int indexOf(Object o) {
        Node<E> ptr = this.head;
        int index = 0;
        while(ptr != null){
            if(o.equals(ptr.value)){
                return index;
            }
            ptr = ptr.next;
            index++;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        Node<E> ptr = this.head;
        for(int i = 0; i < index;i++){
            ptr = ptr.next;
        }
        return ptr.value;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> ptr = this.head;
        while(ptr != null){
            if(o.equals(ptr.value)){
                return true;
            }
            ptr = ptr.next;
        }
        /*
        for(int i = 0; i < this.size;i++){
            if(o.equals(ptr.value)){
                return true;
            }
        }
         */
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> ptr = this.head;
        int index = 0, answer = -1;
        while(ptr != null){
            if(o.equals(ptr.value)){
                answer = index;
            }
            ptr = ptr.next;
            index++;
        }
        return answer;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] elements = c.toArray();
        boolean elementIsInCollection = false;
        for (int i = 0; i < elements.length; i++) {
            elementIsInCollection = false;
            for (int j = 0; j < this.size; j++) {
                if (elements[i].equals(this.get(j))) {
                    elementIsInCollection = true;
                    break;
                }
            }

            if (!elementIsInCollection) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] elements = c.toArray();

        int oldSize = this.size;

        for (Object o : elements) {
            this.add((E)o);
        }

        return this.size != oldSize;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] elements = c.toArray();

        int oldSize = this.size;

        for (Object o : c) {
            this.add(index++, (E) o);
        }

        return this.size != oldSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] elements = c.toArray();
        int oldSize = this.size;
        boolean removeAllelem = true;
        for(Object o : elements){
            removeAllelem = true;
            while(removeAllelem){
                removeAllelem = this.remove((E)o);
            }
            /*
            this.remove((E)o);
            this.remove((E)o);
            this.remove((E)o);
             */
        }

        return this.size != oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ArrayList<Integer> arr = new ArrayList<>();
        int diff = 0;
        int i = 0;
        Node<E> ptr = this.head;
        while (ptr != null){
            if(!c.contains(ptr.value)){
                arr.add(i - diff++);
            }
            ptr = ptr.next;
            i++;
        }
        for (Integer index: arr) {
            this.remove(index.intValue());
        }
        return arr.size() > 0;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
