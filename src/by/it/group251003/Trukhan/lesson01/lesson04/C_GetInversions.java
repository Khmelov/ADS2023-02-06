package by.it.group251003.Trukhan.lesson01.lesson04;

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
    public static int inversionsCount;
    public static int MergeSort (int[] a, int n) {
        if (n == 1) return 0;

        int mid = n / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[n-mid];

        System.arraycopy(a, 0, leftArr, 0, mid);
        System.arraycopy(a, mid, rightArr, 0, n-mid);

        MergeSort(leftArr, mid);
        MergeSort(rightArr, n-mid);
        Merge(a, leftArr, rightArr, mid, n-mid);
        return 0;
    }

    public static void Merge(int[] a, int[] leftArr, int[] rightArr, int leftLen, int rightLen){
        int i = 0, j = 0, k = 0;
        while (i < leftLen && j < rightLen){
            if(leftArr[i] <= rightArr[j]) a[k++] = leftArr[i++];
            else {
                a[k++] = rightArr[j++];
                inversionsCount += leftLen - i;
            }
        }
        while (i < leftLen) a[k++] = leftArr[i++];
        while (j < rightLen) a[k++] = rightArr[j++];
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
        MergeSort(a, n);
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return inversionsCount;
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
