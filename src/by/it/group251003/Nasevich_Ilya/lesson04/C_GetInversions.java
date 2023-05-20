package by.it.group251003.Nasevich_Ilya.lesson04;

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

    public int mergeSort(int array[], int counter){
        if (array.length > 1){
            int middle = array.length / 2;
            int[] leftPart = Arrays.copyOfRange(array, 0, middle);
            int[] rightPart = Arrays.copyOfRange(array, array.length - middle, array.length);
            mergeSort(leftPart, counter);
            mergeSort(rightPart, counter);
            counter = mergeCounter(array, leftPart, rightPart, counter);
        }

        return counter;
    }

    private int mergeCounter(int[] array, int[] leftPart, int[] rightPart, int counter) {
        int i = 0, j=0, k=0;
        while(i<leftPart.length&&j<rightPart.length){
            if(leftPart[i]<=rightPart[j]){
                array[k]=leftPart[i];
                k++;
                i++;//i - количество раз когда
            }
            else{
                array[k]=rightPart[j];
                k++;
                j++; //j - количество раз, когда левая больше правой
            }
        }

        counter += i;

        while (i < leftPart.length){
            array[k]=leftPart[i];
            k++;
            i++;
        }
        while(j<rightPart.length) {
            array[k] = rightPart[j];
            k++;
            j++;
        }

        return counter;
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
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        result=mergeSort(a,result);

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
