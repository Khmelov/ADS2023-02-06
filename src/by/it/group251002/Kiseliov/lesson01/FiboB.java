package by.it.group251002.Kiseliov.lesson01;

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
        BigInteger Fib[] = new BigInteger[n+1];     // initializing the array Fib like squib in Harry Potter:)))
        Fib[0] = BigInteger.ZERO;
        Fib[1] = BigInteger.ONE;
        for (Integer i =2; i<=n ; i++){
            Fib[i]= Fib[i-1].add(Fib[i-2]);
        }
        return Fib[n];
    }

}

