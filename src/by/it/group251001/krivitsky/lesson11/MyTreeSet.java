package by.it.group251001.krivitsky.lesson11;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyTreeSet<E> implements Set<E>{

    private E[] elements;
    private int size;
    MyTreeSet(){
        elements = (E[]) new Object[10];
        size = 0;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size-1; i++){
            sb.append(elements[i]+", ");
        }
        if (size > 0){
            sb.append(elements[size-1]);
        }
        sb.append("]");
        return sb.toString();
    }
    public int size(){
        return size;
    }
    public void clear(){
        size = 0;
        elements = (E[]) new Object[10];
    }
    public boolean isEmpty(){
        return size == 0;
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

    private int compare(Object e1, Object e2){
        return (Integer)e1 - (Integer)e2;
    }

    private int searchforfind(Object e){
        if (size == 0){
            return -1;
        }
        int left = 0;
        int right = size - 1;
        int index = 0;
        while (left <= right){
            index = (left+right) / 2;
            int cmp = compare(e, elements[index]);
            if (cmp < 0){
                right = index - 1;
            }
            else if (cmp > 0){
                left = index + 1;
            }
            else{
                break;
            }
        }
        if (elements[index].equals(e)){
            return index;
        }
        else{
            return -1;
        }
    }

    private int searchforinsert(Object e){
        if (size == 0){
            return 0;
        }
        int left = 0;
        int right = size - 1;
        int index = 0;
        while (left <= right){
            index = (left+right) / 2;
            int cmp = compare(e, elements[index]);
            if (cmp < 0){
                right = index - 1;
            }
            else if (cmp > 0){
                left = index + 1;
            }
        }
        return left;
    }

    public boolean add(E e){
        if (contains(e)){
            return false;
        }
        if (size == elements.length){
            E[] newarr = (E[]) new Object[size*2];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
        int index = searchforinsert(e);
        System.arraycopy(elements, index, elements, index+1, size - index);
        elements[index] = (E) e;
        size++;
        return true;
    }
    public boolean remove(Object e){
        if (contains(e)){
            int index = searchforfind(e);
            System.arraycopy(elements, index+1, elements, index, size - index - 1);
            size--;
            return true;
        }
        return false;
    }
    public boolean contains(Object e){
        int index = searchforfind(e);
        if (index == -1){
            return false;
        }
        return true;
    }

    public boolean containsAll(Collection<?> e){
        for (Object element: e){
            if (!contains(element)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(Collection<? extends E> e){
        boolean result = false;
        for (E ei: e){
            if (add(ei)){
                result = true;
            }
        }
        return result;
    }
    public boolean removeAll(Collection<?> e){
        boolean result = false;
        for (Object ei: e){
            if (remove(ei)){
                result = true;
            }
        }
        return result;
    }
    public boolean retainAll(Collection<?> e){
        int oldsize = size;
        int i = 0;
        while (i < size){
            if (!e.contains(elements[i])){
                System.arraycopy(elements, i+1, elements, i, size - i - 1);
                size--;
            }
            else{
                i++;
            }
        }
        return size != oldsize;
    }
}
