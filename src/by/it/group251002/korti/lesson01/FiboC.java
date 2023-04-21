package by.it.group251002.korti.lesson01;

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
        ArrayList<Integer> kek = new ArrayList<>();
        kek.add(0);
        kek.add(1);
        int i = 2;
        while (true) {
            kek.add((kek.get(i - 1) + kek.get(i - 2)) % m);
            if ((kek.get(i) == 1) && kek.get(i - 1) == 0) {
                break;
            }
            i++;
        }


        return kek.get((int) (n % i));
    }


}

