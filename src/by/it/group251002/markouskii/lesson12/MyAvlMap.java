package by.it.group251002.markouskii.lesson12;

import java.security.Key;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
class Node{
    public int Key;
    public int Height;
    public boolean Exist;
    public String Value;
    public Node Prev;
    public Node Left;
    public Node Right;
    public Node(int K, String Val, Node P) {
        Key = K;
        Value = Val;
        Prev = P;
        Height = 0;
        Left = null;
        Right = null;
        Exist=true;
    }
}
public class MyAvlMap<Integer,String> implements Map<Integer, String>{
    private int size=0;
    private Node head=null;

    public int NHeight(Node pos){
        return (pos!=null ? pos.Height : -1);
    }
    public Node GetLeft(Node pos) {
        Node left=pos;
        if (left==null) return null;
        while (left.Left!=null) left=left.Left;
        return left;
    }
    public Node GetRight(Node pos) {
        Node right=pos;
        if (right==null) return null;
        while (right.Right!=null) right=right.Right;
        return right;
    }
    @Override
    public java.lang.String toString() {
        StringBuilder sb=new StringBuilder("{");
        java.lang.String delimiter = "";
        Node pos=GetLeft(head);
        Node end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && pos.Exist) {sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
            delimiter = ", ";}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && pos.Exist) sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
        sb.append("}");
        return sb.toString();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean containsKey(Object key) {
        Node pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        return (pos!=null && pos.Exist);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if ((int)key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null && pos.Exist) return (String) pos.Value;
        return null;
    }

    private String ReplaceValue(Integer key, String value){
        Node pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null) {
            if (!pos.Exist) {
                pos.Exist=true;
                String answer=(String) pos.Value;
                pos.Value=(java.lang.String)value;
                size++;
                return null;
            }
            String answer=(String) pos.Value;
            pos.Value=(java.lang.String)value;
            return answer;
        }
        return null;
    }
    private void RebalanceTree(Node start){
        //Rotate side 0 - left, 1 - right
        Node pos=start.Prev;
        int rotateStart=0;
        int rotatePos=0;
        if (pos!=null){
        rotateStart=(pos.Left!=null && pos.Left.equals(start)? 0 : 1);
        rotatePos=(pos.Left!=null && pos.Left.equals(start)? 0 : 1);}
        while (pos!=null) {
            int Left=NHeight(pos.Left)+1;
            int Right=NHeight(pos.Right)+1;
            if (Left-Right>1 || Left-Right<-1) {
                if (rotateStart==0 && rotatePos==0) pos=RightRotate(pos);
                if (rotateStart==1 && rotatePos==1) pos=LeftRotate(pos);
                if (rotateStart==1 && rotatePos==0) pos=LeftRightRotate(pos);
                if (rotateStart==0 && rotatePos==1) pos=RightLeftRotate(pos);
            }
            pos.Height=Math.max(NHeight(pos.Left),NHeight(pos.Right))+1;
            rotateStart=rotatePos;
            if (pos.Prev!=null) rotatePos=(pos.Prev.Left!=null && pos.Prev.Left.equals(pos)? 0 : 1);
            pos=pos.Prev;
        }
        return;
    }
    private Node LeftRightRotate(Node P){
        P.Left=LeftRotate(P.Left);
        P=RightRotate(P);
        return P;
    }
    private Node RightLeftRotate(Node P){
        P.Right=RightRotate(P.Right);
        P=LeftRotate(P);
        return P;
    }
    private Node LeftRotate(Node P){
        Node R=P.Right;
        if (R.Left!=null)R.Left.Prev=P;
        P.Right=(R!=null?R.Left : null);
        if (R!=null) R.Left=P;
        P.Height=Math.max(NHeight(P.Left),NHeight(P.Right))+1;
        if (R!=null) R.Height=Math.max(NHeight(R.Right),P.Height)+1;
        if (P.Prev!=null) {
            if (P.Prev.Left!=null && P.Prev.Left.equals(P)) P.Prev.Left=R; else P.Prev.Right=R;}
        else head=R;
        if (R!=null) R.Prev=P.Prev;
        P.Prev=R;
        return R;
    }

    private Node RightRotate(Node P){
        Node L=P.Left;
        if (L.Right!=null)L.Right.Prev=P;
        P.Left=(L!=null?L.Right : null);
        if (L!=null) L.Right=P;
        P.Height=Math.max(NHeight(P.Left),NHeight(P.Right))+1;
        if (L!=null) L.Height=Math.max(NHeight(L.Left),P.Height)+1;
        if (P.Prev!=null) {
            if (P.Prev.Left!=null && P.Prev.Left.equals(P)) P.Prev.Left=L; else P.Prev.Right=L;}
        else head=L;
        if (L!=null) L.Prev=P.Prev;
        P.Prev=L;
        return L;
    }
    @Override
    public String put(Integer key, String value) {
        int Key=(int)key;
        int oldSize=size;
        String Value=ReplaceValue(key,value);
        if (oldSize!=size) return null;
        if (Value!=null) return Value;
        size++;
        Node Prev=null;
        Node pos=head;
        while (pos!=null) {
            Prev=pos;
            if (Key>pos.Key) pos=pos.Right;
                else pos=pos.Left;
        }
        pos=new Node(Key,(java.lang.String)value,Prev);
        if (head==null) head=pos; else
        {if (Key>Prev.Key) Prev.Right=pos;
            else Prev.Left=pos;}
        RebalanceTree(pos);
        return null;
    }

    @Override
    public String remove(Object key) {
        Node pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if ((int)key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null && pos.Exist) {size--;pos.Exist=false;return (String) pos.Value;}
        return null;
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
