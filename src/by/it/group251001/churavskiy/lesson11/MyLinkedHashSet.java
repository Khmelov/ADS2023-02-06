package by.it.group251001.churavskiy.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    private int size = 0;
    final private int minsize = 8;
    private MyNode<E>[] table = (MyNode<E>[])new MyNode[minsize];
    private MyNode<E> first, last;

    static private class MyNode<E>{
        E data;
        MyNode<E> next;
        MyNode<E> before, after;
        MyNode(E e, MyNode next,MyNode before, MyNode after){
            this.data = e;
            this.next = next;
            this.before = before;
            this.after = after;
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
        for(MyNode<E> x = first; x!=null; x = x.after){
            sb.append(divider);
            sb.append(x.data);
            divider = ", ";
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
                e.before = null;
                e.after = null;
                MyNode temp = e;
                e = e.next;
                temp.next = null;
            }
            table[i] = null;
        }
        table = (MyNode<E>[]) new MyNode[minsize];
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {return size()==0;}

    private void resize(){
        MyNode<E>[] newtable = (MyNode<E>[])new MyNode[table.length*2];
        for(MyNode<E> x = first; x!=null; x = x.after){
            int pos = calchash(x.data) % newtable.length;
            if (newtable[pos]==null){
                newtable[pos] = x;
            }else{
                MyNode end = newtable[pos];
                while (end.next!=null)
                    end = end.next;
                end.next = x;
            }
            x.next = null;
        }
        table = newtable;
    }

    @Override
    public boolean add(Object o) {
        if (size == table.length)
            resize();
        MyNode<E> newnode;
        int pos = calchash((E)o) % table.length;
        if (table[pos]==null){
            newnode = new MyNode(o, null, last, null);
            table[pos] = newnode;
        }else {
            MyNode end = table[pos];
            while (end.next != null) {
                if (end.data.equals(o))
                    return false;
                end = end.next;
            }
            if (end.data.equals(o))
                return false;
            newnode = new MyNode(o, null, last, null);
            end.next = newnode;
        }
        if(first!=null)
            last.after = newnode;
        else
            first = newnode;
        last = newnode;
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
                if (prev == null) {///удаление из таблицы
                    table[pos] = table[pos].next;
                } else {
                    prev.next = e.next;
                    e.next = null;
                }

                MyNode<E> after = e.after;///удаление из списка
                MyNode<E> before = e.before;
                if(after != null){
                    after.before = before;
                } else {
                    last = before;
                }
                if(before != null){
                    before.after = after;
                } else {
                    first = after;
                }
                e.after = null;
                e.before = null;
                e.data = null;
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

    @Override
    public boolean containsAll(Collection c) {
        Object []cArray = c.toArray();
        for(int i = 0;i < cArray.length;i++){
            if(!contains(cArray[i]))
                return false;
        }
        return true;
    }
    @Override
    public boolean addAll(Collection c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0) {
            return false;
        }
        boolean result = false;
        for(int i = 0;i < cArray.length;i++){
            result |= add(cArray[i]);
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0) {
            return false;
        }
        boolean result = false;
        for(int i = 0;i < cArray.length;i++){
            result |= remove(cArray[i]);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection c) {
        MyNode<E> x = first;
        boolean result = false;
        while(x!=null){
            if(!c.contains(x.data)){
                E tempE = x.data;
                x = x.after;
                remove(tempE);
                result = true;
            }else {
                x = x.after;
            }
        }
        return result;
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
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
