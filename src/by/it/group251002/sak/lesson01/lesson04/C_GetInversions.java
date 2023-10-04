package by.it.group251002.sak.lesson01.lesson04;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)
Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].
    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )
Sample Input:
5
2 3 9 2 9
Sample Output:
2
Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    // сортировка слиянием
    int MergeSort(int []a, int l, int r){
        if(l==r)
            return 0;
        int c=(l+r)/2;
        int otv = MergeSort(a,l,c);
        otv += MergeSort(a,c+1,r);
        int[] b = new int[a.length];
        for(int i=0;i<a.length;++i)
            b[i] = a[i];
        int i = l, j = l, k = c + 1;
        while(j<=c || k<=r){
            if((j>c) || ((k<=r) && (a[j]>a[k])) ){
                b[i] = a[k];
                ++k;
                otv+=c-j+1;
            } else {
                b[i] = a[j];
                ++j;
            }
            ++i;
        }
        for(i=0;i<a.length;++i)
            a[i] = b[i];
        return otv;
    }

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        // применяем
        int result = MergeSort(a,0,n-1);;
        return result;
    }
    // конец заданяи

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
