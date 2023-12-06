package by.it.group251002.lapus_vitaliy.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class MyAvl<Integer, String>{

    public Integer key;

    public String elem;
    public int size;
    public boolean del;
    MyAvl<Integer, String> left, right, parent;

    public MyAvl(Integer k,String e, MyAvl<Integer, String> par, MyAvl<Integer, String> l, MyAvl<Integer, String> r)
    {
        key=k;
        elem=e;
        left=l;
        right=r;
        parent=par;
        del=false;
        size=0;
    }
}


public class MyAvlMap implements Map<Integer, String> {

    MyAvl<java.lang.Integer, java.lang.String> tree= null;
    int size=0;

    MyAvl<Integer, String> nullleaf = new MyAvl<Integer, String>(0 ,null, null, null, null);

    private void rotateLeft(MyAvl<Integer, String> N)
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

    private void rotateRight(MyAvl<Integer, String> N)
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

    private int height(MyAvl<Integer, String> N)
    {
        return ((N.left==nullleaf || N.left==null) ? 0 : N.left.size)+((N.right==nullleaf || N.right==null) ? 0 : N.right.size);
    }

    public int heightCorrect(MyAvl<Integer, String> N)
    {
        int hl=height(N.left);
        int hr=height(N.right);
        return (hl>hr? hl : hr) + 1;
    }

    private void correction(MyAvl<Integer, String> N){
        N.size=heightCorrect(N);
        if(height(N)==2)
        {
            if(height(N.right)<0)
            {
                rotateRight(N.right);
            }
            rotateLeft(N);
        }
        if(height(N)==-2)
        {
            if(height(N.left)>0)
            {
                rotateLeft(N.left);
            }
            rotateRight(N);
        }
    }


    private MyAvl<Integer, String> goUp(MyAvl<Integer, String> head)
    {
        while(head.parent!=nullleaf)
            head=head.parent;
        return head;
    }

    private void stringMaker(MyAvl<Integer, String> tree, StringBuilder str)
    {
        if((tree!=nullleaf) && (tree!=null)) {
            stringMaker(tree.left, str);
            if(tree.del==false)
            {
                str.append(tree.key).append("=").append(tree.elem).append(", ");
            }
            stringMaker(tree.right, str);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        stringMaker(tree,str);
        if(str.length()>=3)
            str.delete(str.length()-2, str.length());
        str.append("}");
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
    public boolean containsKey(Object key) {
        MyAvl<Integer, String> buf=tree;
        if(buf==null)
        {
            return false;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
            {
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0) {
                buf=buf.right;
            }
            else{
                if(buf.del==false) {
                    return true;
                }
                else{return false;}
            }
        }
        return false;
    }

    private boolean contValue(MyAvl<Integer, String> buf, Object value)
    {
        if((buf!=nullleaf) && (buf!=null)) {
            if((value==null || buf.elem==null) ? value==buf.elem : (buf.elem).equals(value))
                return true;
            return contValue(buf.left, value) || contValue(buf.right, value);
        }
        else{
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return contValue(tree, value);
    }

    @Override
    public String get(Object key) {
        MyAvl<Integer, String> buf=tree;
        if(buf==null)
        {
            return null;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
            {
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0) {
                buf=buf.right;
            }
            else{
                if(buf.del==false) {
                    return buf.elem;
                }
                else{return null;}
            }
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        if(tree==null)
        {
            nullleaf.right=nullleaf;
            nullleaf.left=nullleaf;
            nullleaf.parent=nullleaf;
            tree= new MyAvl<Integer, String>(key, value, nullleaf, nullleaf, nullleaf);
            size++;
            return null;
        }
        else{
            MyAvl<Integer, String> buf = tree;
            MyAvl<Integer, String> lastbuf=tree;
            boolean temp=false;
            while(true)
            {
                if(buf==nullleaf)
                {
                    buf=new MyAvl<Integer, String>(key, value, lastbuf, nullleaf, nullleaf);
                    size++;
                    if(temp){
                        lastbuf.left=buf;
                    }
                    else{
                        lastbuf.right=buf;
                    }
                    correction(buf);
                    tree=goUp(tree);
                    return null;
                }
                if(((Comparable<Integer>)(buf.key)).compareTo((key))>0)
                {
                    temp=true;
                    lastbuf=buf;
                    buf=buf.left;
                } else if (((Comparable<Integer>)(buf.key)).compareTo((key))<0) {
                    temp=false;
                    lastbuf=buf;
                    buf=buf.right;
                }
                else{
                    if(buf.del==true)
                    {
                        buf.del=false;
                        buf.elem=value;
                        size++;
                        return null;
                    }
                    String strBuf= buf.elem;
                    buf.elem=value;
                    return strBuf;
                }
            }
        }
    }

    @Override
    public String remove(Object key) {
        MyAvl<Integer, String> buf=tree;
        if(buf==null)
        {
            return null;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
            {
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0) {
                buf=buf.right;
            }
            else{
                if(buf.del==false) {
                    buf.del = true;
                    size--;
                    return buf.elem;
                }
                else{return null;}
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size=0;
        tree=null;
    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }
}
