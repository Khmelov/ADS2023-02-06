package by.it.group251002.voevoda.lesson11;

public class SLL<E> {
    private Node<E> head, tail;
    private int size;

    public void setHead(Node<E> n) {
        head = n;
    }

    public Node<E> getHead() {
        return head;
    }

    public void setTail(Node<E> n) { tail = n; }

    public void setSize(int size) { this.size = size; }

    public int getSize() { return size; }

    public void append(E value) {
        Node<E> second = new Node<>(value);
        if (size == 0) {
            head = second;
            tail = second;
        } else {
            tail.setNext(second);
            tail = tail.getNext();
        }
        ++size;
    }

    public void append(E value, int index) {
        Node<E> second = new Node<>(value, index);
        if (size == 0) {
            head = second;
            tail = second;
        } else {
            Node<E> ptr;
            for (ptr = head; ptr.next != null; ptr = ptr.next);
            ptr.next = second;
        }
        ++size;
    }

    public boolean remove(E e) {
        if (size == 0) {
            return false;
        }

        if (head.getValue().equals(e)) {
//            head = head.getNext();
            setHead(head.getNext());
            --size;
            return true;
        }

        for (Node<E> ptr = head; ptr.getNext() != null; ptr = ptr.getNext()) {
            if (ptr.getNext().getValue().equals(e)) {
                ptr.setNext(ptr.getNext().getNext());
                --size;
                return true;
            }
        }
        return false;
    }

    public boolean contains(E e) {
        for (Node<E> ptr = head; ptr != null; ptr = ptr.getNext()) {
            if (ptr.getValue().equals(e)) {
                return true;
            }
        }
        return false;
    }

}
