package by.it.group251001.voronovich.lesson01;

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

   /* long fasterC(long n, int m) {
        BigInteger[] ArrayOfPeriodNums = new BigInteger[m^2];
        int j = 1;
        int a;
        BigInteger chislo;
        BigInteger prev;
        BigInteger curr;
        BigInteger fibo;
        prev = BigInteger.ZERO;
        curr = BigInteger.ONE;
        ArrayOfPeriodNums[0] = BigInteger.ZERO;
        ArrayOfPeriodNums[1] = BigInteger.ONE;
        do {
            j++;
            fibo = prev.add(curr);
            prev = curr;
            curr = fibo;
            ArrayOfPeriodNums[j] = fibo.mod(BigInteger.valueOf((long) m));
        }
        while (!(ArrayOfPeriodNums[j].mod(BigInteger.valueOf((long) m))).equals(0));
        chislo = BigInteger.valueOf(n).mod(BigInteger.valueOf((long) j));
        a = chislo.intValue();
        return(ArrayOfPeriodNums[a]);*/
   long fasterC(long n, int m) {
        long pisanoNo = getPisanoPeriod(m);

        n = pisanoNo > 0 ? n % pisanoNo : n;

        return getFibonacci(n).mod(BigInteger.valueOf(m)).longValue();
    }

    long getPisanoPeriod(long m) {
        long prev = 0;
        long curr = 1;
        long res = 0;

        for (long i = 0; i < m * m; i++) {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }

        return res;
    }

    BigInteger getFibonacci(long n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger prev = BigInteger.ZERO;
        BigInteger curr = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            BigInteger temp = curr;
            curr = prev.add(curr);
            prev = temp;
        }

        return curr;
    }
//}*/



        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

    }


//}

