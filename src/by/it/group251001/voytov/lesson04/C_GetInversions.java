package by.it.group251001.voytov.lesson04;

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

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        return countInversions(a, 0);
    }

    public static int countInversions(int[] array, int counter) {

        if (array.length == 1) {
            return 0;
        }

        int[] leftPart = new int[array.length / 2];
        int[] rightPart = new int[array.length - leftPart .length];

        for (int i = 0; i < leftPart.length; i++) {
            leftPart[i] = array[i];
        }
        for (int i = leftPart.length; i < array.length; i++) {
            rightPart[i - leftPart.length] = array[i];
        }

        counter += countInversions(leftPart, counter);
        counter += countInversions(rightPart, counter);

        int i = 0;
        int j = 0;

        while (i < leftPart.length && j < rightPart.length) {

            if (leftPart[i] > rightPart[j]) {
                counter++;
                array[i + j] = rightPart[j];
                j++;
            }
            else {
                array[i + j] = leftPart[i];
                i++;
            }
        }

        while (i < leftPart.length) {
            array[i + j] = leftPart[i];
            i++;
        }

        while (j < rightPart.length) {
            array[i + j] = rightPart[j];
            j++;
        }

        return counter;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
