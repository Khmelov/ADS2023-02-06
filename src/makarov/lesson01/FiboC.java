package makarov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
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
        ArrayList<Integer> rem = new ArrayList<>();
        int rem1, rem2, per,temp;/*rem1, rem2- остатки от деления
                                   per - количество членов поседовательности
                                   temp - aux variable*/
        rem1 = 1;
        rem2 = 1;
        per=1;
        rem.add(0);
        do {
            rem.add(rem1);//добавить в список
            temp=rem1;//сохранить значение
            rem1=rem2;
            rem2=(rem1+temp)%m;//вычислить новый член
            per++;
        }while ((rem1 !=0)&&(rem2 != 1));

        return rem.get((int)(n)%per);
        }
}