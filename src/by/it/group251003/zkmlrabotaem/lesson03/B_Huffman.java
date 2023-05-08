package by.it.group251003.zkmlrabotaem.lesson03;

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
    private String stringFromLeftChar(String str, Character ch) {
        StringBuilder res = new StringBuilder("");
        int i = 0;
        while ((i < str.length()) && (str.charAt(i) != ' '))
            i++;

        ++i;
        while ((i < str.length())) {
            res.append(str.charAt(i));
            i++;
        }
        return res.toString();
    }
    String decode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer lengthEncodedString = scanner.nextInt();

        Map<String, Character> codes = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            String str = scanner.nextLine();
            Character ch = str.charAt(0);
            codes.put(stringFromLeftChar(str, ' '), str.charAt(0));
        }

        String encodedString = scanner.nextLine();
        scanner.close();

        StringBuilder currCode = new StringBuilder("");
        StringBuilder originalString = new StringBuilder("");
        int i = 0;
        while ((i < lengthEncodedString)) {
            currCode.append(encodedString.charAt(i));
            if (codes.containsKey(currCode.toString())) {
                originalString.append(codes.get(currCode.toString()));
                currCode.setLength(0);
            }
            i++;
        }
        return originalString.toString(); //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        by.it.group251003.zkmlrabotaem.lesson03.B_Huffman instance = new by.it.group251003.zkmlrabotaem.lesson03.B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}