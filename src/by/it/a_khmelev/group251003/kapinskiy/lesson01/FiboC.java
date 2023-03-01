package by.it.a_khmelev.group251003.kapinskiy.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

public class FiboC {

    private long startTime = System.currentTimeMillis() ;

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
        int[] mod = new int[m*6];
        long index;
        int i = 2;
        boolean flag = false;
        mod[0] = 0;
        mod[1] = 1 % m;

        while (!flag) {
            mod[i] = (mod[i-1]+mod[i-2]) % m;
            flag = (mod[i] == 1) && (mod[i-1] == 0);
            i = i + 1;
        }

        index = n % (i-1);
        if (n == 1){
            return (int) n;
        }
        else{
            return mod[(int)(index)];
        }
    }


}

