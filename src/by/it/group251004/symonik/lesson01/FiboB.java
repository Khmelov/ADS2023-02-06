package by.it.group251004.symonik.lesson01;

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
        BigInteger[] mas = new BigInteger[n];
        BigInteger res = BigInteger.valueOf(0);
        if (n == 0)
            return BigInteger.ZERO;
        else if (n == 1)
            return BigInteger.ONE;
        else {
            mas[0] = BigInteger.valueOf(0);
            mas[1] = BigInteger.valueOf(1);
            for(int i = 0; i < n;i++) {
                if(i < 2)
                    mas[i]= BigInteger.valueOf(1);
                else {
                    mas[i] = mas[i - 1].add(mas[i - 2]);
                    res = mas[i];
                }
            }

            return res;
        }
    }

}

