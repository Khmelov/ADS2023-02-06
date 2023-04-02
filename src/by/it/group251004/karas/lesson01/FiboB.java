package by.it.group251004.karas.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

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
        return nums.get(n);
    }

}

