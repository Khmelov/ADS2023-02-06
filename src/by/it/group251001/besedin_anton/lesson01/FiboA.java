package by.it.group251001.besedin_anton.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startingTime = System.currentTimeMillis();

    private long time() {
        long result = System.currentTimeMillis() - startingTime;
        startingTime = System.currentTimeMillis();
        return result;
    }

    private int calc(int n) {
        // Для результата, входящего в диапазон int
        // Рекурсия, O(2^n)
        if (n == 0 || n == 1) return n;
        return calc(n - 1) + calc(n - 2); 
    } 

    BigInteger slowA(Integer n) {
        // Для результата, входящего в диапазон BigInteger
        // Рекурсия, O(2^n)


        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        return slowA(n - 1).add(slowA(n - 2));
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();

        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        fibo = new FiboA();
        n = 34;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }
}