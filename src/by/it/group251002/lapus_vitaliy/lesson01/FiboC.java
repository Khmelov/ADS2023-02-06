package by.it.group251002.lapus_vitaliy.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        BigInteger mm = new BigInteger(Integer.toString(m));
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        int count=1;
        while ((0!=((a.mod(mm)).compareTo(BigInteger.ZERO))) ||  (0!=((b.mod(mm)).compareTo(BigInteger.ONE))))
        {
            count +=1;
            b = a.add(b) ;
            a = b.subtract(a) ;
        }
        n=(n) % count;

        a = BigInteger.ZERO;
        b = BigInteger.ONE;

        for (int i=1;i<=n;i++)
        {
            b = a.add(b) ;
            a = b.subtract(a) ;

        }

        return (a.mod(mm)).longValue();
    }


}

