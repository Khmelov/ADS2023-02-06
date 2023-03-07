package by.it.group251004.demidovets.lesson01;

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

    BigInteger fastB(Integer n) {
        ArrayList<BigInteger> numbers = new ArrayList<>(n);
        numbers.add(BigInteger.ZERO);
        numbers.add(BigInteger.ONE);
        for (int i = 2;i <= n;i++) {
            BigInteger f = numbers.get(i - 2).add(numbers.get(i - 1));
            numbers.add(f);
        }
        return numbers.get(n);
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

}

