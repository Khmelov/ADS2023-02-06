package by.it.group251002.lapus_vitaliy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
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
    private class Segment  implements Comparable<Integer>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Integer o) {
            //подумайте, что должен возвращать компаратор отрезков
            if(start>o) {
                return 1;
            }
            else{
                if(start<o)
                {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    Segment[] qsortfun(int start, int finish,Segment[] m)
    {
        Segment tmp;
        int x=m[start].start;
        int k=start;
        int j=start;
        for(int i=start+1;i<=finish;i++)
        {
            if(m[i].compareTo(x)==-1)
            {
                if(k+1!=i)
                {
                    tmp = m[j];
                    m[j] = m[k + 1];
                    m[k + 1] = tmp;
                }
                tmp=m[i];
                m[i]=m[j];
                m[j]=tmp;
                k++;
                j++;
            } else if (m[i].compareTo(x)==0)
            {
                tmp=m[j];
                m[j]=m[k+1];
                m[k+1]=tmp;

                tmp=m[i];
                m[i]=m[j];
                m[j]=tmp;
                k++;
            }
        }
        if(j>start+1)
        {
            m=qsortfun(start, j-1, m);
        }
        if(k<finish-1)
        {
            m=qsortfun(k+1, finish, m);
        }
        return m;
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

        segments=qsortfun(0,n-1,segments);

        int j;


        for(int i=0;i<m;i++)
        {
            j=0;
            while(j<n && segments[j].start<=points[i])
            {
                if(segments[j].stop>=points[i])
                {
                    result[i]++;
                }
                j++;
            }
        }
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
