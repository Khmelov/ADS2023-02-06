package by.it.group251001.besedinAndrei.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {
    private long startingTime = System.currentTimeMillis();

    private long time() {
        long result = System.currentTimeMillis() - startingTime;
        startingTime = System.currentTimeMillis();
        return result;
    }

    BigInteger fastB(Integer n) {
        // Для результата, входящего в диапазон BigInteger
        // С помощью массива, O(n)

        BigInteger[] arr = new BigInteger[n + 1];

        if (n==0) return BigInteger.ZERO;
        if (n==1) return BigInteger.ONE;
       
        arr[0]= BigInteger.valueOf(0);
        arr[1]= BigInteger.valueOf(1);

        for (int i = 2; i <= n; i++) {
            arr[i]=arr[i - 1].add(arr[i - 2]);
        }
            
        return arr[n];
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }
}
