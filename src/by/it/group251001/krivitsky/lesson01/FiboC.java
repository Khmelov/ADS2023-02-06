package by.it.group251001.krivitsky.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        long[] Arr = new long[10*m];
        Arr[0] = 0L;
        Arr[1] = 1L;
        Arr[2] = 1L;
        int i = 3;
        boolean f = true;
        while (f) {
            Arr[i] = (Arr[i-1]+Arr[i-2]) % m;
            if ((Arr[i-1] == Arr[0]) && (Arr[i] == Arr[1]))
            {
                f = false;
        }
            else{
                i++;
            }
        }
        return Arr[(int)(n % i)];
    }


}

