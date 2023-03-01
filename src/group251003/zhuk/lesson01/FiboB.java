package group251003.zhuk.lesson01;

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
        BigInteger[] FibArr = new BigInteger[n];

        FibArr[0] = BigInteger.ONE;
        FibArr[1] = BigInteger.ONE;

        for (int i = 2; i < n; i++) {
            FibArr[i] = FibArr[i-1].add(FibArr[i-2]);
        }


        return FibArr[n-1];
    }

}

