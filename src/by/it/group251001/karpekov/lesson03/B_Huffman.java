package by.it.group251001.karpekov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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
        StringBuilder tempstr2 = new StringBuilder();
        StringBuilder resstr = new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение

        Map<String, Character> codesMap = new HashMap<>();
        for (int i = 0; i < count; i++)
        {
            String tempstr = scanner.next();
            char symb = tempstr.charAt(0);
            String symbcode = scanner.next();
            codesMap.put(symbcode, symb);
        }

        String codeItself = scanner.next();
        char[] code = codeItself.toCharArray();
        for (char charAtPos : code)
        {
           tempstr2.append(charAtPos);
           if (codesMap.containsKey(tempstr2.toString()))
           {
              resstr.append(codesMap.get(tempstr2.toString()));
              tempstr2.setLength(0);
           }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return resstr.toString(); //01001100100111
//        StringBuilder sb = new StringBuilder();
//        StringBuilder temp = new StringBuilder();
//        Map<String, Character> decoder = new HashMap<>();
//
//        for (int i = 0; i < count; i++) {
//            String tempStr = scanner.next();
//            char value = tempStr.charAt(0);
//            String key = scanner.next();
//            decoder.put(key, value);
//        }
//        String str = scanner.next();
//        char[] string = str.toCharArray();
//
//        for (char pos : string) {
//            temp.append(pos);
//
//            if (decoder.containsKey(temp.toString())) {
//                sb.append(decoder.get(temp.toString()));
//                temp.setLength(0);
//            }
//        }
//
//        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
//        return sb.toString(); //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
