package by.it.group251001.zhidkov.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private int size = 0;
    private MyNode root;

    static private class MyNode{
        Integer key;
        String value;
        Boolean color;///красный - false , черный - true
        MyNode left, right, parent;
        MyNode(Integer key,String value,MyNode parent) {
            this.key = key;
            this.value = value;
            this.color = false;
            this.right = null;
            this.left = null;
            this.parent = parent;
        }
        MyNode grandfather(){
            if (this != null && this.parent != null)
                return this.parent.parent;
            else
                return null;
        }
        MyNode uncle(){
            MyNode g = this.grandfather();
            if (g == null)
                return null;
            if (this.parent == g.left)
                return g.right;
            else
                return g.left;
        }
        MyNode sibling(){
            if (this == this.parent.right)
                return this.parent.left;
            else
                return this.parent.right;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        if (root != null) {
            toStringHelper(root, res);
            res.replace(res.length() - 2, res.length(), "}");
        }
        else
            res.append("}");
        return res.toString();
    }

    private void toStringHelper(MyNode node, StringBuilder res) {
        if (node != null) {
            if (node.left != null) {
                toStringHelper(node.left, res);
            }
            res.append(node.key).append("=").append(node.value).append(", ");
            if (node.right != null) {
                toStringHelper(node.right, res);
            }
        }
    }

    @Override
    public String put(Integer key, String value) {
        if (root == null){
            root = new MyNode(key, value,null);
            insertcase_1(root);
            size++;
            return null;
        }
        MyNode x = root;
        MyNode parent = null;
        while(x!=null){
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
        x = new MyNode(key, value, parent);
        if (parent.key.compareTo(key)>0)
            parent.left = x;
        else
            parent.right = x;
        insertcase_1(x);
        size++;
        return null;
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

    private void insertcase_1(MyNode node){
        if(node.parent==null)
            node.color = true;
        else
            insertcase_2(node);
    }
    private void insertcase_2(MyNode node){
        if (!node.parent.color)
            insertcase_3(node);
    }
    private void insertcase_3(MyNode node){
        MyNode u = node.uncle();
        if (u!=null && !u.color) {
            node.parent.color = true;
            u.color = true;
            MyNode g = node.grandfather();
            g.color = false;
            insertcase_1(g);
        } else {
            insertcase_4(node);
        }
    }
    private void insertcase_4(MyNode node){
        MyNode g = node.grandfather();
        if (node.parent.right==node && node.parent==g.left){
            rotateleft(node.parent);
            node = node.left;
        } else if(node.parent.left==node && node.parent==g.right) {
            rotateright(node.parent);
            node = node.right;
        }
        insertcase_5(node);
    }
    private void insertcase_5(MyNode node){
        MyNode g = node.grandfather();
        node.parent.color = true;
        g.color = false;
        if (node==node.parent.left && node.parent==g.left)
            rotateright(g);
        else
            rotateleft(g);
    }

    @Override
    public String get(Object key) {
        MyNode x = root;
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


    private void deletecase_1(MyNode node){
        if (node.parent != null)
            deletecase_2(node);
    }
    private void deletecase_2(MyNode node){
        MyNode s = node.sibling();
        if (!s.color){
            node.parent.color = false;
            s.color = true;
            if (node == node.parent.left)
                rotateleft(node);
            else
                rotateright(node);
        }
        deletecase_3(node);
    }
    private void deletecase_3(MyNode node){
        MyNode s = node.sibling();
        if (node.parent.color && s.color && s.left.color && s.right.color){
            s.color = false;
            deletecase_1(node.parent);
        } else
            deletecase_4(node);

    }
    private void deletecase_4(MyNode node){
        MyNode s = node.sibling();
        if (!node.parent.color && s.color && s.left.color && s.right.color){
            s.color = false;
            node.parent.color = true;
        } else
            deletecase_5(node);
    }
    private void deletecase_5(MyNode node){
        MyNode s = node.sibling();
        if (s.color){
            if (node == node.parent.left && s.right.color && !s.left.color){
                s.color = false;
                s.left.color = true;
                rotateright(s);
            } else if (node == node.parent.right && !s.right.color && s.left.color){
                s.color = false;
                s.right.color = true;
                rotateleft(s);
            }
        }
        deletecase_6(node);
    }

    private void deletecase_6(MyNode node){
        MyNode s = node.sibling();
        s.color = node.parent.color;
        node.parent.color = true;
        if (node == node.parent.left){
            s.right.color = true;
            rotateleft(node.parent);
        } else {
            s.left.color = true;
            rotateright(node.parent);
        }
    }
    private MyNode findmin(MyNode node){
        if (node.left==null)
            return node;
        else
            return findmin(node.left);
    }
    private void replacechild(MyNode node, MyNode child) {
        child.parent = node.parent;
        if (node.parent!=null){
            if (node == node.parent.right)
                node.parent.right = child;
            else
                node.parent.left = child;
        } else
            root = child;
    }
    private void deleteonechild(MyNode node){
        MyNode child = node.right!=null ? node.right : node.left;
        replacechild(node,child);
        if (node.color) {
            if (!child.color)
                child.color = true;
            else
                deletecase_1(child);
        }
        node.key = null;
        node.value = null;
    }
    private void deletenode(MyNode node){
        if (node.right!=null && node.left!=null){
            MyNode min = findmin(node.right);
            node.value = min.value;
            node.key = min.key;
            deletenode(min);
        } else if (node.right!=null ^ node.left!=null){
            deleteonechild(node);
        } else {
            if (node.parent!=null){
                if(node == node.parent.left)
                    node.parent.left = null;
                else
                    node.parent.right = null;
                if (node.color)
                    insertcase_1(node.parent);
            } else
                root = null;
        }
    }
    @Override
    public String remove(Object key) {
        MyNode x = root;
        while(x!=null){
            if (x.key.equals(key)){
                String result = x.value;
                size--;
                deletenode(x);
//                checkrules("remove");
                return result;
            }
            if (x.key.compareTo((Integer) key)>0)
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

    private boolean contains(Object value, MyNode node){
        if (node == null)
            return false;
        return node.value.equals(value) || contains(value, node.right) || contains(value, node.left);
    }
    @Override
    public boolean containsValue(Object value) {
        return contains(value, root);
    }

    @Override
    public int size() {
        return size;
    }

    private MyNode clearNode(MyNode node){
        if (node != null){
            node.right = clearNode(node.right);
            node.left = clearNode(node.left);
            node.key = null;
            node.value = null;
            node.parent = null;
        }
        return null;
    }
    @Override
    public void clear() {
        size = 0;
        root = clearNode(root);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    private SortedMap<Integer, String> resMap(Integer toKey, boolean isHead) {
        MyRbMap result = new MyRbMap();
        addSubMap(result, root, toKey, isHead);
        return result;
    }
    private void addSubMap(SortedMap<Integer, String> result, MyNode node, Integer reachKey, boolean isHead){
        if (node != null && reachKey != null){
            if (!isHead)
                addSubMap(result, node.right, reachKey, isHead);
            else
                addSubMap(result, node.left, reachKey, isHead);
            boolean bool;
            if (isHead)
                bool = node.key.compareTo(reachKey) < 0;
            else
                bool = node.key.compareTo(reachKey) >= 0;
            if (bool) {
                result.put(node.key, node.value);
                if (isHead)
                    addSubMap(result, node.right, reachKey, isHead);
                else
                    addSubMap(result, node.left, reachKey, isHead);
            }
        }
    }
    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        return resMap(toKey, true);
    }
    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        return resMap(fromKey, false);
    }
    @Override
    public Integer firstKey() {
        return extremeKey(true);
    }

    @Override
    public Integer lastKey() {
        return extremeKey(false);
    }
    private Integer extremeKey(boolean isLowExtreme) {
        if (root != null) {
            MyNode x = root;
            if (isLowExtreme) {
                while (x.left != null)
                    x = x.left;
            } else {
                while (x.right != null)
                    x = x.right;
            }
            return x.key;
        }
        else {
            return 0;
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