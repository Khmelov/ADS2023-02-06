package by.it.group251003.skrylev.lesson01;

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
        ArrayList<Integer> listOfMods = new ArrayList<>(1000);

        listOfMods.add(1);
        listOfMods.add(1);

        int i = 1;

        do
        {
            i++;
            listOfMods.add((listOfMods.get(i - 1) + listOfMods.get(i - 2)) % m);
        } while ( !((listOfMods.get(i) == 0) && (listOfMods.get(i - 1) == 1)) );

        return listOfMods.get((int)(n % listOfMods.size()) - 1);
    }


}

