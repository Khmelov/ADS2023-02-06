package by.it.group251002.verbol.lesson09;

public class Node<E> {
    E value;
    Node<E> next;

    Node(E value) {
        this.value = value;
        this.next = null;
    }

    Node() {
        this.value = null;
        this.next = null;
    }
}
