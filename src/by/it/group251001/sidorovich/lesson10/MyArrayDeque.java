package by.it.group251001.sidorovich.lesson10;


import java.util.Deque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E>{
    private Object m[]=new Object[1];
    private int siz=0;

@Override
    public String toString(){
    StringBuilder sb=new StringBuilder("[");
    for (int i=0; i<siz-1;i++){
     sb.append(m[i]+", ");
    }
    if (siz!=0){
        sb.append(m[siz-1]);
    }
    sb.append("]");
    System.out.println(sb);
    return sb.toString();
}


    @Override
    public int size(){
    return siz;
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

    @Override
    public boolean add(E element){
      addLast(element);
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


    @Override
    public void addLast(E element){
  m=Arrays.copyOf(m,++siz);
  m[siz-1]=element;
    }
@Override
    public void addFirst(E element) {
    m = Arrays.copyOf(m, ++siz);
    for (int i = siz-1; i >0; i--) {
        m[i] = m[i-1];
    }
    m[0] = element;
}
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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
    public E element(){
        return (E) m[0];
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
    public boolean remove(Object o) {
        return false;
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
    public E getFirst(){
        return element();
    }

    @Override
    public E getLast(){
        return (E) m[siz-1];
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
    public E poll(){
      /*  E temp =(E) m[siz-1];
        m=Arrays.copyOf(m,--siz);
        return temp;
*/
      return  pollFirst();
    }

    @Override
    public E pollLast(){
        if (siz>0){
        E temp =(E) m[siz-1];

        m=Arrays.copyOf(m,--siz);

        return temp;
        }
        else return null;
 }

    @Override
    public E pollFirst(){
        if (siz>0){
      E temp = (E) m[0];

        for (int i=0;i<siz-1;i++)
            m[i]=m[i+1];
        m=Arrays.copyOf(m,--siz);
        return temp;
        }
        else return null;
    }




}