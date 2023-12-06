package by.it.group251002.samoilenko.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer,String> {

    private static class Avl_node{
        public int key;
        public String value;
        public int height;
        public Avl_node left;
        public Avl_node right;
        public boolean isDeleted;
        Avl_node(int key,String value){
            this.isDeleted=false;
            this.key=key;
            this.value=value;
            this.right=null;
            this.left=null;
            this.height=0;
        }
    }
    private Avl_node root=null;
    private int avltree_height(Avl_node node){
        return (node!=null)? node.height :-1;
    }
    int balance_factor(Avl_node node){
        return node==null?0:avltree_height(node.left)-avltree_height(node.right);
    }
    private Avl_node right_rotate(Avl_node node){
        Avl_node left=node.left;
        node.left=left.right;
        left.right=node;
        node.height=((avltree_height(node.left))>avltree_height(node.right)?avltree_height(node.left):avltree_height(node.right))+1;
        left.height=((avltree_height(left.left))>node.height?avltree_height(left.left):node.height)+1;
        return left;
    }
    private Avl_node left_rotate(Avl_node node){
        Avl_node right=node.right;
        node.right=right.left;
        right.left=node;
        node.height=((avltree_height(node.left))>avltree_height(node.right)?avltree_height(node.left):avltree_height(node.right))+1;
        right.height=((avltree_height(right.right))>node.height?avltree_height(right.right):node.height)+1;
        return right;
    }
    @Override
    public int size() {
        return size(root);
    }
    int size(Avl_node node){
        if(node==null){
            return 0;
        }
        return ((node.isDeleted)?0:1)+size(node.left)+size(node.right);
    }
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    Avl_node find(Object key){
        Avl_node node=root;
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

    @Override
    public boolean containsValue(Object value) {
        return true;
    }

    @Override
    public String get(Object key) {
        Avl_node node=find(key);
        return (node==null)?null:node.value;
    }

    @Override
    public String put(Integer key, String value) {
        StringBuilder result=new StringBuilder();
        root=add(root,key,value,result);
        return result.isEmpty()?null:result.toString();
    }

     Avl_node add(Avl_node node,Integer key,String value,StringBuilder result){
        if(node==null){
            return new Avl_node(key,value);
        }
        if(key<node.key) {
            node.left = add(node.left, key, value, result);
            if(balance_factor(node)==2){
                if(key<node.left.key){
                    node=right_rotate(node);
                }
                else{
                    node.left=left_rotate(node.left);
                    node=right_rotate(node);
                }
            }
        }
        else{
            if(key>node.key){
                node.right=add(node.right,key,value,result);
                if(balance_factor(node)==-2){
                    if(key>node.right.key){
                        node=left_rotate(node);
                    }
                    else{
                        node.right=right_rotate(node.right);
                        node=left_rotate(node);
                    }
                }
            }
            else{
                if(!node.value.equals(value)){
                    if(node.isDeleted){
                        node.isDeleted=false;
                    } else {
                        result.append(node.value);
                    }
                    node.value=value;
                }
            }
        }
        node.height=((avltree_height(node.left))>avltree_height(node.right)?avltree_height(node.left):avltree_height(node.right))+1;
        return node;
    }
    @Override
    public String remove(Object key) {
        Avl_node node=find(key);
        if(node!=null)
            node.isDeleted=true;
        return (node==null)?null:node.value;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }
    void walk(Avl_node node,StringBuilder res){
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
    public void clear() {
       root=null;
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
