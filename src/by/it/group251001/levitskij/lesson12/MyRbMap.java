package by.it.group251001.levitskij.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private int size = 0;
    private MyNode1 root1;
    
    private MyNode root;

    static private class MyNode{
        Integer key;
        String value;
        Boolean color;///красный - false , черный - true
        MyNode left, right, parent;
        MyNode(Integer key,String value,MyNode parent) {
            this.key = key;
            this.value = value;
            this.color = false;
            this.right = null;
            this.left = null;
            this.parent = parent;
        }
        MyNode grandfather(){
            if (this!=null && this.parent!=null)
                return this.parent.parent;
            else
                return null;
        }
        MyNode uncle(){
            MyNode g = this.grandfather();
            if (g!=null)
                return null;
            if (this.parent == g.left)
                return g.right;
            else
                return g.left;
        }
    }

    private void addtostring(MyNode parent, StringBuilder str){
        if(parent.left!=null)
            addtostring(parent.left, str);
        str.append(parent.key);
        str.append("=");
        str.append(parent.value);
        str.append(", ");
        if(parent.right!=null)
            addtostring(parent.right, str);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (root!=null) {
            addtostring(root, sb);
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("}");
        return sb.toString();
    }


    private MyNode insert(MyNode node,MyNode parent, Integer key, String value, StringBuilder oldvalue){
        if (node==null) {
            size++;
            return new MyNode(key, value,parent);
        }
        if (node.key>key) {
            MyNode temp = node.left;
            node.left = insert(node.left, node, key, value, oldvalue);
            if (node.left!=temp)
                insertcase1(node.left);
        }
        else if (node.key<key){
            MyNode temp = node.right;
            node.right = insert(node.right,node, key, value, oldvalue);
            if (node.right!=temp)
                insertcase1(node.right);
        }
        else {
            oldvalue.append(node.value);
            node.value = value;
            return node;
        }
        return node;
    }
    @Override
    public String put(Integer key, String value) {
        StringBuilder oldvalue = new StringBuilder();
        insert(root,null, key, value, oldvalue);
        return oldvalue.isEmpty()?null:oldvalue.toString();
    }
    private void rotateright(MyNode node){
        MyNode nextnode = node.left;
        nextnode.parent = node.parent;
        if (node.parent!=null)
            if (node.parent.left==node)
                node.parent.left = nextnode;
            else
                node.parent.right = nextnode;
        node.parent = nextnode;
        node.left = nextnode.right;
        if (node.left!=null)
            node.left.parent = node;
        nextnode.right = node;
    }

    private void rotateleft(MyNode node){
        MyNode nextnode = node.right;
        nextnode.parent = node.parent;
        if (node.parent!=null)
            if (node.parent.left==node)
                node.parent.left = nextnode;
            else
                node.parent.right = nextnode;
        node.parent = nextnode;
        node.right = nextnode.left;
        if (node.right!=null)
            node.right.parent = node;
        nextnode.left = node;
    }
    
    private void insertcase1(MyNode node){
        if(node.parent==null)
            node.color = true;
        else 
            insertcase2(node);
    }
    private void insertcase2(MyNode node){
        if (!node.parent.color)
            insertcase3(node);
    }
    private void insertcase3(MyNode node){
        MyNode u = node.uncle();
        if (u!=null && !u.color) {
            node.parent.color = true;
            u.color = true;
            MyNode g = node.grandfather();
            g.color = false;
            insertcase1(g);
        } else {
            insertcase4(node);
        }
    }
    private void insertcase4(MyNode node){
        MyNode g = node.grandfather();
        if (node.parent.right==node && node.parent==g.left){
            rotateleft(node.parent);
            node = node.left;
        } else if(node.parent.right==node && node.parent==g.right) {
            rotateright(node.parent);
            node = node.right;
        } else 
            insertcase5(node);
    }
    private void insertcase5(MyNode node){
        MyNode g = node.grandfather();
        node.parent.color = true;
        if (node==node.parent.left && node.parent==g.left)
            rotateright(g);
        else 
            rotateleft(g);
    }

    static private class MyNode1{
        Integer key;
        String value;
        int height;
        MyNode1 left, right;
        MyNode1(Integer key,String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.right = null;
            this.left = null;
        }

    }
    private int height(MyNode1 node){
        return (node!=null) ? node.height : 0;
    }

    private int bfactor(MyNode1 node){
        return height(node.right)-height(node.left);
    }

    private void fixheight(MyNode1 node){
        int heightright = height(node.right);
        int heightleft = height(node.left);
        node.height = (heightright>heightleft ? heightright : heightleft)+1;
    }

    private MyNode1 rotateright(MyNode1 node){
        MyNode1 nextnode = node.left;
        node.left = nextnode.right;
        nextnode.right = node;
        fixheight(node);
        fixheight(nextnode);
        return nextnode;
    }

    private MyNode1 rotateleft(MyNode1 node){
        MyNode1 nextnode = node.right;
        node.right = nextnode.left;
        nextnode.left = node;
        fixheight(node);
        fixheight(nextnode);
        return nextnode;
    }
    private MyNode1 balance(MyNode1 node){
        fixheight(node);
        int h = bfactor(node);
        if (h == 2){
            if (bfactor(node.right) < 0)
                node.right = rotateright(node.right);
            return rotateleft(node);
        }
        if (h == -2){
            if (bfactor(node.left) > 0)
                node.left = rotateleft(node.left);
            return rotateright(node);
        }
        return node;
    }


    private MyNode1 findmin(MyNode1 node){
        if (node.left==null)
            return node;
        else
            return findmin(node.left);
    }

    private MyNode1 delete(MyNode1 node, Integer key, StringBuilder oldvalue){
        if (node.key.equals(key)){
            size--;
            if (oldvalue!=null)
                oldvalue.append(node.value);
            if (node.left==null && node.right==null)
                return null;
            if (node.left==null)
                return node.right;
            if (node.right==null)
                return node.left;
            size++;
            MyNode1 minnode = findmin(node.right);
            node.value = minnode.value;
            node.key = minnode.key;
            node.right = delete(node.right, minnode.key, null);
            return node;
        }
        if (node.key>key) {
            if(node.left==null)
                return node;
            node.left = delete(node.left, key, oldvalue);
        }else{
            if(node.right==null)
                return node;
            node.right = delete(node.right, key, oldvalue);
        }
        return balance(node);
    }
    @Override
    public String remove(Object key) {
        int oldsize = size;
        StringBuilder oldvalue = new StringBuilder();
        root1 = delete(root1, (Integer)key, oldvalue);
//        checkavl(root1);
        return oldsize==size?null:oldvalue.toString();
    }

    @Override
    public String get(Object key) {
        MyNode1 x = root1;
        while(x!=null){
            if (x.key.equals(key))
                return x.value;
            if (x.key>(Integer)key)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        MyNode1 x = root1;
        while(x!=null){
            if (x.key==key)
                return true;
            if (x.key>(Integer)key)
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


    private MyNode1 eraseNode(MyNode1 node){
        if (node != null){
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
        root1 = eraseNode(root1);
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    
    
    
    
    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        return null;
    }

    @Override
    public Integer firstKey() {
        return null;
    }

    @Override
    public Integer lastKey() {
        return null;
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
