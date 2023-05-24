package by.it.group251001.besedin_anton.lesson01;

import java.math.BigInteger;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {
    private long startingTime = System.currentTimeMillis();

    private long time() {
        long result = System.currentTimeMillis() - startingTime;
        startingTime = System.currentTimeMillis();
        return result;
    }

    private long calculatePisanoPeriod(int m) {
       
        long previous = 0;
        long current = 1;
        long result = 0;
        // Цикл для нахождения периода Пизано
        for (int i = 0; i < m * m; i++) {
           
            long temp = current;
            current = (previous + current) % m;
            previous = temp;

            if (previous == 0 && current == 1) {
                result = i + 1;
                break;
            }
        }

        return result;
    }

    long fasterC(long n, int m) {
        n = n % calculatePisanoPeriod(m);

        long previous = 0;
        long current = 1;
        
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        for (int i = 2; i <= n; i++) {
            long temporary = current;
            current = (previous + current) % m;
            previous = temporary;
        }
      
        return current;
    }


    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 5;
        int m = 10;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }
}
