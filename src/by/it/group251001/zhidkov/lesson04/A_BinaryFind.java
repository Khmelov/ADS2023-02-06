package by.it.group251001.zhidkov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {
    int[] findIndex(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        // Создание объекта Scanner для чтения данных из входного потока

        int n = scanner.nextInt();
        // Считывание размера отсортированного массива

        int[] a = new int[n];
        // Создание массива a размером n для хранения элементов отсортированного массива

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        // Циклом for считываются элементы отсортированного массива и записываются в массив a

        int k = scanner.nextInt();
        // Считывание размера массива индексов

        int[] result = new int[k];
        // Создание массива result размером k для хранения найденных индексов

        for (int i = 0; i < k; i++) {
            result[i] = -1;
            // Инициализация элемента result с -1

            int value = scanner.nextInt();
            // Считывание значения value

            int low = 0;
            int high = a.length - 1;
            // Инициализация переменных low и high для определения границ текущего диапазона поиска в массиве a

            boolean isFound = false;
            // Инициализация переменной isFound как false

            int middle = 0;
            // Инициализация переменной middle

            while (low <= high && !isFound) {
                middle = low + (high - low) / 2;
                // Определение середины текущего диапазона

                if (value < a[middle]) {
                    high = middle - 1;
                    // Если значение value меньше элемента в середине, обновление переменной high
                } else if (value > a[middle]) {
                    low = middle + 1;
                    // Если значение value больше элемента в середине, обновление переменной low
                } else {
                    result[i] = middle;
                    isFound = true;
                    // Если значение value равно элементу в середине, установка найденного индекса и установка isFound в true
                }
            }
            if (isFound)
                result[i] = middle + 1;
            else
                result[i] = -1;
            // Обновление значения элемента result в соответствии с найденным индексом
        }

        return result;
        // Возвращение массива индексов result
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
// Получение пути к текущей директории и формирование пути к файлу данных
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
// Создание входного потока данных для чтения из файла

        A_BinaryFind instance = new A_BinaryFind();

// Создание экземпляра класса A_BinaryFind
        int[] result = instance.findIndex(stream);
// Вызов метода findIndex для поиска индексов и сохранение результата в массив result
        for (int index : result) {
            System.out.print(index + " ");
        }
// Вывод найденных индексов на консоль, разделенных пробелом

    }

}
