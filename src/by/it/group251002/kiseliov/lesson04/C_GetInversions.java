package by.it.group251002.kiseliov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    int count=0;
    void merge(int[] a, int[] b,int[] result){
        int left=0,right=0,i=0;
        while(left<a.length && right<b.length){
            if(a[left]<=b[right]){
                result[i]=a[left];
                left++;
            }
            else{
                result[i]=b[right];
                right++;
                count+=a.length-left;
            }
            i++;
        }
        while (left<a.length){
            result[i]=a[left];
            i++;
            left++;
        }
        while (right<b.length){
            result[i]=b[right];
            right++;
            i++;
        }
    }
    void mergeSort(int[] array){
        if (array.length>1) {
            int l = 0, r = array.length-1;
            int m = (l + r) / 2;
            int[] leftPart= Arrays.copyOfRange(array,l,m+1);
            int[] rightPart= Arrays.copyOfRange(array,m+1,array.length);
            mergeSort(leftPart);
            mergeSort(rightPart);
            merge(leftPart,rightPart,array);
        }
    }
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int k = scanner.nextInt();
        //сам массив
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        mergeSort(a);
        result=count;
        System.out.println(result);



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
