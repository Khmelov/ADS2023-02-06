package by.it.group251001.krivitsky.lesson11;

import java.util.Collection;

public class MyLinkedList<E> {
    private class Node<E>{
        E data;
        Node<E> next;
        Node(E data){
            this.data = data;
            next = null;
        }
    }

    private Node<E> head;
    private int size = 0;

    MyLinkedList(){
        head = null;
        size = 0;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> curr = head;
        while (curr != null){
            if (curr.next == null){
                sb.append(curr.data);
            }
            else{
                sb.append(curr.data+", ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }
    public void add(E data){
        Node<E> temp = new Node<>(data);
        temp.next = head;
        head = temp;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E remove(){
        E temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    public boolean remove(Object o){
        Node<E> curr = head;
        Node<E> prev = null;
        while (curr != null){
            if (curr.data.equals(o)){
                if (prev == null){
                    head = head.next;
                }
                else{
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public boolean contains(Object o){
        Node<E> temp = head;
        while (temp != null){
            if (temp.data.equals(o)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void clear(){
        Node<E> curr;
        while (head != null){
            curr = head;
            curr.data = null;
            head = head.next;
            curr.next = null;
        }
        size = 0;
    }

    public int retainAll(Collection<?> c){
        int count = 0;
        Node<E> curr = head;
        Node<E> prev = null;
        while (curr != null){
            if (!c.contains(curr.data)){
                if (prev == null){
                    head.data = null;
                    curr = head.next;
                    head = head.next;
                }
                else{
                    curr.data = null;
                    prev.next = curr.next;
                    curr.next = null;
                    curr = prev.next;
                }
                size--;
                count++;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        return count;
    }
}
