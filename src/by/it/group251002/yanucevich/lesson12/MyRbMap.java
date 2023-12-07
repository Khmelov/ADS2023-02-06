package by.it.group251002.yanucevich.lesson12;

import java.util.*;


class RbNode{
    public Integer key;
    public String value;
    public RbNode left;
    public RbNode right;
    public RbNode parent;
    public Boolean removed;
    public boolean color;
    RbNode(Integer key, String value, RbNode parent, boolean color){
        this.key=key;
        this.value=value;
        this.left=null;
        this.right=null;
        this.parent=parent;
        this.removed=false;
        this.color=color;
    }
}

public class MyRbMap implements SortedMap<Integer,String>{

    private final boolean COLOR_BLACK=true;
    private final boolean COLOR_RED = !COLOR_BLACK;
    private final RbNode Nullode= new RbNode(null,null,null,COLOR_BLACK);
    private RbNode head=Nullode;
    private int size=0;

    private RbNode uncleOf(RbNode node){
        RbNode parentNode=  node.parent;
        if (parentNode.left==node){
            return parentNode.right;
        }
        else{
            return parentNode.left;
        }
    }

    private void completeRot(RbNode node, RbNode child){
        child.parent=node.parent;
        node.parent=child;
        if (child.parent!=Nullode){
            if (child.parent.left==node){
                child.parent.left=child;
            }
            else{
                child.parent.right=child;
            }
        }
        else{
            head=child;
        }
    }
    private void RRotate(RbNode node){
        RbNode temp=node.left;
        node.left=temp.right;
        temp.right=node;
        if(node.left!=Nullode){
            node.left.parent=node;
        }
        completeRot(node,temp);

    }
    private void LRotate(RbNode node){
        RbNode temp=node.right;
        node.right=temp.left;
        temp.left=node;
        if(node.right!=Nullode){
            node.right.parent=node;
        }
        completeRot(node,temp);

    }

    private void LRRotate(RbNode node){
        LRotate(node.left);
        RRotate(node);
    }
    private void RLRotate(RbNode node){
        RRotate(node.right);
        LRotate(node);
    }

