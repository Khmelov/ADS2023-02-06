package by.it.group251003.Stelmakh.lesson04;

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
    void Swap(int a, int b){
        int tmp = b;
        b = a;
        a = tmp;
    }

    int[] MergeSort(int arr[], int  l, int r){
        if (r - l >= 2){
            int mid = (l + r) / 2;
            MergeSort(arr, l, mid);
            MergeSort(arr, mid + 1, r);
            Merge(arr, l, mid, r);
        } else if (r - l == 1)
        {
            if (arr[r] > arr[l]) {Swap(arr[r],arr[l]);}
        }
        return arr;
    }

    int[] Merge (int arr[], int l, int m, int r){
        int []temparr = arr;
        int EF = l, ES = m + 1, CE = 0;
        while((EF <= m) && (ES <= r)){
            if (arr[EF] < arr [ES])
            {
                temparr[CE++] = arr[EF++];
            }
            else
            {
                temparr[CE++] = arr[ES++];
                res += m - EF;
            }
        }
        while (EF<= m){
            temparr[CE++] = arr[EF++];
        }
        while (ES <= r){
            temparr[CE++] = arr[ES++];
        }
        return temparr;
    }
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        int result = 0;
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        MergeSort(a,0,n-1);
        result = res;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
