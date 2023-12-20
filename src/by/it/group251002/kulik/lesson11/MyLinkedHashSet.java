package by.it.group251002.kulik.lesson11;

import java.sql.Struct;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class MyDLLNode<E>{
    public MyDLLNode next;
    public MyDLLNode prevAdd,nextAdd;
    public E value;
    public MyDLLNode(E value){
        this.value=value;
        this.next=null;
        this.nextAdd=null;
        this.prevAdd=null;
    }

}
public class MyLinkedHashSet<E> implements Set<E> {

    private int size = 0;
    private int capacity = 8;
    private MyDLLNode first=null;
    private MyDLLNode last=null;
    private MyDLLNode<E>[] arr = (MyDLLNode<E>[]) new MyDLLNode[capacity];

    private int hashIndex(Object o){
        return o.hashCode()%capacity;
    }
    private void resize(){
        capacity= capacity*3/2+1;
        arr= new MyDLLNode[capacity];
        size=0;
        MyDLLNode temp = first;
        while(temp!=null){
            addWithoutList((E)temp.value);
            temp=temp.nextAdd;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimiter = "";
        MyDLLNode temp=first;
        while(temp!=null){
            sb.append(delimiter).append(temp.value.toString());
            delimiter=", ";
            temp=temp.nextAdd;
        }
        sb.append("]");
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
    public boolean contains(Object o) {
        int index = hashIndex(o);
        MyDLLNode temp = arr[index];
        if(temp!=null){
            while(temp!=null){
                if(Objects.equals(temp.value,o)){
                    return true;
                }
                else{
                    temp=temp.next;
                }
            }
            return false;
        }
        else{
            return false;
        }
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

    private MyDLLNode addWithoutList(E e){
        int index = hashIndex(e);
        MyDLLNode temp = arr[index];
        if(temp!=null){
            while(temp.next!=null){
                if(!Objects.equals(e,temp.value)){
                    temp=temp.next;
                }
                else{
                    return null;
                }
            }
            if(!Objects.equals(e,temp.value)){
                temp.next=new MyDLLNode(e);
                temp=temp.next;
                size++;
                return temp;
            }
            else{
                return null;
            }
        }
        else{
            arr[index]=new MyDLLNode(e);
            size++;
            return arr[index];
        }
    }
    @Override
    public boolean add(E e) {
        if (size+1>=capacity*3/4){
            resize();
        }
        MyDLLNode temp = addWithoutList(e);
        if(temp==null){
            return false;
        }
        else{
            temp.prevAdd=last;
            // as the element was already added, the size's minimum value is 1
            if(size!=1){
                last.nextAdd=temp;
            }
            else{
                first=temp;
            }
            last=temp;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = hashIndex(o);
        MyDLLNode temp = arr[index];
        if(temp!=null){
            if(!Objects.equals(temp.value,o)){
                MyDLLNode previousElem=temp;
                temp=temp.next;
                while(temp!=null){
                    if(!Objects.equals(temp.value,o)){
                        previousElem=temp;
                        temp=temp.next;
                    }
                    else{
                        previousElem.next=temp.next;
                        if(temp.prevAdd==null){
                            first=temp.nextAdd;
                        }
                        else{
                            temp.prevAdd.nextAdd=temp.nextAdd;
                        }
                        if(temp.nextAdd==null){
                            last=temp.prevAdd;
                        }
                        else{
                            temp.nextAdd.prevAdd=temp.prevAdd;
                        }
                        size--;
                        return true;
                    }
                }
                return false;
            }
            else{
                arr[index]=temp.next;
                if(temp.prevAdd==null){
                    first=temp.nextAdd;
                }
                else{
                    temp.prevAdd.nextAdd=temp.nextAdd;
                }
                if(temp.nextAdd==null){
                    last=temp.prevAdd;
                }
                else{
                    temp.nextAdd.prevAdd=temp.prevAdd;
                }
                size--;
                return true;
            }
        }
        else{
            return false;
        }
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

    private boolean removeRoutine(Collection<?> c, boolean cond){
        int prevSize=size;
        MyDLLNode temp=first;
        MyDLLNode nextNode;
        while(temp!=null){
            if(cond==c.contains(temp.value)){
                nextNode=temp.nextAdd;
                remove(temp.value);
                temp=nextNode;
            }
            else{
                temp=temp.nextAdd;
            }
        }
        return prevSize!=size;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        return removeRoutine(c,false);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeRoutine(c,true);
    }

    @Override
    public void clear() {
        for(int i=0;i<capacity;i++){
            arr[i]=null;
        }
        size=0;
        first=null;
        last=null;
    }
}
