package by.it.group251002.samoilenko.lesson12;


import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    enum Color{
        RED,BLACK;
    }
    private static class Rb_node{
        Integer key;
        String value;
        Rb_node parent,left,right;
        Color color;
        boolean isDeleted;
        public Rb_node(Color color,Integer key,String value){
            this.isDeleted=false;
            this.key=key;
            this.value=value;
            this.color=color;
            this.left=null;
            this.right=null;
        }
    }
    Rb_node root;
    private Rb_node getUncle(Rb_node node) {
        Rb_node grand = getGrand(node);
        if (grand == null)
            return null;
        if (grand.right == node.parent) {
            return grand.left;
        }
        return grand.left;
    }

    private Rb_node getGrand(Rb_node node) {
        if (node != null && node.parent != null) {
            return node.parent.parent;
        }
        return null;
    }
    public void rotate_left(Rb_node node) {
        Rb_node pivot = node.right;
        pivot.parent = node.parent;
        if (node.parent != null) {
            if (node.parent.left == node)
                node.parent.left = pivot;
            else
                node.parent.right = pivot;
        }else{
            root=pivot;
        }
        node.right = pivot.left;
        if (pivot.left != null)
            pivot.left.parent = node;
        node.parent = pivot;
        pivot.left = node;
    }

    public void rotate_right(Rb_node node) {
        Rb_node pivot = node.left;
        pivot.parent = node.parent;
        if (node.parent != null) {
            if (node.parent.left == node)
                node.parent.left = pivot;
            else
                node.parent.right = pivot;
        } else{
            root=pivot;
        }
        node.left = pivot.right;
        if (pivot.right != null)
            pivot.right.parent = node;
        node.parent = pivot;
        pivot.right = node;
    }



    void insert_case1(Rb_node node) {
        if (node.parent == null)
            node.color = Color.BLACK;
        else
            insert_case2(node);
    }
    void insert_case2(Rb_node node) {
        if (node.parent.color==Color.BLACK)
            return;
        else
            insert_case3(node);
    }

    void insert_case3(Rb_node node) {
        Rb_node uncle = getUncle(node), grand;
        if (uncle != null && uncle.color==Color.RED) {
            node.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grand = getGrand(node);
            grand.color=Color.RED;
            insert_case1(grand);
        } else
            insert_case4(node);
    }

    void insert_case4(Rb_node node) {
        Rb_node grand = getGrand(node);
        if (node == node.parent.right && node.parent == grand.left) {
            rotate_left(node.parent);
            node = node.left;
        } else if (node == node.parent.left && node.parent == grand.right) {
            rotate_right(node.parent);
            node = node.right;
        }
        insert_case5(node);
    }

    void insert_case5(Rb_node node) {
        Rb_node grand = getGrand(node);
        node.parent.color = Color.BLACK;
        grand.color=Color.RED;
        if (node == node.parent.left && node.parent == grand.left)
            rotate_right(grand);
        else
            rotate_left(grand);
    }

    void walk(Rb_node node, StringBuilder res){
        if(node!=null){
            walk(node.left,res);
            if(!node.isDeleted)
                res.append( node.key+"="+node.value + ", ");
            walk(node.right,res);
        }
    }
    @Override
    public String toString() {
        if(this.isEmpty()){
            return "{}";
        }
        StringBuilder res=new StringBuilder();
        res.append('{');
        walk(root,res);
        res.replace(res.length()-2,res.length(),"}");
        return res.toString();
    }
    @Override
    public int size() {
        return size(root);
    }
    int size(Rb_node node){
        if(node==null){
            return 0;
        }
        return ((node.isDeleted)?0:1)+size(node.left)+size(node.right);
    }
    @Override
    public boolean isEmpty() {
        return size()==0;
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
        if(isEmpty())
            return null;
        MyRbMap headMap=new MyRbMap();
        head_map(root,toKey,headMap);
        return headMap;
    }

    void head_map(Rb_node node,Integer toKey,MyRbMap headMap){
        if(node==null)
            return;
        if(node.key<toKey){
            if(!node.isDeleted)
                headMap.put(node.key, node.value);
            head_map(node.right,toKey,headMap);
        }
        head_map(node.left,toKey,headMap);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        if(isEmpty())
            return null;
        MyRbMap tailMap=new MyRbMap();
        tail_map(root,fromKey,tailMap);
        return tailMap;
    }
    void tail_map(Rb_node node,Integer fromKey,MyRbMap tailMap){
        if(node==null)
            return;
        if(node.key>=fromKey){
            if(!node.isDeleted)
                tailMap.put(node.key, node.value);
            tail_map(node.left,fromKey,tailMap);
        }
        tail_map(node.right,fromKey,tailMap);
    }

    @Override
    public Integer firstKey() {
        if(isEmpty())
            return null;
        Rb_node node=firstNode(root);
        return node.key;
    }
    private Rb_node firstNode(Rb_node node){
        while (node.left!=null)
            node=node.left;
        while(node.isDeleted){
            node=node.parent;
        }
        return node;
    }
    @Override
    public Integer lastKey() {
        if(isEmpty())
            return null;
        Rb_node node=lastNode(root);
        return node.key;
    }
    private Rb_node lastNode(Rb_node node){
        while (node.right!=null)
            node=node.right;
        while(node.isDeleted){
            node=node.parent;
        }
        return node;
    }
    Rb_node find(Object key){
        Rb_node node=root;
        while(node!=null){
            if(node.key<(int)key){
                node=node.right;
            } else{
                if(node.key>(int)key){
                    node=node.left;
                }
                else{
                    if(node.isDeleted)
                        return null;
                    return node;
                }
            }
        }
        return null;
    }
    @Override
    public boolean containsKey(Object key) {
        return find(key)!=null;
    }

    public boolean containsValue(Object value) {
        return containsValue(root, value);
    }

    boolean containsValue(Rb_node node, Object value) {
        if (node == null) {
            return false;
        }
        if (value.equals(node.value)) {
            return true;
        }
        return containsValue(node.left, value) || containsValue(node.right, value);
    }

    @Override
    public String get(Object key) {
        Rb_node node=find(key);
        return (node==null)?null:node.value;
    }
    @Override
    public String put(Integer key, String value) {
        Rb_node new_node=find(key);
        if(new_node!=null){
            String ex_value=null;
            if(!new_node.isDeleted)
                ex_value=new_node.value;
            else
                new_node.isDeleted=false;
            new_node.value=value;
            return ex_value;
        }
        new_node=new Rb_node(Color.RED, key,value);
        if(root==null){
            root =new_node;
            root.parent=null;
        } else {
            Rb_node cur = root;
            Rb_node temp=null;
            while (cur!=null){
                temp=cur;
                if(cur.key< new_node.key)
                    cur=cur.right;
                else
                    cur=cur.left;
            }
            new_node.parent=temp;
            if(temp.key<new_node.key){
                temp.right=new_node;
            }
            else
                temp.left=new_node;
        }
        insert_case1(new_node);
        return null;
    }
    @Override
    public String remove(Object key) {
        Rb_node node=find(key);
        if(node!=null)
            node.isDeleted=true;
        return (node==null)?null:node.value;
    }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = null;
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
