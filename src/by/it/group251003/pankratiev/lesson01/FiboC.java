package by.it.group251003.pankratiev.lesson01;

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

        /*
        * if (m == 0)
        *    throw new ArithmeticException("/ by zero");
        * if (m == 1)
        *    return 0;
        */

        ArrayList<Integer> Remainders = new ArrayList<>(100);

        Remainders.add(0);
        Remainders.add(1);

        for (int i = 2; i <= n; i++){

            Remainders.add( (Remainders.get(i-1) + Remainders.get(i-2)) % m );

            //Если найден период, то выводим результат
            if ((Remainders.get(i - 1) == 0) && (Remainders.get(i) == 1))
                return Remainders.get( (int) (n % (i - 1)) ) ;

        }

        //Если не найден период, то выводим последний элемент
        return Remainders.get( Remainders.size() - 1 );
    }

}

