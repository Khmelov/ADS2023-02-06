package by.it.group251001.zhidkov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    // Класс для представления отрезка
    private static class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            // Конструктор класса
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }
        @Override
        public int compareTo(Segment o) {
            // Реализация интерфейса Comparable для сравнения отрезков по их конечной точке (stop)
            return this.stop - o.stop;
        }
    }
    int[] getAccessory(InputStream stream) {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        // Число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];
        // Читаем сами отрезки
        for (int i = 0; i < n; i++) {
            // Читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        // Читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем массив отрезков по конечным точкам, используя быструю сортировку
        quickSort(segments, 0, segments.length - 1);

        // Проходим по каждой точке и подсчитываем количество отрезков, которые содержат эту точку
        for (int i = 0; i < m; i++) {
            int count = 0;
            int j = 0;
            while (j < n && points[i] <= segments[j].stop) {
                if (segments[j].start <= points[i])
                    count++;
                j++;
            }
            result[i] = count;
        }

        return result;
    }

    int partition(Segment[] A, int left, int right) {
        // Функция для разделения массива на подмассивы
        Segment temp = A[left];
        int ltemp = left;
        for (int i = left + 1; i < right; i++) {
            if (A[i].compareTo(temp) < 0) {
                ltemp++;
                Segment q = A[i];
                A[i] = A[ltemp];
                A[ltemp] = q;
            }
        }
        A[left] = A[ltemp];
        A[ltemp] = A[left];

        return ltemp;
    }

    void quickSort(Segment[] A, int left, int right) {
        // Реализация алгоритма быстрой сортировки (QuickSort) для сортировки массива отрезков
        if (left >= right)
            return;
        int m = partition(A, left, right);
        quickSort(A, left, m - 1);
        quickSort(A, m + 1, right);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
