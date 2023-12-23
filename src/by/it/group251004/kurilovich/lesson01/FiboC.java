package by.it.group251004.kurilovich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

public class FiboC {
    public static long PisanoPeriod(int m)
    {
        long prev = 0;
        long curr = 1;
        long res = 0;

        for(int i = 0; i < m * m; i++)
        {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            if (prev == 0 && curr == 1)
                return i + 1;
        }
        return res;
    }
    public static long FindMOD(long n, int m)
    {
        long prev = 0;
        long curr = 1;
        for(int i = 0; i < n; i++)
        {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;
        }
        return prev % m;
    }
    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long pisano = PisanoPeriod(m);

        n %= pisano;

        if (n == 0L || n == 1L)
            return n;
        return FindMOD(n, m);
    }


}

