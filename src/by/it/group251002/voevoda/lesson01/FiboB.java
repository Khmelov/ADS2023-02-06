package by.it.group251002.voevoda.lesson01;

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
        ArrayList<BigInteger> array = new ArrayList<BigInteger>();
        array.add(BigInteger.ZERO);
        array.add(BigInteger.ONE);
        for (int i = 2; i <= n; i++) {
            array.add(array.get(i - 1).add(array.get(i - 2)));
        }
        return array.get(n);
    }
    /////
}

