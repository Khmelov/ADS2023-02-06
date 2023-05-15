package by.it.group251004.ustsinovich.lesson04;

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

    int inversions = 0;

    void merge(int[] arr, int[] left, int[] right, int left_n, int right_n){
        int i = 0; int k = 0;
        int j = 0;
        while (i < left_n && j < right_n) {
            if (left[i] <= right[j]){
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                inversions += left_n - i;
            }
        }
        while (i < left_n) {
            arr[k++] = left[i++];
        }
        while (j < right_n) {
            arr[k++] = right[j++];
        }
    }

    void mergeSort(int[] a, int size) {
        if (size < 2) {
            return;
        }
        int mid = size/2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];
        System.arraycopy(a, 0, left, 0, mid);
        if (size - mid >= 0) System.arraycopy(a, mid, right, mid - mid, size - mid);
        mergeSort(left, mid);
        mergeSort(right, size - mid);

        merge(a, left, right, mid, size - mid);
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




        mergeSort(a, n);
        result = inversions;



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/prokopchuk/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finishTime - startTime);
    }
}