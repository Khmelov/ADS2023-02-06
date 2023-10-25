package by.it.group251001.krivitsky.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyLinkedHashSet<E> implements Set<E> {

    private int capacity = 5;
    private int size = 0;
    private MyLinkedList<E>[] buckets = new MyLinkedList[5];

    private E[] strarr = (E[]) new Object[5];
    private int arrlen = 0;
    MyLinkedHashSet(){
        for (int i = 0; i < capacity; i++){
            buckets[i] = new MyLinkedList<E>();
        }
    }


    /*

    removeAll(Collection)
    retainAll(Collection)
    */




    @Override
    public int size() {
        return size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arrlen - 1; i++){
            sb.append(strarr[i]+", ");
        }
        if (arrlen > 0){
            sb.append(strarr[size-1]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return buckets[o.hashCode() % capacity].contains(o);
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
        if (contains(e)){
            return false;
        }
        if (size == capacity){
            int newcapacity = capacity * 2;
            MyLinkedList<E>[] newarr = new MyLinkedList[newcapacity];
            for (int i = 0; i < newcapacity; i++){
                newarr[i] = new MyLinkedList<E>();
            }
            for (int i = 0; i < capacity; i++){
                while (!buckets[i].isEmpty()){
                    E temp = buckets[i].remove();
                    newarr[temp.hashCode() % newcapacity].add(temp);
                }
            }
            buckets = newarr;
            capacity = newcapacity;
        }
        buckets[e.hashCode() % capacity].add(e);
        if (arrlen == strarr.length){
            E[] newarr = (E[]) new Object[arrlen*3/2+1];
            System.arraycopy(strarr, 0, newarr, 0, arrlen);
            strarr = newarr;
        }
        strarr[arrlen++] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = buckets[o.hashCode() % capacity].remove(o);
        if (result){
            size--;
            for (int i = 0; i < arrlen; i++){
                if (strarr[i].equals(o)){
                    System.arraycopy(strarr, i+1, strarr, i, arrlen-i-1);
                    arrlen--;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object ci: c){
            if (!contains(ci)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E ci: c){
            add(ci);
        }
        return c.size() != 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (int i = 0; i < capacity; i++){
            size -= buckets[i].retainAll(c);
        }
        int i = 0;
        while (i < arrlen){
            if (!c.contains(strarr[i])){
                System.arraycopy(strarr, i+1, strarr, i, arrlen-i-1);
                arrlen--;
                result = true;
            }
            else{
                i++;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object ci: c){
            remove(ci);
            result = true;
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i].clear();
        }
        size = 0;
        arrlen = 0;
    }
}
