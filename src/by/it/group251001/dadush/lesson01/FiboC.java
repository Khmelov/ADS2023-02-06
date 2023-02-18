package by.it.group251001.dadush.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 12;
        int m = 4;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int i, p1 = 1, p2 = 1, res = 0;
        int a = 0, b = 0, c = 0;
        int pis = 0;
        boolean IsFound = false;

        while (! IsFound) {
            res = ((p2 % m) + (p1 % m)) % m;
            pis++;
            p1 = p2; p2 = res;
            a = b; b = c; c = res;

            if (a == 0 && b == 1 && c == 1) {
                IsFound = true;
            }
        }
        //System.out.println(pis);
        n %= pis;

        if (n <= 1) {
            return n % m;
        }
        else {
          p1 = 0; p2 = 1;
          for (i = 2; i <= n; i++) {
              res = ((p2 % m) + (p1 % m)) % m;
              p1 = p2; p2 = res;
          }
          return (long)res;
        }

    }

}

