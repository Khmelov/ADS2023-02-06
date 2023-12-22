package by.it.group251004.Ivannikova.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {

    //Изучите классы Node InternalNode LeafNode
    abstract class Node implements Comparable<Node> {
        //абстрактный класс элемент дерева
        //(сделан abstract, чтобы нельзя было использовать его напрямую)
        //а только через его версии InternalNode и LeafNode
        private final int frequence; //частота символов

        //генерация кодов (вызывается на корневом узле
        //один раз в конце, т.е. после построения дерева)
        abstract void fillCodes(String code);

        //конструктор по умолчанию
        private Node(int frequence) {
            this.frequence = frequence;
        }

        //метод нужен для корректной работы узла в приоритетной очереди
        //или для сортировок
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }


    private class InternalNode extends Node {
        //внутренный узел дерева
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
        String s = scanner.next();
        scanner.close();
        char[] sArr = s.toCharArray();
        Map<Character, Integer> count = new HashMap<>();

        for (char c : sArr) {
            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                count.put(c, 1);
            }
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int i = 0;
        while (i < sArr.length) {
            if (count.containsKey(sArr[i])) {
                LeafNode currNode = new LeafNode(count.get(sArr[i]), sArr[i]);
                priorityQueue.add(currNode);
                count.remove(sArr[i]);
            }
            i++;
        }

        int lastNum = priorityQueue.size() - 1;
        for (i = 0; i < lastNum; i++) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            assert left != null;
            assert right != null;
            InternalNode parentNode = new InternalNode(left, right);
            priorityQueue.add(parentNode);
        }

        if (lastNum == 0) { //для 1 буквы
            Node left = priorityQueue.poll();
            LeafNode right = new LeafNode(0, '0');
            InternalNode parentNode = new InternalNode(left, right);
            priorityQueue.add(parentNode);
        }

        StringBuilder encryptedString = new StringBuilder();
        Node root = priorityQueue.poll();
        assert root != null;
        root.fillCodes("");

        if (lastNum == 0) codes.remove('0');

        for (i = 0; i < sArr.length; i++) {
            encryptedString.append(codes.get(sArr[i]));
        }

        return encryptedString.toString();
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
