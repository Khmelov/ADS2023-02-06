package by.it.group251001.zhidkov.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {
    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 34;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    private long time() {
        long res = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        return res;
    }

    private int calc(int n) {
        if (n < 2) return n;
        return calc(n - 2) + calc(n - 1);
    }


            BigInteger slowA (Integer n){
            //рекурсия
            //здесь нужно реализовать вариант без ограничения на размер числа,
            //в котором код совпадает с математическим определением чисел Фибоначчи
            //время O(2^n)
            if (n == 0) {
                return BigInteger.valueOf(n);
            }

            if (n == 1) {
                return BigInteger.valueOf(n);
            }

            return slowA(n - 1).add(slowA(n - 2));
        }

}

