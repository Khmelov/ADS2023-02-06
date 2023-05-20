package by.it.group251001.StolbovKirill.lesson01;

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
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] FiboArr = new BigInteger[n + 1];
        FiboArr[0] = BigInteger.ZERO;
        FiboArr[1] = BigInteger.ONE;
        for (int i = 2; i < n + 1; i++)
            FiboArr[i] = FiboArr[i - 1].add(FiboArr[i - 2]);
        return FiboArr[n];
    }

}

