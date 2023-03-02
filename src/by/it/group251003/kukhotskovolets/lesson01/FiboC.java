package by.it.group251003.kukhotskovolets.lesson01;

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

        //Declaring arraylist for Pizano nums
        ArrayList<Integer> Pizano = new ArrayList<>();

        //Adding values from which every period starts
        Pizano.add(0);
        Pizano.add(1);

        for (int i = 2; i <= n * 6; i++){

            // a mod x + b mod x = (a + b) mod x
            Pizano.add((Pizano.get(i - 1) + Pizano.get(i - 2)) % m);

            //if start values of every period was founded
            //then output Pizano(n mod m ) excluding last value
            if (Pizano.get(i) == 1 && Pizano.get(i - 1) == 0){
                return (Pizano.get((int) (n % (Pizano.size() - 1))));
            }
        }
        return 0;
    }


}

