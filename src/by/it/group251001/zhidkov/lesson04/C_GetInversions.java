package by.it.group251001.zhidkov.lesson04;

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

    int sortArray(int[] array, int l, int r) {
        // Функция для сортировки массива и подсчета инверсий
        // Принимает массив, индекс начала l и индекс конца r
        int count = 0;
        int m = (l + r) / 2;
        // Находим середину массива
        if (r - l > 1) {
            // Если разница между индексами больше 1, вызываем рекурсивно функцию sortArray
            // для левой половины массива и для правой половины массива
            count += sortArray(array, l, m - 1);
            count += sortArray(array, m, r);
        }
        count += mergeArray(array, l, m, r);
        // Вызываем функцию mergeArray для слияния двух отсортированных половин массива и подсчета инверсий
        return count;
    }

    int mergeArray(int[] array, int l, int m, int r) {
        // Функция для слияния двух отсортированных половин массива и подсчета инверсий
        // Принимает массив, индекс начала левой половины l, индекс середины m и индекс конца r
        int a = 0, b = 0, count = 0;
        // Инициализируем переменные a и b для отслеживания текущих индексов в левой и правой половинах массива
        int[] result = new int[r - l + 1];
        // Создаем новый массив для сохранения отсортированных элементов
        while ((l + a < m) && (m + b <= r)) {
            // Пока не достигнут конец левой или правой половины массива
            if (array[l + a] <= array[m + b]) {
                result[a + b] = array[l + a];
                a++;
            } else {
                result[a + b] = array[m + b];
                b++;
                count++;
            }
            // Выполняем сравнение элементов левой и правой половин массива и записываем их в результирующий массив
            // Если элемент из правой половины меньше, увеличиваем переменную count для подсчета инверсий
        }
        while (l + a < m) {
            // Если остались элементы только в левой половине массива
            result[a + b] = array[l + a];
            a++;
            count++;
        }
        while (m + b <= r) {
            // Если остались элементы только в правой половине массива
            result[a + b] = array[m + b];
            b++;
        }
        if (a + b >= 0)
            System.arraycopy(result, 0, array, l, a + b);
        // Копируем отсортированный результирующий массив
        return count;
    }
    int calc(InputStream stream) {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        return sortArray(a, 0, a.length - 1);
        // Вызываем функцию sortArray для сортировки массива и подсчета инверсий
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        // Получаем путь к текущей директории
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        // Создаем входной поток stream для чтения данных из файла dataC.txt
        C_GetInversions instance = new C_GetInversions();
        // Создаем экземпляр класса C_GetInversions
        int result = instance.calc(stream);
        // Вызываем функцию calc для подсчета инверсий в массиве
        System.out.print(result);
        // Выводим результат на экран
    }
}
