package by.it.group251001.zhidkov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class B_Huffman {
    String decode(File file) throws FileNotFoundException
    {
        StringBuilder result = new StringBuilder();

        Scanner scanner = new Scanner(file);
        // Чтение количества различных букв и размера закодированной строки
        int count = scanner.nextInt();  // количество различных букв
        // размер закодированной строки

        HashMap<String, Character> codes = new HashMap<>(); // создание HashMap для хранения кодов букв
        scanner.nextLine(); // переход на следующую строку (пропуск пустой строки)

        // Чтение кодов букв и сохранение их в HashMap
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine(); // считывание строки с кодом
            codes.put(line.substring(3), line.charAt(0)); // сохранение кода в HashMap
        }

        String decoded = scanner.next(); // считывание закодированной строки
        StringBuilder key = new StringBuilder(); // создание StringBuilder для формирования текущего кода

        // Декодирование строки
        for (int i = 0; i < decoded.length(); i++) {
            key.append(decoded.charAt(i)); // добавление символа в текущий код

            // Проверка, содержит ли HashMap codes текущий код
            if (codes.containsKey(key.toString())) {
                result.append(codes.get(key.toString())); // добавление соответствующей буквы в результат
                key.setLength(0); // очистка текущего кода
            }
        }

        return result.toString(); // возврат результата декодирования
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}


