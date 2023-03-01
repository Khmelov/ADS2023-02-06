package by.it.group251001.voytov.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private final long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {

        BigInteger[] fibonacciCache = new BigInteger[n + 1];

        return countFibonacci(n, fibonacciCache);
    }

    private static BigInteger countFibonacci(int n, BigInteger[] fibonacciCache) {

        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        fibonacciCache[0] = BigInteger.ZERO;
        fibonacciCache[1] = BigInteger.ONE;

        for (int i = 2; i < fibonacciCache.length; i++) {
            fibonacciCache[i] = fibonacciCache[i - 1].add(fibonacciCache[i - 2]);
        }

        return fibonacciCache[n];
    }

}

