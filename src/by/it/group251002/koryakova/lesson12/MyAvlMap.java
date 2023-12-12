package by.it.group251002.koryakova.lesson12;

import java.util.Map;
import java.util.Collection;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String>{
    private class Node{
        public int key;
        public int height;
        public String data;
        public Node left, right;
        public Node(int key, String data){
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
            height = 1;
        }
        public int height(Node nod){
            return (nod != null ? nod.height : 0);
        }
        public int getHeightDiff(Node nod){
            if(nod == null){
                return 0;
            }
            return height(nod.right) - height(nod.left);
        }
        public void fixHeight(){
            int lheight = height(this.left);
            int rheight = height(this.right);
            this.height = Math.max(lheight, rheight) + 1;
        }
        public Node rotateLeft(){
            Node rtemp = this.right;
            this.right = rtemp.left;
            rtemp.left = this;
            this.fixHeight();
            rtemp.fixHeight();
            return rtemp;
        }
        public Node rotateRight(){
            Node ltemp = this.left;
            this.left = ltemp.right;
            ltemp.right = this;
            this.fixHeight();
            ltemp.fixHeight();
            return ltemp;
        }
        public Node balance(){
            fixHeight();
            if(getHeightDiff(this) == 2){
                if(getHeightDiff(this.right) < 0){
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            }
            if(getHeightDiff(this) == (-2)){
                if(getHeightDiff(this.left) > 0){
                    this.left = this.left.rotateLeft();
                }
                return rotateRight();
            }
            return this;
        }
        public Node getMin(){
            if(this.left == null){
                return this;
            }
            else{
                return this.left.getMin();
            }
        }
        public Node removeMin(){
            if(this.left == null){
                return this.right;
            }
            this.left = this.left.removeMin();
            return this.balance();
        }
    }
    private Node getNode(Node nod, Integer key){
        if(nod == null){
            return null;
        }
        else{
            if(key < nod.key){
                return getNode(nod.left, key);
            }
            else{
                if(key > nod.key){
                    return getNode(nod.right, key);
                }
                else{
                    return nod;
                }
            }
        }
    }
    private Node insertNode(Node nod, Integer key, String data){
        if(nod == null){
            return new Node(key, data);
        }
        if(key < nod.key){
            nod.left = insertNode(nod.left, key, data);
        }
        else{
            nod.right = insertNode(nod.right, key, data);
        }
        return nod.balance();
    }
    private Node removeNode(Node nod, Object key){
        if(nod == null){
            return null;
        }
        if((Integer) key < nod.key){
            nod.left = removeNode(nod.left, key);
        }
        else{
            if((Integer) key > nod.key){
                nod.right = removeNode(nod.right, key);
            }
            else{
                Node lef = nod.left;
                Node righ = nod.right;
                nod = null;
                if(righ == null){
                    return lef;
                }
                Node min = righ.getMin();
                min.right = righ.removeMin();
                min.left = lef;
                min.balance();
                return min;
            }
        }
        return nod.balance();
    }
    private Node tree = null;
    private int size = 0;
    private void toStr(Node nod, StringBuilder rez){
        if(nod == null){
            return;
        }
        toStr(nod.left, rez);
        rez.append(nod.key).append("=").append(nod.data).append(", ");
        toStr(nod.right, rez);
    }
    @Override
    public String toString(){
        StringBuilder rez = new StringBuilder("{");
        toStr(tree, rez);
        if(tree != null){
            rez = new StringBuilder(rez.substring(0, rez.length() - 2));
        }
        rez.append("}");
        return String.valueOf(rez);
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return (size == 0);
    }
    @Override
    public boolean containsKey(Object key){
        Node rez = getNode(tree, (Integer) key);
        return (rez != null);
    }
    @Override
    public String get(Object key){
        Node temp = getNode(tree, (Integer) key);
        if(temp == null){
            return null;
        }
        return temp.data;
    }
    @Override
    public String put(Integer key, String value){
        Node val = getNode(tree, key);
        if(val == null){
            size = size + 1;
            tree = insertNode(tree, key, value);
            return null;
        }
        String retData = val.data;
        val.data = value;
        return retData;
    }
    @Override
    public String remove(Object key){
        String rez = get(key);
        if(rez != null){
            size = size - 1;
            tree = removeNode(tree, key);
        }
        return rez;
    }
    @Override
    public void clear(){
        tree = null;
         size = 0;
    }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }
    @Override
    public boolean containsValue(Object value){
        return false;
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
