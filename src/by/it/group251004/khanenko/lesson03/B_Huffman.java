package by.it.group251004.khanenko.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
public class B_Huffman {
    abstract class Node implements Comparable<Node> {
        private final int frequence;

        abstract void fillCodes(String code);

        private Node(int frequence) {
            this.frequence = frequence;
        }
        public int compareTo(A_Huffman.Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    private class LeafNode extends Node {
        Object symbol;

        LeafNode(int frequence, Object symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {

        }

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }

    public class HuffmanNode {
        char symbol;
        int frequency;
        HuffmanNode left, right;

        public HuffmanNode(char symbol, int frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
        }
    }

    public HuffmanNode createTree(Map<String, Integer> map) {
        PriorityQueue pq = new PriorityQueue<>();
        for(String elem : map.keySet()) {
            pq.add(new HuffmanNode(elem.charAt(0), map.get(elem)));
        }
        while (pq.size() > 1) {
            HuffmanNode left = (HuffmanNode) pq.poll();
            HuffmanNode right = (HuffmanNode) pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', (left.frequency + right.frequency));
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        return (HuffmanNode) pq.poll();
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

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }


    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String elem = scanner.next();
            String numKey = scanner.next();
            map.put(numKey, String.valueOf(elem.charAt(0)));
        }
        String res = scanner.next();
        String str = "";
        for (int i = 0; i < length; i++){
            int ch = Integer.parseInt(String.valueOf(res.charAt(i)));
            str += ch;
            if(map.containsKey(str)) {
                result.append(map.get(str));
                str = "";
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
