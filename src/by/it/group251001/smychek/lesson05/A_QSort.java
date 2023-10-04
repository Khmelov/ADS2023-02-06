package by.it.group251001.smychek.lesson05;

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

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков

            return 0;
        }
    }

    Segment[] QuickSortL(Segment[] a, int l, int r){
        Segment c = a[(l + r) / 2];
        int i = l, j = r;

        while (i <= j){
            while (a[i].start < c.start)
                ++i;
            while (a[j].start > c.start)
                --j;
            if (i <= j) {
                Segment temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                ++i;
                --j;
            }
        }
        if (j>l) a = QuickSortL(a, l, j);
        if (i<r) a = QuickSortL(a, i, r);
        return a;
    }

    Segment[] QuickSortR(Segment[] a, int l, int r){
        Segment c = a[(l + r) / 2];
        int i = l, j = r;

        while (i <= j){
            while (a[i].stop < c.stop)
                ++i;
            while (a[j].stop > c.stop)
                --j;
            if (i <= j) {
                Segment temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                ++i;
                --j;
            }
        }
        if (j>l) a = QuickSortR(a, l, j);
        if (i<r) a = QuickSortR(a, i, r);
        return a;
    }

    int BinSearchStart(Segment[] a, int l, int r, int ethalone){
        if(l == r)
            if(a[l].start<=ethalone)
                return l+1;
            else
                return l;
        if(a[(l + r) / 2].start <= ethalone)
            return BinSearchStart(a, (l + r) / 2 + 1, r, ethalone);
        else
            return BinSearchStart(a, l, (l + r) / 2, ethalone);
    }

    int BinSearchStop(Segment[] a, int l, int r, int ethalone){
        if(l == r)
            if(ethalone > a[l].stop)
                return l+1;
            else
                return  l;
        if(a[(l + r) / 2].stop < ethalone)
            return BinSearchStop(a, (l + r) / 2 + 1, r, ethalone);
        else
            return BinSearchStop(a, l, (l + r) / 2, ethalone);
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segmentsL=new Segment[n];
        Segment[] segmentsR=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segmentsL[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        for (int i = 0; i < n; ++i){
            segmentsR[i] = segmentsL[i];
        }
        segmentsL = QuickSortL(segmentsL, 0, n-1);
        segmentsR = QuickSortR(segmentsR, 0, n-1);
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
            int left = BinSearchStop(segmentsR,0,n-1,points[i]);
            int right = n - BinSearchStart(segmentsL,0,n-1,points[i]);
            result[i] = n - left-  right;
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
