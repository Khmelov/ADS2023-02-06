package by.it.group251002.filistovich.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private class Node{
        Integer key;
        String value;
        Node left;
        Node right;
        Node parent;
        Node (Integer k, String v, Node p){
            key = k;
            value = v;
            left = null;
            right = null;
            parent = p;
        }
    }
    private int size = 0;
    private Node root;



    private void rotateLeft(Node node){
        Node child = node.right;
        node.right = child.left;
        if (child.left != null) {
            child.left.parent = node;
        }
        if (node.parent == null){
            root = child;
        } else {
            if (node.parent.left == node){
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        }
        child.parent = node.parent;
        node.parent = child;
        child.left = node;
    }

    private void rotateRight(Node node){
        Node child = node.left;
        node.left = child.right;
        if (child.right != null) {
            child.right.parent = node;
        }
        if (node.parent == null){
            root = child;
        } else {
            if (node.parent.left == node){
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        }
        child.parent = node.parent;
        node.parent = child;
        child.right = node;
    }

    private void zig(Node node)
    {
        if(node == null) {
            return;
        }
        else{
            if(node.parent.left == node)
            {
                rotateRight(node);
            }
            else{
                rotateLeft(node);
            }
        }
    }

    private void splay(Node node){
        while (node.parent != null){
            Node parent = node.parent;
            Node grandad = node.parent.parent;
            if (node == parent.left)
                if (grandad == null)
                    rotateRight(parent);
                else if (parent == grandad.left){
                    rotateRight(grandad);
                    rotateRight(parent);
                } else {
                    rotateRight(parent);
                    rotateLeft(grandad);
                }
            else
            if (grandad == null)
                rotateLeft(parent);
            else if (parent == grandad.right){
                rotateLeft(grandad);
                rotateLeft(parent);
            } else {
                rotateLeft(parent);
                rotateRight(grandad);
            }
        }
    }

    /*
    private void splay(Node node){
        if(node == null || node.parent == null) {
            return;
        }
        if(node.parent.parent == null)
        {
            zig(node);
        }
        else{
            if((node.parent.parent.left == node.parent && node.parent.left == node) || (node.parent.parent.right == node.parent && node.parent.right == node))
            {
                zig(node.parent);
                zig(node);
            }
            else{
                zig(node);
                zig(node);
            }
            splay(node);
        }
    }
*/
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
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        Integer res = null;
        Node temp = root;
        while (temp != null){
            if (key > temp.key){
                res = temp.key;
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return res;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        Node temp = find(key);
        if (temp == null){
            return lowerKey(key);
        } else {
            return temp.key;
        }
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        Node temp = find(key);
        if (temp == null){
            return higherKey(key);
        } else {
            return temp.key;
        }
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        Integer answer = null;
        Node Iterator = root;
        while (Iterator!=null){
            if (key >= Iterator.key){
                Iterator = Iterator.right;
            } else {
                answer = Iterator.key;
                Iterator = Iterator.left;
            }
        }
        return answer;
    }

    @Override
    public Entry<Integer, String> firstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> lastEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<Integer> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<Integer> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
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
        MySplayMap bufMap = new MySplayMap();
        recHeadMap(bufMap, root, toKey);
        return bufMap;
    }

    private void recHeadMap(MySplayMap res, Node node, Integer key){
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
        res.put(node.key, node.value);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap bufMap= new MySplayMap();
        recTailMap(bufMap, root, fromKey);
        return bufMap;
    }

    private void recTailMap(MySplayMap res, Node node, Integer key){
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
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return checkValues(root, value);
    }

    private boolean checkValues(Node node, Object value){
        if (node == null){
            return false;
        }
        if (node.value.equals(value)){
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
            if (temp.key.equals(key)){
                return temp.value;
            } else if ((Integer)key > temp.key){
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return null;
    }
    /*
    @Override
    public String put(Integer key, String value) {
        String oldVal = get(key);
        root = add(root, key, value, null);
        size += oldVal == null ? 1 : 0;
        return oldVal;
    }

    private Node add(Node node, Integer key, String value, Node parent){
        if (node == null){
            return new Node(key, value, parent);
        }
        if (key > node.key){
            node.right = add(node.right, key, value, node);
        } else if (key < node.key){
            node.left = add(node.left, key, value, node);
        } else {
            node.value = value;
            return node;
        }
        splay(node);

        return node;
    }
    */

    @Override
    public String put(Integer key, String value) {
        Node result = find (key);
        if (result == null){
            size++;
            insert(key, value);
            return null;
        }
        String old = result.value;
        result.value = value;
        return old;
    }

    private void insert(Integer key, String value){
        if (root == null){
            root = new Node(key,value, null);
            return;
        }
        Node Iter = root;
        Node parent = null;
        while (Iter != null) {
            parent = Iter;
            if (key < Iter.key){
                Iter = Iter.left;
            } else {
                Iter = Iter.right;
            }
        }

        Node ToInsert = new Node (key, value, null);
        ToInsert.parent = parent;
        if (key < parent.key){
            parent.left = ToInsert;
        } else {
            parent.right = ToInsert;
        }
        splay(ToInsert);
    }

    private Node find(Integer key){
        Node Iter = root;
        while (Iter != null) {
            if (key < Iter.key)
                Iter = Iter.left;
            else if (key > Iter.key)
                Iter = Iter.right;
            else {
                splay(Iter);
                return Iter;
            }
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        Node res = find((Integer) key);
        if (res != null){
            size--;
            rmNode(res);
            return res.value;
        }
        return null;
    }
    private void rmNode (Node node){
        if (node.left == null) {
            if (node.right != null){
                root = node.right;
            } else {
                if (node == root){
                    root = null;
                } else if (node.parent.left == node){
                    node.parent.left = null;
                } else if (node.parent.right == node){
                    node.parent.right = null;
                }
            }


            if (root != null)
                root.parent = null;
        } else {
            Node right = node.right;
            root = node.left;
            root.parent = null;
            Node temp = node.left;
            while (temp.right != null){
                temp = temp.right;
            }
            splay(temp);
            root.right = right;
            if (right != null)
                right.parent = root;
        }
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
