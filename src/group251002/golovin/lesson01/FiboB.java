package group251002.golovin.lesson01;

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
        BigInteger[] ar1= new BigInteger[n];
        ar1[0]= BigInteger.ONE;
        ar1[1]=ar1[0];
        for(int i=2; i<n; i++) {
            ar1[i] = ar1[i - 1].add(ar1[i - 2]);
        }
        return ar1[n-1];
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
    }

}

