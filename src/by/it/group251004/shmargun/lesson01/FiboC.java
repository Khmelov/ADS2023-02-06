package by.it.group251004.shmargun.lesson01;

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
        if (n <= 1) {
            return (int) n;
        }

        int[] arr = new int[6 * m];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < 6 * m; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % m;

            if (arr[i - 1] == 0 && arr[i] == 1) {
                n %= i - 1;
                return arr[(int) n];
            }
        }
        return 0;
    }


}

