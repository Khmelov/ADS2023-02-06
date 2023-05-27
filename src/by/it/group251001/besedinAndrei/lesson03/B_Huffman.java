package by.it.group251001.besedinAndrei.lesson03;

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

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        // прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        // тут запишите ваше решение

        Node huffmanTree = NewNode('$');

        for (int i = 0; i < count; i++) {

            String z = scanner.next();
            String code = scanner.next();
            Node temp = huffmanTree;

            for (int j = 0; j < code.length() - 1; j++) {
                if (code.charAt(j) == '0') {
                    if (temp.left == null)
                        temp.left = NewNode('$');
                    temp = temp.left;
                    
                } else {
                    if (temp.right == null)
                        temp.right = NewNode('$');
                    temp = temp.right;
                }
            }

            if (code.charAt(code.length() - 1) == '0')
                temp.left = NewNode(z.charAt(0));
            else
                temp.right = NewNode(z.charAt(0));
        }
        String z = scanner.next();
        Node temp = huffmanTree;
        result = new StringBuilder();

        for (int i = 0; i < z.length(); i++) {
            if (z.charAt(i) == '0')
                temp = temp.left;
            else
                temp = temp.right;

            if ((temp.left == null) && (temp.right == null)) {
                result.append(temp.chr);
                temp = huffmanTree;

            }
        }

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); // 01001100100111
    }

    public class Node {
        Node left;
        Node right;
        Character chr;
    }

    Node NewNode(Character chr) {
        Node a = new Node();
        a.chr = chr;
        a.left = null;
        a.right = null;
        return a;
    }

    void writeTree(Node a) {
        if (a.left != null) {
            System.out.print('0');
            writeTree(a.left);
        }
        System.out.print(a.chr);
        if (a.right != null) {
            System.out.print('1');
            writeTree(a.right);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }

}
