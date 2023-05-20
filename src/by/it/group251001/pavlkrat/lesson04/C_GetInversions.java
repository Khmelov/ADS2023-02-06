package by.it.group251001.pavlkrat.lesson04;

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

    int merge(int[] arr, int l, int m, int r)
    {
        int it1=0,it2=0, cnt = 0;
        int[] res = new int[r-l+1];

        while ((l + it1 < m) && (m + it2 <= r))
        {
            if (arr[l + it1] <= arr[m + it2])
            {
                res[it1 + it2] = arr[l + it1];
                it1++;
            }
            else
            {
                res[it1 + it2] = arr[m + it2];
                it2++;
                cnt++;
            }
        }
        while (l + it1 < m)
        {
            res[it1 + it2] = arr[l + it1];
            it1++;
            cnt++;
        }
        while (m + it2 <= r)
        {
            res[it1 + it2] = arr[m + it2];
            it2++;
        }
        if (it1 + it2 >= 0) System.arraycopy(res, 0, arr, l, it1 + it2);
        return cnt;
    }

    int rec(int[] arr, int l, int r)
    {
        int cnt = 0;
        int m = (l + r) / 2;
        if(r-l>1) {
            cnt += rec(arr, l, m - 1);
            cnt += rec(arr, m, r);
        }
        cnt += merge(arr, l, m, r);
        return cnt;
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
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return rec(a,0,n-1);
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
