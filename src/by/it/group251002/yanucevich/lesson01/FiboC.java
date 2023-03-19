package by.it.group251002.yanucevich.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int pi = 0;                           // pi will keep the value of the length of the Pizano sequence

        long PiSequence[] = new long[m * 6 + 1];      // init the array with the modules of the division
        PiSequence[0] = 0;
        PiSequence[1] = 1;


        int i = 2;

        while (pi == 0) {
            PiSequence[i] = (PiSequence[i-1]+PiSequence[i-2]) % m;

            if ((PiSequence[i] == 1)&& PiSequence[i-1]==0) {
                pi = i;
            }

            i++;
        }


        return PiSequence[(int) (n % pi)];
    }

}

