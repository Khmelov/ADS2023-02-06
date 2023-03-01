package group251003.novichenko.lesson01;
import java.util.ArrayList;
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
        ArrayList<Integer> array = new ArrayList<>();
        array.add(0);
        array.add(1);

        long term=2;

        for (int i = 2; i < n; i++) {
            array.add((array.get(i - 1) + array.get(i - 2)) % m);
            if ((array.get(i) == 1) && (array.get(i - 1) == 0)) {
                term = i - 1;
                break;
            }

        }

        n %= term;
        return array.get((int)n);

    }
}

