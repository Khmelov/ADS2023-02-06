package by.it.group251001.besedinAndrei.lesson05;

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

    // отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (stop >= start) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            // подумайте, что должен возвращать компаратор отрезков
            if (this.start > o.start)
                return 1;
            else if (this.start < o.start)
                return -1;
            else
                return 0;
        }
    }

    public void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    public void qsort(Segment[] segments, int start, int end) {
        int i = start;
        int j = end - 1;
        int p = start - 1;
        int q = end;

        Segment m = segments[end];

        do {
            while (segments[i].compareTo(m) < 0) {
                i++;
            }

            while (segments[j].compareTo(m) > 0) {
                j--;
            }

            if (i < j) {
                swap(segments, i, j);

                if (segments[i].compareTo(m) == 0) {
                    p++;

                    swap(segments, i, p);
                }

                if (segments[j].compareTo(m) == 0) {
                    q--;

                    swap(segments, j, q);
                }
            }

        } while (i <= j);

        swap(segments, i, end);

        j = i - 1;

        for (int k = start; k < p; k++, j--) {
            swap(segments, k, j);
        }

        i++;

        for (int k = end - 1; k > q; k--, i++) {
            swap(segments, k, i);
        }

        if (j > start)
            qsort(segments, start, j);

        if (i < end)
            qsort(segments, i, end);
    }

    public int binsearch(Segment[] a, int x) {
        int start = 0;
        int end = a.length - 1;
        int middle = start + (end - start) / 2;

        while (start <= end) {
            middle = start + (end - start) / 2;

            if (a[middle].start < x) return start = middle + 1;
                
            end = middle - 1;
        }

        return middle;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        // !!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
        // число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        // число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // читаем сами отрезки
        for (int i = 0; i < n; i++) {
            // читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        // читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        // тут реализуйте логику задачи с применением быстрой сортировки
        // в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qsort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            result[i] = 0;
            int j = binsearch(segments, points[i]);

            while (j >= 0) {
                if (segments[j].stop >= points[i] && segments[j].start <= points[i])
                    result[i]++;
                j--;
            }
        }

        // !!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}