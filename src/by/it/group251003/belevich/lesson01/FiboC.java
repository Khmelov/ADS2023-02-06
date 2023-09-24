package by.it.group251003.belevich.lesson01;

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

        ArrayList<Integer> Remainder = new ArrayList<>(100);

        Remainder.add(0);
        Remainder.add(1);

        for (int i = 2; i <= n; i++) {

            Remainder.add((Remainder.get(i - 1) + Remainder.get(i - 2)) % m);

            //Если найден период, то выводим результат
            if ((Remainder.get(i - 1) == 0) && (Remainder.get(i) == 1))
                return Remainder.get((int) (n % (i - 1)));
        }

            //Если найден период, то выводим результат
            return Remainder.get( Remainder.size() - 1 );
    }


}

