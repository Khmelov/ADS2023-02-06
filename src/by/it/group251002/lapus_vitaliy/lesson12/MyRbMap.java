package by.it.group251002.lapus_vitaliy.lesson12;

import java.util.*;

class Mylist<Integer, String>{

    public Integer key;

    public String elem;
    public int color;
    public boolean del;
    Mylist<Integer, String> left, right, parent;

    public Mylist(Integer k,String e, int col, Mylist<Integer, String> par, Mylist<Integer, String> l, Mylist<Integer, String> r)
    {
        key=k;
        elem=e;
        color=col;
        left=l;
        right=r;
        parent=par;
        del=false;
    }
}

public class MyRbMap implements SortedMap<Integer, String> {

    Mylist<Integer, String> tree= null;
    int size=0;

    Mylist<Integer, String> nullleaf = new Mylist<Integer, String>(0 ,null, 0, null, null, null);


    private void rotateLeft(Mylist<Integer, String> N)
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

    private void rotateRight(Mylist<Integer, String> N)
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

    private void correction(Mylist<Integer, String> N){
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

    private Mylist<Integer, String> goUp(Mylist<Integer, String> head)
    {
        while(head.parent!=nullleaf)
            head=head.parent;
        return head;
    }

    private void stringMaker(Mylist<Integer, String> tree, StringBuilder str)
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
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void headM(MyRbMap bufMap, Mylist<Integer, String> buf, Integer toKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key>=toKey)
        {
            headM(bufMap, buf.left, toKey);
            return;
        }
        headM(bufMap, buf.left, toKey);
        headM(bufMap, buf.right, toKey);
        if(buf.del==false)
            bufMap.put(buf.key, buf.elem);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap bufMap= new MyRbMap();
        headM(bufMap, tree, toKey);
        return bufMap;
    }

    private void tailM(MyRbMap bufMap, Mylist<Integer, String> buf, Integer fromKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key<fromKey)
        {
            tailM(bufMap, buf.right, fromKey);
            return;
        }
        tailM(bufMap, buf.left, fromKey);
        tailM(bufMap, buf.right, fromKey);
        if(buf.del==false)
            bufMap.put(buf.key, buf.elem);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap bufMap= new MyRbMap();
        tailM(bufMap, tree, fromKey);
        return bufMap;
    }

    private Integer firstK(Mylist<Integer, String> buf){
        if(buf==nullleaf || buf==null)
        {
            return null;
        }
        Integer bufInt=firstK(buf.left);
        if(bufInt!=null)
        {
            return bufInt;
        }
        else{
            if(buf.del==false)
            {
                return buf.key;
            }
            else{
                return firstK(buf.right);
            }
        }
    }

    @Override
    public Integer firstKey() {
        Mylist<Integer, String> buf=tree;
        return firstK(buf);
    }

    private Integer lastK(Mylist<Integer, String> buf){
        if(buf==nullleaf || buf==null)
        {
            return null;
        }
        Integer bufInt=lastK(buf.right);
        if(bufInt!=null)
        {
            return bufInt;
        }
        else{
            if(buf.del==false)
            {
                return buf.key;
            }
            else{
                return lastK(buf.left);
            }
        }
    }

    @Override
    public Integer lastKey() {
        Mylist<Integer, String> buf=tree;
        return lastK(buf);
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
        Mylist<Integer, String> buf=tree;
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


    //десь траблы с сравнением строки и обджект
    private boolean contValue(Mylist<Integer, String> buf, Object value)
    {
        if((buf!=nullleaf) && (buf!=null)) {
            if((value==null || buf.elem==null) ? value==buf.elem : (buf.elem).equals(value)) {
                if (buf.del == false) {
                    return true;
                }
            }
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
        Mylist<Integer, String> buf=tree;
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
            tree= new Mylist<Integer, String>(key, value, 0, nullleaf, nullleaf, nullleaf);
            size++;
            return null;
        }
        else{
            Mylist<Integer, String> buf = tree;
            Mylist<Integer, String> lastbuf=tree;
            boolean temp=false;
            while(true)
            {
                if(buf==nullleaf)
                {
                    buf=new Mylist<Integer, String>(key, value, 1, lastbuf, nullleaf, nullleaf);
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
        Mylist<Integer, String> buf=tree;
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
