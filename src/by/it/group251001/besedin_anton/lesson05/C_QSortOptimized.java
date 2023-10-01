package by.it.group251001.besedin_anton.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.
        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения
        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0
*/


public class C_QSortOptimized {

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
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
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
    }

    public void swap(Segment[] a, int i, int j) {
        Segment temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void quickSort(Segment[] a, int start, int end) {
        int i = start;
        int j = end - 1;
        int x = start - 1;
        int y = end;
        Segment p = a[end];
        do {
            while (a[i].compareTo(p) < 0)
                i++;

            while (a[j].compareTo(p) > 0)
                j--;

            if (i < j) {
                swap(a, i, j);
                if (a[i].compareTo(p) == 0) {
                    x++;
                    swap(a, i, x);
                }
                if (a[j].compareTo(p) == 0) {
                    y--;
                    swap(a, j, y);
                }
            }
        } while (i <= j);
        swap(a, i, end);
        j = i - 1;
        for (int k = start; k < x; k++, j--) {
            swap(a, k, j);
        }
        i++;
        for (int k = end - 1; k > y; k--, i++) {
            swap(a, k, i);
        }
        if (j > start)
            quickSort(a, start, j);
        if (i < end)
            quickSort(a, i, end);

    }


    public int binarySearch(Segment[] a, int x) {

        int start = 0;
        int end = a.length - 1;
        int mid = start + (end - start) / 2;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (a[mid].start < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return mid;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            result[i] = 0;
            int j = binarySearch(segments, points[i]);
            while (j >= 0) {
                if (segments[j].stop >= points[i] && segments[j].start <= points[i])
                    result[i]++;
                j--;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}