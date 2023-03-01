package by.it.group251003.beskosty.lesson1;

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
        int[] PisanoArr= new int[1000];
        PisanoArr[0]= 0;
        PisanoArr[1]= 1;
        int i = 1;
        boolean IsFalse=true;
        do {
            i++;
            PisanoArr[i] = (PisanoArr[i-1]+PisanoArr[i-2])%m;
            if (PisanoArr[i]==1&&PisanoArr[i-1]==0) IsFalse = false;
        }
        while (IsFalse && i != n);
        if (!IsFalse){
            i -= 1;
            n%=i;
        }
        return PisanoArr[(int)n];
    }


}
