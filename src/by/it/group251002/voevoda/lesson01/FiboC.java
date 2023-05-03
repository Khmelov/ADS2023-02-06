package by.it.group251002.voevoda.lesson01;

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
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(1);
        for (int i = 2; i < m * 5; i++) {
            lst.add((lst.get(i - 1) + lst.get(i - 2)) % m);
            if (lst.get(i) == 1 && lst.get(i - 1) == 0)
                break;
        }
        return lst.get((int) (n % (lst.size() - 2)));
    }
    /////

}

