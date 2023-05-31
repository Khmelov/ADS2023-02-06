package by.it.group251001.litvinovich.lesson05;

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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (stop >= start){
                this.start = start;
                this.stop = stop;
            }
            else{
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if (this.start>o.start)
                return 1;
            else
            if (this.start<o.start)
                return -1;
            else
                return 0;
        }
    }
    public void swap(Segment[] a, int i, int j){
        Segment temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public void qsort(Segment[] a, int start, int end){
        int i = start;
        int j = end-1;
        int p = start-1;
        int q = end;
        Segment v = a[end];
        do {
            while (a[i].compareTo(v)<0) {
                i++;
            }
            while (a[j].compareTo(v)>0) {
                j--;
            }
            if (i<j){
                swap(a, i, j);
                if (a[i].compareTo(v)==0){
                    p++;
                    swap(a, i, p);
                }
                if (a[j].compareTo(v)==0){
                    q--;
                    swap(a, j, q);
                }
            }
        }while (i<=j);
        swap(a, i, end);
        j = i - 1;
        for (int k = start; k < p; k++, j--){
            swap(a, k, j);
        }
        i++;
        for (int k = end - 1; k > q; k--, i++){
            swap(a, k, i);
        }
        if (j>start)
            qsort(a, start, j);
        if (i<end)
            qsort(a, i, end);
    }
    public int binsearch(Segment[] a, int x){
        int start = 0;
        int end = a.length-1;
        int mid = start + (end - start) / 2;
        while (start<=end) {
            mid = start + (end - start) / 2;
            if (a[mid].start<x) {
                start=mid+1;
            } else {
                end=mid-1;
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
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qsort(segments, 0, n-1);
        for (int i = 0; i < m; i++) {
            result[i]=0;
            int j = binsearch(segments, points[i]);
            while(j >= 0){
                if (segments[j].stop>=points[i]&&segments[j].start<=points[i])
                    result[i]++;
                j--;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251001/levitskij/lesson05/dataA.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}