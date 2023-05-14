package by.it.group251004.shot.lessons.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    private class Node {
        String data;
        char letter;
        Node left;
        Node right;
        Node (String data, char letter) {
            this.data = data;
            this.letter = letter;
        }
        Node (Node left, Node right) {
            this.left = left;
            this.right = right;
        }
        int encryptStr (StringBuilder result, Node node, String temp, String input, int i) {
            if (node.data != null && node.data.equals(temp)) {
                result.append(node.letter);
                return i;
            } else
                i = (input.charAt(i) == '1') ? node.right.encryptStr(result, node.right, temp + input.charAt(i), input, ++i) : node.left.encryptStr(result, node.left,temp + input.charAt(i), input, ++i);
            return i;
        }
    }

    public Node takeNodeScan(Integer count, Scanner scan) {
        ArrayList<B_Huffman.Node> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] strArr = scan.nextLine().split(": ");
            list.add(new Node(strArr[1], strArr[0].charAt(0)));
        }
        while (list.size() > 1) {
            Node parent = new Node(list.remove(list.size() - 2), list.remove(list.size() - 1));
            list.add(parent);
        }
        return list.get(0);
    }

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        Node node = takeNodeScan(count, scanner);
        String input = scanner.nextLine();
        int i = 0;
        while (i < input.length())
            i = node.encryptStr(result, node, "", input, i);
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
