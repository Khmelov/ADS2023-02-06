package by.it.group251001.zhidkov.lesson01;

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
        long n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d, %d)=%d \n\t time=%d \n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }

    /*public static int PisanoPeriod(int m) {
        if (m == 0) {
            return 0;
        } else if (m == 1) {
            return 1;
        } else if (m == 2) {
            return 3;
        } else if (m == 5) {
            return 20;
        } else if (m % 2 == 0 && m % 5 == 0) {
            return lcm(PisanoPeriod(2), PisanoPeriod(5));
        } else if (m % 2 == 0) {
            return PisanoPeriod(m / 2) * 2;
        } else if (m % 5 == 0) {
            return PisanoPeriod(m / 5) * 5;
        } else {
            int prev = 0;
            int current = 1;
            int temp;
            int i;
            for (i = 0; i < m * m; i++) {
                if (i < 2) {
                    temp = i;
                } else {
                    temp = (prev + current) % m;
                    prev = current;
                    current = temp;
                }
                if (prev == 0 && current == 1 && i > 2) {
                    return i + 1;
                }
            }
            return -1;
        }
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int PisanoPeriod = PisanoPeriod(m);
        long Ost = n % PisanoPeriod;
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        int TrueOst = (int) Math.round((Math.pow(phi, Ost) - Math.pow(psi, Ost)) / sqrt5) % m;
        return TrueOst;
    }
}*/

   public static long fasterC(long n, int m) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        long[] MasForCount = new long[(int) 1E5];
        MasForCount[0] = 0;
        MasForCount[1] = 1;
        int i = 2;
        boolean b = true;
        while (i <= n && b) {
            MasForCount[i] = (MasForCount[i - 1] + MasForCount[i - 2]) % m;
            if ((i > 2) && (MasForCount[1] == MasForCount[i - 1]) && (MasForCount[2] == MasForCount[i])) {
                b = false;
            }
         ++i;
        }
        if (i == n)
            return MasForCount[i];
        long backbuf = i - 2;
        return MasForCount[(int) (n % backbuf)];
    }
}