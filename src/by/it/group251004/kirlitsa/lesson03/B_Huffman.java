package by.it.group251004.kirlitsa.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        Node node = getNode(count, scanner);
        String input = scanner.nextLine();
        //Node tempNode = node;
        //String temp = "";
        int i = 0;
        while (i < input.length())
            i = node.getEncode(result, node, "", input, i);
            /*if (tempNode.content != null && tempNode.content.equals(temp)) {
                result.append(tempNode.letter);
                temp = "";
                tempNode = node;
            } else {
                tempNode = (input.charAt(i) == '1') ? tempNode.right : tempNode.left;
                temp += input.charAt(i);
                i++;
                result.append((i == input.length()) ? tempNode.letter : "");
            }*/
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    public Node getNode(Integer count, Scanner scanner) {
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] strArr = scanner.nextLine().split(": ");
            list.add(new Node(strArr[1], strArr[0].charAt(0)));
        }
        while (list.size() > 1) {
            Node parent = new Node(list.remove(list.size() - 2), list.remove(list.size() - 1));
            list.add(parent);
        }
        return list.get(0);
    }

    private class Node {
        String content;
        char letter;
        Node left;
        Node right;
        Node (String content, char letter) {
            this.content = content;
            this.letter = letter;
        }
        Node (Node left, Node right) {
            this.left = left;
            this.right = right;
        }
        int getEncode (StringBuilder result, Node node, String temp, String input, int i) {
            if (node.content != null && node.content.equals(temp)) {
                result.append(node.letter);
                return i;
            } else
                i = (input.charAt(i) == '1') ? node.right.getEncode(result, node.right, temp + input.charAt(i), input, ++i) : node.left.getEncode(result, node.left,temp + input.charAt(i), input, ++i);
            return i;
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
