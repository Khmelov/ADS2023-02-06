package by.it.group251002.yanucevich.lesson01;

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
        BigInteger F[] = new BigInteger[n+1];     // initializing the array with n+1 elements because of the 0th element
        F[0] = BigInteger.ZERO;
        F[1] = BigInteger.ONE;
        for (Integer i =2; i<=n ; i++){
            F[i]= F[i-1].add(F[i-2]);
        }
        return F[n];
    }

}

