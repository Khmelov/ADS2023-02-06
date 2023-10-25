package by.it.group251004.savenok.lesson10;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {
    public static void main(String[] args) {
        MyLinkedList<Integer> deq = new MyLinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(20);
        deque.add(10);
        deque.add(10);
        deq.add(20);
        deq.add(10);
        deq.add(10);
        System.out.println(deque);
        System.out.println(deq);
    }

}