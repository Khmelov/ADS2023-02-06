package by.it.group251003.kopytok.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */


import java.util.ArrayList;
import java.util.function.IntToLongFunction;
import java.util.function.LongToIntFunction;

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
        ArrayList<Integer> Arr = new ArrayList<>(100);

        int PredFib = 0;
        Arr.add(PredFib);
        int CurrFib = 1;
        Arr.add(CurrFib);

        int number = 1;

        for (int i = 2, OldFib; i <= n; i++){

            OldFib = CurrFib;
            CurrFib = (CurrFib + PredFib) % m;
            PredFib = OldFib;

            Arr.add(CurrFib);

            if ((PredFib == 0) && (CurrFib == 1)) {
                number = (int)( n % (i-1) );
                break;
            }

        }

        return Arr.get(number);

    }


}

