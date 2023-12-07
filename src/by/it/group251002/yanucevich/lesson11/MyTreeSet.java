package by.it.group251002.yanucevich.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class CNode<E extends Comparable<E>>{
    public E value;
    public boolean color;
    public CNode left;
    public CNode right;
    public CNode parent;
    public boolean deleted;

    public CNode(E value,CNode parent,boolean color){
        this.value=value;
        this.color=color;
        this.left=null;
        this.right=null;
        this.parent=parent;
        this.deleted=false;
    }

}

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    private final boolean COLOR_BLACK=true;
    private final boolean COLOR_RED = !COLOR_BLACK;
    public final CNode Nullode=new CNode(null,null,COLOR_BLACK);
    private CNode head=Nullode;
    private int size=0;

    private void stringNext(CNode node, StringBuilder sb){
        if(node!=Nullode){
            stringNext(node.left,sb);

            if(!node.deleted){
                if(sb.length()!=1){
                    sb.append(", ");
                }
                sb.append(node.value.toString());
            }

            stringNext(node.right,sb);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        stringNext(head,sb);
        sb.append("]");
        return sb.toString();
    }
    private CNode uncleOf(CNode node){
        CNode grandpa = node.parent.parent;
        if(grandpa.left==node.parent){
            return grandpa.right;
        }
        else{
            return grandpa.left;
        }
    }

    private void completeRot(CNode child, CNode dad){
        if(dad==head){
            child.parent=Nullode;
            head=child;
        }
        else{
            child.parent=dad.parent;
            if(child.parent.left==dad){
                child.parent.left=child;
            }
            else{
                child.parent.right=child;
            }
        }
        dad.parent=child;
    }
    private void LRotate(CNode child){
        CNode dad = child.parent;
        dad.right=child.left;
        child.left=dad;
        if(dad.right!=Nullode) {
            dad.right.parent=dad;
        }
        completeRot(child,dad);

    }
    private void RRotate(CNode child) {
        CNode dad = child.parent;
        dad.left=child.right;
        child.right=dad;
        if(dad.left!=Nullode){
            dad.left.parent=dad;
        }
        completeRot(child,dad);
    }

    //      0
    //     /
    //    0
    //     \
    //      0
    private void LRRotate(CNode child){
        LRotate(child);
        RRotate(child);
    }

    //    0
    //     \
    //      0
    //     /
    //    0
    private void RLRotate(CNode child){
        RRotate(child);
        LRotate(child);
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
    public boolean contains(Object o) {
        CNode temp=head;
        while(temp!=Nullode){
            if (Objects.equals(temp.value,o)){
                return !temp.deleted;
            } else if (temp.value.compareTo((E)o)<0) {
                temp=temp.right;
            }
            else{
                temp=temp.left;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    private void adjustTree(CNode node){
        if(node.parent.color==COLOR_RED){
            CNode uncle = uncleOf(node);
            if(uncle.color==COLOR_RED){
                node.parent.color=COLOR_BLACK;
                uncle.color=COLOR_BLACK;
                if(!(node.parent.parent==head)){
                    node.parent.parent.color=COLOR_RED;
                    adjustTree(node.parent.parent);
                }
            }
            else{
                boolean nodeIsLeftChild=node.parent.left==node,
                        dadIsLeftChild=node.parent.parent.left==node.parent;

                if(nodeIsLeftChild){
                    if(dadIsLeftChild){
                        RRotate(node.parent);
                        node.parent.color=COLOR_BLACK;
                        node.parent.right.color=COLOR_RED;
                    }
                    else{
                        RLRotate(node);
                        node.color=COLOR_BLACK;
                        node.left.color=COLOR_RED;
                    }
                }
                else{
                    if(dadIsLeftChild){
                        LRRotate(node);
                        node.color=COLOR_BLACK;
                        node.right.color=COLOR_RED;
                    }
                    else{
                        LRotate(node.parent);
                        node.parent.color=COLOR_BLACK;
                        node.parent.left.color=COLOR_RED;
                    }
                }
                //traverse and stop (?)
            }
        }
    }
    @Override
    public boolean add(E e) {
        CNode temp = head;
        size++;
        if(temp==Nullode){
            Nullode.left=Nullode;
            Nullode.right=Nullode;
            head=new CNode(e,Nullode,COLOR_BLACK);
            head.left=Nullode;
            head.right=Nullode;
            return true;
        }
        else {
            CNode previous=temp;
            boolean prevLeft=true;
            while (temp != Nullode) {
                if (Objects.equals(temp.value, e)) {
                    if(temp.deleted){
                        temp.deleted=false;
                        return true;
                    }
                    else{
                        size--;
                        return false;
                    }
                } else if (temp.value.compareTo(e) < 0) {
                    previous=temp;
                    prevLeft=false;
                    temp = temp.right;
                } else {
                    previous=temp;
                    prevLeft=true;
                    temp = temp.left;
                }
            }
            temp = new CNode(e, previous, COLOR_RED);
            if(prevLeft){
                previous.left=temp;
            }
            else{
                previous.right=temp;
            }
            temp.left=Nullode;
            temp.right=Nullode;
            adjustTree(temp);
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        CNode temp= head;
        while(temp!=Nullode) {
            if(Objects.equals(temp.value,o)){
                if(temp.deleted){
                    return false;
                }
                else{
                    temp.deleted=true;
                    size--;
                    return true;
                }
            }
            else if(temp.value.compareTo((E)o)<0){
                temp=temp.right;
            }
            else{
                temp=temp.left;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int prevSize=size;
        for (E e : c) {
            add(e);
        }
        return prevSize!=size;
    }

    private void retainNext(CNode node,Collection<?> c){
        if(node!=Nullode){
            if(!c.contains(node.value)&&(!node.deleted)){
                node.deleted=true;
                size--;
            }
            retainNext(node.left,c);
            retainNext(node.right,c);
        }
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        int prevSize=size;
        retainNext(head,c);
        return prevSize!=size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int prevSize=size;
        for (Object o : c) {
            remove(o);
        }
        return prevSize!=size;
    }

    @Override
    public void clear() {
        head=Nullode;
        size=0;
    }
}
