package by.it.group251004.khanenko.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class A_Huffman {

    abstract class Node implements Comparable<Node> {
        final int frequence;
        abstract void fillCodes(String code);

        private Node(int frequence) {
            this.frequence = frequence;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }
    private class InternalNode extends Node {
        Node left;
        Node right;

        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }

    }

    private class LeafNode extends Node {
        //лист
        Object symbol;

        LeafNode(int frequence, Object symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    static private Map<Object, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();
        int amount = 0;
        Map<Character, Integer> count = new HashMap<>();
        for (char j = 'a'; j < 'e'; j++) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == j) {
                    amount++;
                }
            }
            count.put(j, amount);
            amount = 0;
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for(char elem : count.keySet()) {
            priorityQueue.add(new LeafNode(count.get(elem), elem));
        }
        while (priorityQueue.size() != 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }
        priorityQueue.element().fillCodes("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            sb.append(codes.get(s.charAt(i)));

        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Object, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}
