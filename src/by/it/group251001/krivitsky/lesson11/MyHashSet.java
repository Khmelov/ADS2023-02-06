package by.it.group251001.krivitsky.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//Здесь реализовано на массиве. На односвязном списке будет в задании B.

public class MyHashSet<E> implements Set<E> {
    private int size = 0;

    private int capacity = 5;
    private MyList<E>[] buckets = new MyList[5];

    public MyHashSet(){
        for (int i = 0; i < capacity; i++){
            buckets[i] = new MyList<E>();
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public boolean contains(Object o) {
        int id = o.hashCode() % capacity;
        for (int i = 0; i < buckets[id].size(); i++) {
            if (buckets[id].get(i).equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < capacity; i++){
            for (int j = 0; j < buckets[i].size(); j++){
                sb.append(buckets[i].get(j));
                if (cnt == size-1){
                    sb.append("]");
                }
                else{
                    sb.append(", ");
                }
                cnt++;
            }
        }
        if (size == 0){
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
    @Override
    public Object[] toArray() {
        return null;
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
        if (size == capacity) {
            int newcap = capacity * 2;
            MyList<E>[] newarr = new MyList[newcap];
            for (int i = 0; i < newcap; i++){
                newarr[i] = new MyList<E>();
            }
            for (int i = 0; i < capacity; i++){
                while (!buckets[i].isEmpty()){
                    E temp = buckets[i].remove(0);
                    newarr[temp.hashCode() % newcap].add(temp);
                }
            }
            capacity = newcap;
            MyList<E>[] temp = buckets;
            buckets = newarr;
            temp = null;
        }
        buckets[e.hashCode() % buckets.length].add(e);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = buckets[o.hashCode() % buckets.length].remove(o);
        if (result){
            size--;
        }
        return result;
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
        for (int i = 0; i<capacity; i++){
            buckets[i].clear();
        }
        size = 0;
    }
}
