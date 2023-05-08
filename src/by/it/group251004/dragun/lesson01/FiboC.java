package by.it.group251004.dragun.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public static long PizanoPeriod(int m)
    {
        long previ = 0;
        long curre = 1;
        long resu = 0;

        for(int i = 0; i < m * m; i++)
        {
            long temp = curre;
            curre = (previ + curre) % m;
            previ = temp;

            if (previ == 0 && curre == 1)
                return i + 1;
        }
        return resu;
    }
    public static long FindMOD(long n, int m)
    {
        long prevy = 0;
        long curre = 1;
        for(int i = 0; i < n; i++)
        {
            long temp = curre;
            curre = (prevy + curre) % m;
            prevy = temp;
        }
        return prevy % m;
    }
    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        //Находит период Пизано
        long pizano = PizanoPeriod(m);

        n %= pizano;

        if (n == 0L || n == 1L)
            return n;
        return FindMOD(n, m);
    }


}

