package by.it.group251001.smychek.lesson11;

import java.util.*;
public class MyLinkedHashSet<E> implements Set<E>{
    private class MyList<E>{
        private class node<E>{
            node<E> next;
            E value;
            node(E value) {
                this.value = value;
            }
        }

        node<E> root = null;

        void add(E x){
            node<E> temp = new node<>(x);
            temp.next = root;
            root = temp;
        }

        boolean isEmpty(){
            return root == null;
        }

        void remove(){
            node<E> temp = root;
            root = root.next;
            temp = null;
        }

        void remove(E x){
            if(contains(x)) {
                if (root.value.equals(x))
                    remove();
                else {
                    node<E> temp = root, delet;
                    while (!temp.next.value.equals(x))
                        temp = temp.next;
                    delet = temp.next;
                    temp.next = temp.next.next;
                    delet = null;
                }
            }
        }

        boolean contains(E x){
            node<E> temp = root;
            while(temp != null){
                if(temp.value.equals(x))
                    return true;
                temp = temp.next;
            }
            return false;
        }
    }

    int size = 0, capacity = 0;
    MyList<E>[] basket = new MyList[0];
    E[] to_str_arr = (E[]) new Object[]{};

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String s = "[";
        for(int i = 0; i < size; ++i){
            s += to_str_arr[i];
            if(i != size - 1)
                s += ", ";
        }
        s += "]";
        return s;
    }

    @Override
    public boolean contains(Object o) {
        return size != 0 && basket[o.hashCode() % capacity].contains((E)o);
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
    public boolean add(E e) {
        if (contains(e))
            return false;
        if(size == capacity){
            int new_capacity = capacity * 2;
            if(new_capacity == 0)
                new_capacity = 5;
            MyList<E>[] new_basket = new MyList[new_capacity];
            for(int i = 0; i < new_capacity; ++i)
                new_basket[i] = new MyList<>();
            for(int i = 0; i < capacity; ++i){
                while(!basket[i].isEmpty()){
                    E temp = basket[i].root.value;
                    new_basket[temp.hashCode() % new_capacity].add(temp);
                    basket[i].remove();
                }
            }
            MyList<E>[] temp = basket;
            basket = new_basket;
            temp = null;
            capacity = new_capacity;
            to_str_arr = Arrays.copyOf(to_str_arr, new_capacity);
        }
        int index = e.hashCode() % capacity;
        basket[index].add(e);
        to_str_arr[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o))
            return false;
        else {
            basket[o.hashCode() % capacity].remove((E)o);
            for(int i = 0; i < size - 1; ++i)
                if(to_str_arr[i].equals(o)){
                    E temp = to_str_arr[i];
                    to_str_arr[i] = to_str_arr[i + 1];
                    to_str_arr[i + 1] = temp;
                }
            --size;
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            if(!contains(params[i]))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            add(params[i]);
        return prevSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < size; ++i)
            if(!c.contains(to_str_arr[i]))
                remove(to_str_arr[i--]);
        return prevSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize = size;
        E[] params = (E[]) new Object[c.size()];
        params = c.toArray(params);
        for(int i = 0; i < params.length; ++i)
            if(contains(params[i]))
                remove(params[i]);
        return prevSize != size;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacity; ++i){
            while(!basket[i].isEmpty())
                basket[i].remove();
        }
        size = 0;
    }
}
