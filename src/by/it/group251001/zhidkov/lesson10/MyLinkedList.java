package by.it.group251001.zhidkov.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    private Knot<E> first;
    private Knot<E> last;
    private int size;

    // Конструктор
    public MyLinkedList() { //Инициализируем поля экземпляра класса
        first = null;
        last = null;
        size = 0;
    }

    // Вложенный класс для узла списка
    private static class Knot<E> {
        E data;
        Knot<E> P_Next;
        Knot<E> P_Prev;

        public Knot(E data) {
            this.data = data;
            P_Next = null;
            P_Prev = null;
        }
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        Knot<E> curr = first;
        while (curr != null) {
            result.append(curr.data);
            if (curr.P_Next != null) {
                result.append(", ");
            }
            curr = curr.P_Next;
        }
        result.append("]");
        return result.toString();
    }
    @Override
    public boolean remove(Object e){
        Knot<E> curr = first;
        while (curr != null)
        {

            if (e.equals(curr.data))
            {
                Knot<E> buf = curr;
                curr = curr.P_Prev;
                curr.P_Next = curr.P_Next.P_Next;
                curr = curr.P_Next;
                curr.P_Prev = curr.P_Prev.P_Prev;
                buf.P_Prev = null;
                buf.data = null;
                buf.P_Next = null;
                size--;
                return true;
            }
            curr = curr.P_Next;
        }
        return false;
    }

    @Override
    public void addFirst(E e) {
        Knot<E> newKnot = new Knot<E>(e);
        if (first == null)
        {
            first = newKnot;
            last = newKnot;
            first.P_Next = null;
            first.P_Prev = null;
        }
        else
        {
            first.P_Prev = newKnot;
            newKnot.P_Prev = null;
            newKnot.P_Next = first;
            first = newKnot;

        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Knot<E> newKnot = new Knot<E>(e);
        if (first == null)
        {
            first = newKnot;
            last = newKnot;
            first.P_Next = null;
            first.P_Prev = null;
        }
        else
        {
            last.P_Next = newKnot;
            newKnot.P_Prev = last;
            newKnot.P_Next = null;
            last = newKnot;
        }
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
        return remove(0);
    }

    @Override
    public E pollLast() {
        return remove(size - 1);
    }

   // @Override
    public E remove(int index){
        if (size != 0 && index>= 0 && index < size)
        {
            if (index == 39)
            {
                index = index;
            }
            double k = size / 2;
            if (index < k)
            {
                Knot<E> curr = first;
                for (int i = 0; i < index; i++)
                {
                    curr = curr.P_Next;
                }
                if (curr != first)
                {
                    Knot<E> bufPr = curr.P_Prev;
                    Knot<E> bufNx = curr.P_Next;
                    bufPr.P_Next = bufNx;
                    bufNx.P_Prev = bufPr;
                    curr.P_Next = null;
                    curr.P_Prev = null;
                }
                else
                {
                    Knot<E> buf = first;
                    first = first.P_Next;
                    first.P_Prev = null;
                    buf.P_Next = null;
                    buf.P_Prev = null;
                }
                size--;
                return curr.data;
            }
            else
            {
                Knot<E> curr = last;
                for (int i = size - 1; i > index; i--)
                {
                    curr = curr.P_Prev;
                }
                if (curr != last)
                {
                    Knot<E> bufPr = curr.P_Prev;
                    Knot<E> bufNx = curr.P_Next;
                    bufPr.P_Next = bufNx;
                    bufNx.P_Prev = bufPr;
                    curr.P_Next = null;
                    curr.P_Prev = null;
                }
                else
                {
                    Knot<E> buf = last;
                    last = last.P_Prev;
                    last.P_Next = null;
                    buf.P_Next = null;
                    buf.P_Prev = null;
                }
                size--;
                return curr.data;
            }
        }
        else
        {
            return null;
        }
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
    @Override
    public E getFirst() {
        if (size != 0)
        {
            return first.data;
        }
        else
        {
            return null;
        }
    }

    @Override
    public E getLast() {
        if (size != 0)
        {
            return last.data;
        }
        else
        {
            return null;
        }
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

    public boolean add(E e) {
        addLast(e);
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
    public E poll() {
        return remove(0);
    }

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
