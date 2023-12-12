package by.it.group251002.punko.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

        private int size = 0;
        private MyNode root;

        static private class MyNode{
            Integer key;
            String value;
            int height;
            MyNode left, right;
            MyNode(Integer key,String value) {
                this.key = key;
                this.value = value;
                this.height = 1;
                this.right = null;
                this.left = null;
            }

        }
    private int height(MyNode node){
        return (node!=null) ? node.height : 0;
    }

    private int bfactor(MyNode node){
        return height(node.right)-height(node.left);
    }

        private void fixheight(MyNode node){
        int heightright = height(node.right);
        int heightleft = height(node.left);
        node.height = (heightright>heightleft ? heightright : heightleft)+1;
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

        private MyNode rotateright(MyNode
        node){
        MyNode nextnode = node.left;
        node.left = nextnode.right;
        nextnode.right = node;
        fixheight(node);
        fixheight(nextnode);
        return nextnode;
    }

        private MyNode rotateleft(MyNode
        node){
        MyNode nextnode = node.right;
        node.right = nextnode.left;
        nextnode.left = node;
        fixheight(node);
        fixheight(nextnode);
        return nextnode;
    }
        private MyNode balance(MyNode
        node){
        fixheight(node);
        int h = bfactor(node);
        if (h == 2){
            if (bfactor(node.right) < 0)
                node.right = rotateright(node.right);
            return rotateleft(node);
        }
        if (h == -2){
            if (bfactor(node.left) > 0)
                node.left = rotateleft(node.left);
            return rotateright(node);
        }
        return node;
    }

        private MyNode insert(MyNode
        node, Integer key, String value, StringBuilder oldvalue){
        if (node==null) {
            size++;
            return new MyNode(key, value);
        }
        if (node.key>key)
            node.left = insert(node.left, key, value,oldvalue);
        else if (node.key<key)
            node.right = insert(node.right, key, value, oldvalue);
        else {
            oldvalue.append(node.value);
            node.value = value;
            return node;
        }
        return balance(node);
    }
        public String put(Integer key, String value) {
        StringBuilder oldvalue = new StringBuilder();
        root = insert(root, key, value, oldvalue);
        return oldvalue.isEmpty()?null:oldvalue.toString();
    }


        private MyNode findmin(MyNode
        node){
        if (node.left==null)
            return node;
        else
            return findmin(node.left);
    }

        private MyNode delete(MyNode
        node, Integer key, StringBuilder oldvalue){
        if (node.key.equals(key)){
            size--;
            if (oldvalue!=null)
                oldvalue.append(node.value);
            if (node.left==null && node.right==null)
                return null;
            if (node.left==null)
                return node.right;
            if (node.right==null)
                return node.left;
            size++;
            MyNode minnode = findmin(node.right);
            node.value = minnode.value;
            node.key = minnode.key;
            node.right = delete(node.right, minnode.key, null);
            return node;
        }
        if (node.key>key) {
            if(node.left==null)
                return node;
            node.left = delete(node.left, key, oldvalue);
        }else{
            if(node.right==null)
                return node;
            node.right = delete(node.right, key, oldvalue);
        }
        return balance(node);
    }
        @Override
        public String remove(Object key) {
        int oldsize = size;
        StringBuilder oldvalue = new StringBuilder();
        root = delete(root, (Integer)key, oldvalue);
        return oldsize==size?null:oldvalue.toString();
    }

        @Override
        public String get(Object key) {
        MyNode x = root;
        while(x!=null){
            if (x.key.equals(key))
                return x.value;
            if (x.key>(Integer)key)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }

        @Override
        public boolean containsKey(Object key) {
        MyNode x = root;
        while(x!=null){
            if (x.key==key)
                return true;
            if (x.key>(Integer)key)
                x = x.left;
            else
                x = x.right;
        }
        return false;
    }

        @Override
        public int size() {
        return size;
    }


        private MyNode eraseNode(MyNode
        node){
        if (node != null){
            node.right = eraseNode(node.right);
            node.left = eraseNode(node.left);
            node.key = null;
            node.value = null;
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
///////////////////////////////////////////////////////////////////////////////////////////////
        @Override
        public boolean containsValue(Object value) {
        return false;
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
        public Set<Map.Entry<Integer, String>> entrySet() {
        return null;
    }
}
