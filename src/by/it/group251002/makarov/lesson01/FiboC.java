package by.it.group251002.makarov.lesson01;

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
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Integer> rem = new ArrayList<>();
        int r, per;
        per=2;
        rem.add(0);
        rem.add(1);

        while ((rem.get(per-2) !=0)&&(rem.get(per-1) != 1)) {
            r=(rem.get(per-1)+rem.get(per-2))%m;//вычисления нового члена
            rem.add(r);//занести его в список
            per++;//изменить значение количество эл в периоде
        }
        return rem.get((int)(n)%per);
    }


}

