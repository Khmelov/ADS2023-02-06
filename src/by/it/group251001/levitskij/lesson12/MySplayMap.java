package by.it.group251001.levitskij.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private int size = 0;
    private MyNode root;

    static private class MyNode{
        Integer key;
        String value;
        MyNode parent;
        MyNode left, right;
        MyNode(Integer key, String value, MyNode parent) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.left = null;
            this.parent = parent;
        }
        MyNode grandfather(){
            if (this!=null && this.parent!=null)
                return this.parent.parent;
            else
                return null;
        }

    }

    private void addtostring(MyNode parent, StringBuilder str){
        if(parent.left!=null)
            addtostring(parent.left, str);
        str.append(parent.key);
        str.append("=");
        str.append(parent.value);
        str.append(", ");
        if(parent.right!=null)
            addtostring(parent.right, str);
    }

//    public void checktree(MyNode node, String methodname){
//        if (node != null) {
//            if (node.right != null) {
//                if (node.key > node.right.key)
//                    System.out.println("tree broke " + methodname);
//                checktree(node.right, methodname);
//            }
//            if (node.left != null) {
//                if (node.key < node.left.key)
//                    System.out.println("tree broke " + methodname);
//                checktree(node.left, methodname);
//            }
//        }
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (root!=null) {
            addtostring(root, sb);
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    private void rotateright(MyNode node){
        MyNode nextnode = node.left;
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

    private void rotateleft(MyNode node){
        MyNode nextnode = node.right;
        nextnode.parent = node.parent;
        if (node.parent!=null){
            if (node.parent.left==node)
                node.parent.left = nextnode;
            else
                node.parent.right = nextnode;
        } else
            root = nextnode;
        node.parent = nextnode;
        node.right = nextnode.left;
        if (nextnode.left!=null)
            nextnode.left.parent = node;
        nextnode.left = node;
    }

    private void splay(MyNode node){
        while (node.parent!=null)
            if (node == node.parent.left){
                if (node.grandfather() == null)
                    rotateright(node.parent);
                else if (node.parent == node.grandfather().left){
                    rotateright(node.grandfather());
                    rotateright(node.parent);
                }
                else {
                    rotateright(node.parent);
                    rotateleft(node.parent);
                }
            } else {
                if (node.grandfather() == null)
                    rotateleft(node.parent);
                else if (node.parent == node.grandfather().right) {
                    rotateleft(node.grandfather());
                    rotateleft(node.parent);
                } else {
                    rotateleft(node.parent);
                    rotateright(node.parent);
                }
            }
    }

    private MyNode splaymax(MyNode root){
        MyNode x = root;
        while (x.right!=null)
            x = x.right;
        splay(x);
        return x;
    }
    private MyNode merge(MyNode root1, MyNode root2){
        if (root1==null)
            return root2;
        if (root2==null)
            return root1;
        MyNode x = splaymax(root1);
        x.right = root2;
        root2.parent = x;
//        checktree(x, "merge");
        return x;
    }

    private MyNode split(Integer key){///root - меньше и равно, return - больше
        Integer fkey = floorKey(key);
        if (fkey!=null) {
            MyNode temp = root.right;
            root.right = null;
            if (temp != null)
                temp.parent = null;
            return temp;
        } else {
            MyNode temp = root;
            root = null;
            return temp;
        }

    }
    @Override
    public String put(Integer key, String value) {
        if (root == null){
            root = new MyNode(key, value,null);
            size++;
            return null;
        }
        String oldvalue;
        MyNode greatertree = split(key);
        if (root!=null && root.key.equals(key)){
            oldvalue = root.value;
            root.value = value;
            root.right = greatertree;
        } else {
            oldvalue = null;
            MyNode x = new MyNode(key, value, null);
            x.right = greatertree;
            x.left = root;
            root = x;
            size++;
        }
        if (root.right!=null)
            root.right.parent = root;
        if (root.left!=null)
            root.left.parent = root;
//        checktree(root, "put");
        return oldvalue;
    }


    @Override
    public String remove(Object key) {
        String oldvalue = get(key);
        if (oldvalue!=null){
            if (root.right!=null)
                root.right.parent = null;
            if (root.left!=null)
                root.left.parent = null;
            root = merge(root.left, root.right);
            size--;
        }
//        checktree(root, "remove");
        return oldvalue;
    }

    @Override
    public String get(Object key) {
        MyNode x = root;
        while(x!=null){
            if (x.key.equals(key)) {
                splay(x);
//                checktree(root, "get");
                return x.value;
            }
            if (x.key.compareTo((Integer)key)>0)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key)!=null;
    }

    private boolean nodecontains(Object value, MyNode node){
        if (node==null)
            return false;
        return node.value.equals(value)||nodecontains(value, node.right) || nodecontains(value, node.left);
    }
    @Override
    public boolean containsValue(Object value) {
        return nodecontains(value, root);
    }

    @Override
    public int size() {
        return size;
    }


    private MyNode eraseNode(MyNode node){
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
        return size==0;
    }


    private void addtoHeadMap(SortedMap<Integer, String> result, MyNode node, Integer toKey){
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
    private void addtoTailMap(SortedMap<Integer, String> result, MyNode node, Integer fromKey){
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
        MyNode x = root;
        while (x.left!=null)
            x = x.left;
        splay(x);
        return x.key;
    }

    @Override
    public Integer lastKey() {
        if (root==null)
            throw new NoSuchElementException();
        MyNode x = root;
        while (x.right!=null)
            x = x.right;
        splay(x);
        return x.key;

    }

    @Override
    public Integer lowerKey(Integer key) {
        MyNode x = root;
        while (x!=null){
            if(x.key.compareTo(key) < 0){
                if (x.right!=null)
                    x = x.right;
                else {
                    splay(x);
                    return x.key;
                }
            } else {
                if (x.left!=null)
                    x = x.left;
                else {
                    MyNode parent = x.parent;
                    MyNode child = x;
                    while (parent!=null && child == parent.left){
                        child = parent;
                        parent = parent.parent;
                    }
                    if (parent!=null){
                        splay(parent);
                        return parent.key;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        MyNode x = root;
        while (x!=null){
            if(x.key.compareTo(key) <= 0){
                if (x.right!=null)
                    x = x.right;
                else {
                    splay(x);
                    return x.key;
                }
            } else {
                if (x.left!=null)
                    x = x.left;
                else {
                    MyNode parent = x.parent;
                    MyNode child = x;
                    while (parent!=null && child == parent.left){
                        child = parent;
                        parent = parent.parent;
                    }
                    if (parent!=null){
                        splay(parent);
                        return parent.key;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        MyNode x = root;
        while (x!=null){
            if(x.key.compareTo(key) >= 0){
                if (x.left!=null)
                    x = x.left;
                else {
                    splay(x);
                    return x.key;
                }
            } else {
                if (x.right!=null)
                    x = x.right;
                else {
                    MyNode parent = x.parent;
                    MyNode child = x;
                    while (parent!=null && child == parent.right){
                        child = parent;
                        parent = parent.parent;
                    }
                    if (parent!=null){
                        splay(parent);
                        return parent.key;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        MyNode x = root;
        while (x!=null){
            if(x.key.compareTo(key) > 0){
                if (x.left!=null)
                    x = x.left;
                else {
                    splay(x);
                    return x.key;
                }
            } else {
                if (x.right!=null)
                    x = x.right;
                else {
                    MyNode parent = x.parent;
                    MyNode child = x;
                    while (parent!=null && child == parent.right){
                        child = parent;
                        parent = parent.parent;
                    }
                    if (parent!=null){
                        splay(parent);
                        return parent.key;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
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
