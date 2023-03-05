package by.it.group251001.krivitsky.lesson01;

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
        BigInteger[] Arr = new BigInteger[n+1];
        Arr[0] = BigInteger.ZERO;
        Arr[1] = BigInteger.ONE;

        for(int i=2;i<=n;++i)
            Arr[i] = Arr[i-1].add(Arr[i-2]);
        return Arr[n];
    }

}

