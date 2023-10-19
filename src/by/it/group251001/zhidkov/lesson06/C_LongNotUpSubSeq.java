package by.it.group251001.zhidkov.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/



public class C_LongNotUpSubSeq {
    static int[] answer; // Статический массив для хранения ответа (последовательности индексов)
    int getNotUpSeqSize(InputStream stream) {
        Scanner scanner = new Scanner(stream); // Создание объекта Scanner для чтения данных из входного потока
        int n = scanner.nextInt(); // Чтение целочисленного значения из входного потока и сохранение его в переменной n
        int[] m = new int[n]; // Объявление массива целых чисел m размером n

        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt(); // Чтение n чисел из входного потока и сохранение их в массиве m
        }

        int[] d = new int[n]; // Объявление массива целых чисел d размером n. Массив d будет хранить длину наибольшей невозрастающей подпоследовательности для каждого элемента
        int[] index = new int[n]; // Объявление массива целых чисел index размером n. Массив index будет хранить индексы предыдущих элементов, образующих наибольшую невозрастающую подпоследовательность для каждого элемента

        for (int i = 0; i < n; i++){
            d[i] = 1; // Инициализация каждого элемента массива d значением 1
            index[i] = -1; // Инициализация каждого элемента массива index значением -1
            for (int j = 0; j < i; j++){
                if (m[i] <= m[j] && d[i] < d[j] + 1) {
                    d[i] = d[j] + 1; // Обновление значения d[i] на основе значения d[j] и проверки невозрастания m[i] по отношению к m[j]
                    index[i] = j; // Обновление значения index[i] на j
                }
            }
        }

        int result = d[0]; // Инициализация переменной result значением первого элемента массива d
        int max = 0;

        for (int i = 1; i < n; i++){
            if (d[i] > result) {
                result = d[i]; // Обновление значения result на основе значения d[i]
                max = i; // Обновление значения max на i
            }
        }
        answer = new int[result]; // Инициализация массива answer размером result
        int i = result - 1;


        do {
            answer[i] = max + 1; // Заполнение массива answer индексами элементов
            max = index[max]; // Обновление значения max на основе значения index[max]
            i--;
        } while (max > -1);

        return result; // Возвращение значения result как размера наибольшей невозрастающей подпоследовательности
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.println(result); // Вывод размера наибольшей невозрастающей подпоследовательности
        for (int i = 0; i < result; i++) {
            System.out.print(String.valueOf(answer[i]) + ' '); // Вывод элементов последовательности
        }
    }
}