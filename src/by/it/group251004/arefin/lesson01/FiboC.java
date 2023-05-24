package by.it.group251004.arefin.lesson01;


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
        int n = 18;
        int m = 4;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        //int period = m * 6; // каждые m * 6 раз или меньше остатки m чисел будут повторяться
        BigInteger numberFib;
        BigInteger prev = BigInteger.ONE;
        BigInteger prevprev = BigInteger.ZERO;
        BigInteger bigPeriod = BigInteger.valueOf(m); // m в BigInteger
        long worsePeriod = n %  (m * 6L);
        int [] arrOfReminder = new int[(int) worsePeriod + 1];
        int i = 2;
        arrOfReminder[0] = 0;
        arrOfReminder[1] = 1;
        while (i <= worsePeriod) {
            numberFib = prev.add(prevprev);
            prevprev = prev;
            prev = numberFib;
            arrOfReminder[i] = (numberFib.mod(bigPeriod)).intValue(); // массив остатков
            i++;
        }
        int[] finalArray = new int[(int) worsePeriod + 1];
        i = 0;
        for (int j = 1; j < arrOfReminder.length - 2; j++) {
            if (arrOfReminder[0] == arrOfReminder[j]) {
                if (arrOfReminder[1] == arrOfReminder[j + 1]) {
                    if (arrOfReminder[2] == arrOfReminder[j + 2]) {
                        while (i != j) {
                            finalArray[i] = arrOfReminder[i];
                            i++;
                        }
                        break;
                    }
                }
            }
        }
        int reminder;

        if (n == 1)
            return 1;
        if (n ==2)
            return 2;
        if (n < m)
            reminder = (int)n;
        else
            reminder = (int) n % i; // оно должно работать всегда, когда i != 0
        return finalArray[reminder];
    }


}
