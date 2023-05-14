package by.it.group251001.pavlkrat.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    void merge(int[] arr, int l, int m, int r)
    {
        int it1=0,it2=0;
        int[] res = new int[r-l+1];

        while ((l + it1 < m) && (m + it2 <= r))
        {
            if (arr[l + it1] < arr[m + it2])
            {
                res[it1 + it2] = arr[l + it1];
                it1++;
            }
            else
            {
                res[it1 + it2] = arr[m + it2];
                it2++;
            }
        }
        while (l + it1 < m)
        {
            res[it1 + it2] = arr[l + it1];
            it1++;
        }
        while (m + it2 <= r)
        {
            res[it1 + it2] = arr[m + it2];
            it2++;
        }
        if (it1 + it2 >= 0) System.arraycopy(res, 0, arr, l, it1 + it2);
    }

    void rec(int[] arr, int l, int r)
    {
        int m = (l + r) / 2;
        if(r-l>1) {
            rec(arr, l, m - 1);
            rec(arr, m, r);
        }
        merge(arr, l, m, r);
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }


        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


        rec(a,0,n-1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
