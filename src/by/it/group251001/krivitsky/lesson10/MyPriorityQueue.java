package by.it.group251001.krivitsky.lesson10;
import java.util.Iterator;
import java.util.Queue;
import java.util.Collection;
public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E>{
    private E[] elements = (E[]) new Comparable[10];
    private int size = 0;
    /*
                offer(E element)
    */
    private void posincsize(){
        if (size == elements.length){
            E[] newarr =  (E[]) new Comparable[size * 3 / 2 + 1];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
    }

    private void SiftUp(int index){
        if (index > 0){
            int mother = (index - 1) / 2;
            if (elements[index].compareTo(elements[mother])<0){
                swap(index, mother);
                SiftUp(mother);
            }
        }
    }

    private void SiftDown(int index){
        int child1 = index * 2 + 1;
        int child2 = index * 2 + 2;
        int toswap = index;
        if (child1 < size && elements[child1].compareTo(elements[toswap]) < 0) {
            toswap = child1;
        }
        if (child2 < size && elements[child2].compareTo(elements[toswap]) < 0) {
            toswap = child2;
        }

        if (toswap != index) {
            swap(index, toswap);
            SiftDown(toswap);
        }
    }

    private void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size-1;  i++){
            sb.append(elements[i]).append(", ");
        }
        if (size > 0){
            sb.append(elements[size-1]);
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean offer(E element){
        add(element);
        return true;
    }
    public int size(){
        return size;
    }

    public void clear(){
        size = 0;
        elements = (E[]) new Comparable[1];
    }
    public boolean add(E element){
        posincsize();
        elements[size] = element;
        SiftUp(size);
        size++;
        return true;
    }
    @Override
    public boolean remove(Object o) {

        return false;
    }
    public E remove(){
        E temp = elements[0];
        elements[0] = elements[--size];
        SiftDown(0);
        return temp;
    }
    public boolean contains(Object o){
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
                return true;
            }
        }
        return false;
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

    public E peek(){
        return elements[0];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E poll(){
        E temp = elements[0];
        remove(0);
        return temp;
    }

    public E element(){
        return elements[0];
    }
    @Override
    public boolean containsAll(Collection<?> c){
        boolean isFound;
        for (Object ci:c){
            isFound = false;
            for (int i = 0; i < size; i++){
                if (ci.equals(elements[i])) {
                    isFound = true;
                }
            }
            if (!isFound){
                return false;
            }
        }
        return true;
    }

    public void remove(int index){
        swap(index, size - 1);
        size--;
        SiftDown(index);
    }

    public boolean addAll(Collection<? extends E> c){
        for (E ci: c){
            add(ci);
        }
        return (c.size() != 0);
    }

    public boolean removeAll(Collection<?> c){
        E[] newData = (E[]) new Comparable[size];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                newData[newSize] = elements[i];
                newSize++;
            }
        }
        boolean modified = newSize != size;
        elements = newData;
        size = newSize;
        for (int i = size / 2; i >= 0; i--)
            SiftDown(i);
        return modified;
    }

    public boolean retainAll(Collection<?> c){
        E[] newData = (E[]) new Comparable[size];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                newData[newSize] = elements[i];
                newSize++;
            }
        }
        boolean modified = newSize != size;
        elements = newData;
        size = newSize;
        for (int i = size / 2; i >= 0; i--)
            SiftDown(i);
        return modified;
    }
}
