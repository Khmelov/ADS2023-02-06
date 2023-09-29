package by.it.group251001.pavlkrat.lesson05;

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
    class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start > stop)
            {
                int tmp = start;
                start = stop;
                stop = tmp;
            }
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
            //доделал
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            //извините, но мне нужно два компаратора(
            return 0;
        }
    }


    private void swap(Segment[] arr, int i, int j)
    {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(Segment[] arr, int low, int high, boolean mode)
    {
        Segment pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            boolean usl = mode?arr[j].start <= pivot.start:arr[j].stop <= pivot.stop;
            if (usl)
            {
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }

    public void qsorte(Segment[] arr, int low, int high, boolean mode)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high,mode);
            qsorte(arr, low, pi-1,mode);
            qsorte(arr, pi+1, high,mode);
        }
    }

    public int upperbond(Segment[] A, int val)
    {
        int l = 0, r = A.length - 1,m;
        while(l<r)
        {
            m = (l + r)/2;
            if (A[m].start<=val)
                l = m + 1;
            else
                r = m;
        }
        if (A[l].start<=val)
            return l + 1;
        return l;
    }

    public int upper2(Segment[] A, int val)
    {
        int l = 0, r = A.length - 1,m;
        while(l<r)
        {
            m = (l + r)/2+(l+r)%2;
            if (A[m].stop>=val)
                r = m - 1;
            else
                l = m;
        }
        if (A[l].stop>=val)
            return l - 1;
        return l;
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {

        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n], seg2=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];
        int res1,res2;

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
            seg2[i]=new Segment(segments[i].start,segments[i].stop);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }

        qsorte(segments,0,n-1,true);
        qsorte(seg2,0,n-1,false);
        for(int i=0;i<m;i++) {
            res1 = upperbond(segments,points[i]);
            res2 = upper2(seg2,points[i]);
            result[i] = res1-res2-1;
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
