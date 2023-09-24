package by.it.group251003.khlyschankou.lesson01;


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
        ArrayList<Integer> a = new ArrayList <>(123456789);
        a.add(0);
        a.add(1);
        long cycle = 2;
        for (int i = 2; i < n; i++){
            a.add((a.get(i-1) + a.get(i-2)) % m);
            if ((a.get(i) == 1) && (a.get(i- 1) == 0)) {
                cycle = i - 1;
                break;
            }
        }
        n %= cycle;
        return (a.get((int)n)); //       return 0L;
    }


}
