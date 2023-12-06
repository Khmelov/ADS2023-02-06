package by.it.group251002.makarov.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyAvlMap implements Map<Integer,String> {
    private Node root;
    private int size;
    class Node{
        int key;
        String value;
        Node left,right;
        int height;
        Node(int key,String value){
            this.key=key;
            this.value=value;
            this.height=1;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public String toString(){
        if(isEmpty()) return "{}";
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while(!s.isEmpty()||curr!=null){
            if(curr!=null){
                s.push(curr);
                curr = curr.left;
            } else{
                curr = s.pop();
                sb.append(delimiter).append(curr.key).append("=").append(curr.value);
                delimiter=", ";
                curr=curr.right;
            }
        }
        sb.append("}");
        return sb.toString();
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)){
            return false;
        }
        Integer searchKey = (Integer) key;
        return containsKey(root,searchKey);
    }
    public boolean containsKey(Node node,int key){
        if(node == null)return false;
        if(key<node.key){
            return containsKey(node.left,key);
        }else
        if(key>node.key){
            return containsKey(node.right,key);
        } else return true;
    }
    @Override
    public boolean containsValue(Object value) {
        return containsValue(root,value);
    }
    public boolean containsValue(Node node, Object value){
        if(value.equals(node.value)){
            return true;
        }
        return containsValue(node.left,value)||containsValue(node.right,value);
    }

    @Override
    public String get(Object key) {
        if(key==null||!(key instanceof Integer))return null;
        Integer searchKey = (Integer) key;
        Node result = search(root,searchKey);
        return (result!=null)?result.value:null;
    }
    public Node search(Node node, int key){
        if(node== null || key == node.key){
            return node;
        }
        if(key<node.key){
            return search(node.left,key);
        } else{
            return search(node.right,key);
        }
    }

    @Override
    public String put(Integer key, String value) {
        if (key==null) throw new NullPointerException();
        String oldValue = get(key);
        root=put(root,key,value);
        return oldValue;
    }
    public Node put(Node node, int key, String value){
        if(node==null){
            size++;
            return new Node(key,value);
        }
        if(key<node.key){
            node.left = put(node.left,key,value);
        } else if (key>node.key){
            node.right = put(node.right, key,value);
        }else{
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }
    public int height(Node node){
        if(node!=null)
        return node.height;
        return 0;
    }
    public void updateHeight(Node node){
        if(node!=null){
            node.height=Math.max(height(node.left),height(node.right));
        }
    }
    public Node balance(Node node){
        int balanceFactor = balanceFactor(node);
        if(balanceFactor>1&&balanceFactor(node.left)>=0){//левый поворот
            return rightRotate(node);
        }
        if(balanceFactor<-1&&balanceFactor(node.right)<=0){//правый поворот
            return leftRotate(node);
        }

        if(balanceFactor > 1 && balanceFactor(node.left)<0){//лево-правый поворот
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if(balanceFactor<-1 && balanceFactor(node.right)>=0){//право-левый поворот
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    public Node leftRotate(Node y){
        //
       Node x = y.right;
       Node T2 = x.left;

       //перемещаем x вверх
       x.left = y;
       y.right = T2;

       //обновление высоты
       updateHeight(y);
       updateHeight(x);

       //новый корень поддерева
       return x;
    }
    public Node rightRotate(Node x){
        Node y = x.left;
        Node T2 = y.right;

        //перемещаем вверх
        y.right =x;
        x.left = T2;

        //обновляем высоту
        updateHeight(x);
        updateHeight(y);
        return x;
    }
    public int balanceFactor(Node node){
        return (node==null) ? 0 : height(node.left) - height(node.right);
    }
    @Override

    public String remove(Object key) {
        if(!(key instanceof Integer)){
            return null;
        }
        Integer removeKey = (Integer) key;
        String removedValue = get(removeKey);

        root=remove(root,removeKey);
        if (removedValue != null) {
            size--; // Уменьшаем размер только если элемент был успешно удален
        }

        return removedValue;
    }
    public Node remove(Node node, Integer key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = remove(node.left, key);
        }
        else if (key > node.key) {
                node.right = remove(node.right, key);
        }
        else if (node.left == null || node.right == null)
        {
            node = (node.left != null) ? node.left : node.right;
        }
        else
        {
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key);
        }

        if(node!=null){
            updateHeight(node);
            return balance(node);
        }

        return null;
    }
    public Node findMin(Node node){
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
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
