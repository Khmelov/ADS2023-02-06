package by.it.group251004.symonik.lesson01;

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

    long fasterC(long n, int m) {
        long swap;
        long pred = 0;
        long curr = 1;
        long pisano = 0;
        for(int i = 0; i < m * m; i++)
        {
            swap = curr;
            curr = (pred + curr) % m;
            pred = swap;
            if (pred == 0 && curr == 1)
                pisano = i + 1;
        }

        n %= pisano;

        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        pred = 0;
        curr = 1;


        for(int i = 0; i < n - 1; i++)
        {
            swap = curr;
            curr = (pred + curr) % m;
            pred = swap;
        }
        return curr % m;
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

    }


}

