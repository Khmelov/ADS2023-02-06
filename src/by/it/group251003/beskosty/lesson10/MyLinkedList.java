package by.it.group251003.beskosty.lesson10;
import java.util.*;
public class MyLinkedList<E> implements Deque<E>{
    private E[] List = (E[]) new Object[0];
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        String temp = "";
        for (int i = 0; i < size; i++){
            string.append(temp).append(List[i]);
            temp = ", ";
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public void addFirst(E e) {
        if (size == List.length) {
            E[] newList = (E[]) new Object[2 * List.length + 1];
            System.arraycopy(List, 0, newList, 0, size);
            List = newList;
        }
        for(int i = size; i > 0; i--){
            List[i] = List[i-1];
        }
        List[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == List.length) {
            E[] newList = (E[]) new Object[2 * List.length + 1];
            System.arraycopy(List, 0, newList, 0, size);
            List = newList;
        }
        List[size] = e;
        size++;
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        E temp = List[0];
        for (int i = 0; i < size - 1; i++){
            List[i] = List[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public E pollLast() {
        return List[size-- - 1];
    }

    @Override
    public E getFirst() {
        return List[0];
    }

    @Override
    public E getLast() {
        return List[size-1];
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        if (size == List.length) {
            E[] newList = (E[]) new Object[2 * List.length + 1];
            System.arraycopy(List, 0, newList, 0, size);
            List = newList;
        }
        List[size++] = e;
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }


    public E remove(int index) {
        E DelEl = List[index];
        for (int i = index; i < size; i++) {
            List[i] = List[i + 1];
        }

        List[size - 1] = null;
        size--;

        return DelEl;
    }
    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object e) {
        int Eind = indexOf(e);
        if (Eind < 0) {
            return false;
        }

        remove(Eind);
        return true;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (List[i].equals(o)) return i;
        }
        return -1;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
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

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
