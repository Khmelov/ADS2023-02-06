package by.it.group251003.buhvalova.lesson01;

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
        BigInteger[] num = new BigInteger[n];
        num[0] = BigInteger.ONE;
        num[1] = BigInteger.ONE;

        for (int i = 2; i < n; i++) {
            num[i] = num[i-1].add(num[i-2]);
        }
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        return num[n-1];
    }

}

