package by.it.group251001.churavskiy.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class A_Huffman {
    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, String> codes = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (var x : count.entrySet()) {
            priorityQueue.add(new LeafNode(x.getValue(), x.getKey()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll(), right = priorityQueue.poll();

            priorityQueue.add(new InternalNode(left, right));
        }

        priorityQueue.poll().fillCodes("", codes);

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(codes.get(c));
        }

        return sb.toString();
    }

    private static abstract class Node implements Comparable<Node> {
        private final int frequency;

        private Node(int frequency) {
            this.frequency = frequency;
        }

        abstract void fillCodes(String code, Map<Character, String> codes);

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequency, o.frequency);
        }
    }

    private static class InternalNode extends Node {
        Node left;
        Node right;

        InternalNode(Node left, Node right) {
            super(left.frequency + right.frequency);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code, Map<Character, String> codes) {
            left.fillCodes(code + "0", codes);
            right.fillCodes(code + "1", codes);
        }
    }

    private static class LeafNode extends Node {
        char symbol;

        LeafNode(int frequency, char symbol) {
            super(frequency);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code, Map<Character, String> codes) {
            codes.put(this.symbol, code);
        }
    }
}
