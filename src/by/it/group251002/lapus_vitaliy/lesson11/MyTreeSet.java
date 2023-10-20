package by.it.group251002.lapus_vitaliy.lesson11;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class Mylist<E>{

    public E elem;
    public int color;
    public boolean del;
    Mylist<E> left, right, parent;

    public Mylist(E e, int col, Mylist<E> par, Mylist<E> l, Mylist<E> r)
    {
        elem=e;
        color=col;
        left=l;
        right=r;
        parent=par;
        del=false;
    }
}

public class MyTreeSet<E> implements Set<E> {

    Mylist<E> tree= null;
    int size=0;

    Mylist<E> nullleaf = new Mylist<E>(null, 0, null, null, null);


    void rotateLeft(Mylist<E> N)
    {
        N.parent.right=N.left;
        if(N.left!=nullleaf)
        {
            N.left.parent=N.parent;
        }
        N.left=N.parent;
        if(N.parent.parent.left==N.parent) {
            N.parent.parent.left = N;
        }
        else if(N.parent.parent.right==N.parent){
            N.parent.parent.right = N;
        }
        N.parent = N.left.parent;
        N.left.parent=N;
    }

    void rotateRight(Mylist<E> N)
    {
        N.parent.left=N.right;
        if(N.right!=nullleaf)
        {
            N.right.parent=N.parent;
        }
        N.right=N.parent;
        if(N.parent.parent.left==N.parent) {
            N.parent.parent.left = N;
        }
        else if(N.parent.parent.right==N.parent){
            N.parent.parent.right = N;
        }
        N.parent=N.right.parent;
        N.right.parent=N;
    }

    void correction(Mylist<E> N){
        if(N.parent==nullleaf)
        {
            N.color=0;
        }
        else if((N.parent.color==0) )
        {
            return;
        } else if ((N.parent.parent.left.color==1) && (N.parent.parent.right.color==1)) {
            N.parent.parent.left.color=0;
            N.parent.parent.right.color=0;
            N.parent.parent.color=1;
            correction(N.parent.parent);
            return;
        }
        else
        {
            if(N.parent.left==N)
            {
                if(N.parent.parent.left==N.parent)
                {
                    N=N.parent;
                    rotateRight(N);
                    N.color=0;
                    N.right.color=1;
                }
                else{
                    rotateRight(N);
                    rotateLeft(N);
                    N.color=0;
                    N.right.color=1;
                }
            }
            else{
                if(N.parent.parent.right==N.parent)
                {
                    N=N.parent;
                    rotateLeft(N);
                    N.color=0;
                    N.left.color=1;
                }
                else{
                    rotateLeft(N);
                    rotateRight(N);
                    N.color=0;
                    N.right.color=1;
                }
            }
        }
    }

    Mylist<E> goUp(Mylist<E> head)
    {
        while(head.parent!=nullleaf)
            head=head.parent;
        return head;
    }

    void stringMaker(Mylist<E> tree, StringBuilder str)
    {
        if((tree!=nullleaf) && (tree!=null)) {
            stringMaker(tree.left, str);
            if(tree.del==false)
            {
                str.append(tree.elem).append(", ");
            }
            stringMaker(tree.right, str);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        stringMaker(tree,str);
        if(str.length()>=3)
            str.delete(str.length()-2, str.length());
        str.append("]");
        return str.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        Mylist<E> buf=tree;
        if(buf==null)
        {
            return false;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<E>)(buf.elem)).compareTo((E)(o))>0)
            {
                buf=buf.left;
            } else if (((Comparable<E>)(buf.elem)).compareTo((E)(o))<0) {
                buf=buf.right;
            }
            else{
                if(buf.del==false){
                    return true;
                }
                else {
                    return false;
                }
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
        if(tree==null)
        {
            nullleaf.right=nullleaf;
            nullleaf.left=nullleaf;
            nullleaf.parent=nullleaf;
            tree= new Mylist<E>(e, 0, nullleaf, nullleaf, nullleaf);
            size++;
            return true;
        }
        else{
            Mylist<E> buf = tree;
            Mylist<E> lastbuf=tree;
            boolean temp=false;
            while(true)
            {
                if(buf==nullleaf)
                {
                    buf=new Mylist<E>(e, 1, lastbuf, nullleaf, nullleaf);
                    size++;
                    if(temp){
                        lastbuf.left=buf;
                    }
                    else{
                        lastbuf.right=buf;
                    }
                    correction(buf);
                    tree=goUp(tree);
                    return true;
                }
                if(((Comparable<E>)(buf.elem)).compareTo((e))>0)
                {
                    temp=true;
                    lastbuf=buf;
                    buf=buf.left;
                } else if (((Comparable<E>)(buf.elem)).compareTo((e))<0) {
                    temp=false;
                    lastbuf=buf;
                    buf=buf.right;
                }
                else{
                    if(buf.del==true)
                    {
                        buf.del=false;
                        size++;
                        return true;
                    }
                    return false;
                }
            }
        }

    }

    @Override
    public boolean remove(Object o) {
        Mylist<E> buf=tree;
        if(buf==null)
        {
            return false;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<E>)(buf.elem)).compareTo((E)(o))>0)
            {
                buf=buf.left;
            } else if (((Comparable<E>)(buf.elem)).compareTo((E)(o))<0) {
                buf=buf.right;
            }
            else{
                if(buf.del==false) {
                    buf.del = true;
                    size--;
                    return true;
                }
                else{return false;}
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < c.size(); i++) {
            if(!(contains(bufAr[i])))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            add(bufAr[i]);
        }
        return lastSize!=size;
    }



    ////////не сделано
    public void retain(Collection<?> c, Mylist<E> buf){
        if((buf!=nullleaf) && (buf!=null)) {
            if(!(c.contains(buf.elem)))
            {
                remove(buf.elem);
            }
            retain(c, buf.left);
            retain(c, buf.right);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int lastSize=size;
        retain(c,tree);
        goUp(tree);
        return lastSize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int lastSize=size;
        E[] bufAr = (E[]) new Object[c.size()];
        bufAr=c.toArray(bufAr);
        for (int i = 0; i < bufAr.length; i++) {
            while(remove(bufAr[i]));
        }
        return lastSize!=size;
    }

    @Override
    public void clear() {
        size=0;
        tree=null;
    }
}
