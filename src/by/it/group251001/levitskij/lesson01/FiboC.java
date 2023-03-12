package by.it.group251001.levitskij.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        Long [] mas = new Long [m*6];
        mas[0] = 0L;
        mas[1] = 1L;
        int i = 2;
        do{
            mas[i] = (mas[i-1]+mas[i-2]) % m;
            i++;
        }while(!(mas[i-1]==1 && mas[i-2]==0));
        return mas[(int) (n % (i-2))];
    }


}

