package by.it.group251002.troitsky.lesson01.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int length = 0;
        long Sequence[] = new long[m * 6 + 1];
        Sequence[0] = 0;
        Sequence[1] = 1;
        int i = 2;
        while (length == 0) {
            Sequence[i] = (Sequence[i-1]+Sequence[i-2]) % m;
            if ((Sequence[i] == 1)&& Sequence[i-1]==0) {
                length = i;
            }
            i++;
        }
        return Sequence[(int) (n % length)];
    }

}

