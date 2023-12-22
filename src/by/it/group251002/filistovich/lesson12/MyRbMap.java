package by.it.group251002.filistovich.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private enum color{
        BLACK, RED;
    }
    private class Node{
        Integer key;
        String value;
        color color;
        Node left;
        Node right;
        Node parent;
        boolean deleted;
        Node(Integer k, String s, Node p){
            key = k;
            value = s;
            left = null;
            right = null;
            parent = p;
            color = MyRbMap.color.RED;
            deleted = false;
        }
    }

    private Node root;
    private int size;

    private Node rotateRight(Node node)
    {
        Node left = node.left;
        left.parent = node.parent;

        Node swapNode = node.left.right;
        left.right = node;
        left.right.parent = left;
        node.left = swapNode;
        node.left.parent = node;

        return left;

    }




    private void swapColor(Node node){
        //node.left.color =
    }

    private Node rotateLeft(Node node){

        Node right = node.right;
        right.parent = node.parent;

        Node swapNode = node.right.left;
        right.left = node;
        right.left.parent = right;
        node.right = swapNode;
        node.right.parent = node;

        return right;
    }

    private void correction(Node node){
        if(node.parent == null)
        {
            node.color = color.BLACK;
        }
        else if((node.parent.color == color.BLACK) )
        {
            return;
        } else if ((node.parent.parent.left.color == color.RED) && (node.parent.parent.right.color == color.RED)) {
            node.parent.parent.left.color = color.BLACK;
            node.parent.parent.right.color = color.BLACK;
            node.parent.parent.color = color.RED;
            correction(node.parent.parent);
            return;
        }
        else
        {
            if(node.parent.left == node)
            {
                if(node.parent.parent.left == node.parent)
                {
                    node=node.parent;
                    rotateRight(node);
                    node.color = color.BLACK;
                    node.right.color = color.RED;
                }
                else{
                    rotateRight(node);
                    rotateLeft(node);
                    node.color = color.BLACK;
                    node.right.color = color.RED;
                }
            }
            else{
                if(node.parent.parent.right == node.parent)
                {
                    node = node.parent;
                    rotateLeft(node);
                    node.color = color.BLACK;
                    node.left.color = color.RED;
                }
                else{
                    rotateLeft(node);
                    rotateRight(node);
                    node.color = color.BLACK;
                    node.right.color = color.RED;
                }
            }
        }
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
        MyRbMap bufMap= new MyRbMap();
        recHeadMap(bufMap, root, toKey);
        return bufMap;
    }

    private void recHeadMap(MyRbMap res, Node node, Integer key){
        if (node == null)
        {
            return;
        }
        if(node.key >= key)
        {
            recHeadMap(res, node.left, key);
            return;
        }
        recHeadMap(res, node.left, key);
        recHeadMap(res, node.right, key);
        if(node.deleted == false)
            res.put(node.key, node.value);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap bufMap= new MyRbMap();
        recTailMap(bufMap, root, fromKey);
        return bufMap;
    }

    private void recTailMap(MyRbMap res, Node node, Integer key){
        if (node == null)
        {
            return;
        }
        if(node.key < key)
        {
            recTailMap(res, node.right, key);
            return;
        }
        recTailMap(res, node.left, key);
        recTailMap(res, node.right, key);
        if(node.deleted == false)
            res.put(node.key, node.value);
    }

    @Override
    public Integer firstKey() {
        Node temp = root;
        if (temp == null){
            return 0;
        }
        while (temp.left != null){
            temp = temp.left;
        }
        return temp.key;
    }

    @Override
    public Integer lastKey() {
        Node temp = root;
        if (temp == null){
            return 0;
        }
        while (temp.right != null){
            temp = temp.right;
        }
        return temp.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        if (get((Integer) key) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return checkValues(root, value);
    }

    private boolean checkValues(Node node, Object value){
        if (node == null){
            return false;
        }
        if ((node.value.equals(value)) && (node.deleted == false)){
            return true;
        }
        if ((checkValues(node.left, value) == true) || (checkValues(node.right, value) == true)){
            return true;
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Node temp = root;
        while (temp != null){
            if ((temp.key.equals(key)) && (temp.deleted == false)){
                return temp.value;
            } else if ((Integer)key > temp.key){
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        String oldVal = get(key);
        root = add(root, key, value, null);
        if (root.color == color.RED){
            root.color = color.BLACK;
        }
        size += oldVal == null ? 1 : 0;
        return oldVal;
    }

    private String elemToString(Node node){
        if (node == null){
            return "";
        }
        if (node.deleted){
            return elemToString(node.left) + elemToString(node.right);
        }
        return elemToString(node.left) + node.key + "=" + node.value + ", " + elemToString(node.right);

    }

    @Override
    public String toString(){

        String elems = elemToString(root);
        int l = elems.length();
        String res = "";
        if (elems.length() != 0){
            res = elems.substring(0, l - 2);
        }
        return "{" + res + "}";
    }

    private Node add(Node node, Integer key, String value, Node parent){
        if (node == null){
            return new Node(key, value, parent);
        }
        if (key > node.key){
            node.right = add(node.right, key, value, parent);
        } else if (key < node.key){
            node.left = add(node.left, key, value, parent);
        } else {
            node.value = value;
            return node;
        }
        correction(node);
        // 1) - if right == red && left == black then rotateLeft
        // 2) - if left == red  && left.left == red then rotateRight
        // 3) - if right == red && left == red then swapColor

        return node;
    }

    @Override
    public String remove(Object key) {
        String RemovedValue = get((Integer) key);
        Node temp = root;
        if (RemovedValue != null){
            while (temp != null){
                if ((temp.key.equals(key)) && (temp.deleted == false)){
                    temp.deleted = true;
                    temp = null;
                } else if ((Integer)key > temp.key){
                    temp = temp.right;
                } else if ((Integer)key < temp.key){
                    temp = temp.left;
                }
            }
            size--;
        }
        return RemovedValue;
    }


    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
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
