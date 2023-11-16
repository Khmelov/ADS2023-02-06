package by.it.group251002.makarov.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer,String> {
    public class Node{
        int key;
        String value;
        Node right, left;
        boolean color;
        Node(int key,String value){
            this.key=key;
            this.value=value;
            this.color=RED;
        }
    }
    private int size = 0;
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    @Override
    public String toString(){
        if(isEmpty()){
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(!s.isEmpty()||curr!=null){
            if(curr!=null){
                s.push(curr);
                curr=curr.left;
            } else {
                curr = s.pop();
                sb.append(delimiter).append(curr.key).append("=").append(curr.value);
                delimiter = ", ";
                curr=curr.right;
            }
        }
        sb.append("}");
        return sb.toString();
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
        if (toKey != null) {
            MyRbMap headMap = new MyRbMap();
            travelSearchHead(root, toKey, headMap);
            return headMap;
        }
        return null;
    }

    public void travelSearchHead(Node node, Integer key, MyRbMap hm) {
        if (node == null) {
            return;
        }
        int cmp = key.compareTo(node.key);
        travelSearchHead(node.left,key, hm);
        if (cmp>0) {
            hm.put(node.key, node.value);
            travelSearchHead(node.right,key, hm);
        }
    }

    public void travelSearchTail(Node node, Integer key, MyRbMap hm) {
        if (node == null) {
            return;
        }
        int cmp = key.compareTo(node.key);
        travelSearchTail(node.right,key, hm);
        if (cmp<=0) {
            hm.put(node.key, node.value);
            travelSearchTail(node.left,key, hm);
        }
    }
    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap headMap = new MyRbMap();
        travelSearchTail(root,fromKey,headMap);
        return headMap;
    }

    @Override
    public Integer firstKey() {
        if(root==null)throw new NullPointerException();
        return travelDown(root).key;
    }
    public Node travelDown(Node node){
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
    @Override
    public Integer lastKey() {
        return travelUp(root).key;
    }
    public Node travelUp(Node node){
        if(node==null)return null;
        while(node.right!=null){
            node = node.right;
        }
        return node;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        return travel(root,(Integer)key)!=null;
    }

    @Override
    public boolean containsValue(Object value) {
        return travelValue(root,value);
    }
    public boolean travelValue(Node node,Object value){
        if(node==null) return false;
        if(value.equals(node.value)){
            return true;
        }
        return travelValue(node.left,value)||travelValue(node.right,value);
    }
    @Override
    public String get(Object key) {
        return travel(root,(Integer)key);
    }

        @Override
        public String put(Integer key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            String prev = get(root,key);
            if(prev==null){size++;}
            root = put(root, key, value);
            root.color = BLACK;
            return prev;
        }

        public Node put(Node node, Integer key, String value) {
            if (node == null) {
                return new Node(key, value);
            }
            if (key.compareTo(node.key) < 0) {
                node.left = put(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }

            // Check for rotations and color flips
            if (isRed(node.right) && !isRed(node.left)) {
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }

            return node;
        }


    public void flipColors(Node node){
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    public boolean isRed(Node node){
        return node!=null&&node.color==RED;
    }
//    public Node rotateLeft(Node node){
//        Node x = node.right;
//        node.right = x.left;
//        x.left = node;
//        x.color = node.color;
//        node.color = RED;
//        return x;
//    }
    public String get(Node root, Integer key){
        return travel(root, key);
    }
    public String travel(Node node, Integer key){
        if(node!=null){
            int cmp = key.compareTo(node.key);
            if  (cmp<0) {
                return travel(node.left, key);
            }
            else if  (cmp>0) {
                return travel(node.right, key);
            }
            else return node.value;
            } return null;
    }
    public Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    public Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    @Override
    public String remove(Object key) {
        if (!(key instanceof Integer)) {
            return null;
        }
        Node target = search(root, (Integer) key);
        if (target == null) {
            return null;
        }
        size--;
        String removedVAl=target.value;
        root = remove(root, (Integer) key);
        if (root != null) {
            root.color = BLACK;
        }
        return removedVAl;
    }

    public Node remove(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            // Удаление узла с одним или без детей
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;

                // Узел без детей
                if (temp == null) {
                    return null;
                } else {
                    // Узел с одним ребенком
                    return temp;
                }
            } else {
                // Удаление узла с двумя детьми
                Node successor = findSuccessor(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = remove(node.right, successor.key);
            }
        }
        return balance(node);
    }

    private Node findSuccessor(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
//    @Override
//    public String remove(Object key) {
//        if(!(key instanceof Integer)){
//            return null;
//        }
//        Node target = search(root,(Integer)key);
//        if(target==null){
//            return null;
//        }
//        size--;
//        root = remove(root,(Integer)key);
//        if(root!=null)root.color=BLACK;
//        return target.value;
//    }
//    public Node remove(Node node, Integer key){
//        if(node==null){
//            return null;
//        }
//        int cmp = key.compareTo(node.key);
//        if(cmp>0){
//            node.left = remove(node.left,key);
//        }else if (cmp<0){
//            node.right = remove(node.right,key);
//        } else
//        {
//            if(node.left == null){
//                return node.right;
//            }else if (node.right==null){
//                return node.left;
//            }
//            Node min = travelDown(node.right);
//            node.key = min.key;
//            node.value = min.value;
//            node.right = remove(node.right,min.key);
//        }
//        return balance(node);
//    }
    public Node balance(Node node){
        if(isRed(node.right)){
            node = rotateLeft(node);
        }

        if(isRed(node.left)&&isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        return node;
    }
    public Node search(Node node, Integer key){
        if(node!=null){
            int cmp = key.compareTo(node.key);
            if  (cmp<0) {
                return search(node.left, key);
            }
            else if  (cmp>0) {
                return search(node.right, key);
            }
            else return node;
        } return null;
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
