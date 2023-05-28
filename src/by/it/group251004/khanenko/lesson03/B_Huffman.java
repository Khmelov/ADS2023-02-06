package by.it.group251004.khanenko.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {
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
        //@Override
        public int compareTo(A_Huffman.Node o) {
            return Integer.compare(frequence, o.frequence);
        }
        //метод нужен для корректной работы узла в приоритетной очереди
        //или для сортировок
    }

    private class LeafNode extends Node {
        //лист
        Object symbol; //символы хранятся только в листах

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

      /*  @Override
        void fillCodes(String code) {
            //добрались до листа, значит рекурсия закончена, код уже готов
            //и можно запомнить его в индексе для поиска кода по символу.
            codes.put(this.symbol, code);
        }*/
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
        //внутренный узел дерева
        Node left;  //левый ребенок бинарного дерева
        Node right; //правый ребенок бинарного дерева

        //для этого дерева не существует внутренних узлов без обоих детей
        //поэтому вот такого конструктора будет достаточно
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
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
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
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
