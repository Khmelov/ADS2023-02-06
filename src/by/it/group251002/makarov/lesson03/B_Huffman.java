package by.it.group251002.makarov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        Map<String,Character> codes = new TreeMap<>();//храним строку и ее код
        for (int i = 0; i<count;i++) {
            String line = scanner.next();//считали строку
            Character letter = line.charAt(0);//считали символ
            String code = scanner.next();//считали код этого символа
            codes.put(code, letter);//записали инфу в map
        }
        String encline = scanner.next();//строка которую надо декодировать
        String answer = new String();//строка с результатом декодирования
        String encletter = new String();//текущий символ
        for (int j = 0; j<length; j++){//пока не закончатся символы в строке которую нужно декодировать
            char symb = encline.charAt(j);//symb содержит текущий символ строки которую мы декодируем
            encletter+=symb;// записали символ в переменную которая несет текущий код декодируемого символа
            if (symb=='0'||encletter.length()==count-1){//сравниваем символ с 0 (с 0 начинается следующ. ) и длину сравниваем с максимальной длиной кода симовла
                answer+=codes.get(encletter);//записываем в переменную которая содержит ответ
                encletter="";//обнуляем переменную которая содержит код 1 символа
            }

        }

        result= new StringBuilder(answer);

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
