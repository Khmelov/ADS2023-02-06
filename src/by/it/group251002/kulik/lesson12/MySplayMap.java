package by.it.group251002.kulik.lesson12;

import java.util.*;

class SplayNode{

    // the deleted field of the class was not included because of
    // the easy-to-implement remove method
    public Integer key;
    public String value;
    public SplayNode left;
    public SplayNode right;
    public SplayNode parent;
    SplayNode(Integer key, String value, SplayNode parent){
        this.key=key;
        this.value=value;
        this.left=null;
        this.right=null;
        this.parent=parent;
    }
}
public class MySplayMap implements NavigableMap<Integer,String>{
    private SplayNode head=null;
    private int size=0;

    boolean isLeftChild(SplayNode child){
        if (child.parent!=null){
            return child.parent.left==child;
        }
        return false;
    }
    SplayNode getMostLeftChild(SplayNode node){
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    void completeRotation(SplayNode child, SplayNode node){
        child.parent=node.parent;
        node.parent=child;
        if (node == head){
            head=child;
        }
        else{
            if(child.parent.left==node){
                child.parent.left=child;
            }
            else{
                child.parent.right=child;
            }
        }
    }
    void RRotate(SplayNode node){
        SplayNode child = node.left;
        node.left=child.right;
        if(node.left!=null){
            node.left.parent=node;
        }
        child.right=node;
        completeRotation(child,node);
    }
    void LRotate(SplayNode node){
        SplayNode child = node.right;
        node.right=child.left;
        if(node.right!=null){
            node.right.parent=node;
        }
        child.left=node;
        completeRotation(child,node);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String delimiter = "";
        if (head!=null){
            SplayNode temp=getMostLeftChild(head);
            boolean fromRight=false;
            while(temp!=null){
                if(fromRight){
                    fromRight=!isLeftChild(temp);
                    temp=temp.parent;
                }
                else{
                    sb.append(delimiter).append(temp.key).append("=").append(temp.value);
                    delimiter=", ";

                    if(temp.right!=null){
                        temp=getMostLeftChild(temp.right);
                    }
                    else{
                        fromRight=!isLeftChild(temp);
                        temp=temp.parent;
                    }
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }
    private void splay(SplayNode node){
        while(head!=node){
            if(isLeftChild(node)){
                RRotate(node.parent);
            }
            else{
                LRotate(node.parent);
            }
        }
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        SplayNode res = null;
        SplayNode temp = head;
        while (temp!=null) {
            if (temp.key < key) {
                if (res == null) {
                    res = temp;
                } else {
                    if (res.key < temp.key) {
                        res = temp;
                    }
                }
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        if (res!=null){
            splay(res);
            return head.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        SplayNode res = null;
        SplayNode temp = head;
        while (temp!=null) {
            if (temp.key <= key) {
                if(temp.key==key){
                    splay(temp);
                    return temp.key;
                }
                if (res == null) {
                    res = temp;
                } else {
                    if (res.key < temp.key) {
                        res = temp;
                    }
                }
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        if (res!=null){
            splay(res);
            return head.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        SplayNode res = null;
        SplayNode temp = head;
        while (temp!=null) {
            if (temp.key >= key) {
                if(temp.key==key){
                    splay(temp);
                    return temp.key;
                }
                if (res == null) {
                    res = temp;
                } else {
                    if (res.key > temp.key) {
                        res = temp;
                    }
                }
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (res!=null){
            splay(res);
            return head.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        SplayNode res = null;
        SplayNode temp = head;
        while (temp!=null) {
            if (temp.key > key) {
                if (res == null) {
                    res = temp;
                } else {
                    if (res.key > temp.key) {
                        res = temp;
                    }
                }
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        if (res!=null){
            splay(res);
            return head.key;
        }
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
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MySplayMap newMap = new MySplayMap();

        boolean fromRight;
        SplayNode temp = head;
        SplayNode leftSubTree;
        while(temp!=null){
            if(temp.key<toKey){
                newMap.put(temp.key,temp.value);
                leftSubTree=temp.left;
                if (leftSubTree!=null){
                    leftSubTree=getMostLeftChild(leftSubTree);
                    fromRight=false;
                    while(leftSubTree!=temp){
                        if(fromRight){
                            fromRight=!isLeftChild(leftSubTree);
                            leftSubTree=leftSubTree.parent;
                        }
                        else{
                            newMap.put(leftSubTree.key,leftSubTree.value);

                            if(leftSubTree.right!=null){
                                leftSubTree=getMostLeftChild(leftSubTree.right);
                            }
                            else{
                                fromRight=!isLeftChild(leftSubTree);
                                leftSubTree=leftSubTree.parent;
                            }
                        }
                    }
                }
                temp=temp.right;
            }
            else{
                temp=temp.left;
            }
        }

        return newMap;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap newMap = new MySplayMap();

        boolean fromRight;
        SplayNode temp = head;
        SplayNode rightSubTree;
        while(temp!=null){
            if(temp.key>=fromKey){
                newMap.put(temp.key,temp.value);
                rightSubTree=temp.right;
                if(rightSubTree!=null){
                    rightSubTree=getMostLeftChild(rightSubTree);
                    fromRight=false;
                    while(rightSubTree!=temp){
                        if(fromRight){
                            fromRight=!isLeftChild(rightSubTree);
                            rightSubTree=rightSubTree.parent;
                        }
                        else{
                            newMap.put(rightSubTree.key,rightSubTree.value);

                            if(rightSubTree.right!=null){
                                rightSubTree=getMostLeftChild(rightSubTree.right);
                            }
                            else{
                                fromRight=!isLeftChild(rightSubTree);
                                rightSubTree=rightSubTree.parent;
                            }
                        }
                    }
                }
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }

        return newMap;
    }

    @Override
    public Integer firstKey() {
        SplayNode temp = head;
        if (temp!=null){
            while(temp.left!=null){
                temp=temp.left;
            }
            return temp.key;
        }
        return null;
    }

    @Override
    public Integer lastKey() {
        SplayNode temp = head;
        if(temp!=null){
            while(temp.right!=null){
                temp=temp.right;
            }
            return temp.key;
        }
        return null;
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
        SplayNode temp=head;
        while(temp!=null){
            if(Objects.equals(key,temp.key)){
                splay(temp);
                return true;
            }
            if((Integer)key<temp.key){
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        SplayNode temp=getMostLeftChild(head);

        boolean fromRight=false;
        while(temp!=null){
            if(fromRight){
                fromRight=!isLeftChild(temp);
                temp=temp.parent;
            }
            else{
                if(Objects.equals(value,temp.value)){
                    splay(temp);
                    return true;
                }

                if(temp.right!=null){
                    temp=getMostLeftChild(temp.right);
                }
                else{
                    fromRight=!isLeftChild(temp);
                    temp=temp.parent;
                }

            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        SplayNode temp = head;

        while(temp!=null){
            if(Objects.equals(key,temp.key)){
                splay(temp);
                return head.value;
            }
            if((Integer)key<temp.key){
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }

        return null;
    }

    @Override
    public String put(Integer key, String value) {
        size++;
        if(head!=null){
            SplayNode temp = head;
            SplayNode prev=temp;
            while(temp!=null){
                if(Objects.equals(temp.key,key)){
                    String prevValue=temp.value;
                    size--;
                    temp.value=value;
                    splay(temp);
                    return prevValue;
                }
                prev=temp;
                if(key<temp.key){
                    temp=temp.left;
                }
                else{
                    temp=temp.right;
                }
            }

            if(key<prev.key){
                prev.left=new SplayNode(key,value,prev);
                temp=prev.left;
            }
            else{
                prev.right=new SplayNode(key,value,prev);
                temp=prev.right;
            }
            splay(temp);
            return null;
        }
        else{
            head=new SplayNode(key,value,null);
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        SplayNode temp = head;
        if (temp==null){
            return null;
        }
        boolean flag=true;
        while(temp!=null&&flag){
            if(Objects.equals(temp.key,key)){
                splay(temp);
                flag=false;
            }
            else if((Integer)key<temp.key){
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }

        if(!flag){
            String prevValue=head.value;
            temp=head.right;
            size--;
            if(temp!=null){
                temp=getMostLeftChild(temp);
                splay(temp);
                // when the node is splayed, it's guaranteed that the
                // right child of the element that is currently node
                // (which'll become the left child of the new root)
                // will be null, as the left child of the node that
                // is splayed is null. that way the element can be
                // easily removed this way:
                head.left=head.left.left;
                head.left.parent=head;

            }
            else{
                head=head.left;
                head.parent=null;
            }
            return prevValue;
        }
        else{
            return null;
        }
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        head=null;
        size=0;
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
