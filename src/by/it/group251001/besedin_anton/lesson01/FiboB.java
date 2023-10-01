package by.it.group251001.besedin_anton.lesson01;

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
        // Используя массив для хранения результатов
        // Сложность O(n)
    
        BigInteger[] fibonacciArray = new BigInteger[n + 1];
    
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }
    
        fibonacciArray[0] = BigInteger.ZERO;
        fibonacciArray[1] = BigInteger.ONE;
    
        for (int i = 2; i <= n; i++) {
            fibonacciArray[i] = fibonacciArray[i - 1].add(fibonacciArray[i - 2]);
        }
    
        return fibonacciArray[n];
    }
    

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }
}
