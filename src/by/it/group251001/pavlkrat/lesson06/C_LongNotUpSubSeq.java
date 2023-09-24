package by.it.group251001.pavlkrat.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int lowerbond(int[] A, int val)
    {
        int l = 0, r = A.length - 1,m;
        while(l<r)
        {
            m = (l + r)/2;
            if (A[m] >= val)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int INF = 2000000007;
        int[] m = new int[n], dp = new int[n+1],ind = new int[n+1],pr = new int[n+1];
        ArrayList<Integer> ans = new ArrayList<>();
        //читаем всю последовательность
        dp[0] = INF;
        ind[0] = -1;
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
            dp[i+1] = -INF;
            pr[i] = -1;
            ind[i+1] = -1;
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        for(int i=0;i<n;i++)
        {
            int j = lowerbond(dp,m[i]);
            if (dp[j-1] >= m[i] && dp[j] < m[i]) {
                ind[j] = i;
                dp[j] = m[i];
                pr[i] = ind[j-1];
            }
        }
        for (int i=0;i<=n;i++)
            if (dp[i] == -INF) {
                int cur = ind[i-1];
                while(cur != -1) {
                    ans.add(cur + 1);
                    cur = pr[cur];
                }
                break;
            }
        int result = ans.size();
        System.out.println(result);
        for (int i = result-1;i>=0;i--) {
            System.out.print(ans.get(i));
            System.out.print(' ');
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}