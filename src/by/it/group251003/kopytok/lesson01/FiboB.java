package by.it.group251003.kopytok.lesson01;

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
        BigInteger[] Arr = new BigInteger[n+1];
        for (int g=0; g<=n; g++){
            if (g==0) Arr[g]= BigInteger.ZERO;
            else if (g==1) Arr[g]= BigInteger.ONE;
            else Arr[g]=Arr[g-1].add(Arr[g-2]);
        }
        return Arr[n];
    }

}

