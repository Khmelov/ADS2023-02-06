package by.it.group251001.lashkin.lesson12;

import java.util.*;

public class MyAvlMap implements Map<Integer, String> {

    static private class MyNode{
        Integer key;
        String value;
        int height;
        MyNode left, right;
        MyNode(Integer key,String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.right = null;
            this.left = null;
        }

    }
    private int size = 0;
    private MyNode root;

    private int height(MyNode node){
        return (node != null) ? node.height : 0;
    }

    private int factorization(MyNode node){
        return height(node.right)-height(node.left);
    }

    private void fixHeight(MyNode node){
        int heightRight = height(node.right);
        int heightLeft = height(node.left);
        node.height = (Math.max(heightRight, heightLeft))+1;
    }

    private void addToSting(MyNode parent, StringBuilder str){
        if(parent.left!=null)
            addToSting(parent.left, str);
        str.append(parent.key);
        str.append("=");
        str.append(parent.value);
        str.append(", ");
        if(parent.right!=null)
            addToSting(parent.right, str);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (root!=null) {
            addToSting(root, sb);
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    private MyNode rotateRight(MyNode node){
        MyNode nextNode = node.left;
        node.left = nextNode.right;
        nextNode.right = node;
        fixHeight(node);
        fixHeight(nextNode);
        return nextNode;
    }

    private MyNode rotateLeft(MyNode node){
        MyNode nextNode = node.right;
        node.right = nextNode.left;
        nextNode.left = node;
        fixHeight(node);
        fixHeight(nextNode);
        return nextNode;
    }
    private MyNode balance(MyNode node){
        fixHeight(node);
        int h = factorization(node);
        if (h == 2){
            if (factorization(node.right) < 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        if (h == -2){
            if (factorization(node.left) > 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        return node;
    }

    private MyNode insert(MyNode node, Integer key, String value, StringBuilder oldValue){
        if (node==null) {
            size++;
            return new MyNode(key, value);
        }
        if (node.key>key)
            node.left = insert(node.left, key, value,oldValue);
        else if (node.key<key)
            node.right = insert(node.right, key, value, oldValue);
        else {
            oldValue.append(node.value);
            node.value = value;
            return node;
        }
        return balance(node);
    }
    @Override
    public String put(Integer key, String value) {
        StringBuilder oldValue = new StringBuilder();
        root = insert(root, key, value, oldValue);
        return oldValue.isEmpty() ? null : oldValue.toString();
    }


    private MyNode findMin(MyNode node){
        if (node.left==null)
            return node;
        else
            return findMin(node.left);
    }

    private MyNode delete(MyNode node, Integer key, StringBuilder oldValue){
        if (node.key.equals(key)){
            size--;
            if (oldValue!=null)
                oldValue.append(node.value);
            if (node.left==null && node.right==null)
                return null;
            if (node.left==null)
                return node.right;
            if (node.right==null)
                return node.left;
            size++;
            MyNode minNode = findMin(node.right);
            node.value = minNode.value;
            node.key = minNode.key;
            node.right = delete(node.right, minNode.key, null);
            return node;
        }
        if (node.key>key) {
            if(node.left==null)
                return node;
            node.left = delete(node.left, key, oldValue);
        }else{
            if(node.right==null)
                return node;
            node.right = delete(node.right, key, oldValue);
        }
        return balance(node);
    }
    @Override
    public String remove(Object key) {
        int oldSize = size;
        StringBuilder oldValue = new StringBuilder();
        root = delete(root, (Integer)key, oldValue);
        return oldSize == size?null:oldValue.toString();
    }

    @Override
    public String get(Object key) {
        MyNode x = root;
        while (x != null) {
            if (x.key.equals(key))
                return x.value;
            if (x.key > (Integer) key)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        MyNode x = root;
        while (x != null) {
            if (x.key == key)
                return true;
            if (x.key > (Integer) key)
                x = x.left;
            else
                x = x.right;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private MyNode eraseNode(MyNode node){
        if (node != null) {
            node.right = eraseNode(node.right);
            node.left = eraseNode(node.left);
            node.key = null;
            node.value = null;
        }
        return null;
    }
    @Override
    public void clear() {
        size = 0;
        root = eraseNode(root);
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public boolean containsValue(Object value) {
        return false;
    }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
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
