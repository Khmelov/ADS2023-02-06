package by.it.group251001.levitskij.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E> implements Set<E> {

    private int size = 0;
    private MyNode<E> root;


    static private class MyNode<E>{
        E data;
        int hash;
        MyNode<E> left, right;
        MyNode(E e, int hash, MyNode left, MyNode right){
            this.data = e;
            this.hash = hash;
            this.right = right;
            this.left = left;
        }

    }

    private void addtostring(MyNode parent, StringBuilder str){
        if(parent.left!=null)
            addtostring(parent.left, str);
        str.append(parent.data);
        str.append(", ");
        if(parent.right!=null)
            addtostring(parent.right, str);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (root!=null) {
            addtostring(root, sb);
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("]");
        return sb.toString();
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
    public boolean add(E e) {
        if (root == null){
            root = new MyNode<>(e, e.hashCode(),null,null);
            size++;
            return true;
        }
        MyNode x = root;
        MyNode parent = null;
        int hash = e.hashCode();
        while(x!=null){
            if (x.hash==hash)
                return false;
            parent = x;
            if (x.hash>hash)
                x = x.left;
            else
                x = x.right;
        }
        if (parent.hash>hash)
            parent.left = new MyNode<>(e, e.hashCode(),null,null);
        else
            parent.right = new MyNode<>(e, e.hashCode(),null,null);
        size++;
        return true;
    }
    @Override
    public boolean contains(Object o) {
        MyNode x = root;
        int hash = o.hashCode();
        while(x!=null){
            if (x.hash==hash)
                return true;
            if (x.hash>hash)
                x = x.left;
            else
                x = x.right;
        }
        return false;
    }

    private MyNode findmin(MyNode node){
        if (node.left==null)
            return node;
        else
            return findmin(node.left);
    }
    private MyNode delete(MyNode node, int hash){
        if (node.hash ==hash){
            size--;
            if (node.left==null && node.right==null)
                return null;
            if (node.left==null)
                return node.right;
            if (node.right==null)
                return node.left;
            size++;
            MyNode minnode = findmin(node.right);
            node.data = minnode.data;
            node.hash = minnode.hash;
            node.right = delete(node.right, minnode.hash);
            return node;
        }
        if (node.hash>hash) {
            if(node.left==null)
                return node;
            node.left = delete(node.left, hash);
        }else{
            if(node.right==null)
                return node;
            node.right = delete(node.right, hash);
        }
        return node;
    }
    @Override
    public boolean remove(Object o) {
        MyNode x = root;
        int oldsize = size;
        if(x==null)
            return false;
        root = delete(root, o.hashCode());
        return oldsize!=size;
    }

    private void erase(MyNode node){
        if (node.left!=null)
            erase(node.left);
        if (node.right!=null)
            erase(node.right);
        node.left=null;
        node.right=null;
        node.hash=0;
        node.data=null;
    }
    @Override
    public void clear() {
        erase(root);
        root = null;
        size = 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object []cArray = c.toArray();
        for(int i = 0;i < cArray.length;i++){
            if(!contains(cArray[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0) {
            return false;
        }
        boolean result = false;
        for(int i = 0;i < cArray.length;i++){
            result |= add((E)cArray[i]);
        }
        return result;
    }

    public boolean retain(MyNode node, Collection<?> c){
        boolean result = false;
        if(node.left!=null)
            result |= retain(node.left, c);
        if(node.right!=null)
            result |= retain(node.right, c);
        if(!c.contains(node.data)) {
            result = remove(node.data);///можно лучше
        }
        return result;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        if(root==null)
            return false;
        return retain(root, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object []cArray = c.toArray();
        if(cArray.length == 0) {
            return false;
        }
        boolean result = false;
        for(int i = 0;i < cArray.length;i++){
            result |= remove(cArray[i]);
        }
        return result;
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
}
