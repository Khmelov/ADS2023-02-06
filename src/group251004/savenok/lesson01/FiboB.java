package group251004.savenok.lesson01;

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
        BigInteger result = BigInteger.valueOf(0);
        if (n == 0)
            return BigInteger.ZERO;
        if (n == 1)
            return BigInteger.ONE;
        BigInteger[] array = new BigInteger[n];
        array[0] = BigInteger.valueOf(0);
        array[1] = BigInteger.valueOf(1);
        for (int i = 0; i < n; i++) {
            if (i < 2)
                array[i] = BigInteger.valueOf(1);
            else {
                array[i] = array[i - 1].add(array[i - 2]);
                result = array[i];
            }
        }
        return result;
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
    }

}

