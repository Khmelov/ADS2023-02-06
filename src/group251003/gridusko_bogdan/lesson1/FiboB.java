package group251003.gridusko_bogdan.lesson1;

import java.math.BigInteger;
import java.util.Arrays;

//import static java.lang.StringUTF16.compareTo;

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
        if (n == 0)
            return BigInteger.ZERO;
        if (n == 1)
            return BigInteger.ONE;

        BigInteger prev1 = new BigInteger("1");
        BigInteger prev2 = new BigInteger("0");
        BigInteger res = new BigInteger("0");

        for (int i = 1; i < n; ++i) {
            res = prev1.add(prev2);
            prev2 = prev1;
            prev1 = res;
        }

        return res;
    }

}

