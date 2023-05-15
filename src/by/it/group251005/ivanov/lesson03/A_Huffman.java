package by.it.group251005.ivanov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {
    abstract class Node implements Comparable<Node> {
        private final int frequence; //частота символов

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
        char symbol;

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    static private Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String str = scanner.next();

        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < str.length(); i++)
            count.put(str.charAt(i), count.get(str.charAt(i)) != null ? count.get(str.charAt(i)) + 1 : 1);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (char ch: count.keySet())
            priorityQueue.add(new LeafNode(count.get(ch), ch));

        while (priorityQueue.size() > 1) {
            InternalNode parentNode = new InternalNode(priorityQueue.remove(), priorityQueue.remove());
            priorityQueue.add(parentNode);
        }

        priorityQueue.element().fillCodes("");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            stringBuilder.append(codes.get(str.charAt(i)));

        return stringBuilder.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}
