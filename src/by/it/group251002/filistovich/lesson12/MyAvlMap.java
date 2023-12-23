package by.it.group251002.filistovich.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {


    private class Node {
        Integer key;
        String value;
        Node left;
        Node right;
        int height;
        Node(Integer k, String v){
            key = k;
            value = v;
            left = null;
            right = null;
            height = 0;
        }
    }
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int solveDif(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    private int takeHeight(Node node){
        return (node == null) ? 0: node.height;
    }
    private Node rotateRight(Node node){
        Node left = node.left;
        Node swapNode = node.left.right;
        left.right = node;
        node.left = swapNode;
        node.height = Math.max(takeHeight(node.left), takeHeight(node.right)) + 1;
        left.height = Math.max(takeHeight(left.left), takeHeight(left.right)) + 1;
        return left;
    }


    private Node rotateLeft(Node node){
        Node right = node.right;

        Node NodeSwap = node.right.left;
        right.left = node;

        node.right = NodeSwap;
        node.height = Math.max(takeHeight(node.left), takeHeight(node.right)) + 1;
        right.height = Math.max(takeHeight(right.left), takeHeight(right.right)) + 1;

        return right;
    }

    private Node rightNLeft(Node node){
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    private Node leftRight(Node node){
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Node add(Node node, Integer key, String value){
        if (node == null){
            return new Node(key, value);
        }
        if (key > node.key){
            node.right = add(node.right, key, value);


        } else if (key < node.key){
            node.left = add(node.left, key, value);


        } else {
            node.value = value;
            return node;
        }

        int diff = solveDif(node);

        if (diff > 1){

            if (key < node.left.key){
                return rotateRight(node);

            } else {
                return leftRight(node);
            }
        }
        if (diff < -1){
            if (key < node.right.key){
                return rightNLeft(node);
            } else {
                return rotateLeft(node);
            }
        }
        return node;
    }

    private Node rmNode(Node node, Integer key){

        if (node == null){
            return null;
        }
        if (key < node.key){
            node.left = rmNode(node.left, key);
        } else if (key > node.key){
            node.right = rmNode(node.right, key);
        } else {
            if (node.right == null && node.left == null){
                return null;
            }
            if (node.left == null){
                node = node.right;
            } else if (node.right == null){
                node = node.left;
            } else {
                Node temp = node.left;
                while (temp.right != null){
                    temp = temp.right;
                }
                Integer k = temp.key;
                String s = temp.value;
                remove(temp.key);
                node.key = k;
                node.value = s;
            }
        }

        int diff = solveDif(node);

        if (diff > 1){

            if (key < node.left.key){
                return rotateRight(node);

            } else {
                return leftRight(node);
            }
        }
        if (diff < -1){
            if (key < node.right.key){
                return rotateLeft(node);
            } else {
                return rightNLeft(node);
            }
        }
        return node;
    }

    private Node root = null;

    private int size = 0;

    private String elemToString(Node node){
        if (node == null){
            return "";
        }
        return elemToString(node.left) + node.key + "=" + node.value + ", " + elemToString(node.right);

    }

    @Override
    public String toString(){

        String elems = elemToString(root);
        int l = elems.length();
        String result = "";
        if (elems.length() != 0){
            result = elems.substring(0, l - 2);
        }
        return "{" + result + "}";
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
        Node temp = root;
        while (temp != null){
            if (temp.key == key){
                return true;
            }
            if ((Integer)key > temp.key){
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node temp = root;
        while (temp != null){
            if (temp.key.equals(key)){
                return temp.value;
            }
            if ((Integer)key > temp.key){
                temp = temp.right;

            } else {
                temp = temp.left;

            }
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        String Old = get(key);
        root = add(root, key, value);
        size += (Old == null) ? 1 : 0;
        return Old;
    }
    @Override
    public String remove(Object key) {
        String removed = get((Integer) key);
        if (removed != null){
            root = rmNode(root,(Integer) key);
            size--;
        }
        return removed;
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
