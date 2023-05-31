package by.it.group251002.evgenuch.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    int binarySearch(int[] a, int length, int x) {
        int l = 0;
        int r = length;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] dp = new int[n]; // Array for dynamic prog
        int[] p = new int[n]; // Parents array
        int length = 1; // Length of array right now
        int last = 0; // Last element of the biggest subsequence
        dp[0] = m[0];
        p[0] = -1;
        for (int i = 1; i < n; i++) {
            int j = binarySearch(dp, length, m[i]);
            dp[j] = m[i];
            if (j > 0) {
                p[i] = j - 1;
            } else {
                p[i] = -1;
            }
            if (j == length) {
                last = i;
                length++;
            }
        }

        // Forming answer
        List<Integer> indices = new ArrayList<>();
        while (last != -1) {
            indices.add(last + 1);
            last = p[last];
        }
        Collections.reverse(indices);
        System.out.println(length);
        for (int index : indices) {
            System.out.print(index + " ");
        }
        System.out.println();


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return length;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}