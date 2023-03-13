package by.it.a_khmelev.group251004.Ivannikova.lesson01;

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
        BigInteger[] arr = new BigInteger[n];
        int i;
        arr[0] = arr[1]= BigInteger.valueOf(1);
        for(i = 2; i < n; i++)
        {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        return arr[n - 1];
    }

}

