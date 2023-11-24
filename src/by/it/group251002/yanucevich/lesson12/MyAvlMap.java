package by.it.group251002.yanucevich.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


class AvlNode{
    public Integer key;
    public String value;
    public AvlNode left;
    public AvlNode right;
    public AvlNode parent;
    public boolean present;
    public int height;

    //
    // Note: at the lecture it was stated that the height
    // of an empty node is equal to -1 and, consequently,
    // the height of any leaf is 0. However, I tried to
    // implement a different approach and set the height
    // of an empty node to 0 and the height of each leaf
    // to 1, as seen in the constructor of AvlNode.
    //
    // To me, it seems as a more perceptible approach.
    //
    public AvlNode(Integer key, String value, AvlNode parent) {
        this.key = key;
        this.value = value;
        this.left=null;
        this.right=null;
        this.parent=parent;
        this.present=true;
        this.height=1;
    }
}

public class MyAvlMap implements Map<Integer,String> {

    AvlNode head=null;
    int size=0;

    int calcBalanceValue(AvlNode node){
        int res=0;
        if(node.left!=null){
            res+=node.left.height;
        }
        if(node.right!=null){
            res-=node.right.height;
        }
        return res;
    }

    int calcHeight(AvlNode node){
        int res=1,l=0,r=0;
        if(node.left!=null){
            l=1+node.left.height;
        }
        if(node.right!=null){
            r=1+node.right.height;
        }
        if(res<r){
            res=r;
        }
        if(res<l){
            res=l;
        }
        return res;
    }
    boolean isLeftChild(AvlNode child){
        if (child.parent!=null){
            return child.parent.left==child;
        }
        return false;
    }
    void completeRotation(AvlNode child, AvlNode node){
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
    void RRotate(AvlNode node){
        AvlNode child = node.left;
        node.left=child.right;
        if(node.left!=null){
            node.left.parent=node;
        }
        child.right=node;
        completeRotation(child,node);
    }
    void LRotate(AvlNode node){
        AvlNode child = node.right;
        node.right=child.left;
        if(node.right!=null){
            node.right.parent=node;
        }
        child.left=node;
        completeRotation(child,node);
    }

    void RLRotate(AvlNode node){
        RRotate(node.right);
        LRotate(node);
    }

    void LRRotate(AvlNode node){
        LRotate(node.left);
        RRotate(node);
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String delimiter = "";

        AvlNode temp = head;
        AvlNode prev=temp;
        boolean fromRight=false;

        if (temp!=null){
            while(temp.left!=null){
                temp=temp.left;
            }
            while(temp!=null){
                if(fromRight){
                    fromRight=!isLeftChild(temp);
                    temp=temp.parent;
                }
                else{
                    if(temp.present){
                        sb.append(delimiter).append(temp.key).append("=").append(temp.value);
                        delimiter=", ";
                    }
                    if(temp.right!=null){
                        temp=temp.right;
                        while(temp.left!=null){
                            temp=temp.left;
                        }

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
        AvlNode temp=head;
        while(temp!=null){
            if(Objects.equals(key,temp.key)){
                return temp.present;
            }
            else{
                if((Integer)key<temp.key){
                    temp=temp.left;
                }
                else{
                    temp=temp.right;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        AvlNode temp=head;
        while(temp!=null){
            if(Objects.equals(key,temp.key)){
                if(temp.present){
                    return temp.value;
                }
                else{
                    return null;
                }
            }
            else{
                if((Integer)key<temp.key){
                    temp=temp.left;
                }
                else{
                    temp=temp.right;
                }
            }
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        // returns previous value (prev, or null)
        AvlNode temp = head;
        if(temp==null){
            head= new AvlNode(key,value,null);
            size++;
            return null;
        }
        else {
            AvlNode prev=temp;
            while (temp != null) {
                if (Objects.equals(temp.key, key)) {
                    String prevValue = temp.value;
                    if(!temp.present){
                        prevValue=null;
                        temp.present=true;
                        size++;
                    }
                    temp.value = value;
                    return prevValue;
                }
                prev = temp;
                if (key<temp.key) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }

            // at this point we must insert a new node
            temp=new AvlNode(key,value,prev);
            if (key<prev.key){
                prev.left=temp;
            }
            else{
                prev.right=temp;
            }

            // recalculating heights and adjusting the tree
            boolean pFromLeft=true, ppFromLeft=true;
            int diff;
            pFromLeft=isLeftChild(temp);
            temp=temp.parent;
            while(temp!=null){
                diff=calcBalanceValue(temp);
                if(!((diff>=-1)&&(diff<=1))){
                    if(pFromLeft){
                        if(ppFromLeft){
                            RRotate(temp);
                        }
                        else{
                            LRRotate(temp);
                            temp.parent.left.height=calcHeight(temp.parent.left);
                        }
                    }
                    else{
                        if(ppFromLeft){
                            RLRotate(temp);
                            temp.parent.right.height=calcHeight(temp.parent.right);
                        }
                        else{
                            LRotate(temp);
                        }
                    }
                }
                temp.height=calcHeight(temp);
                ppFromLeft=pFromLeft;
                pFromLeft=isLeftChild(temp);
                temp=temp.parent;
            }
            size++;
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        AvlNode temp=head;
        while(temp!=null){
            if(Objects.equals(key,temp.key)){
                if(temp.present){
                    temp.present=false;
                    size--;
                    return temp.value;
                }
                else{
                    return null;
                }
            }
            else{
                if((Integer)key<temp.key){
                    temp=temp.left;
                }
                else{
                    temp=temp.right;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size=0;
        head=null;
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
