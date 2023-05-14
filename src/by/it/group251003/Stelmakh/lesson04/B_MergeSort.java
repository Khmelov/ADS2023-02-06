package by.it.group251003.Stelmakh.lesson04;

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
        MergeSort (a, 0, n - 1);



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
