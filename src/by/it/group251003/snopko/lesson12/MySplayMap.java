package by.it.group251003.snopko.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer,String> {
    Node tree;
    int size;
    MySplayMap(){
        tree = null;
        size = 0;
    }
    private class Node{
        Integer key;
        String value;
        Node right;
        Node left;
        Node parent;

        Node (Integer key, String value){
            this.right = null;
            this.left = null;
            this.parent = null;
            this.key = key;
            this.value = value;
        }
    }

    void rotateLeft(Node node){
        Node child = node.right;
        node.right = child.left;
        if (child.left != null) {
            child.left.parent = node;
        }
        if (node.parent == null){
            tree = child;
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

    void rotateRight(Node node){
        Node child = node.left;
        node.left = child.right;
        if (child.right != null) {
            child.right.parent = node;
        }
        if (node.parent == null){
            tree = child;
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

    void splay(Node node){
        while (node.parent != null){
            Node parent = node.parent;
            Node grand = node.parent.parent;
            if (node == parent.left)
                if (grand == null)
                    rotateRight(parent);
                else if (parent == grand.left){
                    rotateRight(grand);
                    rotateRight(parent);
                } else {
                    rotateRight(parent);
                    rotateLeft(grand);
                }
            else
                if (grand == null)
                    rotateLeft(parent);
                else if (parent == grand.right){
                    rotateLeft(grand);
                    rotateLeft(parent);
                } else {
                    rotateLeft(parent);
                    rotateRight(grand);
                }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(toStringMap(tree));
        if (sb.length() != 1){
            sb.deleteCharAt(sb.length() - 1);sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    private String toStringMap(Node node){
        if (node == null){
            return "";
        }
        return toStringMap(node.left) + node.key + "=" + node.value + ", " + toStringMap(node.right);

    }

    private Node find(Integer key){
        Node Iter = tree;
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

    private Node maxNodeSubtree(Node node){
        if(node.right != null){
            return maxNodeSubtree(node.right);
        }
        return node;
    }
    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        Integer ans = null;
        Node Iter = tree;
        while (Iter!=null){
            if (key > Iter.key){
                ans = Iter.key;
                Iter = Iter.right;
            } else {
                Iter = Iter.left;
            }
        }
        return ans;
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
        Integer ans = null;
        Node Iter = tree;
        while (Iter!=null){
            if (key >= Iter.key){
                Iter = Iter.right;
            } else {
                ans = Iter.key;
                Iter = Iter.left;
            }
        }
        return ans;
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
        MySplayMap map = new MySplayMap();
        collectHeadMap(toKey, map, tree);
        return map;
    }

    private void collectHeadMap(Integer toKey, MySplayMap map, Node node){
        if (node == null){return;}
        if (node.key.compareTo(toKey) < 0){
           map.put(node.key, node.value);
           collectHeadMap(toKey,map,node.right);
        }
        collectHeadMap(toKey,map,node.left);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap map = new MySplayMap();
        collectTailMap(fromKey, map, tree);
        return map;
    }

    private void collectTailMap(Integer toKey, MySplayMap map,Node node){
        if (node == null){return;}
        if (node.key.compareTo(toKey) >= 0){
            map.put(node.key, node.value);
            collectTailMap(toKey,map,node.left);
        }
        collectTailMap(toKey,map,node.right);
    }


    @Override
    public Integer firstKey() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        Node Iter = tree;
        while (Iter.left != null){
            Iter = Iter.left;
        }
        return Iter.key;
    }

    @Override
    public Integer lastKey() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        Node Iter = tree;
        while (Iter.right != null){
            Iter = Iter.right;
        }
        return Iter.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Node Iter = tree;
        while(Iter != null) {
            if ((Integer)key < Iter.key) {
                Iter = Iter.left;
            } else if ((Integer)key > Iter.key){
                Iter = Iter.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        return checkValue(tree, value);
    }


    private boolean checkValue(Node node, Object value){
        if (node == null){
            return false;
        }
        if (node.value.equals(value)){
            return true;
        }
        return checkValue(node.left, value) || checkValue(node.right,value);
    }


    @Override
    public String get(Object key) {
        Node answer = find((Integer)key);
        if(answer == null) { return null;}
        return answer.value;
    }


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
        if (tree == null){
            tree = new Node(key,value);
            return;
        }
        Node Iter = tree;
        Node parent = null;
        while (Iter != null) {
            parent = Iter;
            if (key < Iter.key){
                Iter = Iter.left;
            } else {
                Iter = Iter.right;
            }
        }

        Node ToInsert = new Node (key, value);
        ToInsert.parent = parent;
        if (key < parent.key){
            parent.left = ToInsert;
        } else {
            parent.right = ToInsert;
        }
        splay(ToInsert);
    }

    @Override
    public String remove(Object key) {
        Node result = find ((Integer) key);
        if (result != null){
            size--;
            delete(result);
            return result.value;
        }
        return null;
    }
    private void delete (Node node){
        if (node.left == null) {
            tree = node.right;
            if (tree != null)
                tree.parent = null;
        } else {
            Node right = node.right;
            tree = node.left;
            tree.parent = null;
            Node maxLeft = maxNodeSubtree(tree);
            splay(maxLeft);
            tree.right = right;
            if (right != null)
                right.parent = tree;
        }

    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        tree = Free(tree);
        size = 0;
    }

    private Node Free(Node node){
        if (node == null){return null;}
        node.left = Free(node.left);
        node.right = Free(node.right);
        return null;
    };


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
