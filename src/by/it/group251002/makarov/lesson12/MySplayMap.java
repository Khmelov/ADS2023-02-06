package by.it.group251002.makarov.lesson12;

import javax.imageio.ImageTranscoder;
import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {
    public class Node{
        Integer key;
        String value;
        Node left,right,parent;
        Node(Integer key,String value){
            this.key=key;
            this.value=value;
            this.left=this.parent=this.right=null;
        }
    }
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
    private int size = 0;
    private Node root = null;
    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {

        return getLowerKey(root,key).key;
    }
    public Node getLowerKey(Node node,Integer key){
        if(node==null){
            return null;
        }

        int cmp = key.compareTo(node.key);
        if(cmp>0){
//            node.right = getLowerKey(node.right,key);
            Node LowerInRight = getLowerKey(node.right,key);
            return (LowerInRight!=null )?LowerInRight:node;
        }
        else if(cmp<0){
            return getLowerKey(node.left,key);
        }else {
            if(node.left!=null){
                return getMax(node.left);
            }
            else {return null;}

        }
    }
    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
            if(root == null){
                return null;
            }
            Node node = getFloorKey(root,key);
            if(node != null){
                return node.key;
            }
        return null;
    }
    public Node getFloorKey(Node node, Integer key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp > 0){
            Node floorInLeft = getFloorKey(node.right, key);
            return floorInLeft != null ? floorInLeft : node;
        } else if(cmp == 0){
            return node;
        } else {
            return getFloorKey(node.left,key);
        }
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {

        return getCeiling(root,key).key;
    }
    public Node getCeiling(Node node,Integer key){
        if(node==null)return null;
        int cmp = key.compareTo(node.key);
        if(cmp>0){
            return getCeiling(node.right,key);
        }else if(cmp<=0){
            Node CeilingInLeft = getCeiling(node.left,key);
            return CeilingInLeft != null ? CeilingInLeft : node;
        }else return null;
    }
    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        return getHigherKey(root,key).key;
    }
    public Node getHigherKey(Node node, Integer key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp<0){
            Node higherInLeft = getHigherKey(node.left,key);
            return (higherInLeft!=null)?higherInLeft:node;
        }else
        return getHigherKey(node.right,key);
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
    public void headMap(Node node, Integer key, MySplayMap hm){
        if(node==null){
            return;
        }
        if(node.key.compareTo(key)<0){
            hm.put(node.key,node.value);
            headMap(node.right,key,hm);
        }
        headMap(node.left,key,hm);
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
        MySplayMap hm = new MySplayMap();
        headMap(root,toKey,hm);
        return hm;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap hm = new MySplayMap();
        tailmap(root,fromKey,hm);
        return hm;
    }
    public void tailmap(Node node, Integer key, MySplayMap hm){
        if(node==null)return;
        if(node.key.compareTo(key)>=0){
            hm.put(node.key,node.value);
            tailmap(node.left,key,hm);
        }
       tailmap(node.right,key,hm);
    }
    @Override
    public Integer firstKey() {

        return getMin(root).key;
    }

    @Override
    public Integer lastKey() {

        return getMax(root).key;
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
        return get(key)!=null;
    }

    @Override
    public boolean containsValue(Object value) {
        if(value ==null || !(value instanceof String)){
            return false;
        }
        return containsValue(root,(String)value);
    }
    public boolean containsValue(Node node, String value){
        if(node == null) return false;
        if(node.value.equals(value)){
            return true;
        }
        return containsValue(node.left,value)||containsValue(node.right,value);
    }
    @Override
    public String get(Object key) {
        Node node = searchKey((Integer) key, root);
        if (node == null)
            return null;

        root = splay(root, node.key);
        return node.value;
    }

    private Node searchKey(Integer key, Node node) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0)
            return searchKey(key, node.left);
        return searchKey(key, node.right);
    }

    public Node getMax(Node node){
        if(node==null) return null;
        while(node.right!=null){
            node=node.right;
        }
        return node;
    }
    public Node getMin(Node node){
        if(node==null) return null;
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
    @Override
    public String put(Integer key, String value) {
        if(key==null){
            throw new NullPointerException();
        }
        if(root==null){
            root=new Node(key,value);
            size++;
            return null;
        }
        String prev = get(key);
        root = insertNode(root,key,value);
        if(prev==null)size++;
        return prev;
    }
    public Node insertNode(Node node, Integer key, String value){
        if(node == null){
            return new Node(key,value);
        }
        int cmp = key.compareTo(node.key);
        if(cmp<0){
            node.left = insertNode(node.left,key,value);
        } else if(cmp>0){
            node.right = insertNode(node.right,key,value);
        } else {
            node.value = value;
            return node;
        }
        return splay(node,key);
    }

    public Node splay(Node node, Integer key){
        if(node == null)return null;
        int cmp = key.compareTo(node.key);
        if(cmp<0){
            //ключа нет в дереве
            if(node.left==null){
                return node;
            }
            //ZIG-ZIG - Двойной поворот, когда узлы формируют цепочку.
            if(key.compareTo(node.left.key)<0){
                node.left.left = splay(node.left.left,key);
                node = rotateRight(node);

            //ZIG-ZAG - Первый поворот в одном направлении, затем в другом
            }else if (key.compareTo(node.left.key)>0){
                node.left.right = splay(node.left.right,key);
                if(node.left.right!=null){
                    node.left = rotateLeft(node.left);
                }
            }

            //последний ZIG
            return (node.left==null)?node:rotateRight(node);

        } else if(cmp>0)
        {
            //ключ в правом поддереве

            //если ключа нет в дереве
            if (node.right == null) {
                return node;
            }

            //ZAG-ZIG
            if(key.compareTo(node.right.key)<0){
                node.right.left=splay(node.right.left,key);
                if(node.right.left!=null){
                    node.right = rotateRight(node.right);
                }
            }else if(key.compareTo(node.right.key)>0){
                //ZAG-ZAG
                node.right.right = splay(node.right.right,key);
                node = rotateLeft(node);
            }
            //последний ZAG
            return (node.right==null)?node:rotateLeft(node);

        } else return node;
    }
    public Node rotateRight(Node x){
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }
    public Node rotateLeft(Node x){
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }
    @Override
    public String remove(Object key) {
        if (root == null || key == null || !(key instanceof Integer)) {
            return null;
        }

        root = splay(root, (Integer) key);
        int cmp = ((Integer) key).compareTo(root.key);
        if (cmp != 0) {
            return null;
        }

        String removedValue = root.value;
        if (root.left == null) {
            root = root.right;
            if (root != null) {
                root.parent = null;
            }
        } else {
            Node newRoot = root.right;
            if (newRoot != null) {
                newRoot = splay(newRoot, (Integer) key);
                newRoot.left = root.left;
                newRoot.left.parent = newRoot;
                root = newRoot;
            } else {
                root = root.left;
                root.parent = null;
            }
        }
        size--;
        return removedValue;
    }

    public Node search(Node node,Integer key){
        return null;
    }
    public Node remove(Node node, Integer key) {
        if (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                node.right = remove(node.right, key);
            } else if (cmp < 0) {
                node.left = remove(node.left, key);
            } else {
                // Ключи равны
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
// Есть оба поддерева
                Node minRight = getMin(node.right);
                node.key = minRight.key;
                node.value = minRight.value;
                node.right = remove(node.right, minRight.key);

            }
                return node; // Вернуть узел после операции удаления
        } else {
            return null; // Узел не найден
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
