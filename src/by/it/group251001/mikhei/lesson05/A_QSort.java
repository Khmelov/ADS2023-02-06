package by.it.group251001.mikhei.lesson05;

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
    record Point(int x, int cnt, int i) implements Comparable<Point> {

        @Override
        public int compareTo(Point o) {
            int res = Integer.compare(x, o.x);
            if (res == 0) res = Integer.compare(o.cnt, cnt);

            return res;
        }
    }

    <T extends Comparable<T>> void qSort(T[] a, int l, int r) {
        T piv = a[(l + r) / 2];

        int i = l, j = r;

        while (i <= j) {
            while (a[i].compareTo(piv) < 0) i++;
            while (a[j].compareTo(piv) > 0) j--;

            if (i <= j) {
                T temp = a[i];
                a[i] = a[j];
                a[j] = temp;

                i++;
                j--;
            }
        }

        if (j > l) qSort(a, l, j);
        if (i < r) qSort(a, i, r);
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int m = scanner.nextInt();

        Point[] points = new Point[2 * n + m];

        for (int i = 0; i < n; i++) {
            points[2 * i] = new Point(scanner.nextInt(), +1, -1);
            points[2 * i + 1] = new Point(scanner.nextInt(), -1, -1);
        }

        for (int i = 0; i < m; i++) {
            points[2 * n + i] = new Point(scanner.nextInt(), 0, i);
        }

        int[] result = new int[m];
        qSort(points, 0, points.length - 1);

        int cnt = 0;
        for (Point point : points) {
            cnt += point.cnt();

            if (point.cnt() == 0) result[point.i()] = cnt;
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
