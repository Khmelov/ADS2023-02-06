package by.it.group251003.belevich.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class  FiboB {

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
        BigInteger[] FibNumbers = new BigInteger[n];

        FibNumbers[0] = BigInteger.ONE;
        FibNumbers[1] = BigInteger.ONE;

        for (int i = 2; i < FibNumbers.length; i++) {
            FibNumbers[i] = FibNumbers[i-1].add(FibNumbers[i-2]);
        }

        return FibNumbers[n-1];
    }

}

