package by.it.group251003.palitanski.lesson01;

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

        ArrayList<Integer> TermArray = new ArrayList<>(15);
        TermArray.add(0);
        TermArray.add(1);
        long a = 2;
        for (int i = 2; i < n; i++) {
            TermArray.add( (TermArray.get(i-1) + TermArray.get(i-2)) % m ) ;
            if ( (TermArray.get(i) == 1) && (TermArray.get(i-1) == 0) ) {
                a = i - 1;
                break;
            }
        }
        n %= a ;
        return TermArray.get((int) n );
    }


}

