package by.it.group251004.arefin.lesson01;


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
        BigInteger result = BigInteger.ZERO;
        BigInteger prev = BigInteger.ONE;
        BigInteger prevprev = BigInteger.ZERO;
        int i = 2;
        while (i <= n) {
            result = prev.add(prevprev);
            prevprev = prev;
            prev = result;
            i++;
        }
        return result;
    }
}





