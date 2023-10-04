package by.it.group251001.voytov.lesson05;

import java.awt.image.renderable.RenderableImage;
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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start > o.start) {
                return 1;
            }

            if (this.start < this.start) {
                return -1;
            }

            return 0;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        for (int i = 0; i < n; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }

        QuickSort(segments, 0, segments.length - 1);

        for (int j = 0; j < points.length; j++) {
            for (int i = 0; i < segments.length && points[j] >= segments[i].start; i++) {
                if (points[j] <= segments[i].stop) {
                    result[j]++;
                }
            }
        }
        return result;
    }

    private static void QuickSort(Segment[] array, int high, int low) {

        if (low >= high) {
            return;
        }

        int leftIndex = low;
        int rightIndex = high;
        Segment pivot = array[high];

        while (leftIndex < rightIndex) {

            while (array[leftIndex].compareTo(pivot) <= 0 && leftIndex < rightIndex) {
                leftIndex++;
            }

            while (array[rightIndex].compareTo(pivot) > 0 && leftIndex < rightIndex) {
                rightIndex++;
            }

            swap(array, leftIndex, rightIndex);
        }

        swap(array, leftIndex, high);

        QuickSort(array, low, leftIndex - 1);
        QuickSort(array, leftIndex + 1, high);
    }

    private static void swap(Segment[] array, int index1, int index2) {
        Segment temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
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
