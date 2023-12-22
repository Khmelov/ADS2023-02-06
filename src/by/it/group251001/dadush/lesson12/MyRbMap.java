package by.it.group251001.dadush.lesson12;


import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private enum Color {RED, BLACK}

    static private class Node {
        Integer key;
        String value;
        //Boolean color;///красный - false , черный - true
        Color color;
        Node left, right, parent;
        Node(Integer key, String value, Node parent) {
            this.key = key;
            this.value = value;
            this.color = Color.RED;
            this.right = null;
            this.left = null;
            this.parent = parent;
        }
        Node grandfather(){
            if (this != null && this.parent != null)
                return this.parent.parent;
            else
                return null;
        }
        Node uncle(){
            Node g = this.grandfather();
            if (g == null)
                return null;
            if (this.parent == g.left)
                return g.right;
            else
                return g.left;
        }
        Node sibling(){
            if (this == this.parent.right)
                return this.parent.left;
            else
                return this.parent.right;
        }
    }

    private int size = 0;
    private Node root;

    private void addToString(Node parent, StringBuilder str){
        if(parent.left != null)
            addToString(parent.left, str);
        str.append(parent.key);
        str.append("=");
        str.append(parent.value);
        str.append(", ");
        if(parent.right!=null)
            addToString(parent.right, str);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (root != null) {
            addToString(root, sb);
            sb.delete(sb.length() - 2,sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String put(Integer key, String value) {
        if (root == null){
            root = new Node(key, value,null);
            insertcase1(root);
            size++;
            return null;
        }
        Node x = root;
        Node parent = null;
        while(x != null) {
            if (x.key.equals(key)){
                String oldvalue = x.value;
                x.value = value;
                return oldvalue;
            }
            parent = x;
            if (x.key.compareTo(key)>0)
                x = x.left;
            else
                x = x.right;
        }
        x = new Node(key, value, parent);
        if (parent.key.compareTo(key) > 0)
            parent.left = x;
        else
            parent.right = x;
        insertcase1(x);
        size++;
//        checkrules("put");
        return null;
    }

    private void rotateRight(Node node) {
        Node nextnode = node.left;
        nextnode.parent = node.parent;
        if (node.parent!=null) {
            if (node.parent.left == node)
                node.parent.left = nextnode;
            else
                node.parent.right = nextnode;
        } else
            root = nextnode;

        node.parent = nextnode;
        node.left = nextnode.right;
        if (nextnode.right!=null)
            nextnode.right.parent = node;
        nextnode.right = node;
    }

    private void rotateLeft(Node node) {
        Node nextnode = node.right;
        nextnode.parent = node.parent;
        if (node.parent != null){
            if (node.parent.left==node)
                node.parent.left = nextnode;
            else
                node.parent.right = nextnode;
        } else
            root = nextnode;
        node.parent = nextnode;
        node.right = nextnode.left;
        if (nextnode.left != null)
            nextnode.left.parent = node;
        nextnode.left = node;
    }

    private void insertcase1(Node node){
        if(node.parent==null)
            node.color = Color.BLACK;
        else
            insertcase2(node);
    }
    private void insertcase2(Node node){
        if (node.parent.color == Color.RED)
            insertcase3(node);
    }
    private void insertcase3(Node node){
        Node u = node.uncle();
        if (u != null && u.color == Color.RED) {
            node.parent.color = Color.BLACK;
            u.color = Color.BLACK;
            Node g = node.grandfather();
            g.color = Color.RED;
            insertcase1(g);
        } else {
            insertcase4(node);
        }
    }
    private void insertcase4(Node node) {
        Node g = node.grandfather();
        if (node.parent.right == node && node.parent == g.left){
            rotateLeft(node.parent);
            node = node.left;
        } else if (node.parent.left == node && node.parent == g.right) {
            rotateRight(node.parent);
            node = node.right;
        }
        insertcase5(node);
    }
    private void insertcase5(Node node){
        Node g = node.grandfather();
        node.parent.color = Color.BLACK;
        g.color = Color.RED;
        if (node == node.parent.left && node.parent == g.left)
            rotateRight(g);
        else
            rotateLeft(g);
    }

    @Override
    public String get(Object key) {
        Node x = root;
        while(x != null){
            if (x.key.equals(key))
                return x.value;
            if (x.key.compareTo((Integer) key) > 0)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }


    private void deletecase1(Node node){
        if (node.parent != null)
            deletecase2(node);
    }
    private void deletecase2(Node node){
        Node s = node.sibling();
        if (s.color == Color.RED){
            node.parent.color = Color.RED;
            s.color = Color.BLACK;
            if (node == node.parent.left)
                rotateLeft(node);
            else
                rotateRight(node);
        }
        deletecase3(node);
    }
    private void deletecase3(Node node) {
        Node s = node.sibling();
        if (node.parent.color == Color.BLACK && s.color == Color.BLACK
                && s.left.color == Color.BLACK && s.right.color == Color.BLACK){
            s.color = Color.RED;
            deletecase1(node.parent);
        } else
            deletecase4(node);

    }
    private void deletecase4(Node node){
        Node s = node.sibling();
        if (node.parent.color == Color.RED && s.color == Color.BLACK
                && s.left.color == Color.BLACK && s.right.color == Color.BLACK){
            s.color = Color.RED;
            node.parent.color = Color.BLACK;
        } else
            deletecase5(node);
    }
    private void deletecase5(Node node){
        Node s = node.sibling();
        if (s.color == Color.BLACK){
            if (node == node.parent.left && s.right.color == Color.BLACK && s.left.color == Color.RED){
                s.color = Color.RED;
                s.left.color = Color.BLACK;
                rotateRight(s);
            } else if (node == node.parent.right && s.right.color == Color.RED && s.left.color == Color.BLACK){
                s.color = Color.RED;
                s.right.color = Color.BLACK;
                rotateLeft(s);
            }
        }
        deletecase6(node);
    }

    private void deletecase6(Node node){
        Node s = node.sibling();
        s.color = node.parent.color;
        node.parent.color = Color.BLACK;
        if (node == node.parent.left){
            s.right.color = Color.BLACK;
            rotateLeft(node.parent);
        } else {
            s.left.color = Color.BLACK;
            rotateRight(node.parent);
        }
    }
    private Node findmin(Node node){
        if (node.left==null)
            return node;
        else
            return findmin(node.left);
    }
    private void replacechild(Node node, Node child) {
        child.parent = node.parent;
        if (node.parent!=null){
            if (node == node.parent.right)
                node.parent.right = child;
            else
                node.parent.left = child;
        } else
            root = child;
    }
    private void deleteonechild(Node node){
        Node child = node.right!=null ? node.right : node.left;
        replacechild(node,child);
        if (node.color == Color.BLACK) {
            if (child.color == Color.RED)
                child.color = Color.BLACK;
            else
                deletecase1(child);
        }
        node.key = null;
        node.value = null;
    }
    private void deletenode(Node node){
        if (node.right!=null && node.left!=null) {
            Node min = findmin(node.right);
            node.value = min.value;
            node.key = min.key;
            deletenode(min);
        } else if (node.right != null ^ node.left != null) {
            deleteonechild(node);
        } else {
            if (node.parent != null){
                if(node == node.parent.left)
                    node.parent.left = null;
                else
                    node.parent.right = null;
                if (node.color == Color.BLACK)
                    insertcase1(node.parent);
            } else
                root = null;
        }
    }
    @Override
    public String remove(Object key) {
        Node x = root;
        while (x != null) {
            if (x.key.equals(key)) {
                String result = x.value;
                size--;
                deletenode(x);
//                checkrules("remove");
                return result;
            }
            if (x.key.compareTo((Integer) key) > 0)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    private boolean containsNode(Object value, Node node){
        if (node == null)
            return false;
        return node.value.equals(value) || containsNode(value, node.right) || containsNode(value, node.left);
    }
    @Override
    public boolean containsValue(Object value) {
        return containsNode(value, root);
    }

    @Override
    public int size() {
        return size;
    }

    private Node eraseNode(Node node){
        if (node != null){
            node.right = eraseNode(node.right);
            node.left = eraseNode(node.left);
            node.key = null;
            node.value = null;
            node.parent = null;
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
        return size == 0;
    }

    private void addtoHeadMap(SortedMap<Integer, String> result, Node node, Integer toKey) {
        if (node!=null){
            addtoHeadMap(result, node.left, toKey);
            if (node.key.compareTo(toKey)<0) {
                result.put(node.key, node.value);
                addtoHeadMap(result, node.right, toKey);
            }
        }
    }
    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        if (toKey==null)
            throw new NullPointerException();
        if (root == null)
            return null;
        TreeMap result = new TreeMap();
        addtoHeadMap(result, root, toKey);
        return result;
    }
    private void addtoTailMap(SortedMap<Integer, String> result, Node node, Integer fromKey){
        if (node!=null){
            addtoTailMap(result, node.right, fromKey);
            if (node.key.compareTo(fromKey)>=0) {
                result.put(node.key, node.value);
                addtoTailMap(result, node.left, fromKey);
            }
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        if (fromKey==null)
            throw new NullPointerException();
        if (root == null)
            return null;
        MyRbMap result = new MyRbMap();
        addtoTailMap(result, root, fromKey);
        return result;
    }
    @Override
    public Integer firstKey() {
        if (root==null)
            throw new NoSuchElementException();
        Node x = root;
        while (x.left!=null)
            x = x.left;
        return x.key;
    }

    @Override
    public Integer lastKey() {
        if (root==null)
            throw new NoSuchElementException();
        Node x = root;
        while (x.right!=null)
            x = x.right;
        return x.key;

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
