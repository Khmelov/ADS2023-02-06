package by.it.group251004.khanenko.lesson04;

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
    static int counter = 0;
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
        a = cutArray(a);
        int result = counter;
        return result;
    }

    public int [] cutArray(int[] array) { // сортировка Массива который передается в функцию
        if (array.length < 2) {
            return array;
        }
        int [] firstArray = new int[array.length / 2];
        System.arraycopy(array, 0, firstArray, 0, array.length / 2);
        int [] secondArray = new int[array.length - array.length / 2];
        System.arraycopy(array, array.length / 2, secondArray, 0, array.length - array.length / 2);
        firstArray = cutArray(firstArray);
        secondArray = cutArray(secondArray);
        return sortArrays(firstArray, secondArray);
    }
    public static int[] sortArrays(int[] firstArray, int[] secondArray) {
        int[] resArray = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0, k = 0;
        while (i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] <= secondArray[j]) {
                resArray[k] = firstArray[i];
                i++;
            }
            else {
                resArray[k] = secondArray[j];
                j++;
                counter++;
            }
            k++;
        }
        while (i < firstArray.length) {
            resArray[k] = firstArray[i];
            i++;
            k++;
        }
        while (j < secondArray.length) {
            resArray[k] = secondArray[j];
            j++;
            k++;
        }
        return resArray;
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
