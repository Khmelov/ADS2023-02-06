package by.it.group251002.markouskii.lesson11;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private int[] keys = new int[0];
    private Lists<E>[] lists = (Lists<E>[])  new Lists[]{};
    private E[] elements=(E[]) new Comparable[0];
    private int size=0;

    public int BinarySearch(int val,int length){
        int l=0,r=length-1;
        int pos=(l+r+1)/2;;
        while (l<=r && val!=keys[pos]) {
            if (keys[pos] > val) {
                l = pos+1;
                pos = (r - l) / 2 + l;
            } else{
                r = pos-1;
                pos = (r - l) / 2 + l;
            }
        }
        if (pos < length && val==keys[pos])
        return pos;
        else
            return -1;
    }
    public int GetPos(int val,int length){
        int l=0,r=length-1;
        int pos;
        while(l<=r){
            pos= (l+r+1)/2;
            if (keys[pos]>val) {
                l=pos+1;
            } else r=pos-1;
        }
        return l;
    }

    public int BinarySearchElem(E val,int length){
        int l=0,r=length-1;
        int pos=(l+r+1)/2;;
        while (l<=r && !val.equals(elements[pos])) {
            if (elements[pos].compareTo(val)>0) {
                l = pos+1;
                pos = (r - l) / 2 + l;
            } else{
                r = pos-1;
                pos = (r - l) / 2 + l;
            }
        }
        if (pos < length && val.equals(elements[pos]))
            return pos;
        else
            return -1;
    }
    public int GetPosElem(E val,int length){
        int l=0,r=length-1;
        int pos;
        while(l<=r){
            pos = (l+r+1)/2;
            if (elements[pos].compareTo(val)>0) {
                l=pos+1;
            } else r=pos-1;
        }
        return l;
    }

    public void InsertVal(E val){
        int pos=GetPosElem(val,size-1);
        E[] newelem = (E[]) new Comparable[size];
        System.arraycopy(elements,0,newelem,0,pos);
        System.arraycopy(elements,pos,newelem,pos+1,size-pos-1);
        newelem[pos]=val;
        elements=newelem;
    }
    public void DeleteVal(E val){
        int pos=BinarySearchElem(val,size+1);
        E[] newelem = (E[]) new Comparable[size];
        System.arraycopy(elements,0,newelem,0,pos);
        System.arraycopy(elements,pos+1,newelem,pos,size-pos);
        elements=newelem;
    }
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        String delimiter = "";
        for (int i=size-1;i>=0;i--) {
            sb.append(delimiter).append(elements[i]);
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
        int pos=BinarySearch(code,keys.length);
        if (pos == -1) return false;

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
        pos=BinarySearch(code,keys.length);
        if (pos==-1) {
            int[] newarray=new int[keys.length+1];
            Lists<E>[] newlists =  (Lists<E>[])  new Lists[keys.length+1];

            pos=GetPos(code,keys.length);
            System.arraycopy(keys,0,newarray,0,pos);
            System.arraycopy(keys,pos,newarray,pos+1,keys.length-pos);
            System.arraycopy(lists,0,newlists,0,pos);
            System.arraycopy(lists,pos,newlists,pos+1,keys.length-pos);
            newarray[pos]=code;
            newlists[pos]=new Lists<E>(e);
            keys=newarray;
            lists=newlists;
            size++;
            InsertVal(e);
        } else {
            Lists<E> temp=lists[pos];
            if (temp==null){
                lists[pos]=new Lists<E>(e);
                size++;
                InsertVal(e);
                return true;
            }
            if (temp.elem.equals(e)) return false;

            while (temp.next!=null) {
                if (temp.elem.equals(e)) return false;
                temp=temp.next;
            }
            temp.next=new Lists<E>(e);
            size++;
            InsertVal(e);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int code = o.hashCode();
        int pos=BinarySearch(code,keys.length);
        if (pos==-1) return false;

        Lists<E> temp=lists[pos];
        if (temp==null) return false;
        if (temp.elem.equals(o)) {
            lists[pos]=null;
            size--;
            DeleteVal((E)o);
            return true;
        }
        while (temp.next!=null && !temp.next.elem.equals(o)){
            temp=temp.next;
        }
        if (temp.next!=null) {
            temp.next=temp.next.next;
        } else return false;
        size--;
        DeleteVal((E)o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize = size;
        boolean changed = false;
        for (E element : c) {
            add(element);
            if (prevSize < size) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        int i=0;
        while (i<size) {
            if (!c.contains(elements[i])) {
                remove(elements[i]);
                changed=true;
            } else i++;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        int i=0;
        while (i<size) {
            if (c.contains(elements[i])) {
                remove(elements[i]);
                changed=true;
            } else i++;
        }
        return changed;
    }

    @Override
    public void clear() {
        for(int i=0;i< lists.length;i++){
            lists[i]=null;
        }
        E[] newarray=(E[]) new Comparable[0];
        int[] newkeys=new int[0];
        elements=newarray;
        keys=newkeys;
        size=0;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }


}
