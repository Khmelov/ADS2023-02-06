package by.it.group251001.zhidkov.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12
    Sample Output:
    3
*/
public class B_LongDivComSubSeq {
    int getDivSeqSize(InputStream stream) {
        Scanner scanner = new Scanner(stream); // Создание объекта Scanner для чтения данных из входного потока
        int n = scanner.nextInt(); // Чтение целочисленного значения из входного потока и сохранение его в переменной n
        int[] m = new int[n]; // Объявление массива целых чисел m размером n
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt(); // Чтение n чисел из входного потока и сохранение их в массиве m
        }
        int[] d = new int[n]; // Объявление массива целых чисел d размером n. Массив d будет хранить длину наибольшей кратной подпоследовательности для каждого элемента

        for (int i = 0; i < n; i++){
            d[i] = 1; // Инициализация каждого элемента массива d значением 1

            for (int j = 0; j < i; j++){
                if (m[i] % m[j] == 0 && d[i] < d[j] + 1) {
                    d[i] = d[j] + 1; // Обновление значения d[i] на основе значения d[j] и проверки деления m[i] на m[j]
                }
            }
        }

        int result = d[0]; // Инициализация переменной result значением первого элемента массива d

        for (int i = 1; i < n; i++){
            if (d[i] > result) {
                result = d[i]; // Обновление значения result на основе значения d[i]
            }
        }

        return result; // Возвращение значения result как размера максимальной кратной подпоследовательности
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }
}
