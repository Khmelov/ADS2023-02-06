package by.it.group251002.zhukovskaya.lesson04;

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
    public static class Container {
        public int counter=0;
    }
    private int mergeSort(int[] a, int[] temp, int left, int right) {
        int inversions = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inversions += mergeSort(a, temp, left, mid);
            inversions += mergeSort(a, temp, mid + 1, right);
            inversions += merge(a, temp, left, mid + 1, right);
        }
        return inversions;
    }

    private int merge(int[] a, int[] temp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int inversions = 0;
        int numElements = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (a[left] <= a[right]) {
                temp[k++] = a[left++];
            } else {
                temp[k++] = a[right++];
                inversions += leftEnd - left + 1;
            }
        }

        while (left <= leftEnd) {
            temp[k++] = a[left++];
        }

        while (right <= rightEnd) {
            temp[k++] = a[right++];
        }

        System.arraycopy(temp, k - numElements, a, k - numElements, numElements);

        return inversions;
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
        int[] temp = new int[n];
        int inversions = mergeSort(a, temp, 0, n - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return inversions;
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