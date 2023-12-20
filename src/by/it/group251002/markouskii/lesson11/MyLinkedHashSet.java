package by.it.group251002.markouskii.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E>{
    private int[] keys = new int[0];
    private Lists<E>[] lists = (Lists<E>[])  new Lists[]{};
    private Lists<E> addOrderList=null;
    private int size=0;


    public void addOrder(E e){
        if (addOrderList==null){
            addOrderList=new Lists<E>(e);
        } else {
            Lists<E> temp = addOrderList;
            while (temp.next!=null) temp=temp.next;
            temp.next=new Lists<E>(e);
        }
    }
    public void removeOrder(Object e){
        Lists<E> temp=addOrderList;
        if (e.equals(addOrderList.elem)) {
            addOrderList=addOrderList.next;
            return;
        }
        while (temp.next!=null && !temp.next.elem.equals(e)) {
            temp=temp.next;
        }
        temp.next=temp.next.next;
    }
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String delimiter = "";
        Lists<E> temp=addOrderList;
        while (temp!=null){
            sb.append(delimiter).append(temp.elem);
            temp = temp.next;
            delimiter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean contains(Object o) {
        int code = o.hashCode();
        int pos=0;
        while(pos<keys.length && keys[pos]!=code) pos++;
        if (pos == keys.length) return false;

        Lists<E> temp=lists[pos];
        while (temp!=null && !temp.elem.equals(o)) temp=temp.next;
        if (temp==null) return false;
        return true;
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
        int code = e.hashCode();
        int pos=0;
        while(pos<keys.length && keys[pos]!=code) pos++;
        if (pos==keys.length) {
            int[] newarray=new int[keys.length+1];
            Lists<E>[] newlists =  (Lists<E>[])  new Lists[keys.length+1];
            System.arraycopy(keys,0,newarray,0,keys.length);
            System.arraycopy(lists,0,newlists,0,keys.length);
            newarray[keys.length]=code;
            newlists[keys.length]=new Lists<E>(e);
            addOrder(e);
            keys=newarray;
            lists=newlists;
            size++;
        } else {
            Lists<E> temp=lists[pos];
            if (temp==null){
                lists[pos]=new Lists<E>(e);
                size++;
                addOrder(e);
                return true;
            }
            if (temp.elem.equals(e)) return false;

            while (temp.next!=null) {
                if (temp.elem.equals(e)) return false;
                temp=temp.next;
            }
            temp.next=new Lists<E>(e);
            addOrder(e);
            size++;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int code = o.hashCode();
        int pos=0;
        while(pos<keys.length && keys[pos]!=code) pos++;
        if (pos==keys.length) return false;

        Lists<E> temp=lists[pos];
        if (temp==null) return false;
        if (temp.elem.equals(o)) {
            lists[pos]=null;
            size--;
            removeOrder(o);
            return true;
        }
        while (temp.next!=null && !temp.next.elem.equals(o)){
            temp=temp.next;
        }
        if (temp.next!=null) {
            temp.next=temp.next.next;
        } else return false;
        size--;
        removeOrder(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] a = (E[]) new Object[c.size()];
        a = c.toArray(a);
        for (int i=0;i<a.length;i++){
            if (!contains(a[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        boolean changed = false;
        E[] newArr = (E[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add(newArr[i]);
            if (prevSize < size) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Lists<E> a = addOrderList;
        E temp;
        boolean changed = false;
        while (a!=null){
            temp=a.elem;
            a=a.next;
            if (!c.contains(temp)) {
                remove(temp);
                changed=true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Lists<E> a = addOrderList;
        E temp;
        boolean changed = false;
        while (a!=null){
            temp=a.elem;
            a=a.next;
            if (c.contains(temp)) {
                remove(temp);
                changed=true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        for(int i=0;i< lists.length;i++){
            lists[i]=null;
        }
        addOrderList=null;
        size=0;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }


}
