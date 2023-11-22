package by.it.group251001.churavskiy.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private int size = 0;
    private MyNode<E>[] table = (MyNode<E>[])new MyNode[8];
    private int takenplacecount = 0;

    static private class MyNode<E>{
        E data;
        MyNode<E> next;
        MyNode(E e, MyNode next){
            this.data = e;
            this.next = next;
        }

    }

    private int calchash(E e){
            return e.hashCode();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String divider = "";
        sb.append("[");
        for(int i = 0; i < table.length; i++){
            MyNode e = table[i];
                while (e!=null) {
                    sb.append(divider);
                    sb.append(e.data);
                    divider = ", ";
                    e = e.next;
                }
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public int size() {return size;}

    @Override
    public void clear() {
        for(int i = 0; i < table.length; i++){
            MyNode e = table[i];
            while(e!=null){
                e.data = null;
                MyNode temp = e;
                e = e.next;
                temp.next = null;
            }
            table[i] = null;
        }
        table = (MyNode<E>[]) new MyNode[0];
        size = 0;
        takenplacecount = 0;
    }

    @Override
    public boolean isEmpty() {return size()==0;}

    private void resize(){
        MyNode<E>[] newtable = (MyNode<E>[])new MyNode[table.length==0?8:table.length*2];
        int newtakenplacecount = 0;
        for(int i = 0; i<table.length;i++){
            MyNode<E> e = table[i];
            while(e!=null){
                int pos = calchash(e.data) % newtable.length;
                if (newtable[pos]==null){
                    newtable[pos] = new MyNode(e.data,null);
                    newtakenplacecount++;
                }else {
                    MyNode last = newtable[pos];
                    while (last.next != null)
                        last = last.next;
                    last.next = new MyNode(e.data,null);
                }
                e = e.next;
            }
        }
        int newsize = size;
        clear();
        table = newtable;
        size = newsize;
        takenplacecount = newtakenplacecount;
    }

    @Override
    public boolean add(Object o) {
        if (takenplacecount == table.length)
            resize();
        int pos = calchash((E)o) % table.length;
        if (table[pos]==null){
            table[pos] = new MyNode(o, null);
            takenplacecount++;
        }else{
            MyNode last = table[pos];
            while (last.next!=null) {
                if (last.data.equals(o))
                    return false;
                last = last.next;
            }
            if (last.data.equals(o))
                return false;
            last.next = new MyNode(o, null);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int pos = calchash((E)o) % table.length;
        MyNode prev = null;
        MyNode e = table[pos];
        while (e!=null){
            if (e.data.equals(o)) {
                if (prev == null) {
                    table[pos].data = null;
                    table[pos] = table[pos].next;
                } else {
                    e.data = null;
                    prev.next = e.next;
                    e.next = null;
                }
                size--;
                return true;
            }
            prev = e;
            e = e.next;
        }
        return false;
    }
        @Override
        public boolean contains(Object o) {
            int pos = calchash((E)o) % table.length;
            MyNode e = table[pos];
            while (e!=null){
                if (e.data.equals(o)) {
                    return true;
                }
                e = e.next;
            }
            return false;
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
