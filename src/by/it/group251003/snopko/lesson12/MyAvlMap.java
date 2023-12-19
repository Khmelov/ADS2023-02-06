package by.it.group251003.snopko.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer,String> {

    private class Node{
        Integer key;
        String value;
        Node left;
        Node right;
        Integer height;
        Node(Integer key, String str){
            this.height = 0;
            this.key = key;
            this.value = str;
            this.left = null;
            this.right = null;
        }
    }

    Integer getHeight (Node node){
        if (node == null) {return -1;}
        return node.height;
    }
    Integer getBalanceFactor (Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    Integer size = 0;
    Node tree = null;

    /*
                    1                             2
             2          T1                   T2          1
         T2     T3             -->        X           T3    T1
      X
     */
    Node RightTurn (Node node){
        Node child = node.left;
        Node tempSwapNode = node.left.right;

        child.right = node;
        node.left = tempSwapNode;

        node.height = Math.max(node.left.height, node.right.height) + 1;
        child.height = Math.max(child.left.height, child.right.height) + 1;

        return child;
    }


    /*
                 1                                       2
          T1           2               -->        1          T2
                   T3       T2                 T1   T3            X
                               X
    */
    Node LeftTurn (Node node){
        Node child = node.right;
        Node tempSwapNode = node.right.left;

        child.left = node;
        node.right = tempSwapNode;

        node.height = Math.max(node.left.height, node.right.height) + 1;
        child.height = Math.max(child.left.height, child.right.height) + 1;

        return child;
    }

    Node RightLeft (Node node){
        node.right = RightTurn(node.right);
        return LeftTurn(node);
    }

    Node LeftRight (Node node){
        node.left = LeftTurn(node.left);
        return RightTurn(node);
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
        return false;
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
        String oldValue = get(key);
        tree = addNode(tree,key,value);
        size += oldValue == null ? 1 : 0;
        return oldValue;
    }

    private Node addNode (Node node, Integer key, String value){
        if (node == null){
            return new Node(key,value);
        }
        if (key < node.key){
            node.left = addNode(node.left, key, value);
        } else if (key > node.key){
            node.right = addNode(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }

        Integer BalanceLvl = getBalanceFactor(node);
        if (BalanceLvl == 2){
            if (key < node.left.key){
                return LeftTurn(node);
            } else {
                return LeftRight(node);
            }
        }
        if (BalanceLvl == -2){
            if (key < node.right.key){
                return RightLeft(node);
            } else {
                return RightTurn(node);
            }
        }

        return node;
    }


    @Override
    public String remove(Object key) {
        String RemovedValue = get((Integer) key);
        if (RemovedValue != null){
            tree = removeNode(tree,(Integer) key);
            size--;
        }
        return RemovedValue;
    }

    private Node removeNode(Node node, Integer key){
        if (node == null){
            return null;
        }
        if (key < node.key){
            node.left = removeNode(node.left, key);
        } else if (key > node.key){
            node.right = removeNode(node.right, key);
        } else {
            if (node.right == null && node.left == null){
                return null;
            }
            if (node.right == null || node.left == null){
                node = node.left == null ? node.right : node.left;
            } else {
                Node newNode = maxNodeSubtree(node.left);
                node.value = newNode.value;
                node.key = newNode.key;
            }

        }

        Integer Factor = getBalanceFactor(node);
        if (Factor == 2){
            if (getBalanceFactor(node.left) >= 0){
                return RightTurn(node);
            }
            if (getBalanceFactor(node.left) < 0){
                return LeftRight(node);
            }
        }
        if (Factor == -2){
            if (getBalanceFactor(node.right) >= 0){
                return LeftTurn(node);
            }
            if (getBalanceFactor(node.right) < 0){
                return RightLeft(node);
            }
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        return node;
    }

    private Node maxNodeSubtree(Node node){
        if(node.right != null){
            return maxNodeSubtree(node.right);
        }
        return node;
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
