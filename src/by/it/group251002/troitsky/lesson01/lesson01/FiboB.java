package by.it.group251002.troitsky.lesson01.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

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
        BigInteger [] Arr;
        Arr = new BigInteger[n+1];
        Arr[0] = BigInteger.ZERO;
        Arr[1] = BigInteger.ONE;
        for(Integer i = 2; i <=  n; i++) {
            Arr[i]= Arr[i-1].add(Arr[i-2]);
        }
        return Arr[n];
    }

}

