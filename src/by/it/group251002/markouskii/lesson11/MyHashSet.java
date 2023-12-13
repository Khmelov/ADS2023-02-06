package by.it.group251002.markouskii.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class Lists<E>{
    public E elem;
    public Lists<E> next;
    public Lists(E data){
        elem=data;
        next=null;
    }
}

public class MyHashSet<E> implements Set<E> {
    private int[] keys = new int[0];
    private Lists<E>[] lists = (Lists<E>[])  new Lists[]{};
    private int size=0;

    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String delimiter = "";
        for (int i=0;i< lists.length;i++){
            Lists<E> temp=lists[i];
            while(temp!=null) {
                sb.append(delimiter).append(temp.elem);
                temp = temp.next;
                delimiter = ", ";
            }
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
            keys=newarray;
            lists=newlists;
            size++;
        } else {
            Lists<E> temp=lists[pos];
            if (temp==null){
                lists[pos]=new Lists<E>(e);
                size++;
                return true;
            }
            if (temp.elem.equals(e)) return false;

            while (temp.next!=null) {
                if (temp.elem.equals(e)) return false;
                temp=temp.next;
            }
            temp.next=new Lists<E>(e);
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
            return true;
        }
        while (temp.next!=null && !temp.next.elem.equals(o)){
            temp=temp.next;
        }
        if (temp.next!=null) {
            temp.next=temp.next.next;
        } else return false;
        size--;
        return true;
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
        for(int i=0;i< lists.length;i++){
            lists[i]=null;
        }
        size=0;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }


}
