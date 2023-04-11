package by.it.group251003.AlexanderKovalevich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

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

        int [] mas = new int [100000-1];
        int i = 2;
        int ind;
        int j = 0;
        mas[0]= 1 % m;
        mas[1]=1 % m;
        boolean Flag = true;
        //if (n >= m) {
            while (i < n && Flag == true) {
                mas[i] = (mas[i - 1] + (mas[i - 2])) % m;
                if (mas[i] == mas[j + 1] && mas[i - 1] == mas[j + 1]) {
                    Flag = false;
                }
                i++;
            }
      //  }
        ind = i-2;
        if (n < 2) {
          ind = 2;
        }
        return mas[((int) n % ind) - 1];

    }


}

