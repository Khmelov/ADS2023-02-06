package by.it.a_khmelev.lesson01;

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
        List<Long> o = new ArrayList<>();
        o.add(0L);
        o.add(1L);
        int i = 2;
        while (!(o.get(i - 2) == 0 && o.get(i - 1) == 1) || i <= 2) {
            o.add((o.get(i - 2) + o.get(i - 1)) % m);
            i++;
        }
        return o.get((int) (n % (i - 2)));
    }


}

