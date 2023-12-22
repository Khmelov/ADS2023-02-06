package by.it.group251004.a_nediakin.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
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
        int cnt = 0;
        String s = "[";
        for(int i = 0; i < capacity; ++i){
            MyList.node temp = basket[i].root;
            while(temp != null){
                s += temp.value.toString();
                ++cnt;
                if(cnt != size)
                    s += ", ";
                temp = temp.next;
            }
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
        }
        int index = e.hashCode() % capacity;
        basket[index].add(e);
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o))
            return false;
        else {
            basket[o.hashCode() % capacity].remove((E)o);
            --size;
            return true;
        }
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

    @Override
    public void clear() {
        for(int i = 0; i < capacity; ++i){
            while(!basket[i].isEmpty())
                basket[i].remove();
        }
        size = 0;
    }
}
