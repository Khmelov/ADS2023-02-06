package by.it.group251001.brutskaya.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }

    String decode(File file) throws FileNotFoundException {
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        int count = Integer.parseInt(split[0]);
        int length = Integer.parseInt(split[1]);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        Node huffmanTree = new InternalNode();
        for (int i = 0; i < count; i++) {
            line = scanner.nextLine();
            split = line.split(": ");
            char symbol = split[0].charAt(0);
            String code = split[1];
            huffmanTree.addNode(symbol, code, 0);
        }

        String code = scanner.nextLine();
        StringBuilder result = new StringBuilder();
        int[] offset = new int[]{0};
        while (offset[0] < length) {
            result.append(huffmanTree.getChar(code, offset));
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    interface Node {

        void addNode(char symbol, String code, int offset);

        char getChar(String code, int[] offset);
    }

    private class InternalNode implements Node {
        //внутренный узел дерева
        Node left;  //левый ребенок бинарного дерева
        Node right; //правый ребенок бинарного дерева

        //для этого дерева не существует внутренних узлов без обоих детей
        //поэтому вот такого конструктора будет достаточно
        @Override
        public char getChar(String code, int[] offset) {
            char c = code.charAt(offset[0]++);
            if (c == '0') {
                return left.getChar(code, offset);
            } else {
                return right.getChar(code, offset);
            }
        }

        @Override
        public void addNode(char symbol, String code, int offset) {
            char c = code.charAt(offset);
            if (c == '0') {
                if (offset == code.length() - 1) {
                    if (left != null)
                        throw new IllegalArgumentException(String.format("Symbol %s has incorrect prefix %s",
                                symbol,
                                code));
                    left = new LeafNode(symbol);
                } else {
                    if (left == null)
                        left = new InternalNode();
                    left.addNode(symbol, code, offset + 1);
                }
            } else {
                if (offset == code.length() - 1) {
                    if (right != null)
                        throw new IllegalArgumentException(String.format("Symbol %s has incorrect prefix %s",
                                symbol,
                                code));
                    right = new LeafNode(symbol);
                } else {
                    if (right == null)
                        right = new InternalNode();
                    right.addNode(symbol, code, offset + 1);
                }
            }
        }
    }

    private class LeafNode implements Node {
        //лист
        char symbol; //символы хранятся только в листах

        LeafNode(char symbol) {
            this.symbol = symbol;
        }

        @Override
        public void addNode(char symbol, String code, int offset) {
            throw new IllegalArgumentException(String.format("Symbol %s has incorrect prefix %s",
                    symbol,
                    code));
        }

        @Override
        public char getChar(String code, int[] offset) {
            return symbol;
        }
    }
}
