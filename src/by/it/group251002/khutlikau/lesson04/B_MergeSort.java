package by.it.group251002.khutlikau.lesson04;

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

/*  Честно стыреный код из википедии, который я пытался переписать для джавы
    static void merge_sort(int[] A) {
        int len = A.length;
        if (len == 1 || len == 0) {
            return A
        }
        else {
            int[] L = new int[];
            int[] R = new int[];
            for(int i = 0; i < len; i++){
                if (i < len/2) {
                    int
                }
            }
            int[] L = merge_sort(A[:A / 2])
            int[] R = merge_sort(A[len(A) / 2:])
            n = m = k = 0
            C = [0] *(len(L) + len(R))
            while n<len (L) and m<len (R):
            if L[n] <= R[m]:
            C[k] = L[n]
            n += 1
            else:
            C[k] = R[m]
            m += 1
            k += 1
            while n<len (L):
            C[k] = L[n]
            n += 1
            k += 1
            while m<len (R):
            C[k] = R[m]
            m += 1
            k += 1
            for i in range(len(A)):
            A[i] = C[i]
            return A
        }
    }
*/

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //   !!!!!!!!!!!!!!!!!!!!!!!!!  НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

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

        // Без рекурсии
        int[] b = new int[n/2+2];
        int[] c = new int[n/2];
        int ib = 0;
        int ic = 0;
        int degree = 2;
        while (degree < 2*n){
            ib = 0;
            ic = 0;
            int i = 0;
            while (i<n){
                if(i%degree < degree/2){
                    b[ib] = a[i];
                    ib++;
                } else{
                    c[ic] = a[i];
                    ic++;
                }
                i++;
            }
            int ia = degree / 2;
            i = 0;
            int j = 0;
            while(ia<n) {
                while (i < ia && j < ia && i < ib && j < ic) {

                    if (b[i] <= c[j]) {
                        a[i+j] = b[i];
                        i += 1;
                    }
                    else {
                        a[i+j] = c[j];
                        j += 1;
                    }
                }
                while (i < ia && i < ib) {
                    a[i+j] = b[i];
                    i += 1;
                }
                while (j < ia && j < ic) {
                    a[i+j] = c[j];
                    j += 1;
                }
                ia += degree / 2;
            }
            degree *= 2;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251002/khutlikau/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
