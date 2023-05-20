package by.it.group251004.kirlitsa.lesson04;

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
    int res = 0;

    void swapELements(int a, int b) {
        int temp = b;
        b = a;
        a = temp;
    }

    int[] MergeSort(int[] arr, int  left, int right) {
        if (right - left >= 2) {
            int middle = (left + right) / 2;

            MergeSort(arr, left, middle);
            MergeSort(arr, middle + 1, right);
            Merge(arr, left, middle, right);
        } else if ((right - left == 1) && (arr[right] > arr[left]))
            swapELements(arr[right],arr[left]);

        return arr;
    }

    int[] Merge (int[] arr, int left, int middle, int right) {
        int EF = left, ES = middle + 1, CE = 0;
        while((EF <= middle) && (ES <= right)) {
            if (arr[EF] < arr [ES])
                arr[CE++] = arr[EF++];
            else
            {
                arr[CE++] = arr[ES++];
                res += middle - EF;
            }
        }

        while (EF <= middle)
            arr[CE++] = arr[EF++];
        while (ES <= right)
            arr[CE++] = arr[ES++];

        return arr;
    }
    int calc(InputStream stream) {
        int result;
        Scanner scan = new Scanner(stream);

        int size = scan.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scan.nextInt();
        }

        MergeSort(arr,0,size-1);
        result = res;

        return result;
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

