package by.it.group251002.voevoda.lesson10;

public class Node<E> {
    public Node(E e) {
        value = e;
        prev = null;
        next = null;
    }
    public E value;
    public Node<E> prev;
    public Node<E> next;
}