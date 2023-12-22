package by.it.group251001.sidorovich.lesson10;


import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.Collection;

public class MyPriorityQueue<E> implements Deque<E> {

    private Object m[]=new Object[1];
    private int siz=0;



private int parent(int index) {
    return (index - 1) / 2;
}

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parentInd = parent(index);
        if (((Comparable<E>) m[parentInd]).compareTo((E)m[index]) < 0) {
            return;
        }

        E tmp = (E)m[parentInd];
        m[parentInd] = m[index];
        m[index] = tmp;

        siftUp(parentInd);
    }

    private void siftDown(int index) {
        int l = leftChild(index), r = rightChild(index);
        if (l >= siz) {
            return;
        }

        int dest = l;
        if (r < siz && ((Comparable<E>) m[r]).compareTo((E)m[l]) < 0) {
            dest = r;
        }
        if (((Comparable<E>) m[index]).compareTo((E)m[dest]) < 0) {
            return;
        }

        E tmp =(E) m[dest];
        m[dest] = m[index];
        m[index] = tmp;

        siftDown(dest);
    }

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
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

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
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
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
       siz++;
       m=Arrays.copyOf(m,siz);
     /*    int i=0;
       while ((i<siz-1)&&((Comparable<E>)(m[i])).compareTo(e)<0){
           i++;
       }

           for (int ii=siz-1; ii>i;ii--){
               m[ii]=m[ii-1];
           }
       m[i]=e;//*/
        m[siz-1]=e;
        siftUp(siz-1);
           return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);

    }

    @Override
    public E remove() {
    E temp=(E)m[0];
        siz--;
        m[0] = m[siz];
     m=Arrays.copyOf(m,siz);
        siftDown(0);
        return temp;
    }

    @Override
    public E poll() {
       return remove();
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek() {
    if(siz>0) return (E)m[0];
    return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (var O:c) {
            add(O);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
            boolean changed = false;
            int i = 0;
            while (i < siz) {
                if (c.contains(m[i])) {
                    System.arraycopy(m, i+1, m, i, siz-i-1);
                    siz--;
                    changed = true;
                } else {
                    i++;
                }
            }
        int s = siz - 1;
        while (s >= 0) {
            siftDown(s);
            s--;
        }
            return changed;
        }

    @Override
    public boolean retainAll(Collection<?> c) {
            boolean changed = false;
            int ii = 0;
            while (ii < siz) {
                if (!c.contains(m[ii])) {
                    System.arraycopy(m, ii + 1, m, ii, siz - ii - 1);
                    siz--;
                    changed = true;
                } else {
                    ii++;
                }
            }
            int s = siz - 1;
            while (s >= 0) {
                siftDown(s);
                s--;
            }
            return changed;
        }


    @Override
    public void clear() {
m= Arrays.copyOf(m,0);
siz=0;
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
        for (int i = 0; i < siz; i++) {
            if (o.equals(m[i])) {
                m[i] = m[--siz];
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var O:c)
            if (!contains(O))
                return false;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0; i<siz;i++)
        {
            if (o.equals(m[i]))
                return true;

        }
        return false;

    }

    @Override
    public int size() {
        return siz;
    }

    @Override
    public boolean isEmpty() {
      if(siz>0) return false;
        return true;
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