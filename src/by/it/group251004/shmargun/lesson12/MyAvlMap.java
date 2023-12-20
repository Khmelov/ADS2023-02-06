package by.it.group251004.shmargun.lesson12;

import java.util.*;

public class MyAvlMap implements Map<Integer, String>{
    private class Str{
        String res;
    }
    private class Node{
        Integer key;
        String value;
        int height, diff;
        Node left, right;
        Node(Integer k){
            key = k; value = null;
            height = 1; diff = 0;
            left = null; right = null;
        }
    }
    private int size = 0;
    private Node root = null;

    private void travle(Node p, Str res){
        if(p == null) return;
        if(p.left != null)
            travle(p.left, res);
        res.res += p.key.toString() + "=" + p.value + ", ";
        if(p.right != null)
            travle(p.right, res);
    }

    @Override
    public String toString(){
        Str res = new Str();
        res.res = "{";
        travle(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
    }

    private int height(Node x){
        return x == null ? 0 : x.height;
    }

    private int diff(Node x){
        return  x == null ? 0 : x.diff;
    }

    private void update(Node x){
        int l = height(x.left), r = height(x.right);
        x.height = (l>r ? l : r) + 1;
        x.diff = r-l;
    }

    private Node rotateright(Node p){
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        update(p);
        update(q);
        return q;
    }

    private Node rotateleft(Node q){
        Node p = q.right;
        q.right = p.left;
        p.left = q;
        update(q);
        update(p);
        return p;
    }

    private Node balance(Node p){
        update(p);
        if(diff(p) == 2)
        {
            if( diff(p.right) < 0 )
                p.right = rotateright(p.right);
            return rotateleft(p);
        }
        if( diff(p) == -2 )
        {
            if( diff(p.left) > 0  )
                p.left = rotateleft(p.left);
            return rotateright(p);
        }
        return p;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        Node temp = root;
        while (temp != null && !temp.key.equals(key)){
            if((Integer)key < temp.key)
                temp = temp.left;
            else
                temp = temp.right;
        }
        return temp != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node temp = root;
        while (temp != null && !temp.key.equals(key)){
            if((Integer)key < temp.key)
                temp = temp.left;
            else
                temp = temp.right;
        }
        return temp != null ? temp.value : null;
    }

    private Node getNode(Object key) {
        Node temp = root;
        while (temp != null && !temp.key.equals(key)){
            if((Integer)key < temp.key)
                temp = temp.left;
            else
                temp = temp.right;
        }
        return temp;
    }

    private Node insert(Node p, int k){
        if(p == null) return new Node(k);
        if(k < p.key)
            p.left = insert(p.left, k);
        else
            p.right = insert(p.right,k);
        return balance(p);
    }

    @Override
    public String put(Integer key, String value) {
        Boolean exist = containsKey(key);
        String prev = exist ? get(key) :  null;
        if(exist)
            getNode(key).value = value;
        else {
            root = insert(root, key);
            getNode(key).value = value;
            size++;
        }
        return prev;
    }

    private Node findmin(Node p){
        return p.left != null ? findmin(p.left) : p;
    }

    private Node removemin(Node p)
    {
        if(p.left == null)
            return p.right;
        p.left = removemin(p.left);
        return balance(p);
    }

    Node removeNode(Node p, int k){
        if(p == null) return null;
        if(k < p.key)
            p.left = removeNode(p.left, k);
        else if(k > p.key)
            p.right = removeNode(p.right, k);
        else
        {
            Node q = p.left;
            Node r = p.right;
            p = null;
            if(r == null) return q;
            Node min = findmin(r);
            min.right = removemin(r);
            min.left = q;
            return balance(min);
        }
        return balance(p);
    }

    @Override
    public String remove(Object key) {
        Boolean exist = containsKey(key);
        String res = exist ? getNode(key).value : null;
        if(exist){
            root = removeNode(root, (Integer)key);
            --size;
        }

        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        while(!isEmpty())
            remove(root.key);
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
