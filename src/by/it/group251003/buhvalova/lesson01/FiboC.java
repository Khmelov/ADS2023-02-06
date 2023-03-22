package by.it.group251003.buhvalova.lesson01;

import java.math.BigInteger;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        long[] arr = new long[m*12];
        BigInteger LastElem = BigInteger.ONE;
        BigInteger LastElem2 = BigInteger.ONE;
        BigInteger CurElem;
        arr[0] = 1;
        arr[1] = 1;

        BigInteger bigInteger = BigInteger.valueOf(m);
        for (int i = 2; i < m*12; i++) {
            CurElem = LastElem.add(LastElem2);
            LastElem = LastElem2;
            LastElem2 = CurElem;
            arr[i] = (CurElem.mod(bigInteger)).longValue();
        }
        int Period = 2;
        boolean indicator = true;
        boolean isThat;
        while(indicator) {
            isThat = true;
            for (int i = 0; i < Period; i++) {
                if (arr[i] != arr[i + Period]) {
                    isThat = false;
                }
            }
            if (isThat) {
                indicator = false;
            }
            else Period++;
        }
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        return arr[((int)(n % Period)) - 1];
    }


}

