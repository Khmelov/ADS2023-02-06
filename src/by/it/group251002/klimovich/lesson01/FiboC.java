package by.it.group251002.klimovich.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int[] Numbers =  new int[6*m];
        Numbers[0] = 0;
        Numbers[1] = 1;
        int i=2;
        while (true){
            Numbers[i]=(Numbers[i-1]+Numbers[i-2])%m;
            if (!((Numbers[i]%m!=1) || (Numbers[i-1]%m!=0))){
                break;
            }
            i++;
        }
        return Numbers[(int) n%i];
    }


}

