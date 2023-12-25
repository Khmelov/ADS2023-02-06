package by.it.group251001.politykina.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private static class Node {
        Integer key;
        String value;
        int height;
        Node left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }
    int size=0;
    Node root =null;

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public void clear() {
        root =null;
        size = 0;
    }
    @Override
    public String get(Object o) {
        Node res = search((int)o);
        return  res == null ? null : res.value;
    }
    @Override
    public boolean containsKey(Object o) {
        Node res = search((int)o);
        return  res == null ? false : true;
    }
    private Node search (int key){
        Node temp = root;
        while (temp!=null){
            if(temp.key == key){
                return temp;
            }else if (key<temp.key )
                temp=temp.left;
            else
                temp=temp.right;
        }
        return null;
    }


    private  void arb (Node head ,StringBuilder sb ){
        if (head == null) return;
        if (head.left!= null)
            arb(head.left, sb);
        sb.append(head.key + "=" +head.value + ", ");
        if (head.right!= null)
            arb(head.right, sb);
    }
    @Override
    public String toString() {
        if (root == null) return "{}";
        StringBuilder sb = new StringBuilder().append('{');
        arb(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }
    private Node rotateRight(Node root){
        Node temp = root.left;
        root.left=root.left.right;
        temp.right = root;
        root.height= Math.max(height(root.right), height(root.left))+1;
        temp.height= Math.max(height(temp.right), height(temp.left))+1;
        return temp;
    }
    private Node rotateLeft(Node root){
        Node rightCh = root.right;
        root.right = rightCh.left;
        rightCh.left = root;

        root.height = Math.max(height(root.right), height(root.left))+1;
        rightCh.height = Math.max(height(rightCh.right), height(rightCh.left))+1;
        return rightCh;
    }
    private Node balance(Node node) {
        if (node == null)
            return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balanceFactor = height(node.left)-height(node.right);
        if (balanceFactor > 1) {
            if (height(node.left.left)-height(node.left.right) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balanceFactor < -1)
        {
            if (height(node.right.left)-height(node.right.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }
    int height(Node node) {
        return node == null ? -1 : node.height;
    }
    private Node add (Node node, int key, String value){
        if (node== null){
            Node newNode = new Node(key, value);
            return newNode;
        }
        if (key<node.key){
            node.left= add(node.left, key, value);
        } else {
            node.right= add(node.right, key, value);
        }
        return balance(node);
    }
    @Override
    public String put(Integer key, String value) {
        Node res = search(key);
        if (res==null){
            root=add(root, key, value);
            size++;
            return null;
        }else {
            String str = res.value;
            res.value=value;
            return str;
        }
    }
    private Node findMin(Node n) {
        return n.left == null ? n : findMin(n.left);
    }

    private Node removeMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = removeMin(n.left);
        return balance(n);
    }
    private Node remove(Node n, Integer key) {
        if (n == null) return null;
        if ((int)key < (int)n.key) {
            n.left = remove(n.left, key);
        } else if ((int)key > (int)n.key) {
            n.right = remove(n.right, key);
        } else {
            Node q = n.left;
            Node r = n.right;
            if (r == null) return q;
            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }
        return balance(n);
    }
    @Override
    public String remove(Object key) {
        Node n = search( (Integer)key);
        if (n == null) {
            return null;
        }
        root = remove(root, (Integer) key);
        size--;
        return n.value;
    }






    /////////////////////////////////////////
    @Override
    public boolean containsValue(Object o) {
        return false;
    }



    @Override
    public void putAll(Map map) {

    }



    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }


}
