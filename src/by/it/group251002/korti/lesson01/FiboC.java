package by.it.group251002.korti.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

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

        int period = 1;
        long[] array = {0, 1, 1};

        int cur = 1;
        int nxt = 1;

        ArrayList<Integer> periodFib = new ArrayList<>(6 * 100000);
        periodFib.add(0);

        int i = 2;
        do {
            periodFib.add(cur);
            period++;
            array[0] = array[1];
            array[1] = array[2];
            array[2] = array[0] + array[1];

            cur = nxt;
            nxt = (int)(array[2] % m);
            i++;
        } while (!(cur == 0 && nxt == 1) && i < n);

        return periodFib.get((int)(n % period));
    }

}