package by.it.group251003.snopko.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    enum color {BLACK, RED};

    class Node {
        Node left, right, parent;
        color color;
        Integer key;
        String value;
        boolean deleted;

        Node(color color, Node parent, Node left, Node right, Integer key, String value){
            this.color = color;
            this.parent = parent;
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.deleted = false;
        }
    }


    Node tree;
    int size;

    MyRbMap(){
        tree = null;
        size = 0;
    }
    void RotateLeft(Node node){
        Node child = node.right;

        node.right = child.left;
        if (child.left != null){
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
        child.left = node;
        child.parent = node.parent;
        node.parent = child;
    }

    void RotateRight (Node node){
        Node child = node.left;
        node.left = child.right;
        if (child.right != null){
            child.right.parent = node;
        }
        if (node.parent == null){
            tree = child;
        } else {
            if(node.parent.right == node){
                node.parent.right = child;
            } else {
                node.parent.left = child;
            }
        }
        child.right = node;
        child.parent = node.parent;
        node.parent = child;
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
        if (node.deleted){
            return toStringMap(node.left) + toStringMap(node.right);
        }
        return toStringMap(node.left) + node.key + "=" + node.value + ", " + toStringMap(node.right);

    }



    @Override
    public int size() {return size;}

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
        Node Iter = tree;
        while(Iter != null) {
            if ((Integer)key < Iter.key) {
                Iter = Iter.left;
            } else if ((Integer)key > Iter.key){
                Iter = Iter.right;
            } else {
                return Iter.value;
            }
        }
        return null;
    }


    @Override
    public String put(Integer key, String value) {
        Node Iter = tree;
        Node Parent = null;
        boolean left = true;
        while (Iter != null){
            Parent = Iter;
            if (Iter.key > key){
                Iter = Iter.left;
                left = true;
            } else if (Iter.key < key){
                Iter = Iter.right;
                left = false;
            } else {
                if (Iter.deleted){
                    Iter.deleted = false;
                    size++;
                    return null;
                } else {
                    String temp = Iter.value;
                    Iter.value = value;
                    return temp;
                }
            };
        }
        size++;
        Node toInsert = new  Node (color.RED, Parent, null, null, key, value);
        if (Parent != null){
            if (left){
                Parent.left = toInsert;
            } else {
                Parent.right = toInsert;
            }
        } else {
            tree = toInsert;
        }
        putCaseFirst(toInsert);
        return null;
    }
    Node findGrand(Node node){
        if (node != null && node.parent != null){
            return node.parent.parent;
        } else {
            return null;
        }
    }

    Node findUncle(Node node){
        Node grand = findGrand(node);
        if (grand == null){
            return null;
        }
        if (grand.left == node.parent){
            return grand.right;
        } else {
            return grand.left;
        }
    }

    //Корень
    void putCaseFirst(Node node){
        if(node.parent == null){
            node.color = color.BLACK;
        } else {
            putCaseSecond(node);
        }
    }
    //Предок черный
    void putCaseSecond(Node node){
        if (node.parent.color != color.BLACK) {
            putCaseThird(node);
        }
    }
    //Родитель и Дядя красные
    void putCaseThird(Node node){
        Node tempUncle = findUncle(node);
        if ((tempUncle != null) && (tempUncle.color == color.RED)){
            tempUncle.color = color.BLACK;
            node.parent.color = color.BLACK;
            Node tempGrand = findGrand(node);
            tempGrand.color = color.RED;
            putCaseFirst(tempGrand);
        } else {
            putCaseForth(node);
        }
    }
    //2 красных подряд, двойной поворот
    void putCaseForth(Node node){
        Node tempGrand = findGrand(node);
        if ((node.parent.left == node) && (tempGrand.right == node.parent)){
            RotateRight(node.parent);
            node = node.right;
        } else if ((node.parent.right == node) && (tempGrand.left == node.parent)){
            RotateLeft(node.parent);
            node = node.left;
        }
        putCaseFifth(node);
    }

    void putCaseFifth(Node node){
        Node tempGrand = findGrand(node);
        node.parent.color = color.BLACK;
        tempGrand.color = color.RED;
        if (node == node.parent.left){
            RotateRight(tempGrand);
        } else {
            RotateLeft(tempGrand);
        }
    }

    @Override
    public String remove(Object key) {
        Node Iter = tree;
        while (Iter != null){
            if (Iter.key > (Integer)key){
                Iter = Iter.left;
            } else if (Iter.key < (Integer)key){
                Iter = Iter.right;
            } else {
                if (!Iter.deleted){
                    Iter.deleted = true;
                    size--;
                    return Iter.value;
                } else {
                    return null;
                }
            };
        }
        return null;
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
        MyRbMap map = new MyRbMap();
        collectHeadMap(toKey, map, tree);
        return map;
    }

    private void collectHeadMap(Integer toKey, MyRbMap map, Node node){
        if (node == null){return;}
        if (node.key.compareTo(toKey) < 0){
            if ( !node.deleted) {map.put(node.key, node.value);}
            collectHeadMap(toKey,map,node.right);
        }
        collectHeadMap(toKey,map,node.left);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap map = new MyRbMap();
        collectTailMap(fromKey, map, tree);
        return map;
    }

    private void collectTailMap(Integer toKey, MyRbMap map, Node node){
        if (node == null){return;}
        if (node.key.compareTo(toKey) >= 0){
            if (!node.deleted) {map.put(node.key, node.value);}
            collectTailMap(toKey,map,node.left);
        }
        collectTailMap(toKey,map,node.right);
    }

    public Integer firstKey(){
        if (size == 0){
            throw new NoSuchElementException();
        }
        Node Iter = tree;
        while (Iter.left != null){
            Iter = Iter.left;
        }
        return Iter.key;
    }

    public Integer lastKey(){
        if (size == 0){
            throw new NoSuchElementException();
        }
        Node Iter = tree;
        while (Iter.right != null){
            Iter = Iter.right;
        }
        return Iter.key;
    }
}
