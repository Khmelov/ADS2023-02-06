package by.it.group251003.beskosty.lesson04;

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
    public static void MergeSort(int[] Array, int Length) {
        if (Length < 2) return;
        int Mid = Length / 2;
        int[] Left = new int[Mid];
        int[] Right = new int[Length - Mid];

        for (int i = 0; i < Mid; i++) {
            Left[i] = Array[i];
        }
        for (int i = Mid; i < Length; i++) {
            Right[i - Mid] = Array[i];
        }
        MergeSort(Left, Mid);
        MergeSort(Right, Length - Mid);

        Merge(Array, Left, Right, Mid, Length - Mid);
    }
    public static void Merge(
            int[] Array, int[] LeftA, int[] RightA, int L, int R) {
        int i,j,k;
        i = j = k = 0;
        while (i < L && j < R) {
            if (LeftA[i] <= RightA[j]) {
                Array[k++] = LeftA[i++];
            }
            else {
                Array[k++] = RightA[j++];
            }
        }
        while (i < L) {
            Array[k++] = LeftA[i++];
        }
        while (j < R) {
            Array[k++] = RightA[j++];
        }
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
        MergeSort(a,a.length);





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
