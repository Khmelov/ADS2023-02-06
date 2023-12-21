package by.it.group251002.golovin.lesson05;

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
    private class Segment  implements Comparable{
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
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            //извините, но мне нужно 2 компаратора(
            return 0;
        }
    }

    private void swap(C_QSortOptimized.Segment[] arr, int i, int j)
    {
        C_QSortOptimized.Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(C_QSortOptimized.Segment[] arr, int low, int high, boolean mode)
    {
        C_QSortOptimized.Segment pivot = arr[high];
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

    public void qsorte(C_QSortOptimized.Segment[] arr, int low, int high, boolean mode)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high,mode);
            qsorte(arr, low, pi-1,mode);
            qsorte(arr, pi+1, high,mode);
        }
    }

    public int upperbond(C_QSortOptimized.Segment[] A, int val)
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

    public int upper2(C_QSortOptimized.Segment[] A, int val)
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


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        C_QSortOptimized.Segment[] segments=new C_QSortOptimized.Segment[n], seg2=new C_QSortOptimized.Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];
        int res1,res2;

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new C_QSortOptimized.Segment(scanner.nextInt(),scanner.nextInt());
            seg2[i]=new C_QSortOptimized.Segment(segments[i].start,segments[i].stop);
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
