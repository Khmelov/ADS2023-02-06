package by.it.group251001vasilenko.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

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

        BigInteger[] fibonacciArr = new BigInteger[n + 1];

        return countFibonacci(n, fibonacciArr);
    }

    private static BigInteger countFibonacci(int n, BigInteger[] fibonacciArr) {

        if (n < 2) {
            return BigInteger.valueOf(n);
        }

        fibonacciArr[0] = BigInteger.ZERO;
        fibonacciArr[1] = BigInteger.ONE;

        for (int i = 2; i < fibonacciArr.length; i++) {
            fibonacciArr[i] = fibonacciArr[i - 1].add(fibonacciArr[i - 2]);
        }

        return fibonacciArr[n];
    }
}

