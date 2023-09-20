package by.it.group251003.skrylev.lesson01;

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
        ArrayList<BigInteger> nums = new ArrayList<>(n);
        nums.add(BigInteger.ZERO);
        nums.add(BigInteger.ONE);
        int i = 2;
        while (i <= n){
            BigInteger f = nums.get(i - 2).add(nums.get(i - 1));
            nums.add(f);
            i++;
        }
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        ArrayList<BigInteger> f=new ArrayList<>();
        f.add(BigInteger.ZERO);
        f.add(BigInteger.ONE);
        for (int j = 2; j <= n ; j++) {
            BigInteger s1=f.get(j-1);
            BigInteger s2=f.get(j-2);
            BigInteger result=s1.add(s2);
            f.add(result);
        }
        return nums.get(n);
    }

}

