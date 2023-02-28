package by.it.group251004.zayats.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 6;
        int m = 3;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    public static long PeriodPisano(int m)
    {
        long previous = 0;
        long current = 1;
        long result = 0;
        for (int i = 0; i < m * m; i++)
        {
            long temp = current;
            current = (previous + current) % m;
            previous = temp;
            result = (previous == 0 && current == 1) ? i + 1 : result;
        }
        return result;
    }

    private static long FindResult (long n, int m)
    {
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n;i++)
        {
            long temp = current;
            current = (previous + current) % m;
            previous = temp;
        }
        return previous % m;
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long period = PeriodPisano(m);
        n %= period;
        if (n == 0L || n == 1L)
        {
            return n;
        }
        return FindResult(n,m);
    }


}