    boolean isLeftChild(RbNode node){
        if (node.parent!=Nullode){
            return node.parent.left==node;
        }
        return false;
    }
    RbNode getMostLeftChild(RbNode node){
        while (node.left!=Nullode){
            node=node.left;
        }
        return node;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String delimiter="";

        boolean fromRight=false;
        RbNode temp=head;
        temp=getMostLeftChild(temp);
        while(temp!=Nullode){
            if(fromRight){
                fromRight=!isLeftChild(temp);
                temp=temp.parent;
            }
            else{
                if (!temp.removed){
                    sb.append(delimiter).append(temp.key).append("=").append(temp.value);
                    delimiter=", ";
                }
                if(temp.right!=Nullode){
                    temp=getMostLeftChild(temp.right);
                }
                else{
                    fromRight=!isLeftChild(temp);
                    temp=temp.parent;
                }
            }
        }

        sb.append("}");
        return sb.toString();

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
        MyRbMap newMap = new MyRbMap();
        RbNode temp = head;
        RbNode leftSubTree;
        boolean fromRight;
        while(temp!=Nullode){
            if(temp.key<toKey){
                newMap.put(temp.key,temp.value);
                leftSubTree=temp.left;
                if(leftSubTree!=Nullode){
                    fromRight=false;
                    leftSubTree=getMostLeftChild(leftSubTree);
                    while(leftSubTree!=temp){
                        if(fromRight){
                            fromRight=!isLeftChild(leftSubTree);
                            leftSubTree=leftSubTree.parent;
                        }
                        else{
                            if(!leftSubTree.removed){
                                newMap.put(leftSubTree.key,leftSubTree.value);
                            }
                            if(leftSubTree.right!=Nullode){
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
        MyRbMap newMap = new MyRbMap();
        RbNode temp=head;
        RbNode rightSubTree;
        boolean fromRight;
        while(temp!=Nullode){
            if(temp.key>=fromKey){
                newMap.put(temp.key,temp.value);
                rightSubTree=temp.right;
                if(rightSubTree!=Nullode){
                    fromRight=false;
                    rightSubTree=getMostLeftChild(rightSubTree);
                    while(rightSubTree!=temp){
                        if(fromRight){
                            fromRight=!isLeftChild(rightSubTree);
                            rightSubTree=rightSubTree.parent;
                        }
                        else{
                            if(!rightSubTree.removed){
                                newMap.put(rightSubTree.key, rightSubTree.value);
                            }
                            if(rightSubTree.right!=Nullode){
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
        RbNode temp=head;
        if (temp!=Nullode){
            temp=getMostLeftChild(temp);
            return temp.key;
        }
        return null;
    }

    @Override
    public Integer lastKey() {
        RbNode temp=head;
        if(temp!=Nullode){
            while(temp.right!=Nullode){
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
        RbNode temp=head;
        while(temp!=Nullode){
            if(Objects.equals(key,temp.key)){
                return !temp.removed;
            } else if ((Integer)key<temp.key) {
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

        RbNode temp=head;
        if (temp==Nullode){
            return false;
        }
        // setting up the starting value of the traverse
        temp=getMostLeftChild(temp);
        boolean fromRight=false;
        while (temp!=Nullode){
            if(fromRight){
                fromRight=!isLeftChild(temp);
                temp=temp.parent;
            }
            else{
                if((Objects.equals(value,temp.value))&&(!temp.removed)){
                    return true;
                }
                if(temp.right!=Nullode){
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
        RbNode temp=head;
        while(temp!=Nullode){
            if (Objects.equals(key,temp.key)){
                if(temp.removed){
                    return null;
                }
                else{
                    return temp.value;
                }
            } else if ((Integer)key<temp.key) {
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }
        return null;
    }


    private void adjustTree(RbNode node){
        boolean finished=false;
        while (!finished){
            if (node.parent.color==COLOR_RED){
                RbNode uncle = uncleOf(node);
                if (uncle.color==COLOR_RED){
                    node.parent.color=COLOR_BLACK;
                    uncle.color=COLOR_BLACK;
                    node=node.parent.parent;
                    if(node!=head){
                        node.color=COLOR_RED;
                    }
                    else{
                        finished=true;
                    }
                }
                else{
                    RbNode father=node.parent;
                    RbNode grandpa=father.parent;
                    boolean nodeIsLeftChild=father.left==node,
                            dadIsLeftChild=grandpa.left==father;
                    if(nodeIsLeftChild){
                        if(dadIsLeftChild){
                            RRotate(grandpa);
                            grandpa.color=COLOR_RED;
                            father.color=COLOR_BLACK;
                        }
                        else{
                            RLRotate(grandpa);
                            node.color=COLOR_BLACK;
                            grandpa.color=COLOR_RED;
                        }
                    }
                    else{
                        if(dadIsLeftChild){
                            LRRotate(grandpa);
                            node.color=COLOR_BLACK;
                            grandpa.color=COLOR_RED;
                        }
                        else{
                            LRotate(grandpa);
                            grandpa.color=COLOR_RED;
                            father.color=COLOR_BLACK;
                        }
                    }
                    finished=true;
                }

            }
            else{
                finished=true;
            }
        }

    }
    @Override
    public String put(Integer key, String value) {
        RbNode temp = head;
        size++;
        if (temp==Nullode){
            Nullode.left=Nullode;
            Nullode.right=Nullode;
            head=new RbNode(key,value,Nullode,COLOR_BLACK);
            head.left=Nullode;
            head.right=Nullode;
            return null;
        }
        else {
            RbNode prev=temp;
            while(temp!=Nullode){
                if (Objects.equals(temp.key, key)) {
                    String prevValue = temp.value;
                    if(temp.removed){
                        prevValue=null;
                        temp.removed=false;
                        size++;
                    }
                    size--;
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
            temp = new RbNode(key,value,prev,COLOR_RED);
            if (key<prev.key){
                prev.left=temp;
            }
            else{
                prev.right=temp;
            }
            temp.left=Nullode;
            temp.right=Nullode;
            adjustTree(temp);
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        RbNode temp=head;
        while(temp!=Nullode){
            if(Objects.equals(key,temp.key)){
                if(!temp.removed){
                    temp.removed=true;
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
        head=Nullode;
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