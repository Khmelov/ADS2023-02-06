package by.it.group251001.sidorovich.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private double k= 0.75;
    private int rsize=4;
    private int nofel=0;
    protected static class Node<E> {
        public E data;
        public Node<E> next;

        public Node() {

        }

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    private Node m[]=new Node[rsize];

private int hashcode(E e)
    {
    return (int)e%rsize;
    }
private void rehash(){
    Node tm[] = new Node[rsize/2];
tm=Arrays.copyOf(m,rsize/2);
    for (int i=0;i<rsize/2;i++) m[i]=null;
    m= Arrays.copyOf(m,rsize);
    nofel=1;
    Node tempnode;
    System.out.println(rsize);
    for (int i=0;i<rsize/2;i++) {
        tempnode = tm[i];
        while (tempnode != null) {
           add((E)tempnode.data);
            tempnode = tempnode.next;
        }
    }

}
    private void resize(){
        rsize*=2;
        rehash();


    }


    @Override
    public String toString(){
    if (isEmpty()) return "[]";
        StringBuilder sb=new StringBuilder("[");
        Node tempnode=new Node();
        for (int i=0;i<rsize;i++) {
            tempnode = m[i];
            while (tempnode != null) {
                sb.append(tempnode.data + ", ");
                tempnode = tempnode.next;
            }
        }
      //  sb.append("]");
       System.out.println(sb.substring(0,sb.length()-2)+"]");
        return sb.substring(0,sb.length()-2)+"]";
    }


    @Override
    public int size() {
        return nofel;
    }

    @Override
    public boolean isEmpty() {
    if (nofel==0)
        return true;
    return false;
    }

    @Override
    public boolean contains(Object o) {
        Node tempnode;
    for (int i=0;i<rsize;i++){
        tempnode=m[i];
        while (tempnode!=null){
            if (tempnode.data.equals(o))
                return true;
            tempnode=tempnode.next;
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

    @Override
    public boolean add(E e) {
   if (contains(e)) return false;

      nofel++;
      if (nofel/rsize>k) resize();

      Node n=new Node(e);
       int h= hashcode(e);
      if (m[h]==null)
      m[h]=n;
else {
          Node tempnode = new Node();
          tempnode = m[h];
          while (!(tempnode.next==null))
              tempnode = tempnode.next;

tempnode.next=n;
      }
        return true;
    }

    @Override
    public boolean remove(Object o) {

        if (contains((E)o)){
            Node tempnode,tm=new Node();

                tempnode = m[hashcode((E)o)];
                tm=null;
                while (!(tempnode.data .equals(o))) {
                  tm=tempnode;
                    tempnode = tempnode.next;
                }
                if (tm==null) {
                    if (tempnode.next == null) {
                        m[hashcode((E) o)] = null;
                    } else
                        m[hashcode((E) o)] = tempnode.next;
                }
                else{
                    tm.next=tempnode.next;
                }
nofel--;

            return true;

        }
        else return false;
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
for (int i=0;i<rsize;i++)m[i]=null;
rsize=20;
        m= Arrays.copyOf(m,rsize);
nofel=0;
    }
}
