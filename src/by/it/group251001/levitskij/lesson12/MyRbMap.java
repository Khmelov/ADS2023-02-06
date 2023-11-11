package by.it.group251001.levitskij.lesson12;


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
            if (this!=null && this.parent!=null)
                return this.parent.parent;
            else
                return null;
        }
        MyNode uncle(){
            MyNode g = this.grandfather();
            if (g==null)
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

//    private boolean checkrule4(MyNode node, String methodname){
//        if (node==null)
//            return true;
//        if (!node.color)
//            if (!(checkrule4(node.right, methodname) && checkrule4(node.left, methodname)))
//                System.out.println("broken rule 4 in"+ methodname);
//        return node.color;
//    }
//    private int checkrule5(MyNode node, String methodname){
//        if (node == null)
//            return 1;
//        int leftbheight = checkrule5(node.left, methodname);
//        int rightbheight = checkrule5(node.right, methodname);
//        if (leftbheight != rightbheight)
//            System.out.println("broken rule 2 in"+ methodname);
//        return leftbheight+(node.color ? 1 : 0);
//    }
//    private void checkrules(String methodname){
//        if (root!=null){
//            if (!root.color)
//                System.out.println("broken rule 2 in"+ methodname);
//            checkrule4(root, methodname);
//            checkrule5(root, methodname);
//        }
//    }




    @Override
    public String put(Integer key, String value) {
        if (root == null){
            root = new MyNode(key, value,null);
            insertcase1(root);
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
        insertcase1(x);
        size++;
//        checkrules("put");
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
    
    private void insertcase1(MyNode node){
        if(node.parent==null)
            node.color = true;
        else 
            insertcase2(node);
    }
    private void insertcase2(MyNode node){
        if (!node.parent.color)
            insertcase3(node);
    }
    private void insertcase3(MyNode node){
        MyNode u = node.uncle();
        if (u!=null && !u.color) {
            node.parent.color = true;
            u.color = true;
            MyNode g = node.grandfather();
            g.color = false;
            insertcase1(g);
        } else {
            insertcase4(node);
        }
    }
    private void insertcase4(MyNode node){
        MyNode g = node.grandfather();
        if (node.parent.right==node && node.parent==g.left){
            rotateleft(node.parent);
            node = node.left;
        } else if(node.parent.left==node && node.parent==g.right) {
            rotateright(node.parent);
            node = node.right;
        }
        insertcase5(node);
    }
    private void insertcase5(MyNode node){
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
        while(x!=null){
            if (x.key.equals(key))
                return x.value;
            if (x.key.compareTo((Integer) key)>0)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }


    private void deletecase1(MyNode node){
        if (node.parent != null)
            deletecase2(node);
    }
    private void deletecase2(MyNode node){
        MyNode s = node.sibling();
        if (!s.color){
            node.parent.color = false;
            s.color = true;
            if (node == node.parent.left)
                rotateleft(node);
            else
                rotateright(node);
        }
        deletecase3(node);
    }
    private void deletecase3(MyNode node){
        MyNode s = node.sibling();
        if (node.parent.color && s.color && s.left.color && s.right.color){
            s.color = false;
            deletecase1(node.parent);
        } else
            deletecase4(node);

    }
    private void deletecase4(MyNode node){
        MyNode s = node.sibling();
        if (!node.parent.color && s.color && s.left.color && s.right.color){
            s.color = false;
            node.parent.color = true;
        } else
            deletecase5(node);
    }
    private void deletecase5(MyNode node){
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
        deletecase6(node);
    }

    private void deletecase6(MyNode node){
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
                deletecase1(child);
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
                    insertcase1(node.parent);
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
        return x.key;
    }

    @Override
    public Integer lastKey() {
        if (root==null)
            throw new NoSuchElementException();
        MyNode x = root;
        while (x.right!=null)
            x = x.right;
        return x.key;

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
