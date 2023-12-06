package by.it.group251002.koryakova.lesson11;

import java.util.Collection;
import java.util.Arrays;
import java.util.Set;
import java.util.Iterator;

public class MyTreeSet<E> implements Set<E> {
    private Object[] arr = new Object[0];
    private int size = 0;
    @Override
    public String toString(){
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder rez = new StringBuilder("[");
        int i;
        for (i = 0; i < (size - 1); i++) {
            rez.append(arr[i].toString()).append(", ");
        }
        return rez + arr[size - 1].toString() + "]";
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        arr = new Object[0];
    }
    @Override
    public boolean isEmpty(){
        return (size == 0);
    }
    @Override
    public boolean add(E e){
        int index = 0;
        while((index < size) && (((Comparable<? super E>) arr[index]).compareTo(e) < 0)){
            index = index + 1;
        }
        if(!isEmpty() && (index < size) && (arr[index].equals(e))){
            return false;
        }
        if(arr.length == size){
            arr = Arrays.copyOf(arr, size * 2 + 1);
        }
        size = size + 1;
        int i;
        for(i = (size - 1); i > index; i--){
            arr[i] = arr[i - 1];
        }
        arr[index] = e;
        return true;
    }
    @Override
    public boolean remove(Object o){
        int index = 0;
        while((index < size) && (!arr[index].equals(o))){
            index = index + 1;
        }
        if(index == size){
            return false;
        }
        int i;
        for(i = index; i < size() - 1; i++){
            arr[i] = arr[i + 1];
        }
        size = size - 1;
        return true;
    }
    @Override
    public boolean contains(Object o){
        int i;
        for(i = 0; i < size; i++){
            if(arr[i].equals(o)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c){
        for(Object o : c){
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        boolean added = false;
        for(E o : c){
            if(add(o)){
                added = true;
            }
        }
        return added;
    }
    @Override
    public boolean removeAll(Collection<?> c){
        boolean[] bool = new boolean[size];
        int quan = 0;
        int qnt = 0;
        int i;
        for(i = 0; i < size; i++){
            if(c.contains(arr[i])){
                bool[i] = true;
                quan = quan + 1;
            }
        }
        if(quan == 0){
            return false;
        }
        Object[] newarr = new Object[size - quan];
        for(i = 0; i < size ; i++){
            if(!bool[i]){
                newarr[qnt++] = arr[i];
            }
        }
        arr = newarr;
        size = size - quan;
        return true;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean[] bool = new boolean[size];
        int quan = 0;
        int qnt = 0;
        int i;
        for (i = 0; i < size; i++)
            if (c.contains(arr[i])) {
                bool[i] = true;
                quan = quan + 1;
            }
        if (quan == size)
            return false;
        Object[] newArr = new Object[quan];
        for (i = 0; i < size; i++)
            if (bool[i])
                newArr[qnt++] = arr[i];
        arr = newArr;
        size = quan;
        return true;
    }
    @Override
    public Iterator<E> iterator(){
        return null;
    }

    @Override
    public Object[] toArray(){
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a){
        return null;
    }
}