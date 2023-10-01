package by.it.group251001.politykina.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    public static void merge(int[] main, int[] left, int[] right){
        int i = 0, j=0,k=0;
        while(i<left.length && j<right.length){
            if(left[i]<right[j]){
                main[k]=left[i];
                k++;
                i++;
            }
            else{
                main[k]=right[j];
                k++;
                j++;
            }
        }
        while(j<right.length) {
            main[k]=right[j];
            j++;
            k++;
        }
        while (i<left.length){
            main[k]=left[i];
            k++;
            i++;
        }


    }
    void mergeSort(int[] a, int size) {
        if (size <=2 ) {
            return;
        }
        int mid = size/2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];
        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, size - mid);
        mergeSort(left, mid);
        mergeSort(right, size - mid);

        merge(a, left, right);
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

        mergeSort(a, n);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    void merge(int[] a)
    {
        if (a.length<=1) return;
        int len = (a.length-1)/2;
        int b[] = Arrays.copyOfRange(a, 0, len);
        int c[] = Arrays.copyOfRange(a, len+1, a.length);

        merge(b);
        merge(c);
        int x = 0, y = 0, z = 0;

        while (x < b.length && y < c.length)

            //       a[z++] = b[x] < c[y] ? b[x++] : c[y++];
            if (b[x]<c[y]){
                a[z++]=b[x++];
            }
            else {
                a[x++]=c[y++];
            }

        while (x < b.length) a[z++] = b[x++];
        while (y < c.length) a[z++] = c[y++];
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
