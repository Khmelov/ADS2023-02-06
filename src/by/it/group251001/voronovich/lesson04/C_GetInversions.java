package by.it.group251001.voronovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    static int count = 0;

    public static void MergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }

        int middle = n / 2;
        int[] leftArr = new int[middle];
        int[] rightArr = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArr[i] = a[i];
        }
        for (int i = middle; i < n; i++) {
            rightArr[i - middle] = a[i];
        }

        MergeSort(leftArr, middle);
        MergeSort(rightArr, n - middle);
        Merge(a, leftArr, rightArr, middle, n - middle);
    }

    public static void Merge(int[] a, int[] lft, int[] rght, int left, int right) {
        int i = 0, j = 0, m = 0;
        while (i < left && j < right) {
            if (lft[i] < rght[j]) {
                a[m++] = lft[i++];
            } else {
                count = left - i;
                a[m++] = rght[j++];
            }
        }
        while (i < left) {
            a[m++] = lft[i++];
        }
        while (j < right) {
            a[m++] = rght[j++];
        }
    }

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        MergeSort(a, n);
        result = count;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/ADS2023-02-06/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
