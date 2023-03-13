package by.it.group251001.hatalskiy.lesson01;

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
        int m = 5555;
        System.out.printf("fasterC(%d, %d)=%d \n\t time=%d \n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Integer> fibMas = new ArrayList<Integer>();
        fibMas.add(0);
        fibMas.add(1);
        int period = 0;
        do {
            fibMas.add( ( fibMas.get(fibMas.size() - 1) + fibMas.get(fibMas.size() - 2) ) % m );
            period++;
        } while (!( (fibMas.get(fibMas.size() - 2) == 0) && (fibMas.get(fibMas.size() - 1) == 1) ) );
        int index = (int)((n + 1) % period) - 1;
        return fibMas.get(index);
    }


}