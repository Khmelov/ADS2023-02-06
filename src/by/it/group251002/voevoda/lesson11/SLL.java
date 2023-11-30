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
            tail.setNext(second);
            tail = tail.getNext();
        }
        ++size;
    }

    // getPrev returns null if value is either head or there are no such value
    public Node<E> getPrev(E value) {
        if (size == 0) {
            return null;
        }

        int counter = 0;
        Node<E> prev = null;
        for (Node<E> ptr = head; ptr != null; ptr = ptr.getNext()) {
            switch (counter) {
                case 0:
                    ++counter;
                    break;
                case 1:
                    prev = head;
                    ++counter;
                    break;
                case 2:
                    prev = prev.getNext();
                    break;
            }

            if (ptr.getValue().equals(value)) {
                return prev;
            }
        }
        return null;
    }

    public boolean contains(E e) {
        for (Node<E> ptr = head; ptr != null; ptr = ptr.getNext()) {
            if (ptr.getValue().equals(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHead(E e) {
        if (size == 0 || head == null) {
            return false;
        }

        return head.getValue().equals(e);
    }
}
