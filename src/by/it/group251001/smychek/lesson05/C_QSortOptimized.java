package by.it.group251001.smychek.lesson05;

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
    private class Segment  implements Comparable<C_QSortOptimized.Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(C_QSortOptimized.Segment o) {
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

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
