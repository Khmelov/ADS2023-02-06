package by.it.group251002.voevoda.lesson11;

public class Node<E> {
    private E value;
    public Node<E> next;
    private int index;

    public Node(E value) {
        this.value = value;
    }

    public Node(E value, int index) {
        this.value = value;
        this.index = index;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public int getIndex() { return index; }

}
